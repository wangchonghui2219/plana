package com.dlwx.plana.activity;

import android.content.Intent;
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
 * 获取是当前手机号验证码
 */
public class GetPhoneCodeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_auth)
    EditText etAuth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_get_phone_code);
        ButterKnife.bind(this);
        register();
    }

    @Override
    protected void initData() {

        initTabBar(toolbar);
        titleTxt.setText("更换绑定手机号");
        senAuth();
    }
    private int HttpType;
    /**
     * 发送短信获取验证码
     */
    private void senAuth() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,false,HttpUtiles.Get_Token_code,"");

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
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_auth,2000)) {
                    if (btnAuth.getText().equals("验证码")||btnAuth.getText().equals("重新发送")) {

                        HttpType = 1;
                        Map<String,String> map = new HashMap<>();
                        map.put("token",User_Token);
                        mPreenter.fetch(map,false,HttpUtiles.Get_Token_code,"");
                    }
                }
                break;
            case R.id.btn_submit:

                String authcode = etAuth.getText().toString().trim();
                if (TextUtils.isEmpty(authcode)) {
                    Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpType = 2;
                Map<String,String> map = new HashMap<>();
                map.put("token",User_Token);
                map.put("code",authcode);
                mPreenter.fetch(map,false,HttpUtiles.Code_Verify,"");
                break;
        }
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();

        if (HttpType == 2) {

            codeResult(s, gson);
        }else{
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                CountDownUtiles countDownUtiles = new CountDownUtiles(btnAuth);
                countDownUtiles.startCountDown();
            }else{
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    /**
     * 验证码验证结果
     * @param s
     * @param gson
     */
    private void codeResult(String s, Gson gson) {
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
                startActivity(new Intent(ctx,ChangePhoneActivity.class));
        }else{
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
