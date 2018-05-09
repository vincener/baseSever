package com.hq.CloudPlatform.CA.restful;

import com.hq.CloudPlatform.CA.utils.Constants;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 7/20/2017.
 */
public interface IFileUploadRestService {

    /**
     * 对文件进行校验，判断文件是否已上传过
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("checkFile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(Constants.MediaType.APPLICATION_JSON)
    String checkFile(String jsonStr);

    /**
     * 对文件分片进行校验
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("checkChunk")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(Constants.MediaType.APPLICATION_JSON)
    String checkChunk(String jsonStr);

    /**
     * 上传文件分片
     *
     * @param form
     * @return
     */
    @POST
    @Path("uploadChunk")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(Constants.MediaType.TEXT_PLAIN_TYPE)
    String uploadChunk(FormDataMultiPart form);

    /**
     * 对文件分片进行合并
     *
     * @param jsonStr
     * @return
     */
    @POST
    @Path("mergeChunk")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(Constants.MediaType.APPLICATION_JSON)
    String mergeChunk(String jsonStr);
}
