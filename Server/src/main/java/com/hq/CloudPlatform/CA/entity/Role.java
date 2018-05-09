package com.hq.CloudPlatform.CA.entity;

/**
 * Created by admin on 2017/3/6.
 * 角色信息表实体类
 */
public class Role extends BaseEntity {

    private String name;

    private String code;

    private String description;

    private Integer isDelete;

  // private String appcode;

    private Integer orgId;

    private CaOrganization caOrganization;

    public CaOrganization getCaOrganization() {
        return caOrganization;
    }

    public void setCaOrganization(CaOrganization caOrganization) {
        this.caOrganization = caOrganization;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

  /* public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }*/
}
