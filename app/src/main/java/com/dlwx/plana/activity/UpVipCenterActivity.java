package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.MyOrderBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 *
 */
public class UpVipCenterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.ll_upcode)
    LinearLayout ll_upcode;
    private String next_level;
    private String level_name;
    private String next_price;
    @Override
    protected void initView() {
        Intent intent = getIntent();
        next_level = intent.getStringExtra("next_level");
        level_name = intent.getStringExtra("level_name");
        next_price = intent.getStringExtra("next_price");
        setContentView(R.layout.activity_up_vip);
        ButterKnife.bind(this);
        if (next_level.equals("2")) {//黄金
            titleTxt.setText("成为黄金会员");
            tvMoney.setText("300");
            tvTitle.setText("价值300元抵用券一张");
            btnSubmit.setText("成为黄金会员");
            llCode.setVisibility(View.VISIBLE);
        } else if (next_level.equals("4")) {//钻石
            titleTxt.setText("成为钻石会员");
            tvMoney.setText("600");
            tvTitle.setText("价值600元抵用券一张");
            btnSubmit.setText("成为钻石会员");
        }else{
            tvTitle.setText(level_name);
            ll_upcode.setVisibility(View.GONE);
            btnSubmit.setText("成为"+level_name);
        }
        tvPrice.setText("￥"+next_price);
        register();
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void showData(String s) {
        wch(s);
        loading.dismiss();
        Gson gson = new Gson();
        MyOrderBean myOrderBean = gson.fromJson(s, MyOrderBean.class);
        if (myOrderBean.getCode() == 200) {
            MyOrderBean.BodyBean body = myOrderBean.getBody();
            String order_no = body.getOrder_no();
            startActivity(new Intent(ctx, PayActivity.class).putExtra("order_no", order_no));
        } else {
            Toast.makeText(ctx, myOrderBean.getResult(), Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.tv_protocol, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_protocol://服务协议
            Intent intent = new Intent(ctx,WebActivity.class);
                intent.putExtra("url",HttpUtiles.Pay_Protocol);
                intent.putExtra("title","支付服务协议");

                startActivity(intent);


                break;
            case R.id.btn_submit:
                Map<String, String> map = new HashMap<>();
                if (next_level.equals("2")) {
                    String code = etCode.getText().toString().trim();
                    if (TextUtils.isEmpty(code)) {
                        Toast.makeText(ctx, "请输入推荐人推荐码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    map.put("p_exten_code", code);
                }
                map.put("token", User_Token);
                map.put("next_level", next_level);
                mPreenter.fetch(map, false, HttpUtiles.UpVIp, "");
                break;
        }
    }
}
