package com.kaltsit.commons;

public enum VisibleStatus {

    SHOW(1, "显示"),
    HIDDEN(2, "隐藏"),
    FORBIDDEN(3, "禁用");

    private final int key;

    private final String desc;

    VisibleStatus(int key, String desc){
        this.key = key;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
