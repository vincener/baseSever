package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.UploadFileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.UploadFileInfoMapper;
import com.hq.CloudPlatform.CA.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class FileUploadServiceImpl extends BaseServiceImpl<UploadFileInfo> implements IFileUploadService {

    @Autowired
    private UploadFileInfoMapper uploadFileInfoMapper;

    @Override
    public BaseMapper<UploadFileInfo> getBaseMapper() {
        return uploadFileInfoMapper;
    }

    @Override
    public UploadFileInfo findByKey(String key) throws ServiceException {
        UploadFileInfo entity;

        try {
            entity = this.uploadFileInfoMapper.findByKey(key);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return entity;
    }

    @Override
    public boolean deleteByKey(String key) throws ServiceException {
        try {
            this.uploadFileInfoMapper.deleteByKey(key);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }
}
