package com.hq.CloudPlatform.CA.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Administrator on 7/20/2017.
 */
public interface IFileDownloadRestService {

    @GET
    @Path("download/{fileId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    Response download(@PathParam("fileId") String fileId);
}
