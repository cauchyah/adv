package com.example.administrator.hei;

/**
 * Created by Administrator on 2016/10/17.
 */
public class Bean {
    private String tag;

    public Bean(String tag, String value) {
        this.tag = tag;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

}
