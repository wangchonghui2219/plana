package com.dlwx.plana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseFastAdapter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.InComeBean;

import java.util.List;

/**
 *
 */

public class InComeDetailAdapter extends BaseFastAdapter {
    private List<InComeBean.BodyBean> body;
    public InComeDetailAdapter(Context ctx,List<InComeBean.BodyBean> body) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_incom, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        InComeBean.BodyBean bodyBean = body.get(position);
        vh.tv_time.setText(bodyBean.getCreate_time());
        vh.tv_num.setText("+"+bodyBean.getPrice());
        vh.tv_name.setText(bodyBean.getDetail());
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_time;
        public TextView tv_num;
        public TextView tv_name;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
