package com.hq.CloudPlatform.CA.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.hq.CloudPlatform.CA.entity.Permission;
import com.hq.CloudPlatform.CA.exception.ServiceException;

/**
 * Created by WY
 * Path com.hq.CloudPlatform.CA.restful.view
 * Created Date 2017/4/28.
 */
public interface IPermissionRestService extends IBaseRestService{

	/**
     * 根据登陆名称获取所有数据
     *
     * @return
     */
    @GET
    @Path("getPermissionStringsByLoginName")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPermissionStringsByLoginName(@QueryParam("loginName") String loginName) throws ServiceException;

    @GET
    @Path("getCurrentUserPermissions")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getCurrentUserPermissions() throws ServiceException;

    /**
     * 通过登录用户的id获取所有数据
     * @param request
     * @return
     * @throws ServiceException
     */
    
    @GET
    @Path("findByUserRolePer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String findByUserRolePer(@Context HttpServletRequest request) throws ServiceException;
    
    /**
     * 变更角色权限
     * @param request
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("saveRolePermission")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String saveRolePermission(String jsonStr,@Context HttpServletRequest request) throws ServiceException;
    
    @GET
    @Path("findByRoleId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findByRoleId(@QueryParam("id")String id);
}
