package com.example.administrator.hei;

/**
 * Created by Administrator on 2016/10/31.
 */

public class AdvInfo {
    private Integer res;//资源文件
    private String url;//网络来源

    public AdvInfo(Integer res) {
        this.res = res;
    }

    public AdvInfo(String url) {
        this.url = url;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
