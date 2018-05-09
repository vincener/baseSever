package com.hq.CloudPlatform.CA.entity;

/**
 * Created by Administrator on 7/24/2017.
 */
public class FileInfo extends BaseEntity {

    private String name;

    private String url;

    private UploadFileInfo uploadFileInfo = new UploadFileInfo();

    public UploadFileInfo getUploadFileInfo() {
        return uploadFileInfo;
    }

    public void setUploadFileInfo(UploadFileInfo uploadFileInfo) {
        this.uploadFileInfo = uploadFileInfo;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
