package com.hq.CloudPlatform.CA.entity;

/**
 * Created by hellowin10 on 2017/7/23.
 */
public class UploadFileInfo extends BaseEntity {

    private String key;

    private String filePath;

    private String contentType;

    private long size;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
