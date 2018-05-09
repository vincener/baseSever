package com.hq.CloudPlatform.CA.service.filter;

import org.apache.shiro.web.servlet.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 5/2/2017.
 */
public class CrossDomainFilter extends AbstractFilter {
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse rsp = (HttpServletResponse)response;

        rsp.setHeader("Access-Control-Allow-Origin","*");
        rsp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        rsp.setHeader("Access-Control-Max-Age", "3600");
        rsp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        chain.doFilter(request,rsp);
    }
}
