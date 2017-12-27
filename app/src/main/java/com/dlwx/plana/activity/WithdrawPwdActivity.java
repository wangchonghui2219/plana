package com.dlwx.plana.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.views.PswInputView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 提现密码
 */
public class WithdrawPwdActivity extends BaseActivity {
    @BindView(R.id.et_pwd)
    PswInputView etPwd;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private String title;
    private String income_type;
    private Intent intent;
    private int is_payPwd;
    private String pwd;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        income_type = intent.getStringExtra("income_type");
        setContentView(R.layout.activity_withdraw_pwd);
        ButterKnife.bind(this);
        is_payPwd = sp.getInt(SpUtiles.IS_PayPwd, 2);
    }

    @Override
    protected void initData() {
        if (is_payPwd == 1) {
            titleTxt.setText("提现密码");
        }else{
            titleTxt.setText("设置提现密码");
            tvForget.setVisibility(View.GONE);
        }
        initTabBar(toolbar);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
               hideInputKeyboard(etPwd);
                break;
        }

        return true;
    }

    @Override
    protected void initListener() {
            etPwd.setInputCallBack(new PswInputView.InputCallBack() {
                @Override
                public void onInputFinish(String result) {
                    pwd = result;
                }
            });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.tv_forget, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                intent = new Intent(ctx, ForgetPwdActivity.class);
                intent.putExtra("title", "忘记提现密码");
                startActivity(intent);
                break;
            case R.id.btn_submit:

                if (TextUtils.isEmpty(pwd)) {
                    vibrator.vibrate(50);
                    return;
                }
                wch(pwd);
                hideInputKeyboard(etPwd);
                Map<String,String> map = new HashMap<>();
                map.put("token", User_Token);
                if ( titleTxt.getText().toString().equals("提现密码")) {
                    map.put("old_pay_pwd",pwd);
                    HttpType = 2;
                    mPreenter.fetch(map,false, HttpUtiles.CheckPayPwd,"");
                }else{
                    HttpType = 1;
                    map.put("pay_pwd",pwd);

                    mPreenter.fetch(map,false, HttpUtiles.Sett_payPwd,"");
                }


                break;
        }
    }
    private int HttpType;
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();

            if (HttpType == 2) {
                ResultBean resultBean = gson.fromJson(s, ResultBean.class);
                if (resultBean.getCode() == 200) {

                isCanWitde(income_type);


                }
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }else if (HttpType == 3) {
                ResultBean resultBean = gson.fromJson(s, ResultBean.class);
                if (resultBean.getCode() == 200) {

                    intent = new Intent(ctx, WitdeActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("income_type", income_type);
                    startActivity(intent);
                    Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("resultdate",resultBean.getResult());
                    intent.putExtra("light",resultBean.getBody().getLight());
                    setResult(1,intent);
                    finish();

                }
            }

            else{
                ResultBean resultBean = gson.fromJson(s, ResultBean.class);
                if (resultBean.getCode() == 200) {
                sp.edit().putInt(SpUtiles.IS_PayPwd,1).commit();
                etPwd.clearResult();
                titleTxt.setText("提现密码");
                tvForget.setVisibility(View.VISIBLE);

                }else{
                    Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
                }

            }

    }

    /**
     * 判断是否能提现
     */
    private void isCanWitde(String income_type) {
        HttpType = 3;
//        Intent intent = new Intent(ctx, WithdrawPwdActivity.class);
//        intent.putExtra("title", title);
//        intent.putExtra("income_type", income_type);
//        startActivity(intent);
        Map<String, String> map = new HashMap<>();
        map.put("token", User_Token);
        map.put("income_type", income_type);
        mPreenter.fetch(map, false, HttpUtiles.IsCanWitde, "");
    }

    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (imm.isActive(etPwd)) {
                hideInputKeyboard(etPwd);
            }
        }
        return super.onTouchEvent(event);
    }
    /**隐藏键盘 */
    protected void hideInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
