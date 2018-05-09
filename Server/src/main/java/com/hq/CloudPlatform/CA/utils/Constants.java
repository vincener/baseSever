package com.hq.CloudPlatform.CA.utils;

/**
 * Created by admin on 2017/3/7.
 */
public interface Constants {

    String SESSION_KEY_USER = "SESSION_USER";

    String UPLOADER_TEMP_DIR = ConfigHelper.getValue("uploader.temp.dir");

    String UPLOADER_PROD_DIR = ConfigHelper.getValue("uploader.prod.dir");

    boolean USE_REDIS = Boolean.parseBoolean(ConfigHelper.getValue("sys.useRedis"));

    interface Caches {
        String DICTIONARY_CACHE = "dictionary";
    }

    interface MediaType {

        /**
         * 设置restful接口返回json格式,此处统一限制使用UTF-8的格式
         */
        String APPLICATION_JSON = javax.ws.rs.core.MediaType.APPLICATION_JSON
                + ";charset=utf-8";

        String TEXT_PLAIN_TYPE = javax.ws.rs.core.MediaType.TEXT_XML
                + ";charset=utf-8";

    }

    /**
     * Restful 对外的静态变量
     */
    interface JsonView {

        /**
         * 成功
         */
        String STATUS_SUCCESS = "success";

        /**
         * 失败
         */
        String STATUS_FAIL = "fail";

        /**
         * 未认证（即未登陆系统）
         */
        String UNAUTHENTICATED = "unauthenticated";

        /**
         * 未授权(即登陆成功但没有相关操作权限)
         */
        String UNAUTHORIZED = "unauthorized";

    }
}
