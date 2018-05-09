package com.hq.CloudPlatform.CA.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/3/6.
 * 用户信息表实体类
 */
public class User extends BaseEntity {

    private String orgId;

    private String roleId;

    private String loginName;

    private String password;

    private String username;

    private String jobNum;

    private String loginIp;

    private Date lastLoginDate;

    private CaOrganization organization;

    private String oldPassword;

    private String identifyCode;

    public CaOrgManager getCaOrgManager() {
        return caOrgManager;
    }

    public void setCaOrgManager(CaOrgManager caOrgManager) {
        this.caOrgManager = caOrgManager;
    }

    private CaOrgManager caOrgManager;

    private List<Role> roleList;

    private Integer isDelete;

    private Integer isLock;

    private Date loceDate;

    private Integer tryCount;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 手机号码
     */
    private String phone;

    private String email;

    private String directManagerId;

    public String getDirectManagerId() {
        return directManagerId;
    }

    public void setDirectManagerId(String directManagerId) {
        this.directManagerId = directManagerId;
    }

    public String getDirectManagerName() {
        return directManagerName;
    }

    public void setDirectManagerName(String directManagerName) {
        this.directManagerName = directManagerName;
    }

    private String directManagerName;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public CaOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(CaOrganization organization) {
        this.organization = organization;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Date getLoceDate() {
        return loceDate;
    }

    public void setLoceDate(Date loceDate) {
        this.loceDate = loceDate;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }
}
