package com.wu.androidsmartrobbit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wu.androidsmartrobbit.bean.ChatBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wu on 15-3-30.
 */
public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ChatBean> mDatas;
    public MyAdapter(Context context,List<ChatBean> lists) {
        mInflater = LayoutInflater.from(context);
        mDatas = lists;
    }

    @Override
    public int getCount() {
        return mDatas.size()    ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChatBean chatBean = mDatas.get(i);
        ViewHolder viewHolder = null;
        //通过ItemType设置布局
        if (view == null){
            if (getItemViewType(i)==0){
                view = mInflater.inflate(R.layout.item_from_msg,viewGroup,false);
                viewHolder = new ViewHolder();
                viewHolder.mData=(TextView)view.findViewById(R.id.id_from_msg_date);
                viewHolder.mMsg=(TextView)view.findViewById(R.id.id_from_msg_info);
            }else {
                view = mInflater.inflate(R.layout.item_to_msg,viewGroup,false);
                viewHolder = new ViewHolder();
                viewHolder.mData=(TextView)view.findViewById(R.id.id_to_msg_date);
                viewHolder.mMsg=(TextView)view.findViewById(R.id.id_to_msg_info);
            }
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        viewHolder.mData.setText(sdf.format(chatBean.date));
        viewHolder.mMsg.setText(chatBean.msg);
        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        ChatBean chatBean = mDatas.get(position);
        if (chatBean.type == ChatBean.Type.INCOMING){
            return 0;
        }
        return 1;
    }

    /**
     * 因为现在有两种布局 所以要返回２
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    class ViewHolder{
        TextView mData;
        TextView mMsg;
    }
}

