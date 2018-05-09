package com.hq.CloudPlatform.CA.entity;

/**
 * Created by WY
 * Path com.hq.CloudPlatform.CA.entity
 * Created Date 2017/4/26.
 * 编码表实体类
 */
public class CaCode extends BaseEntity {

    private String code;

    private String value;

    private String type;

    private Integer isDelete;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
