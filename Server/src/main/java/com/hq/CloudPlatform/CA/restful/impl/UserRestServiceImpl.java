package com.hq.CloudPlatform.CA.restful.impl;

import com.alibaba.fastjson.JSON;
import com.hq.CloudPlatform.CA.annotations.AttributeCheck;
import com.hq.CloudPlatform.CA.entity.User;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IUserRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.restful.view.Page;
import com.hq.CloudPlatform.CA.service.IBaseService;
import com.hq.CloudPlatform.CA.service.IUserService;
import com.hq.CloudPlatform.CA.utils.CodeUtil;
import com.hq.CloudPlatform.CA.utils.ConfigHelper;
import com.hq.CloudPlatform.CA.utils.Constants;
import com.hq.CloudPlatform.CA.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/7.
 */
@Path("user")
@Component
@Slf4j
public class UserRestServiceImpl extends BaseRestServiceImpl<User> implements IUserRestService {

    @Autowired
    private IUserService userService;

    private AttributeCheck<User> check = new AttributeCheck<>();

    @Override
    public IBaseService<User> getService() {
        return userService;
    }

    /**
     * 获取当前用户的信息
     *
     * @return
     */
    @Override
    public String getCurrentUser() {
        JsonViewObject jsonView = new JsonViewObject();
        User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);

