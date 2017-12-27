package com.dlwx.plana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseFastAdapter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.MyGenerListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class MyGenerListAdapter extends BaseFastAdapter {
    private List<MyGenerListBean.BodyBean> body;
    public MyGenerListAdapter(Context ctx,List<MyGenerListBean.BodyBean> body) {
        super(ctx);
        this.body = body;
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_generlist, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MyGenerListBean.BodyBean bodyBean = body.get(position);
        Glide.with(ctx).load(bodyBean.getHeader_pic()).into(vh.iv_pic);
        vh.tv_name.setText(bodyBean.getNickname());
        vh.tv_vip.setText(bodyBean.getLevel_name());
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_name;
        public TextView tv_vip;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_vip = (TextView) rootView.findViewById(R.id.tv_vip);
        }

    }
}
