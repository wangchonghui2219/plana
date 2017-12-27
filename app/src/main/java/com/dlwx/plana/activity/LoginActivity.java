package com.dlwx.plana.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.LoginBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_findpwd)
    TextView tvFindpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_regis)
    TextView tvRegis;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("登录");

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.tv_findpwd, R.id.btn_submit, R.id.tv_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_findpwd:
                startActivity(new Intent(ctx, ForgetPwdActivity.class).putExtra("title","忘记密码"));
                break;
            case R.id.btn_submit:

                submit();
                break;
            case R.id.tv_regis:
                startActivity(new Intent(ctx, RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("login_type", "1");
        map.put("phone", phone);
        map.put("password", pwd);
        mPreenter.fetch(map, false, HttpUtiles.Login, "login");
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
        if (loginBean.getCode() == 200) {
            LoginBean.BodyBean body = loginBean.getBody();
            sp.edit().putString(SpUtiles.User_Token, body.getToken()).commit();
            sp.edit().putString(SpUtiles.Phone, body.getPhone()).commit();
            sp.edit().putString(SpUtiles.NickName, body.getNickname()).commit();

            sp.edit().putString(SpUtiles.header_pic, body.getHeader_pic()).commit();
            sp.edit().putString(SpUtiles.terrace, body.getExten_code()).commit();
            sp.edit().putString(SpUtiles.User_ID, body.getUser_id()).commit();
            sp.edit().putString(SpUtiles.Head_pic, body.getHeader_pic()).commit();
            sp.edit().putInt(SpUtiles.IS_PayPwd, body.getIs_paypwd()).commit();
            sp.edit().putString(SpUtiles.VipName, body.getLevel_name()).commit();
            MyApplication.User_Token = body.getToken();
            MyApplication.Head_Pic = body.getHeader_pic();
            MyApplication.NickName = body.getNickname();
            Glide.with(ctx).asFile().load(body.getHeader_pic())
                    .into(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(File resource,
                                                    Transition<? super File> transition) {

                            wch(resource);

                        }
                    });
            finish();
        } else {
            Toast.makeText(ctx, loginBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }



}
