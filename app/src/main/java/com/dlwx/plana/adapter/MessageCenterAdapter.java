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
import com.dlwx.plana.bean.MessListBean;

import java.util.List;

/**
 * 信息列表
 */

public class MessageCenterAdapter extends BaseFastAdapter {
    private List<MessListBean.BodyBean> body;

    public MessageCenterAdapter(Context ctx, List<MessListBean.BodyBean> body) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_message_center, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MessListBean.BodyBean bodyBean = body.get(position);
        vh.tv_date.setText(bodyBean.getCreate_time());
        vh.tv_title.setText(bodyBean.getTitle());
//        vh.tv_count.setText(bodyBean.getContent());
        Glide.with(ctx).load(bodyBean.getImg_url()).into(vh.iv_pic);

        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public TextView tv_date;
        public TextView tv_title;
        public TextView tv_count;
        public ImageView iv_pic;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_count = (TextView) rootView.findViewById(R.id.tv_count);
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }
}
