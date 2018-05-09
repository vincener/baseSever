package com.hq.CloudPlatform.CA.restful.impl;

import com.hq.CloudPlatform.CA.entity.CaLoginfo;
import com.hq.CloudPlatform.CA.mapper.CaLoginfoMapper;
import com.hq.CloudPlatform.CA.restful.ICaLoginfoRestService;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.ICaLoginfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

/**
 * Created by WY
 * Path com.hq.CloudPlatform.CA.restful.impl
 * Created Date 2017/4/27.
 */
@Path("caloginfo")
@Component
@Slf4j
public class CaLoginfoRestServiceImpl extends BaseRestServiceImpl<CaLoginfo> implements ICaLoginfoRestService{

    @Autowired
    private ICaLoginfoService caLoginfoService;
    @Override
    public IBaseService<CaLoginfo> getService() {
        return caLoginfoService;
    }
}
