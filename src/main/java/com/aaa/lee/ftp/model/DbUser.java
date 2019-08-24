package com.aaa.lee.ftp.model;

import java.io.Serializable;

public class DbUser implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String headPic;

    private String filName;

    private String fileNameOriginal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public String getFilName() {
        return filName;
    }

    public void setFilName(String filName) {
        this.filName = filName == null ? null : filName.trim();
    }

    public String getFileNameOriginal() {
        return fileNameOriginal;
    }

    public void setFileNameOriginal(String fileNameOriginal) {
        this.fileNameOriginal = fileNameOriginal == null ? null : fileNameOriginal.trim();
    }
}