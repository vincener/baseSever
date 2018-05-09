package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.annotations.AttributeCheck;
import com.hq.CloudPlatform.CA.entity.CaCode;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.ICacodeRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.ICacodeService;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

/**
 * Created by Administrator on 2017/4/27.
 */
@Path("cacode")
@Component
@Slf4j
public class CacodeRestServicImpl extends BaseRestServiceImpl<CaCode> implements ICacodeRestService {
    @Autowired
    private ICacodeService icacodeservice;

    private AttributeCheck<CaCode> check = new AttributeCheck<>();

    @Override
    public IBaseService<CaCode> getService() {
        return icacodeservice;
    }

    @Override
    public String save(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        CaCode entity = JSON.parseObject(jsonStr, this.getEntityClass());
        String msg = check.CheckString(entity);
        if (msg != null) {
            jsonView.failPack("false", msg);
        } else {
            try {

                if (entity != null) {
                    entity.setIssuer(((User) request.getSession().getAttribute(Constants.SESSION_KEY_USER)).getUsername());
                    String id = this.getService().save(entity);
                    if ("exists".equals(id)) {
                        jsonView.setMessage("exists");
                    } else {
                        jsonView.successPack(id);
                    }
                }
            } catch (UnauthorizedException unauthorizedException) {
                jsonView.unauthorizedPack();
            } catch (ServiceException e) {
                String message = e.getMessage();
                if (StringUtils.isBlank(message)) {
                    message = "保存数据失败！";
                }
                jsonView.failPack("false", message);
                log.error("CacodeRestServicImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
            }
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
}
