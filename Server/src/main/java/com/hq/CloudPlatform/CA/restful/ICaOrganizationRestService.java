package com.hq.CloudPlatform.CA.restful;

import java.util.List;

import com.hq.CloudPlatform.CA.entity.CaOrganization;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IBaseRestService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by admin on 2017/3/7.
 */
public interface ICaOrganizationRestService extends IBaseRestService {
    /**
     * 通过传递的parent_id值，查询符合该条件的所有机构信息
     * @param id
     * @return
     */
    @GET
    @Path("findByParentId")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findByParentId(@QueryParam("id")String id);

    /**
     * 保存机构下的机构管理员
     * 将实体bean转化成jsonStr
     */
    @POST
    @Path("saveOrgManager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String saveOrgManager(String jsonStr);

    /**
     * 删除机构下的的机构管理员
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("deleteOrgManager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String deleteOrgManager(String jsonStr) throws ServiceException;

    @GET
    @Path("findCountByParentId/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String findCountByParentId(@PathParam("id") String id);

    /**
     * 批量删除机构下的的机构管理员
     * @return
     * @throws ServiceException
     */
    @POST
    @Path("batchDeleteOrgManager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDeleteOrgManager(String jsonStr) throws ServiceException;


    /**
     * 批量禁用
     * @param jsonStr
     * @return
     */
    @POST
    @Path("batchDisableOrgManager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDisableOrgManager(String jsonStr);


    /**
     * 批量启用
     * @param jsonStr
     * @return
     */
    @POST
    @Path("batchEnableOrgManager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchEnableOrgManager(String jsonStr);
}
