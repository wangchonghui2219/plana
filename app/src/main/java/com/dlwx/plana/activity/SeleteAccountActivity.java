package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 选择支付宝帐号
 */
public class SeleteAccountActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String money;
    private String bank_id;
    private String number;
    private String income_type;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        bank_id = intent.getStringExtra("bank_id");
        number = intent.getStringExtra("number");
        income_type = intent.getStringExtra("income_type");
        setContentView(R.layout.activity_selete_account);
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(bank_id)) {
            llAccount.setVisibility(View.VISIBLE);
            tvAccount.setText(number);
        }
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("选择支付宝帐号");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.tv_change, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                startActivityForResult(new Intent(ctx,ChangeAccountActivity.class),1);

                break;
            case R.id.btn_submit:

                startwithdraw();

                break;
        }
    }

    /**
     * 发起提现
     */
    private void startwithdraw() {
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("money",money);
        map.put("type","1");
        map.put("bank_id",bank_id);
        map.put("income_type",income_type);
        mPreenter.fetch(map,false, HttpUtiles.Withdraw,"");

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {

        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            wch(number);
            bank_id = data.getStringExtra("bank_id");
            number = data.getStringExtra("number");
            if (!TextUtils.isEmpty(bank_id)) {
                llAccount.setVisibility(View.VISIBLE);
                tvAccount.setText(number);
            }
        }
    }
}
