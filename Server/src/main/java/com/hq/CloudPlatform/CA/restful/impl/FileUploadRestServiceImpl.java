package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hq.CloudPlatform.CA.entity.FileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IFileUploadRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.service.IFileInfoService;
import com.hq.CloudPlatform.CA.service.impl.FileInfoServiceImpl;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author Administrator
 * @date 7/20/2017
 */
@Path("fileUpload")
@Component
@Slf4j
public class FileUploadRestServiceImpl implements IFileUploadRestService {

    @Autowired
    private IFileInfoService fileInfoService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 检查上传的文件是否已上传过
     * 判断的标准备是文件的MD5值和大小都一致则认为是同一个文件,返回文件已存在的响应
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String checkFile(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        JSONObject fileInfo = JSON.parseObject(jsonStr);

        String fileName = fileInfo.getString("fileName");
        String md5 = fileInfo.getString("md5");
        String size = fileInfo.getString("size");
        String fileKey = fileInfoService.generateKey(fileName, md5, size);
        Map<String, Object> content = new HashMap<>(2);

        try {
            //检查上传的文件是否已上传过
            boolean isExist = fileInfoService.checkFileIsExist(fileKey);

            if (isExist) {
                content.put("isExist", true);

                //通过文件名称和文件的唯一标识来查找文件的信息
                FileInfo fileInfoEntity = fileInfoService.getByKeyAndName(fileKey, fileName);

                //如果未找到则进行添加， 此种情况出现的原因是上传的文件已存在（即MD5和大小一致），只有文件名变化了
                if (null == fileInfoEntity) {
                    String fileUploadId = fileInfoService.getUploadFileIdByKey(fileKey);
                    String fileId = fileInfoService.generateUUID();

                    fileInfoEntity = new FileInfo();
                    fileInfoEntity.setId(fileId);
                    fileInfoEntity.setName(fileName);
                    fileInfoEntity.getUploadFileInfo().setId(fileUploadId);
                    fileInfoEntity.setUrl(getFileUrl(fileId));

                    fileInfoService.save(fileInfoEntity);
                }

                content.put("url", fileInfoEntity.getUrl());
            } else {
                content.put("isExist", false);

                //如果是部分上传了则返回当前的上传进度
                content.put(FileInfoServiceImpl.PERCENT_KEY, fileInfoService.getUploadedPercent(fileKey));
            }

            jsonView.successPack(content);
        } catch (ServiceException e) {
            jsonView.failPack(e, "服务接口异常!");
        }

        return JSON.toJSONString(jsonView);
    }

    /**
     * 检查该分块文件是否已上传
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String checkChunk(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        JSONObject chunkFileInfo = JSON.parseObject(jsonStr);

        String fileName = chunkFileInfo.getString("fileName");
        String fileMd5 = chunkFileInfo.getString("fileMd5");
        String fileSize = chunkFileInfo.getString("fileSize");
        String chunkNum = chunkFileInfo.getString("chunkNum");
        long chunkSize = chunkFileInfo.getLongValue("chunkSize");
        String fileKey = fileInfoService.generateKey(fileName, fileMd5, fileSize);
        Map<String, Object> content = new HashMap<>(1);

        try {
            Boolean isExist = fileInfoService.checkChunkIsExist(fileKey, chunkNum, chunkSize);

            if (isExist) {
                content.put("isExist", true);
            } else {
                content.put("isExist", false);
            }

            jsonView.successPack(content);
        } catch (ServiceException serviceException) {
            jsonView.failPack(serviceException, serviceException.getMessage());
        }

        return JSON.toJSONString(jsonView);
    }

    /**
     * 保存上传的文件分块
     *
     * @param form
     * @return
     */
    @Override
    public String uploadChunk(FormDataMultiPart form) {
        JsonViewObject jsonView = new JsonViewObject();
        FormDataBodyPart filePart = form.getField("file");
        FormDataBodyPart fileNamePart = form.getField("name");
        FormDataBodyPart chunkPart = form.getField("chunk");
        FormDataBodyPart fileSizePart = form.getField("fileSize");
        FormDataBodyPart fileMd5Part = form.getField("fileMd5");
        FormDataBodyPart percentPart = form.getField("percent");

        String fileName = fileNamePart.getValue();
        String chunk = chunkPart.getValue();
        String fileSize = fileSizePart.getValue();
        String fileMd5 = fileMd5Part.getValue();
        String percent = percentPart.getValue();
        String fileKey = fileInfoService.generateKey(fileName, fileMd5, fileSize);

        try {
            File tempDir = new File(Constants.UPLOADER_TEMP_DIR, fileKey);

            if (!tempDir.exists()) {
                tempDir.mkdir();
            }

            //把表单内容转换成流
            InputStream fileInputStream = filePart.getValueAs(InputStream.class);
            File chunkFile = new File(tempDir, chunk);

            //保存文件
            FileUtils.copyInputStreamToFile(fileInputStream, chunkFile);

            //保存成功后在Redis中缓存相应的记录,方便记录上传的完成比列
            fileInfoService.cacheChunkInfoByRedis(fileKey, chunk, percent);

            jsonView.successPack("success");
        } catch (IOException e) {
            log.error("文件写入失败!", e);
            jsonView.failPack(e, "文件写入失败!");
        } catch (RedisConnectionFailureException redisConnectionException) {
            log.error("Redis服务器异常!", redisConnectionException);
            jsonView.failPack(redisConnectionException, "Redis服务器异常!");
        }

        return JSON.toJSONString(jsonView);
    }

