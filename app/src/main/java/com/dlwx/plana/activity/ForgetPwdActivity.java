package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.CountDownUtiles;
import com.dlwx.baselib.utiles.Regularutiles;
import com.dlwx.baselib.utiles.VerificationCodeUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.base.MyApplication;
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
 * 忘记密码或忘记支付密码
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_auth)
    EditText etAuth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String title;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);
        if (title.equals("忘记密码")) {
            etPwd.setInputType(InputType.TYPE_CLASS_TEXT);
            etAffpwd.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.btn_auth, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Regularutiles.Photo(phone)) {
                    Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
                loading.show();
                CountDownUtiles countDownUtiles = new CountDownUtiles(btnAuth);
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_auth,2000)) {
                    if (btnAuth.getText().equals("验证码")||btnAuth.getText().equals("重新发送")) {

                        VerificationCodeUtiles codeUtiles =  new VerificationCodeUtiles(ctx,phone,2,countDownUtiles);
                        codeUtiles.sendVerificationCode(HttpUtiles.Get_SMS,loading);
                    }
                }
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!Regularutiles.Photo(phone)) {
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }

        String auth = etAuth.getText().toString().trim();
        if (TextUtils.isEmpty(auth)) {
            Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String affpwd = etAffpwd.getText().toString().trim();
        if (TextUtils.isEmpty(affpwd)) {
            Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!affpwd.equals(pwd)) {
            etPwd.setText("");
            etAffpwd.setText("");
            Toast.makeText(ctx, "两次密码不一致 请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        if (title.equals("忘记支付密码")) {
            if (pwd.length()>6) {
                Toast.makeText(ctx, "支付密码格式错误,请输入六位支付密码", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("token", MyApplication.User_Token);
        map.put("code",auth);
        wch("pwd:"+pwd);
        if (title.equals("忘记提现密码")) {
            map.put("pay_pwd",pwd);
            map.put("p_pay_pwd",affpwd);
            mPreenter.fetch(map,false,HttpUtiles.ForPayPwd,"");
        }else{

            map.put("password",pwd);
            map.put("repwd",affpwd);

            mPreenter.fetch(map,false, HttpUtiles.ForgetPwd,"");
        }

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            finish();
        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();

    }
}
