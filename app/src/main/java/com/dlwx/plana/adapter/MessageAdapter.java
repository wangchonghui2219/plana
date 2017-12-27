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
import com.dlwx.plana.bean.MessBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class MessageAdapter extends BaseFastAdapter {
    private List<MessBean.BodyBean> body;
    private int[] pics = {R.mipmap.icon_xxxt,R.mipmap.icon_xxneibu,R.mipmap.icon_xiaoxi};
    public MessageAdapter(Context ctx,List<MessBean.BodyBean> body) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_mess, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        MessBean.BodyBean bodyBean = body.get(position);
        vh.tv_title.setText(bodyBean.getTitle());
        vh.tv_name.setText(bodyBean.getLast());
        int is_read = bodyBean.getIs_read();
        if (is_read == 0) {
            vh.tv_is_read.setVisibility(View.GONE);
        }else{
            vh.tv_is_read.setText(is_read+"");
            vh.tv_is_read.setVisibility(View.VISIBLE);
        }
        Glide.with(ctx).load(pics[position]).into(vh.iv_pic);
        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_name;
        public TextView tv_is_read;
       public ImageView iv_pic;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_is_read = (TextView) rootView.findViewById(R.id.tv_is_read);
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }
}
