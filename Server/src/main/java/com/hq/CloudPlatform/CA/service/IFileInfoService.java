package com.hq.CloudPlatform.CA.service;

import com.hq.CloudPlatform.CA.entity.FileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;

/**
 * @author Administrator
 * @date 2017/4/27
 */
public interface IFileInfoService extends IBaseService<FileInfo> {

    /**
     * 根据文件的MD5和大小生成文件的唯一标识
     *
     * @param fileName
     * @param md5
     * @param fileSize
     * @return
     */
    String generateKey(String fileName, String md5, String fileSize);

    /**
     * 根据文件的唯一标识和文件名称查询相关的文件信息记录
     *
     * @param fileKey
     * @param fileName
     * @return
     * @throws ServiceException
     */
    FileInfo getByKeyAndName(String fileKey, String fileName) throws ServiceException;

    /**
     * 检查是否已存在所指定的唯一标识的文件
     *
     * @param fileKey
     * @return
     * @throws ServiceException
     */
    boolean checkFileIsExist(String fileKey) throws ServiceException;

    /**
     * 通过文件的唯一标识找到其对应的上传文件记录的ID
     *
     * @param fileKey
     * @return
     * @throws ServiceException
     */
    String getUploadFileIdByKey(String fileKey) throws ServiceException;

    /**
     * 检查文件分片是否已上传过
     *
     * @param fileKey
     * @param chunkNum
     * @param chunkSize
     * @return
     */
    boolean checkChunkIsExist(String fileKey, String chunkNum, long chunkSize) throws ServiceException;

    /**
     * 获取指定的文件上传完成的百分比
     *
     * @param fileKey
     * @return
     */
    String getUploadedPercent(String fileKey);

    /**
     * 通过文件ID生成其访问地址
     *
     * @param fileId
     * @return
     */
    String getFileUrlById(String fileId, int port, String serverName);

    /**
     * 将文件分片的上传信息缓存在REDIS中
     *
     * @param fileKey
     * @param chunkNum
     * @param percent
     */
    void cacheChunkInfoByRedis(String fileKey, String chunkNum, String percent);

    /**
     * 将已上传的文件信息缓存在REDIS中
     *
     * @param fileInfo
     */
    void cacheFileInfoByRedis(FileInfo fileInfo);
}
