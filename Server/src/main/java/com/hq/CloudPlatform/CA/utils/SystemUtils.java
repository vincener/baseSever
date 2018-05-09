package com.hq.CloudPlatform.CA.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class SystemUtils {

    public static String getLocalHostAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取本地服务器IP地址失败！", e);
        }

        //获取本地服务器IP地址异常的情况下降级从配置文件中获取
        return ConfigHelper.getValue("sys.ip");
    }
}
