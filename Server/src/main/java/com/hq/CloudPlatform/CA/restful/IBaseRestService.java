package com.hq.CloudPlatform.CA.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.hq.CloudPlatform.CA.annotations.SystemVerification;
import com.hq.CloudPlatform.CA.annotations.SystemVerificationType;

/**
 * Created by admin on 2017/3/7.
 */
public interface IBaseRestService {

    /**
     * 分页查询
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("getPage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getPage(String jsonStr);

    /**
     * 获取所有数据
     *
     * @return
     */
    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getAll();

    /**
     * 根据条件查询
     * 将条件实体bean转化成jsonStr
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("getByWhere")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getByWhere(String jsonStr);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GET
    @Path("getById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String getById(@QueryParam("id") String id);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @GET
    @Path("deleteById")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String deleteById(@QueryParam("id") String id);

    /**
     * 批量删除
     * @param jsonStr
     * @return
     */
    @POST
    @Path("batchDelete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String batchDelete(String jsonStr);

    /**
     * 保存
     * 将实体bean转化成jsonStr
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String save(String jsonStr);

    /**
     * 编辑   将实体bean转化成jsonStr
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String update(String jsonStr);

    /**
     * 根据条件做验证
     * 将条件实体bean转化成jsonStr
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("checkByWhere")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    String checkByWhere(String jsonStr);
}
