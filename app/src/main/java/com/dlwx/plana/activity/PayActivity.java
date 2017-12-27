package com.dlwx.plana.activity;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.PayResultBean;
import com.dlwx.plana.bean.WXPAtResultBean;
import com.dlwx.plana.utiles.AlPay.AliPayUtiles;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.utiles.wechatpay.WeChatPayUtiles;
import com.dlwx.plana.utiles.wechatpay.wxapi.WXPayResultListener;
import com.dlwx.plana.wxapi.AllInterface;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付界面
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.cb_check)
    ImageView cbCheck;
    @BindView(R.id.ll_Ali)
    LinearLayout llAli;
    @BindView(R.id.cb_checkWx)
    ImageView cbCheckWx;
    @BindView(R.id.ll_Wx)
    LinearLayout llWx;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private String orderNo;

    @Override
    protected void initView() {
        orderNo = getIntent().getStringExtra("order_no");
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("支付");
        register();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private String PayType = "1";//支付方式

    @OnClick({R.id.ll_Ali, R.id.ll_Wx, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_Ali:
                PayType = "1";
                Glide.with(ctx).load(R.mipmap.icon_zhifuwei).into(cbCheckWx);
                Glide.with(ctx).load(R.mipmap.icon_zhifuxuan).into(cbCheck);
                break;
            case R.id.ll_Wx:
                PayType ="2";
                Glide.with(ctx).load(R.mipmap.icon_zhifuwei).into(cbCheck);
                Glide.with(ctx).load(R.mipmap.icon_zhifuxuan).into(cbCheckWx);


                break;
            case R.id.btn_pay:

                //Todo
                AllInterface.setPayResultListener(new AllInterface.PayResultListener() {
                    @Override
                    public void result() {
                        finish();
                    }
                });
                startPay();

                break;
        }
    }

    /**
     * 支付
     */
    private void startPay() {
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("pay_type",PayType);
        map.put("order_no",orderNo);
        mPreenter.fetch(map,false, HttpUtiles.Pay,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (PayType.equals("1")) {//支付宝
//
            PayResultBean payResultBean = gson.fromJson(s, PayResultBean.class);
            if (payResultBean.getCode() == 200) {
                AliPayUtiles utiles = new AliPayUtiles((Activity) ctx,payResultBean.getBody());
                utiles.setOnResultListener(new AliPayUtiles.PayResultOnListener() {
                    @Override
                    public void getResult(Boolean result) {
                        if (result) {

                            Toast.makeText(ctx, "支付成功", Toast.LENGTH_SHORT).show();
                           close();
                        }else{
                            Toast.makeText(ctx, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                utiles.startPay();
            }else{
                Toast.makeText(ctx, payResultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }else{//微信
            WXPAtResultBean wxpAtResultBean = gson.fromJson(s, WXPAtResultBean.class);
            if (wxpAtResultBean.getCode() == 200) {
                WXPAtResultBean.BodyBean body = wxpAtResultBean.getBody();
                WeChatPayUtiles utiles = new WeChatPayUtiles(ctx);
                utiles.WXPay(body);
                utiles.setResult(new WXPayResultListener() {
                    @Override
                    public void setResult(boolean result) {
                        if (result) {
                            close();
                            AllInterface.payResultListener.result();
                        }
                    }
                });
            }else{
                Toast.makeText(ctx, wxpAtResultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
