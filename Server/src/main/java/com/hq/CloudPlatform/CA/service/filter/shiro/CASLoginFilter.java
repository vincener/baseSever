package com.hq.CloudPlatform.CA.service.filter.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Administrator on 7/7/2017.
 */
@Component("casLoginFilter")
public class CASLoginFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String userName = AssertionHolder.getAssertion().getPrincipal().getName();

        if (StringUtils.isNotBlank(userName)) {
            //已登陆
            return true;
        } else {
            //未登陆
            return false;
        }
    }
}
