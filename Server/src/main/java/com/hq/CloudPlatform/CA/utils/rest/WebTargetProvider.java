package com.hq.CloudPlatform.CA.utils.rest;



import com.hq.CloudPlatform.CA.utils.ConfigHelper;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by sticver on 14-12-22.
 */
public class WebTargetProvider {

    private static String URL = "http://[ip]:[port]/[service]/restful/";

    public static WebTarget getWebResource(String ip, String port) {
        String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]", "cloud");
        return ClientBuilder.newClient().target(url);
    }

    public static WebTarget getWebResource(String ip, String port, String service) {
        if ("DEV".equals(ConfigHelper.getValue("App.RunMode"))) {
            return new WebTargetMocker();
        } else {
            String url = URL.replace("[ip]", ip).replace("[port]", port).replace("[service]", service);
            return ClientBuilder.newClient().target(url);
        }
    }

    public static WebTarget getWebResource(String url) {
        return ClientBuilder.newClient().target(url);
    }

}
