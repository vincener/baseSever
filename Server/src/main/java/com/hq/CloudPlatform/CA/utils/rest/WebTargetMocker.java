package com.hq.CloudPlatform.CA.utils.rest;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

/**
 * Created by Administrator on 4/8/2016.
 */
public class WebTargetMocker implements WebTarget {

    private StringBuffer path = new StringBuffer();

    @Override
    public URI getUri() {
        return null;
    }

    @Override
    public UriBuilder getUriBuilder() {
        return null;
    }

    @Override
    public WebTarget path(String path) {
        this.path.append("/").append(path);
        return this;
    }

    @Override
    public WebTarget resolveTemplate(String name, Object value) {
        return this;
    }

    @Override
    public WebTarget resolveTemplate(String name, Object value, boolean encodeSlashInPath) {
        return this;
    }

    @Override
    public WebTarget resolveTemplateFromEncoded(String name, Object value) {
        return this;
    }

    @Override
    public WebTarget resolveTemplates(Map<String, Object> templateValues) {
        return this;
    }

    @Override
    public WebTarget resolveTemplates(Map<String, Object> templateValues, boolean encodeSlashInPath) {
        return this;
    }

    @Override
    public WebTarget resolveTemplatesFromEncoded(Map<String, Object> templateValues) {
        return null;
    }

    @Override
    public WebTarget matrixParam(String name, Object... values) {
        return this;
    }

    @Override
    public WebTarget queryParam(String name, Object... values) {
        this.path.append("/").append(name).append("/");

        for (Object obj : values) {
            this.path.append((String) obj);
        }

        return this;
    }

    @Override
    public Invocation.Builder request() {
        return new InvocationBuilderMocker(path.toString());
    }

    @Override
    public Invocation.Builder request(String... acceptedResponseTypes) {
        return null;
    }

    @Override
    public Invocation.Builder request(MediaType... acceptedResponseTypes) {
        InvocationBuilderMocker builderMocker = new InvocationBuilderMocker();

        return builderMocker;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public WebTarget property(String name, Object value) {
        return this;
    }

    @Override
    public WebTarget register(Class<?> componentClass) {
        return this;
    }

    @Override
    public WebTarget register(Class<?> componentClass, int priority) {
        return this;
    }

    @Override
    public WebTarget register(Class<?> componentClass, Class<?>... contracts) {
        return this;
    }

    @Override
    public WebTarget register(Class<?> componentClass, Map<Class<?>, Integer> contracts) {
        return this;
    }

    @Override
    public WebTarget register(Object component) {
        return this;
    }

    @Override
    public WebTarget register(Object component, int priority) {
        return this;
    }

    @Override
    public WebTarget register(Object component, Class<?>... contracts) {
        return this;
    }

    @Override
    public WebTarget register(Object component, Map<Class<?>, Integer> contracts) {
        return this;
    }
}
