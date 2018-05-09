package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.annotations.AttributeCheck;
import com.hq.CloudPlatform.CA.entity.CaUserRole;
import com.hq.CloudPlatform.CA.entity.Role;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IRoleRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.IRoleService;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2017/4/27
 */
@Path("role")
@Component
@Slf4j
public class RoleRestServiceImpl extends BaseRestServiceImpl<Role> implements IRoleRestService {

    @Autowired
    private IRoleService roleService;

    private AttributeCheck<Role> check = new AttributeCheck<>();

    @Override
    public IBaseService getService() {
        return roleService;
    }

    /**
     * 新增相关的角色
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String save(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Role entity = JSON.parseObject(jsonStr, this.getEntityClass());
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
            } catch (Exception e) {
                String message = e.getMessage();

                if (StringUtils.isBlank(message)) {
                    message = "保存数据失败！";
                }
                jsonView.failPack("false", message);
                log.error("RoleRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
            }
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 查询该角色下的机构信息
     *
     * @return
     */
    @Override
    public String findAllJoinOrg() {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            List<Role> list = roleService.findAllJoinOrg();
            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("RoleRestServiceImpl getAll is error", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getPageRoleByUserId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = roleService.findPageRoleByUserId(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("RoleRestServiceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 管理用户下的角色
     */
    @Override
    public String saveUserRole(String jsonStr) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            Object o = request.getSession().getAttribute(Constants.SESSION_KEY_USER);
            String issuer = ((User) o).getUsername();

            if (o != null) {
                if (!StringUtils.isBlank(jsonStr)) {
                    Map<String, Object> map = JSON.parseObject(jsonStr);
                    String type = (String) map.get("type");

                    if ("byRole".equals(type)) {
                        String roleId = (String) map.get("roleId");
                        List<String> userIds = (List<String>) map.get("userId");
                        roleService.deleteUserRoleByRoleId(roleId);

                        if (userIds.size() != 0) {
                            for (String uid : userIds) {
                                CaUserRole cur = new CaUserRole();
                                cur.setIssuer(issuer);
                                cur.setUserId(uid);
                                cur.setRoleId(roleId);
                                roleService.saveUserRole(cur);
                            }
                        }
                    } else {
                        String userId = (String) map.get("userId");
                        List<String> roleIds = (List<String>) map.get("roleId");
                        roleService.deleteUserRoleByUserId(userId);

                        if (roleIds.size() != 0) {
                            for (String rid : roleIds) {
                                CaUserRole cur = new CaUserRole();
                                cur.setIssuer(issuer);
                                cur.setRoleId(rid);
                                cur.setUserId(userId);
                                roleService.saveUserRole(cur);
                            }
                        }
                    }

                    jsonView.successPack("true", "修改成功！");
                }
            } else {
                jsonView.failPack("false", "登陆失效！");
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack("false", "保存数据失败！");
            log.error("RoleRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String deleteUserRole(String jsonStr) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;
        try {
            if (!StringUtils.isBlank(jsonStr)) {
                Map<String, Object> map = JSON.parseObject(jsonStr);
                String userId = (String) map.get("userId");
                String roleId = (String) map.get("roleId");
                flag = roleService.deleteUserRole(userId, roleId);
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("RoleRestServiceImpl deleteUserRole is error,{Id:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String batchDeleteUserRole(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;
        try {
            if (!StringUtils.isBlank(jsonStr)) {
                Map<String, Object> map = JSON.parseObject(jsonStr);
                String userId = (String) map.get("userId");
                List<String> roleIdList = (List<String>) map.get("roleId");
                for (String roleId : roleIdList) {
                    flag = roleService.deleteUserRole(userId, roleId);
                }

            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("RoleRestServiceImpl deleteUserRole is error,{Id:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String batchDeleteRoleUser(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;
        try {
            if (!StringUtils.isBlank(jsonStr)) {
                Map<String, Object> map = JSON.parseObject(jsonStr);
                String roleId = (String) map.get("roleId");
                List<String> userIdList = (List<String>) map.get("userId");
                for (String userId : userIdList) {
                    flag = roleService.deleteUserRole(userId, roleId);
                }

            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("RoleRestServiceImpl deleteRoleUser is error,{Id:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
}
