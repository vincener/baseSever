package com.hq.CloudPlatform.CA.restful;

import com.hq.CloudPlatform.CA.exception.ServiceException;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 2017/4/27.
 */
public interface IRoleRestService extends IBaseRestService {
    /**
     * 查询该角色下的机构和角色信息
     * @return
     */
	@GET
    @Path("getAllJoinOrg")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	String findAllJoinOrg();

    /**
     * 根据用户传的id分页查询该用户下的所有角色
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("getPageRoleByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPageRoleByUserId(String jsonStr);

    /**
     * 变更用户下的角色
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("saveUserRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String saveUserRole(String jsonStr) throws ServiceException;

    /**
     * 删除用户下的角色
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("deleteUserRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String deleteUserRole(String jsonStr) throws ServiceException;

    /**
     * 批量删除用户下的角色
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("batchDeleteUserRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDeleteUserRole(String jsonStr) throws ServiceException;

    /**
     * 批量角色下的用户
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("batchDeleteRoleUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDeleteRoleUser(String jsonStr) throws ServiceException;

}
