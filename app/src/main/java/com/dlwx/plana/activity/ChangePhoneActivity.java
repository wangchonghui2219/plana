package com.dlwx.plana.activity;

import android.support.v7.widget.Toolbar;
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
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.utiles.ButtonUtils;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;


/**
 * 修改手机号
 */
public class ChangePhoneActivity extends BaseActivity {
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
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
        titleTxt.setText("修改绑定手机号");
        register();
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

                        VerificationCodeUtiles codeUtiles =  new VerificationCodeUtiles(ctx,phone,1,countDownUtiles);
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

        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("phone",phone);
        map.put("code",auth);
        mPreenter.fetch(map,false, HttpUtiles.ChangePhone,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
           close();
        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
    }
}
