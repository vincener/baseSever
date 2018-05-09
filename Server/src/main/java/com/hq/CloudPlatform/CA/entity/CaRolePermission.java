package com.hq.CloudPlatform.CA.entity;

/**
 * Created by WY
 * com.hq.CloudPlatform.CA.entity
 * Created Date 2017/4/26.
 * 角色权限关联表实体类
 */
public class CaRolePermission extends BaseEntity {

    private String roleId;

    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
