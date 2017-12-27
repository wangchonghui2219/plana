package com.dlwx.plana.adapter;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseFastAdapter;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.LoginActivity;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.MyExten;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.utiles.wechatpay.wxapi.WXShareUtiles;
import com.dlwx.plana.wxapi.QQUtiles;

import java.util.List;

import static com.dlwx.plana.base.MyApplication.bitmap;

/**
 * 我的推广
 */

public class MyGenerAdapter extends BaseFastAdapter implements View.OnClickListener{
    private List<MyExten.BodyBean.TicketBean> ticket;
    private AlertDialog dialog_show;
    public MyGenerAdapter(Context ctx,List<MyExten.BodyBean.TicketBean> ticket) {
        super(ctx);
        this.ticket = ticket;
    }

    @Override
    public int getCount() {
        return ticket.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_mygener, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        final MyExten.BodyBean.TicketBean ticketBean = ticket.get(position);
        vh.tv_money.setText(ticketBean.getTicket());
        vh.tv_title.setText("价值"+ticketBean.getTicket()+"元现金抵用券一张");
        vh.tv_excode.setText("推广码："+ticketBean.getExten_code());
        vh.tv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 得到剪贴板管理器
                ClipboardManager cmb = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(ticketBean.getExten_code());
            }
        });
        vh.tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.Exten_Code = ticketBean.getExten_code();
                showShareDia();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_money;
        public TextView tv_title;
        public TextView tv_excode;
        public TextView tv_copy;
        public TextView tv_share;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_excode = (TextView) rootView.findViewById(R.id.tv_excode);
            this.tv_copy = (TextView) rootView.findViewById(R.id.tv_copy);
            this.tv_share = (TextView) rootView.findViewById(R.id.tv_share);
        }
    }
    private void showShareDia() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_share, null);
        ViewHolderShare vh = new ViewHolderShare(view);
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setView(view);

        dialog_show = dialog.show();
        vh.ll_QQ.setOnClickListener(this);
        vh.ll_Zone.setOnClickListener(this);
        vh.ll_pyq.setOnClickListener(this);
        vh.ll_Wx.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(MyApplication.User_Token)) {
            ctx.startActivity(new Intent(ctx,LoginActivity.class));
            return;
        }


        String mess = "邀请码:"+MyApplication.Exten_Code;
        QQUtiles qqUtiles = new QQUtiles();
        WXShareUtiles shareUtiles = new WXShareUtiles(ctx);
        switch (v.getId()){
            case R.id.ll_QQ:
                dialog_show.dismiss();

                qqUtiles.shareImageQQ(ctx, HttpUtiles.Share_Pic);
                break;
            case R.id.ll_Zone:
                qqUtiles.shareImageQQZone(ctx,HttpUtiles.Share_Pic);
                dialog_show.dismiss();
                break;
            case R.id.ll_pyq:
                dialog_show.dismiss();
                shareUtiles.wxImgShare(2,bitmap);
                break;
            case R.id.ll_Wx:
                dialog_show.dismiss();
                shareUtiles.wxImgShare(1,bitmap);
                break;

        }
    }
    private  class ViewHolderShare {
        public View rootView;
        public LinearLayout ll_QQ;
        public LinearLayout ll_Zone;
        public LinearLayout ll_pyq;
        public LinearLayout ll_Wx;

        public ViewHolderShare(View rootView) {
            this.rootView = rootView;
            this.ll_QQ = (LinearLayout) rootView.findViewById(R.id.ll_QQ);
            this.ll_Zone = (LinearLayout) rootView.findViewById(R.id.ll_Zone);
            this.ll_pyq = (LinearLayout) rootView.findViewById(R.id.ll_pyq);
            this.ll_Wx = (LinearLayout) rootView.findViewById(R.id.ll_Wx);
        }

    }
}
