package com.shop.models.bean;

public class ApkBean {

    /**
     * id : 1
     * name : test
     * versioncode : 1
     * version : 1.0
     * isupdate : 0
     * apkurl : http://cdwan.cn:9000/res/apk-debug.apk
     * apksize : 1613
     * description : 更新版本
     * packagename : com.shop
     */

    private int id;
    private String name;
    private int versioncode;
    private String version;
    private int isupdate;
    private String apkurl;
    private int apksize;
    private String description;
    private String packagename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(int isupdate) {
        this.isupdate = isupdate;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public int getApksize() {
        return apksize;
    }

    public void setApksize(int apksize) {
        this.apksize = apksize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }
}
