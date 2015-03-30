package com.wu.androidsmartrobbit.utils;

import com.wu.androidsmartrobbit.bean.ChatBean;
import com.wu.androidsmartrobbit.bean.CodeBean;
import com.wu.androidsmartrobbit.bean.ResultBean;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by wu on 15-3-30.
 */
public class HttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "4ed34969163b7f7a11e4a8f9ccf16fad";

    public static ChatBean sendMessage(String msg) throws Exception{
        ChatBean chatBean = new ChatBean();
        chatBean.date = new Date();
        chatBean.type= ChatBean.Type.INCOMING;
        chatBean.msg=getResultBean(msg).text;
        return chatBean;
    }

    public static ResultBean getResultBean (String msg) throws Exception{
        ResultBean resultBean = new ResultBean();
        String result = doGet(msg);
        JSONTokener json = new JSONTokener(result);
        JSONObject bean = (JSONObject)json.nextValue();
        resultBean.code=bean.getString("code");
        resultBean.text = bean.getString("text");
        if (resultBean.code.equals(CodeBean.PLANE)||resultBean.code.equals(CodeBean.TRAIN)) {
            resultBean.list = bean.getString("list");
        }else {
            resultBean.list=null;
        }
        return resultBean;
    }

    public static String doGet(String msg) throws Exception {
        HttpURLConnection connection = null;
        String url = setParams(msg);
        java.net.URL urlStr = new URL(url);
        connection = (HttpURLConnection) urlStr.openConnection();
        connection.connect();
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        if (bf != null) {
            bf.close();
        }
        if (connection != null) {
            connection.disconnect();
        }
        return sb.toString();
    }

    private static String setParams(String msg) {
        String info = null;
        try {
            info = URLEncoder.encode(msg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = URL + "?key=" + API_KEY + "&info=" + info;
        return url;
    }
}