    /**
     * 对文件分片进行合并
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String mergeChunk(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        JSONObject fileInfo = JSON.parseObject(jsonStr);
        String name = fileInfo.getString("fileName");
        String md5 = fileInfo.getString("fileMd5");
        long size = fileInfo.getLongValue("fileSize");
        String type = fileInfo.getString("fileType");
        String fileKey = fileInfoService.generateKey(name, md5, String.valueOf(size));

        File tempDir = new File(Constants.UPLOADER_TEMP_DIR, fileKey);
        File prodDir = new File(Constants.UPLOADER_PROD_DIR);

        //读取目录里的所有文件, 排除目录只要文件
        File[] fileArray = tempDir.listFiles(pathname -> !pathname.isDirectory());

        //转成集合，便于排序
        List<File> fileList = Arrays.asList(fileArray);
        fileList.sort(Comparator.comparing(f -> Integer.valueOf(f.getName())));

        //合并后的文件
        File outputFile = new File(prodDir, fileKey);
        FileChannel outChannel = null;

        try {
            //创建文件
            outputFile.createNewFile();

            //输出流
            outChannel = new FileOutputStream(outputFile).getChannel();

            //合并
            FileChannel inChannel = null;

            for (File file : fileList) {
                try {
                    inChannel = new FileInputStream(file).getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                } finally {
                    if (null != inChannel) {
                        inChannel.close();
                    }
                }

                //删除分片
                file.delete();
            }

            //清除文件所使用的临时文件夹(用于存放分块文件的目录)
            if (tempDir.isDirectory() && tempDir.exists()) {
                tempDir.delete();
            }

            //合并成功后生成文件访问的URL, 并将文件KEY(MD5,Size)以及URL存入数据库以及Redis中
            FileInfo fileInfoEntity = new FileInfo();
            String fileId = fileInfoService.generateUUID();

            fileInfoEntity.setId(fileId);
            fileInfoEntity.setName(name);
            fileInfoEntity.setUrl(getFileUrl(fileId));
            fileInfoEntity.getUploadFileInfo().setKey(fileKey);
            fileInfoEntity.getUploadFileInfo().setFilePath(outputFile.getCanonicalPath());
            fileInfoEntity.getUploadFileInfo().setContentType(type);
            fileInfoEntity.getUploadFileInfo().setSize(size);

            fileInfoService.save(fileInfoEntity);

            //将文件的信息缓存到Redis中
            fileInfoService.cacheFileInfoByRedis(fileInfoEntity);

            Map<String, Object> content = new HashMap<>(1);
            content.put("url", fileInfoEntity.getUrl());
            jsonView.successPack(content);
        } catch (IOException e) {
            log.error("文件合并失败!", e);
            jsonView.failPack(e, "文件合并失败!");
        } catch (ServiceException e) {
            log.error("数据库异常!", e);
            jsonView.failPack(e, "数据库异常!");
        } finally {
            if (null != outChannel) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    log.error("输出流关闭异常!", e);
                }
            }
        }

        return JSON.toJSONString(jsonView);
    }

    private String getFileUrl(String fileId) {
        int serverPort = request.getServerPort();
        String ServerPath = request.getContextPath();

        return fileInfoService.getFileUrlById(fileId, serverPort, ServerPath);
    }
}
