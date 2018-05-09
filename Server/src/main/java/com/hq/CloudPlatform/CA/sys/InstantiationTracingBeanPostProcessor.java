package com.hq.CloudPlatform.CA.sys;

import com.hq.CloudPlatform.CA.entity.UploadFileInfo;
import com.hq.CloudPlatform.CA.exception.ServiceException;
import com.hq.CloudPlatform.CA.service.IFileUploadService;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author Administrator
 * @date 7/3/2017
 */
@Component
@Slf4j
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    @Lazy
    private IFileUploadService fileUploadService;


    @Autowired(required = false)
    @Lazy
    private StringRedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //初始化文件上传时所使用到的临时目录和正式目录
        File tempDir = new File(Constants.UPLOADER_TEMP_DIR);

        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        log.info("文件上传模块临时文件存储目录为:" + tempDir.getAbsolutePath());

        File prodDir = new File(Constants.UPLOADER_PROD_DIR);

        if (!prodDir.exists()) {
            prodDir.mkdirs();
        }

        log.info("文件上传模块正式文件存储目录为:" + prodDir.getAbsolutePath());


        //启动Redis的情况下
        if (Constants.USE_REDIS) {
            log.info("初始化Redis服务...");

            try {
                List<UploadFileInfo> uploadFileInfoList = fileUploadService.findAll();
                HashOperations<String, String, String> ops = redisTemplate.opsForHash();

                uploadFileInfoList.forEach(fileInfo -> {
                    ops.put(fileInfo.getKey(), "uploadFileId", fileInfo.getId());
                });


                log.info("Redis服务初始化完成");
            } catch (RedisConnectionFailureException redisConnectionException) {
                log.error("Redis服务器异常!", redisConnectionException);
            } catch (ServiceException servException) {
                log.error("数据库服务器异常!", servException);
            }
        }

        log.info("系统初始化完成...");
    }
}
