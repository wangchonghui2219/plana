package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.WithdBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 提现
 */
public class WitdeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_zfb)
    LinearLayout llZfb;
    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.iv_zfb)
    ImageView ivZfb;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String title;
    @BindView(R.id.et_account)
    EditText etAccount;
    private String account;
    private String income_type;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        income_type = intent.getStringExtra("income_type");
        setContentView(R.layout.activity_witde);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private int type = 1;

    @OnClick({R.id.ll_zfb, R.id.ll_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zfb:
                type = 1;
                Glide.with(ctx).load(R.mipmap.icon_zhifuxuan).into(ivZfb);
                Glide.with(ctx).load(R.mipmap.icon_zhifuwei).into(ivWx);
                break;
            case R.id.ll_wx:
                type = 2;
                Glide.with(ctx).load(R.mipmap.icon_zhifuxuan).into(ivWx);
                Glide.with(ctx).load(R.mipmap.icon_zhifuwei).into(ivZfb);
                break;
        }
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "请输入提现金额", Toast.LENGTH_SHORT).show();
            return;
        }
        getWithAcc(type);

    }

    private void getWithAcc(int type) {
        Map<String,String> map = new HashMap<>();
        map.put("token", User_Token);
        map.put("type",type+"");
        mPreenter.fetch(map,true, HttpUtiles.With_number,"With_number"+User_Token);

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        WithdBean withdBean = gson.fromJson(s, WithdBean.class);
        if (withdBean.getCode() == 200) {
            WithdBean.BodyBean body = withdBean.getBody();
            Intent intent = null;
            if (type == 1) {//支付宝

                intent =  new Intent(ctx,SeleteAccountActivity.class);
                intent.putExtra("money",account);
                intent.putExtra("bank_id",body.getBank_id());
                intent.putExtra("income_type",income_type);
                intent.putExtra("number",body.getNumber());
                startActivity(intent);
            }else{//微信
                //判断是否存在微信首款码
                //存在
                intent =  new Intent(ctx,WxGatherActivity.class);
                intent.putExtra("money",account);
                intent.putExtra("income_type",income_type);
                intent.putExtra("bank_id",body.getBank_id());
                intent.putExtra("number",body.getNumber());
                startActivity(intent);
                //不存在
            }
        }else{
            Toast.makeText(ctx, withdBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
