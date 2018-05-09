package com.hq.CloudPlatform.CA.restful;

import com.hq.CloudPlatform.CA.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by admin on 2017/3/7.
 */
public interface IUserRestService extends IBaseRestService {
    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @GET
    @Path("getCurrentUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getCurrentUser() throws ServiceException;

    /**
     * 通过不同的条件查询用户信息
     *
     * @param jobNum
     * @return
     */
    @GET
    @Path("getByJobNum")
    String getByJobNum(@QueryParam("jobNum") String jobNum);

    @GET
    @Path("getByJobNumWithPathParam/{jobNum}")
    String getByJobNumWithPathParam(@PathParam("jobNum") String jobNum);

    /**
     * 生成验证码
     */
    @GET
    @Path("generateCode")
    void generateCode(@Context HttpServletRequest request, @Context HttpServletResponse response);

    @POST
    @Path("editPwd")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String editPwd(String jsonStr);

    /**
     * 用户登录
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String login(String jsonStr);

    /**
     * 查询所有用户不关联角色信息
     */
    @GET
    @Path("getAllUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findAllUser();

    /**
     * 逻辑删除用户信息，删除成功返回true，失败返回false
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("deleteByIdByLogic")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String deleteByIdByLogic(@QueryParam("id") String id) throws ServiceException;

    /**
     * 用户登出
     */
    @GET
    @Path("logout")
    String logout();

    @GET
    @Path("devOrProduct")
    String devOrProduct();

    /**
     * 根据登陆账号查询用户信息
     *
     * @param login_name
     * @return
     * @throws ServiceException
     */
    @GET
    @Path("findByLongName")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findByLongName(@QueryParam("login_name") String login_name) throws ServiceException;

    /**
     * 根据当前机构id查询该机构所有子机构，关联的人员信息
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("findUserByOrgId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findUserByOrgId(String jsonStr);

    /**
     * 删除机构时，查看该机构下是否还有成员
     */
    @GET
    @Path("findExistUserByOrgId/{orgId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findExistUserByOrgId(@PathParam("orgId") String orgId);

    /**
     * 根据当前机构ID只查询本机构，关联人员信息
     * @param jsonStr
     * @return
     */
    @POST
    @Path("findUserByCurrentOrgId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findUserByCurrentOrgId(String jsonStr);

    /**
     * 分页查询用户下的所有角色
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("getPageRoleById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPageRoleById(String jsonStr);

    /**
     * 通过角色ID进行过滤查询
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("getPageWithRoleId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPageWithRoleId(String jsonStr);

    @POST
    @Path("getAllWithRoleId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getAllWithRoleId(String jsonStr);

    @POST
    @Path("getPageOrgManager")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPageOrgManager(String jsonStr);

    @POST
    @Path("getAllOrgManagerByOrgId")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getAllOrgManagerByOrgId(String jsonStr);


    /**
     * 批量禁用
     * @param jsonStr
     * @return
     */
    @POST
    @Path("batchDisable")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDisable(String jsonStr);

    /**
     * 批量启用
     * @param jsonStr
     * @return
     */
    @POST
    @Path("batchEnable")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchEnable(String jsonStr);

}
