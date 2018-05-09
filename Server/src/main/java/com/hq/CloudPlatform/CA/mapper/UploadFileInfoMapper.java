package com.hq.CloudPlatform.CA.mapper;

import com.hq.CloudPlatform.CA.entity.UploadFileInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/3/7.
 */
@Repository
public interface UploadFileInfoMapper extends BaseMapper<UploadFileInfo> {

    UploadFileInfo findByKey(String key);

    void deleteByKey(String key);
}
