package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.FileInfo;
import com.hq.CloudPlatform.CA.entity.UploadFileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.FileInfoMapper;
import com.hq.CloudPlatform.CA.service.IFileInfoService;
import com.hq.CloudPlatform.CA.service.IFileUploadService;
import com.hq.CloudPlatform.CA.utils.Constants;
import com.hq.CloudPlatform.CA.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2017/4/27
 */
@Service
@Slf4j
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo> implements IFileInfoService {

    public static final String CHUNK_KEY_PREFIX = "chunk_";

    public static final String UPLOAD_FILE_ID_KEY = "uploadFileId";

    public static final String PERCENT_KEY = "percent";

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Override
    public BaseMapper<FileInfo> getBaseMapper() {
        return fileInfoMapper;
    }

    @Override
    public String save(FileInfo entity) throws ServiceException {
        String uploadFileId = entity.getUploadFileInfo().getId();

        //如果uploadFileId不存在则保存，否则说明已入库则不做处理
        if (null == uploadFileId || "".equals(uploadFileId)) {
            fileUploadService.save(entity.getUploadFileInfo());
        }

        return super.save(entity);
    }

    @Override
    public String generateKey(String fileName, String md5, String fileSize) {
        StringBuffer keySB = new StringBuffer();

        //根据文件的MD5和大小来生成唯一的KEY，如果文件MD5值和大小一致则认为是同一个文件
        keySB.append(md5).append("_").append(fileSize);

        return keySB.toString();
    }

    @Override
    public FileInfo getByKeyAndName(String fileKey, String fileName) throws ServiceException {
        String fileUploadId = getUploadFileIdByKey(fileKey);
        Map<String, Object> params = new HashMap<>(2);
        params.put("name", fileName);
        params.put("uploadFileId", fileUploadId);

        List<FileInfo> fileInfoList = fileInfoMapper.findByMap(params);

        if (null != fileInfoList && fileInfoList.size() > 0) {
            return fileInfoList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean checkFileIsExist(String fileKey) throws ServiceException {
        if (Constants.USE_REDIS) {
            try {
                //先检查Redis,如果Redis不可用则降级检查数据库,系统启动时会将信息初始化到Redis服务器中
                return checkFileIsExistByRedis(fileKey);
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务不可用!", redisConnectionException);
            }
        }

        try {
            //Redis服务不可用时则降级从数据库中获取
            return checkFileIsExistByDB(fileKey);
        } catch (ServiceException serviceException) {
            log.error("数据库服务不可用!", serviceException);
        }

        //数据库服务不可用时则降级从文件系统中进行检测
        return checkFileIsExistByFileSystem(fileKey);
    }

    @Override
    public String getUploadFileIdByKey(String fileKey) throws ServiceException {
        if (Constants.USE_REDIS) {
            try {
                return getUploadFileIdByKeyFromRedis(fileKey);
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务不可用!", redisConnectionException);
            }
        }

        UploadFileInfo uploadFileInfo = fileUploadService.findByKey(fileKey);

        if (null == uploadFileInfo) {
            throw new ServiceException("未找到fileKey为的" + fileKey + "记录！");
        } else {
            return uploadFileInfo.getId();
        }
    }

    public String getUploadFileIdByKeyFromRedis(String fileKey) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();
        return ops.get(fileKey, UPLOAD_FILE_ID_KEY);
    }

    private boolean checkFileIsExistByRedis(String fileKey) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();

        if (ops.hasKey(fileKey, UPLOAD_FILE_ID_KEY)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkFileIsExistByDB(String fileKey) throws ServiceException {
        //MD5和文件大小一样即认为是同一个文件
        UploadFileInfo uploadFileInfo = fileUploadService.findByKey(fileKey);

        if (null != uploadFileInfo) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkFileIsExistByFileSystem(String fileKey) {
        File file = new File(Constants.UPLOADER_PROD_DIR, fileKey);

        return file.exists();
    }

    /**
     * 检查文件的分片已上传
     *
     * @param fileKey
     * @param chunkNum
     * @param chunkSize
     * @return
     */
    @Override
    public boolean checkChunkIsExist(String fileKey, String chunkNum, long chunkSize) throws ServiceException {
        if (Constants.USE_REDIS) {
            try {
                //通过redis来判断chunk是否存在
                return isExistOfChunkByRedis(fileKey, chunkNum);
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务器异常!", redisConnectionException);
            }
        }

        try {
            //当Redis服务器不可用时直接检查是否已存在该分片文件
            return isExistOfChunkByFileSystem(fileKey, chunkNum, chunkSize);
        } catch (Exception exception) {
            log.error("检查是否已存在该分片文件时发生异常!", exception);

            throw new ServiceException("检查文件分片是否存在时发生异常！", exception);
        }
    }

    @Override
    public String getUploadedPercent(String fileKey) {
        if (Constants.USE_REDIS) {
            try {
                HashOperations<String, String, String> ops = redisTemplate.opsForHash();

                //如果是部分上传了则返回当前的上传进度
                if (ops.hasKey(fileKey, PERCENT_KEY)) {
                    return ops.get(fileKey, PERCENT_KEY);
                } else {
                    return "0";
                }
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务器异常!", redisConnectionException);
            }
        }

        return "0";
    }

    @Override
    public String getFileUrlById(String fileId, int port, String serverName) {
        //这个地方可以实现一个文件URL生成器, 根据文件存储类型来自动生成
        // 例如: 本地文件系统, HDFS文件系统, 小文件系统, 远程文件系统等等
        // 这样每个文件系统的存取规则是不一样的,这样有利于系统的扩展
        StringBuffer fileUrl = new StringBuffer();
        fileUrl.append("http://").append(SystemUtils.getLocalHostAddress())
                .append(":").append(port).append(serverName)
                .append("/restful/fileDownload/download/")
                .append(fileId);

        return fileUrl.toString();
    }

    @Override
    public void cacheChunkInfoByRedis(String fileKey, String chunkNum, String percent) {
        if (Constants.USE_REDIS) {
            try {
                HashOperations<String, String, String> ops = redisTemplate.opsForHash();
                ops.put(fileKey, CHUNK_KEY_PREFIX + chunkNum, "uploaded");
                ops.put(fileKey, PERCENT_KEY, percent);
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务器异常!", redisConnectionException);
            }
        }
    }

    @Override
    public void cacheFileInfoByRedis(FileInfo fileInfo) {
        if (Constants.USE_REDIS) {
            try {
                String fileKey = fileInfo.getUploadFileInfo().getKey();
                HashOperations<String, String, String> ops = redisTemplate.opsForHash();

                ops.put(fileKey, "uploadFileId", fileInfo.getUploadFileInfo().getId());
                ops.put(fileKey, "url", fileInfo.getUrl());
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务器异常!", redisConnectionException);
            }
        }

    }

    /**
     * 在Redis中检查是否已记录该分片文件
     *
     * @param fileKey
     * @param chunkNum
     * @return
     */
    public boolean isExistOfChunkByRedis(String fileKey, String chunkNum) {
        HashOperations<String, String, String> ops = redisTemplate.opsForHash();

        if (ops.hasKey(fileKey, CHUNK_KEY_PREFIX + chunkNum)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查临时文件目录中是否已存在该分片文件
     *
     * @param fileKey
     * @param chunkNum
     * @param chunkSize
     * @return
     */
    public boolean isExistOfChunkByFileSystem(String fileKey, String chunkNum, long chunkSize) {
        //判断临时文件目录是否存在该分片文件
        File tempDir = new File(Constants.UPLOADER_TEMP_DIR, fileKey);
        File chunkFile = new File(tempDir, chunkNum);

        //如果文件存在且大小一致则返回该分块文件已上传
        if (chunkFile.exists() && chunkFile.length() == chunkSize) {
            return true;
        } else {
            return false;
        }
    }

}
