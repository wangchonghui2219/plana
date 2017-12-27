package com.dlwx.plana.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseRecrviewAdapter;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.LoginActivity;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.ProductListbean;
import com.dlwx.plana.utiles.wechatpay.wxapi.WXShareUtiles;
import com.dlwx.plana.wxapi.QQUtiles;

import java.util.List;

/**
 *
 */

public class ProductListAdapter extends BaseRecrviewAdapter implements View.OnClickListener{

    private AlertDialog dialog_show;
    private   List<ProductListbean.BodyBean.ListBean> list;
    private int pos;
    public ProductListAdapter(Context ctx,  List<ProductListbean.BodyBean.ListBean> list) {
        super(ctx);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(ctx).inflate(R.layout.item_product, parent,false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        wch("sdsd");
        ProductListbean.BodyBean.ListBean listBean = list.get(position);
        Glide.with(ctx).load(listBean.getPict_url()).into(((ViewHolder)holder).iv_pic);
        ((ViewHolder)holder).tv_title.setText(listBean.getTitle());
        ((ViewHolder)holder).tv_price.setText("￥"+listBean.getZk_final_price());
        ((ViewHolder)holder).tv_sale.setText("月销"+listBean.getVolume());
        ((ViewHolder)holder).tv_code.setText(listBean.getCoupon_info());
        ((ViewHolder)holder).ll_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyApplication.Exten_Code = ticketBean.getExten_code();
                pos = position;
                showShareDia();
            }
        });
        ((ViewHolder)holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.setOnClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_title;
        public TextView tv_price;
        public TextView tv_sale;
        public LinearLayout ll_share;
        public TextView tv_code;
        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_sale = (TextView) rootView.findViewById(R.id.tv_sale);
            this.tv_code = (TextView) rootView.findViewById(R.id.tv_code);
            this.ll_share = (LinearLayout) rootView.findViewById(R.id.ll_share);
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
        ProductListbean.BodyBean.ListBean listBean = list.get(pos);
        String mess = listBean.getTitle()+"\n"
                +listBean.getCoupon_info()+"\n"
                +"淘口令:"+listBean.getCommand();
        QQUtiles qqUtiles = new QQUtiles();
        WXShareUtiles shareUtiles = new WXShareUtiles(ctx);
        switch (v.getId()){
            case R.id.ll_QQ:
                dialog_show.dismiss();

                qqUtiles.shareText(ctx,mess);
                break;
            case R.id.ll_Zone:
                mess = "淘口令:"+listBean.getCommand();
                qqUtiles.shareQzone(ctx,mess);
                dialog_show.dismiss();
                break;
            case R.id.ll_pyq:
                dialog_show.dismiss();
                shareUtiles.wxTextShare(2,mess);
                break;
            case R.id.ll_Wx:
                dialog_show.dismiss();
                shareUtiles.wxTextShare(1,mess);
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
