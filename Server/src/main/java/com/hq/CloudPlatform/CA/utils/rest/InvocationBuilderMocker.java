package com.hq.CloudPlatform.CA.utils.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hq.CloudPlatform.CA.utils.FileUtil;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.*;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Administrator on 4/8/2016.
 */
public class InvocationBuilderMocker implements Invocation.Builder {

    private String path;

    public InvocationBuilderMocker() {

    }

    public InvocationBuilderMocker(String path) {
        this.path = path;
    }

    private String getValue(String path) {
        //此处每次都直接读取配置文件而不进行缓存的目的是方便在开发模式下修改debug.json即时生效
        String jsonStr = FileUtil.readFile(this.getClass().getResource("/configs/debug.json").getPath());
        JSONObject obj = JSON.parseObject(jsonStr);
        Set<String> keySet = obj.keySet();

        if (keySet.contains(path)) {
            String resultJson = obj.get(path).toString();
            return resultJson;
        } else {
            throw new RuntimeException("当前处于开发模式，未配置该接口[" + path + "]的返回值，请检查配置文件Configs/debug.json");
        }
    }

    @Override
    public Invocation build(String method) {
        return null;
    }

    @Override
    public Invocation build(String method, Entity<?> entity) {
        return null;
    }

    @Override
    public Invocation buildGet() {
        return null;
    }

    @Override
    public Invocation buildDelete() {
        return null;
    }

    @Override
    public Invocation buildPost(Entity<?> entity) {
        return null;
    }

    @Override
    public Invocation buildPut(Entity<?> entity) {
        return null;
    }

    @Override
    public AsyncInvoker async() {
        return null;
    }

    @Override
    public Invocation.Builder accept(String... mediaTypes) {
        return null;
    }

    @Override
    public Invocation.Builder accept(MediaType... mediaTypes) {
        return null;
    }

    @Override
    public Invocation.Builder acceptLanguage(Locale... locales) {
        return null;
    }

    @Override
    public Invocation.Builder acceptLanguage(String... locales) {
        return null;
    }

    @Override
    public Invocation.Builder acceptEncoding(String... encodings) {
        return null;
    }

    @Override
    public Invocation.Builder cookie(Cookie cookie) {
        return null;
    }

    @Override
    public Invocation.Builder cookie(String name, String value) {
        return null;
    }

    @Override
    public Invocation.Builder cacheControl(CacheControl cacheControl) {
        return null;
    }

    @Override
    public Invocation.Builder header(String name, Object value) {
        return null;
    }

    @Override
    public Invocation.Builder headers(MultivaluedMap<String, Object> headers) {
        return null;
    }

    @Override
    public Invocation.Builder property(String name, Object value) {
        return null;
    }

    @Override
    public Response get() {
        return null;
    }

    @Override
    public <T> T get(Class<T> responseType) {
        String typeName = responseType.getName();

        if ("java.lang.String".equals(typeName)) {
            T t = (T)getValue(path);
            return t;
        } else {
            throw new RuntimeException("当前处于开发模式[com.tscloud.adminui.utils.debug.InvocationBuilderMocker],不支持的返回类型:" + typeName);
        }
    }

    @Override
    public <T> T get(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response put(Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T put(Entity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T put(Entity<?> entity, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response post(Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T post(Entity<?> entity, Class<T> responseType) {
        String typeName = responseType.getName();

        if ("java.lang.String".equals(typeName)) {
            T t = (T)getValue(path + "/" + entity.getEntity());
            return t;
        } else {
            throw new RuntimeException("当前处于开发模式[com.tscloud.adminui.utils.debug.InvocationBuilderMocker],不支持的返回类型:" + typeName);
        }
    }

    @Override
    public <T> T post(Entity<?> entity, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response delete() {
        return null;
    }

    @Override
    public <T> T delete(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T delete(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response head() {
        return null;
    }

    @Override
    public Response options() {
        return null;
    }

    @Override
    public <T> T options(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T options(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response trace() {
        return null;
    }

    @Override
    public <T> T trace(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T trace(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response method(String name) {
        return null;
    }

    @Override
    public <T> T method(String name, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T method(String name, GenericType<T> responseType) {
        return null;
    }

    @Override
    public Response method(String name, Entity<?> entity) {
        return null;
    }

    @Override
    public <T> T method(String name, Entity<?> entity, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T method(String name, Entity<?> entity, GenericType<T> responseType) {
        return null;
    }
}
