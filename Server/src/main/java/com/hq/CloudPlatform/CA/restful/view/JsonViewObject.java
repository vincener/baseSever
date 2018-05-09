package com.hq.CloudPlatform.CA.restful.view;


import com.alibaba.fastjson.annotation.JSONField;
import com.hq.CloudPlatform.CA.utils.Constants;
import lombok.Data;

/**
 * restful对外的JSON 对象封装
 */
@Data
public class JsonViewObject {

    /**
     * 状态值
     */
    private String status;

    /**
     * 返回的编码
     */
    private String code;

    /**
     * 返回的消息描述
     */
    private String message;

    /**
     * 返回的内容
     */
    private Object content;

    public JsonViewObject successPack(Object content) {
        this.setMessage("");
        this.setContent(content);
        this.setStatus(Constants.JsonView.STATUS_SUCCESS);
        return this;
    }

    public JsonViewObject successPack(Object content, String msg) {
        this.setContent(content);
        this.setMessage(msg);
        this.setStatus(Constants.JsonView.STATUS_SUCCESS);
        return this;
    }

    public JsonViewObject failPack(Exception e) {
        String message = e.getMessage();
        int index = message.indexOf(":");
        setMessage(index == -1 ? message : message.substring(index + 1));
        setContent("");
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }

    public JsonViewObject failPack(String errMsg) {
        setMessage(errMsg);
        setContent("");
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }

    /**
     * 未认证的响应结果
     * （未认证表示未进行登陆，无法获取用户的信息）
     *
     * @return
     */
    public JsonViewObject unauthenticatedPack() {
        setMessage("未认证，请先登陆系统！");
        setContent("");
        setStatus(Constants.JsonView.UNAUTHENTICATED);
        return this;
    }

    /**
     * 未授权的响应结果
     * （未授权表示已登陆，已确认用户的身份，只是没有某项操作的权限）
     *
     * @return
     */
    public JsonViewObject unauthorizedPack() {
        setMessage("您无权进行该操作！");
        setContent("");
        setStatus(Constants.JsonView.UNAUTHORIZED);
        return this;
    }

    public JsonViewObject failPack(Object content, String errMsg) {
        setMessage(errMsg);
        setContent(content);
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }

    public JsonViewObject failPackMessage(String errMsg, Object content) {
        setMessage(errMsg);
        setContent(content);
        setStatus(Constants.JsonView.STATUS_FAIL);
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    @JSONField(serialize = false)
    public String getContentAsStr() {
        if (null == content) {
            return "";
        } else {
            return content.toString();
        }
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
