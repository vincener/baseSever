package com.hq.CloudPlatform.CA.service.filter.shiro;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 7/6/2017.
 */
@Component("ipFilter")
public class IpFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        System.out.println("IP: " + request.getRemoteAddr());

        return true;
    }
}
