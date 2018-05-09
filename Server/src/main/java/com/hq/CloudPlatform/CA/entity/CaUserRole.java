package com.hq.CloudPlatform.CA.entity;

/**
 * Created by admin on 2017/4/26.
 * 用户角色关联表实体类
 */
public class CaUserRole extends BaseEntity {

    private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
