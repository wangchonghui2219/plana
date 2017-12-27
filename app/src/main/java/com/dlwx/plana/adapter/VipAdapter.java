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
import com.dlwx.plana.bean.VipMesBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class VipAdapter extends BaseFastAdapter {
    private List<VipMesBean.BodyBean.PromoteShowBean> promote_show;
    public VipAdapter(Context ctx,List<VipMesBean.BodyBean.PromoteShowBean> promote_show) {
        super(ctx);
        this.promote_show = promote_show;
    }

    @Override
    public int getCount() {
        return promote_show.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_vip, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        VipMesBean.BodyBean.PromoteShowBean promoteShowBean = promote_show.get(position);
        Glide.with(ctx).load(promoteShowBean.getImg()).into(vh.iv_pic);
        vh.tv_text.setText(promoteShowBean.getName());

        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_text;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_text = (TextView) rootView.findViewById(R.id.tv_text);
        }

    }
}
