package com.wu.androidsmartrobbit.bean;

import java.util.Date;

/**
 * Created by wu on 15-3-30.
 * 涉及到性能优化，用公共访问权限的变量来访问比用get(),set()方法访问速度会有提升
 */
public class ChatBean {
    public String name;
    public String msg;
    public Type type;
    public Date date;

    public ChatBean() {
    }

    public ChatBean( String msg, Type type, Date date) {
        this.msg = msg;
        this.type = type;
        this.date = date;
    }

    public enum Type{
        INCOMING,OUTCOMING
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
