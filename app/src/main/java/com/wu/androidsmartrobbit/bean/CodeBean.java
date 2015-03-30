package com.wu.androidsmartrobbit.bean;

/**
 * Created by wu on 15-3-30.
 * 返回的代码
 100000	文本类数据
 305000	列车
 306000	航班
 40001	key的长度错误（32位）
 40002	请求内容为空
 40003	key错误或帐号未激活
 40004	当天请求次数已用完
 40005	暂不支持该功能
 40006	服务器升级中
 40007	服务器数据格式异常
 */
public class CodeBean {
    public static final String TEXT ="100000";
    public static final String TRAIN="305000";
    public static final String PLANE="306000";
    public static final String LENGTHE_ERROR="40001";
    public static final String EMPTY="40002";
    public static final String KEY_ERROR="40003";
    public static final String COUNT_ERROR="40004";
    public static final String ERROR="40005";
    public static final String SERVER_UPDATE="40006";
    public static final String JSON_ERROR="40007";
}
