package com.hq.CloudPlatform.CA.restful.impl;

import com.hq.CloudPlatform.CA.entity.FileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.restful.IFileDownloadRestService;
import com.hq.CloudPlatform.CA.restful.view.JsonViewObject;
import com.hq.CloudPlatform.CA.service.IFileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 7/20/2017.
 */
@Path("fileDownload")
@Component
@Slf4j
public class FileDownloadRestServiceImpl implements IFileDownloadRestService {

    @Autowired
    private IFileInfoService fileInfoService;

    /**
     * 根据文件ID下载文件
     *
     * @param fileId
     * @return
     */
    @Override
    public Response download(String fileId) {
        JsonViewObject jsonView = new JsonViewObject();

        try {
            FileInfo fileInfo = fileInfoService.findById(fileId);
            File file = new File(fileInfo.getUploadFileInfo().getFilePath());
            String fileName = URLEncoder.encode(fileInfo.getName(), "UTF-8");
            String contentType = fileInfo.getUploadFileInfo().getContentType();

            Response.ResponseBuilder builder = Response
                    .ok((StreamingOutput) output -> {
                        FileUtils.copyFile(file, output);
                    });

            //图片不需要设置该响应头
            if (!contentType.startsWith("image")) {
                builder.header("Content-disposition", "attachment;filename=" + fileName);
            }

            builder.header("content-type", contentType)
                    .header("Content-Length", file.length())
                    .header("Cache-Control", "no-cache");

            return builder.build();
        } catch (ServiceException e) {
            jsonView.failPack(e);
            log.error("FileDownloadRestServiceImpl download is error,{fileId:" + fileId + "}", e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        } catch (UnsupportedEncodingException e) {
            jsonView.failPack(e);
            log.error("文件名编码错误", e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
}
