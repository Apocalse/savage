package com.kaltsit.commons;

public enum MenuLevel {

    FIRST(0, "目录"),
    SECOND(1, "一级菜单"),
    THIRD(2, "二级菜单"),
    BUTTON(3, "按钮");

    private final int key;

    private final String desc;

    MenuLevel(int key, String desc){
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
