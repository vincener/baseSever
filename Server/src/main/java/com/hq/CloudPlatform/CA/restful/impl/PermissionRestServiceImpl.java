package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.annotations.AttributeCheck;
import com.hq.CloudPlatform.CA.entity.CaRolePermission;
import com.hq.CloudPlatform.CA.entity.CaUserRolePermission;
import com.hq.CloudPlatform.CA.entity.Permission;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IPermissionRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.IPermissionService;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by WY
 * Path com.hq.CloudPlatform.CA.restful.impl
 * Created Date 2017/4/28.
 */
@Path("capermission")
@Component
@Slf4j
public class PermissionRestServiceImpl extends BaseRestServiceImpl<Permission> implements IPermissionRestService {

    protected JsonViewObject jsonView = new JsonViewObject();

    @Autowired
    private IPermissionService permissionService;

    private AttributeCheck<Permission> check = new AttributeCheck<>();

    @Override
    public IBaseService<Permission> getService() {
        return permissionService;
    }

    /**
     * 新增相关的权限
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String save(String jsonStr) {
        Permission entity = JSON.parseObject(jsonStr, this.getEntityClass());
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
                log.error("BaseRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
            }
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据登录名查询该用户下的权限
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @Override
    public String getPermissionStringsByLoginName(String loginName) throws ServiceException {
        Set<String> result = permissionService.getPermissionStringsByLoginName(loginName);
        jsonView.successPack(result);
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getCurrentUserPermissions() throws ServiceException {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);

        if (null != user) {
            Set<String> result = permissionService.getPermissionStringsByLoginName(user.getLoginName());
            jsonView.successPack(result);
        } else {
            jsonView.failPack("Not Login!");
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过登录登录存到session中的user对象中的id值，查询该用户下的权限
     *
     * @param req
     * @return
     * @throws ServiceException
     */
    @Override
    public String findByUserRolePer(HttpServletRequest req) throws ServiceException {
        User user = (User) req.getSession().getAttribute(Constants.SESSION_KEY_USER);
        if (user != null) {
            List<CaUserRolePermission> urp = permissionService.findByUserRolePer(user.getId(), "ca");
            jsonView.successPack(urp);
        } else {
            jsonView.failPack("获取权限失败");
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String saveRolePermission(String jsonStr, HttpServletRequest request)
            throws ServiceException {
        try {
            Object o = request.getSession().getAttribute(Constants.SESSION_KEY_USER);
            if (o != null) {
                if (!StringUtils.isBlank(jsonStr)) {
                    Map<String, Object> map = JSON.parseObject(jsonStr);
                    String roleId = (String) map.get("roleId");
                    List<String> permissionId = (List<String>) map.get("permissionId");
                    String issuer = ((User) o).getUsername();
                    CaRolePermission crp;
                    permissionService.deleteRolePermission(roleId);
                    for (String pid : permissionId) {
                        crp = new CaRolePermission();
                        crp.setIssuer(issuer);
                        crp.setPermissionId(pid);
                        crp.setRoleId(roleId);
                        permissionService.saveRolePermission(crp);
                    }
                    jsonView.successPack("true", "修改成功！");
                }
            } else {
                jsonView.failPack("false", "登陆失效！");
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
//             String message = e.getMessage();

//             if (StringUtils.isBlank(message)) {
//                 message = "保存数据失败！";
//             }
            jsonView.failPack("false", "保存数据失败！");
            log.error("BaseRestServiceImpl save is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /* (non-Javadoc)
     * @see com.hq.CloudPlatform.CA.restful.IPermissionRestService#findByRoleId(java.lang.String)
     */
    @Override
    public String findByRoleId(String id) {
        if (id != null) {
            jsonView.successPack(permissionService.findByRoleId(id));
        } else {
            jsonView.failPack("ROID不能为空");
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
}
