package com.dlwx.plana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.CountDownUtiles;
import com.dlwx.baselib.utiles.Regularutiles;
import com.dlwx.baselib.utiles.VerificationCodeUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.utiles.ButtonUtils;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etphone;
    @BindView(R.id.et_auth)
    EditText etauth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.tv_gologin)
    TextView tvGologin;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("注册");
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
        loading.dismiss();
        Gson gson = new Gson();
        if (HttpType == 1) {
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                finish();
            }
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void disLoading() {
        super.disLoading();
    }

    @OnClick({R.id.btn_auth, R.id.btn_submit, R.id.tv_protocol, R.id.tv_gologin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth://验证码

                String phone = etphone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    vibrator.vibrate(50);

                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Regularutiles.Photo(phone)) {
                    Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                    return;
                }
                loading.show();
                CountDownUtiles countDownUtiles = new CountDownUtiles(btnAuth);
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_auth, 2000)) {
                    if (btnAuth.getText().equals("验证码") || btnAuth.getText().equals("重新发送")) {

                        VerificationCodeUtiles codeUtiles = new VerificationCodeUtiles(ctx, phone, 1, countDownUtiles);
                        codeUtiles.sendVerificationCode(HttpUtiles.Get_SMS,loading);
                    }
                }
                break;
            case R.id.btn_submit://注册
                submit();
                break;
            case R.id.tv_protocol://协议
                Intent intent = new Intent(ctx,WebActivity.class);
                intent.putExtra("url", HttpUtiles.Regist_Deal);
                intent.putExtra("title","注册协议");
                startActivity(intent);
                break;
            case R.id.tv_gologin://去登陆
                finish();
                break;
        }
    }

    private void submit() {
        String phone = etphone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }

        String auth = etauth.getText().toString().trim();
        if (TextUtils.isEmpty(auth)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            vibrator.vibrate(50);

            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String affpwd = etAffpwd.getText().toString().trim();
        if (TextUtils.isEmpty(affpwd)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!affpwd.equals(pwd)) {
            etPwd.setText("");
            etAffpwd.setText("");
            Toast.makeText(ctx, "两次密码不一致 请重新输入", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }

//        String descAdd = etDescAddress.getText().toString().trim();
//        if (TextUtils.isEmpty(descAdd)) {
//            vibrator.vibrate(50);
//            Toast.makeText(ctx, "推广码不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", auth);
        map.put("password", pwd);
        map.put("repwd", affpwd);
//        map.put("p_exten_code", descAdd);
        mPreenter.fetch(map, false, HttpUtiles.Register, "register");

    }
    private int HttpType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }



}