        if (user != null) {
            jsonView.successPack(user);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 新增用户
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String save(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        User entity = JSON.parseObject(jsonStr, this.getEntityClass());
        String msg = check.CheckString(entity);
        if (msg != null) {
            jsonView.failPack("false", msg);
        } else {
            try {
                if (entity != null) {
                    entity.setPassword(MD5Util.md5(entity.getPassword()));
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
     * 修改用户的信息
     */
    @Override
    public String update(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            User entity = JSON.parseObject(jsonStr, this.getEntityClass());

            if (entity != null) {
                if (StringUtils.isNotBlank(entity.getPassword())) {
                    entity.setPassword(MD5Util.md5(entity.getPassword()));
                }
                entity.setUpdateDate(new Date());
                jsonView.successPack(this.getService().update(entity));
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            String message = e.getMessage();

            if (StringUtils.isBlank(message)) {
                message = "更新数据失败！";
            }

            jsonView.failPack("false", message);
            log.error("BaseRestServiceImpl update is error,{jsonStr:" + jsonStr + "}," + e.getMessage(), e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通过不同的条件查询用户信息，如通过登录账号、姓名查询
     *
     * @param jobNum
     * @return
     */
    @Override
    public String getByJobNum(String jobNum) {
        JsonViewObject jsonView = new JsonViewObject();
        String result = "";

        try {
            User entity = new User();
            entity.setJobNum(jobNum);
            Map mapBean = new BeanMap(entity);
            List<User> list = this.getService().findByMap(mapBean);

            if (list != null) {
                result = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
            }

            jsonView.successPack(result);
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("UserRestServiceImpl getByJobNum is error，{jobNum:" + jobNum + "}", e);
        }

        result = JSON.toJSONString(jsonView);

        return result;
    }

    @Override
    public String getByJobNumWithPathParam(String jobNum) {
        return getByJobNum(jobNum);
    }

    /**
     * 生成验证码
     */
    @Override
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        CodeUtil.genRandomCodeImage(response, request.getSession());
    }

    @Override
    public String editPwd(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        User user = JSON.parseObject(jsonStr, User.class);
        try {
            int count = userService.checkPassword(user.getLoginName(), MD5Util.md5(user.getOldPassword()));
            if (count <= 0) {
                jsonView.failPack("原密码输入有误");
            } else {
                user.setPassword(MD5Util.md5(user.getPassword()));
                boolean flag = userService.update(user);
                if (flag) {
                    jsonView.successPack("success");
                    user.setPassword(null);
                    request.getSession().setAttribute(Constants.SESSION_KEY_USER, user);
                } else {
                    jsonView.failPack("fail");
                }
            }
        } catch (ServiceException e) {
            jsonView.failPack(e);
        }
        return JSON.toJSONString(jsonView);
    }

    /**
     * 通过登录名和密码对用户进行登录验证
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String login(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        String result;
        User user = JSON.parseObject(jsonStr, User.class);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
        String url="";
        if(!WebUtils.getRequestUri(request).equals("/BaseServer/restful/user/login")){
            url = WebUtils.getSavedRequest(request).getRequestUrl();
        }

        try {
            // 验证码
            String validateCode = (String) request.getSession().getAttribute("validation_code");
            if (user.getIdentifyCode().equalsIgnoreCase(validateCode)) {
                subject.login(token);
                user = userService.findByLoginName(user.getLoginName());
                user.setLastLoginDate(new Date());
                if (null != user) {
                    if (user.getIsLock() == 1){
                        jsonView.failPack("该用户已停用，请联系管理员！");
                        return JSON.toJSONString(jsonView);
                    }
                    user.setPassword(null);
                    request.getSession().setAttribute(Constants.SESSION_KEY_USER, user);
                }
                userService.update(user);
                jsonView.successPack("success");
            } else {
                jsonView.failPack("验证码输入有误");
            }
        } catch (AuthenticationException e) {
            request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
            jsonView.failPack("用户名或密码不正确！");
        } catch (ServiceException e) {
            request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
            e.printStackTrace();
        }
        if(user!=null){
            user.setUrl(url);
        }
        jsonView.setContent(user);
        result = JSON.toJSONString(jsonView);

        return result;
    }

    /**
     * 查询所有用户不关联角色,机构信息信息
     */
    @Override
    public String findAllUser() {
        JsonViewObject jsonView = new JsonViewObject();
        List<User> list = new ArrayList<User>();
        try {
            list = userService.findAllUser();
            jsonView.successPack(list);
        } catch (ServiceException e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getAll is error", e);
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 逻辑删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public String deleteByIdByLogic(String id) {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            Map<String, Object> result = userService.deleteByIdByLogic(id);
            jsonView.successPack(result);
        } catch (ServiceException e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getAll is error", e);
        }
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 用户退出登录
     *
     * @return
     */
    @Override
    public String logout() {
        JsonViewObject jsonView = new JsonViewObject();
        String result;
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
            jsonView.successPack("success");
        } catch (AuthenticationException e) {
            jsonView.failPack("用户退出异常，请重试");
        }
        result = JSON.toJSONString(jsonView);

        return result;
    }

    @Override
    public String devOrProduct() {
        JsonViewObject jsonView = new JsonViewObject();
        String devOrProduct = ConfigHelper.getValue("model");
        if ("dev".equalsIgnoreCase(devOrProduct)) {
            User user = new User();
            user.setLoginName("zhangsan");
            user.setPassword("111111");
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
            try {
                    subject.login(token);
                    user = userService.findByLoginName(user.getLoginName());
                    user.setLastLoginDate(new Date());
                    if (null != user) {
                        user.setPassword(null);
                        request.getSession().setAttribute(Constants.SESSION_KEY_USER, user);
                    }
                    userService.update(user);
                    jsonView.successPack("success");
            } catch (AuthenticationException e) {
                request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
            } catch (ServiceException e) {
                request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
                e.printStackTrace();
            }
        } else {
            jsonView.failPack("fail");
        }
        return JSON.toJSONString(jsonView);
    }

    /**
     * 通过登录名查询用户信息
     *
     * @param login_name
     * @return
     * @throws ServiceException
     */
    @Override
    public String findByLongName(String login_name) throws ServiceException {
        JsonViewObject jsonView = new JsonViewObject();
        Map<String, String> result = userService.findByLongName(login_name);
        jsonView.successPack(result);
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据当前机构id查询该机构所有子机构，关联的人员信息
     *
     * @param jsonStr
     * @return
     */
    @Override
    public String findUserByOrgId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = userService.findUserByOrgId(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
        /*
         * try { jsonView.successPack(userService.findUserByOrgId(id)); } catch
		 * (ServiceException e) { jsonView.failPack(e);
		 * log.error("BaseRestServiceImpl getAll is error", e); } return
		 * JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
		 */
    }

    @Override
    public String findExistUserByOrgId(String orgId) {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            int count = userService.findExistUserByOrgId(orgId);
            if (count > 0) {
                jsonView.failPack("fail");
            } else {
                jsonView.successPack("success");
            }
        } catch (ServiceException e) {
            jsonView.failPack(e);
            e.printStackTrace();
        }
        return JSON.toJSONString(jsonView);
    }


    @Override
    public String findUserByCurrentOrgId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = userService.findUserByCurrentOrgId(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("UserRestServiceImpl findUserByCurrentOrgId is error,{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");

    }

    @Override
    public String getPageRoleById(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = userService.findPageRoleById(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }

        //return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getPageWithRoleId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = userService.findByPageWithRoleId(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("UserRestServiceImpl getPageWithRoleId is error,{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getAllWithRoleId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            Map<String, Object> mapBean = JSON.parseObject(jsonStr);
            List<User> list = userService.findAllWithRoleId(mapBean);

            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("UserRestServiceImpl getAllWithRoleId is error，{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getPageOrgManager(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        Page page;

        try {
            if (!StringUtils.isBlank(jsonStr)) {
                page = JSON.parseObject(jsonStr, Page.class);
            } else {
                page = new Page();
            }

            page = userService.findPageOrgManager(page);
            jsonView.successPack(page);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("BaseRestServiceImpl getPage is error,{jsonStr:" + jsonStr + "}", e);
        }

        //return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteMapNullValue);
        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 批量禁用
     * 请求参数样例：["id1","id2","id3","id4"]
     * @param jsonStr
     * @return
     */
    @Override
    public String batchDisable(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;

        try {
            List<String> idList = JSON.parseArray(jsonStr, String.class);
            if(idList.size()<=0){
                jsonView.successPack("false");
            }else{
                Date loceDate = new Date();
                flag = userService.batchDisable(idList,loceDate);
                jsonView.successPack(JSON.toJSONString(flag));
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("BaseRestServiceImpl batchDisable is error," + jsonStr, e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String batchEnable(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        boolean flag = false;

        try {
            List<String> idList = JSON.parseArray(jsonStr, String.class);
            if(idList.size()<=0){
                jsonView.successPack("false");
            }else{
                Date loceDate = null;
                flag = userService.batchEnable(idList,loceDate);
                jsonView.successPack(JSON.toJSONString(flag));
            }
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(JSON.toJSONString(flag));
            log.error("BaseRestServiceImpl batchDisable is error," + jsonStr, e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String getAllOrgManagerByOrgId(String jsonStr) {
        JsonViewObject jsonView = new JsonViewObject();
        try {
            Map<String, Object> mapBean = JSON.parseObject(jsonStr);
            List<User> list = userService.findAllOrgManagerByOrgId(mapBean);

            jsonView.successPack(list);
        } catch (UnauthorizedException unauthorizedException) {
            jsonView.unauthorizedPack();
        } catch (Exception e) {
            jsonView.failPack(e);
            log.error("UserRestServiceImpl getAllWithRoleId is error，{jsonStr:" + jsonStr + "}", e);
        }

        return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd HH:mm:ss");
    }
}
