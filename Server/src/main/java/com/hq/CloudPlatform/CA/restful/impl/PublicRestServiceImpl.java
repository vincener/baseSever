package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.entity.CaOrganization;
import com.hq.CloudPlatform.CA.entity.CaUserRolePermission;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IPublicRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 7/6/2017.
 */
@Path("public")
@Component
@Slf4j
public class PublicRestServiceImpl implements IPublicRestService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private ICaOrganizationService organizationService;


    /**
     * 根据用户的登陆名获取用户信息
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @Override
    public String getUserInfoByLoginName(String loginName) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            if (StringUtils.isNotBlank(loginName)) {
                User entity = userService.findByLoginName(loginName);

                if(null != entity){
                    jsonView.successPack(entity);
                } else {
                    jsonView.failPack("未找到登陆帐号为" + loginName + "的用户！");
                }
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getUserInfoByLoginName is error,{loginName:" + loginName + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getUserInfoById(String id) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            if (StringUtils.isNotBlank(id)) {
                User entity = userService.findById(id);

                if(null != entity){
                    jsonView.successPack(entity);
                } else {
                    jsonView.failPack("未找到id为" + id + "的用户！");
                }
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getUserInfoById is error,{id:" + id + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据用户登陆名获取该用户所拥有的角色列表
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @Override
    public String getRoleListByLoginName(String loginName) throws ServiceException {
        Set<String> roleList = userService.getRoleStringsByUserName(loginName);

        return JSON.toJSONStringWithDateFormat(roleList, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据用户登陆名获取该用户所拥有的权限列表
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @Override
    public String getPermissionListByLoginName(String loginName) throws ServiceException {
        Set<String> permissionList = permissionService.getPermissionStringsByLoginName(loginName);

        return JSON.toJSONStringWithDateFormat(permissionList, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getPermissionTreeByLoginName(String loginName, String appCode) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            User user = userService.findByLoginName(loginName);

            if (user != null) {
                List<CaUserRolePermission> urp = permissionService.findByUserRolePer(user.getId(), appCode);
                jsonView.successPack(urp);
            } else {
                jsonView.failPack("用户登陆名不存在!");
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getPermissionTreeByLoginName is error,{loginName:" + loginName + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getAllOrganizations() {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            List<CaOrganization> list = organizationService.findAll();
            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getAllOrganizations is error", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String recursionGetSubOrgListById(String orgId) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            List<CaOrganization> list = organizationService.recursionFindByParentId(orgId);
            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl organizationService is error", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getSubOrgListById(String orgId) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            List<CaOrganization> list = organizationService.findByParentId(orgId);
            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getSubOrgListById is error", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getOrgListByPage(String jsonStr) {
        return getPage(jsonStr, organizationService);
    }

    @Override
    public String getRoleListByPage(String jsonStr) {
        return getPage(jsonStr, roleService);
    }

    @Override
    public String getUserListByPage(String jsonStr) {
        return getPage(jsonStr, userService);
    }

    private String getPage(String jsonStr, IBaseService service) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = service.findByPage(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("PublicRestServiceImpl getPage is error,{jsonStr:" + jsonStr + ", service: " + service.getClass() + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
}
