package com.hq.CloudPlatform.CA.service.realm;

import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.service.IPermissionService;
import com.hq.CloudPlatform.CA.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/9.
 */
@Slf4j
public class CasRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private HttpServletRequest request;

    @Autowired
    @Lazy
    private IUserService userService;

    @Autowired
    @Lazy
    private IPermissionService permissionService;

    /**
     * 授权操作，决定那些角色可以使用那些资源
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);

        try {
            return getAuthorizationInfo(username);
        } catch (ServiceException e) {
            throw new AuthorizationException(e);
        }
    }

    private SimpleAuthorizationInfo getAuthorizationInfo(String username) throws ServiceException {
        Set<String> roleSet = userService.getRoleStringsByUserName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);

        Set<String> permissionSet = permissionService.getPermissionStringsByLoginName(username);
        info.setStringPermissions(permissionSet);

        return info;
    }

    /**
     * 认证操作，判断一个请求是否被允许进入系统
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        /*
         * 此处不做用户有效性验证, 因为验证过程已交由CAS单点登陆平台完成
         */

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        return new SimpleAuthenticationInfo(username, "", getName());
    }
}
