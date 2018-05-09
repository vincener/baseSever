package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.CaCode;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.CaCodeMapper;
import com.hq.CloudPlatform.CA.service.ICacodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class CaCodeServiceImpl extends BaseServiceImpl<CaCode> implements ICacodeService{

    @Autowired
    private CaCodeMapper cacodemammer;
    public BaseMapper<CaCode> getBaseMapper() {
        return cacodemammer;
    }
}
