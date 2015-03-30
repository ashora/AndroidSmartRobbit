package com.wu.androidsmartrobbit;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.wu.androidsmartrobbit.bean.ChatBean;
import com.wu.androidsmartrobbit.utils.HttpUtils;

/**
 * Created by wu on 15-3-30.
 * 这是一个测试类，用来测试httpUtils类的正确性
 */
public class Test extends InstrumentationTestCase {

    public void test(){
        try {
            ChatBean res =  HttpUtils.sendMessage("你好");
            Log.i("tag", res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
