package com.hq.CloudPlatform.CA.service.impl;

import com.hq.CloudPlatform.CA.entity.CaLoginfo;
import com.hq.CloudPlatform.CA.mapper.BaseMapper;
import com.hq.CloudPlatform.CA.mapper.CaLoginfoMapper;
import com.hq.CloudPlatform.CA.service.ICaLoginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WY
 * Path com.hq.CloudPlatform.CA.service.impl
 * Created Date 2017/4/27.
 */
@Service
public class CaLoginfoServiceImpl extends BaseServiceImpl<CaLoginfo> implements ICaLoginfoService{

    @Autowired
    private CaLoginfoMapper CaLoginfoMapper;
    public BaseMapper<CaLoginfo> getBaseMapper() {
        return CaLoginfoMapper;
    }
}
