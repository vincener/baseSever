package com.hq.CloudPlatform.CA.entity;

import java.util.Date;

/**
 * Created by admin on 2017/3/8.
 */
public class BaseEntity {

    private String id;

    private Date createDate;

    private Date updateDate;

    private String issuer;

    private String issuerId;

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuer() {

        return issuer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }
}
