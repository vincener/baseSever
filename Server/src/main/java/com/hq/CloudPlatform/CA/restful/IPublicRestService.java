package com.hq.CloudPlatform.CA.restful;

import com.hq.CloudPlatform.CA.exception.ServiceException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 系统对外提供的公共接口
 */
public interface IPublicRestService {

    /**
     * 根据用户的登陆名获取用户信息
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("user/getByLoginName")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getUserInfoByLoginName(@QueryParam("loginName") String loginName) throws ServiceException;

    /**
     * 根据用户ID获取用户的信息
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("user/getById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getUserInfoById(@QueryParam("id") String id) throws ServiceException;

    /**
     * 用户的分页查询
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("user/getPage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getUserListByPage(String jsonStr);

    /**
     * 根据用户登陆名获取该用户所拥有的角色列表
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("role/getAllByLoginName")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getRoleListByLoginName(@QueryParam("loginName") String loginName) throws ServiceException;

    /**
     * 角色的分页查询
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("role/getPage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getRoleListByPage(String jsonStr);

    /**
     * 根据用户登陆名获取该用户所拥有的权限列表
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("permission/getAllByLoginName")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPermissionListByLoginName(@QueryParam("loginName") String loginName) throws ServiceException;

    /**
     * 根据用户登陆名获取该用户所拥有的权限列表,返回的是树型结构,包含id,parentId以及name
     *
     * @param loginName
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("permission/getAllForTreeByLoginName")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPermissionTreeByLoginName(@QueryParam("loginName") String loginName, @QueryParam("appCode") String appCode) throws ServiceException;

    /**
     * 获取所有的机构信息
     *
     * @return
     */
    @GET
    @Path("org/getAll")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getAllOrganizations();

    /**
     * 根据指定的机构ID递归获取该机构下所有的子机构列表,包括子机构下的子机构也一并获取
     *
     * @param orgId
     * @return
     */
    @GET
    @Path("org/recursionGetSubOrgListById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String recursionGetSubOrgListById(@QueryParam("orgId") String orgId);

    /**
     * 根据指定的机构ID获取该机构下其直接子机构列表
     *
     * @param orgId
     * @return
     */
    @GET
    @Path("org/getSubOrgListById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getSubOrgListById(@QueryParam("orgId") String orgId);

    /**
     * 机构的分页查询
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("org/getPage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getOrgListByPage(String jsonStr);

}
