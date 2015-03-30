package com.wu.androidsmartrobbit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wu.androidsmartrobbit.bean.ChatBean;
import com.wu.androidsmartrobbit.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

    private ListView listView;
    private MyAdapter adapter;
    private List<ChatBean> datas;

    private EditText editText;
    private Button sendButton;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //等待接收子线程完成数据的返回
            ChatBean chatBean = (ChatBean) msg.obj;
            datas.add(chatBean);
            adapter.notifyDataSetChanged();
            listView.setSelection(adapter.getCount());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        initListener();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.id_listView);
        editText = (EditText) findViewById(R.id.id_edit);
        sendButton = (Button) findViewById(R.id.id_sendButton);
    }

    private void initDatas() {
        datas = new ArrayList<ChatBean>();
        datas.add(new ChatBean("你好,我是萌萌", ChatBean.Type.INCOMING, new Date()));
        adapter = new MyAdapter(this, datas);
        listView.setAdapter(adapter);
    }

    private void initListener() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String tomsg = editText.getText().toString();
                if (TextUtils.isEmpty(tomsg)) {
                    Toast.makeText(MainActivity.this, "发送消息不能为空", Toast.LENGTH_SHORT);
                    return;
                }

                ChatBean to_Msg = new ChatBean();
                to_Msg.date = new Date();
                to_Msg.msg = tomsg;
                to_Msg.type = ChatBean.Type.OUTCOMING;
                datas.add(to_Msg);
                adapter.notifyDataSetChanged();

                editText.setText("");
                listView.setSelection(adapter.getCount());

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            ChatBean bean = HttpUtils.sendMessage(tomsg);
                            Message msg = Message.obtain();
                            msg.obj = bean;
                            mHandler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }
}
