package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
 * 修改支付宝帐号
 */
public class ChangeAccountActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_account);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
            titleTxt.setText("修改支付宝帐号");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        String account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "支付宝帐号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("number",account);
        map.put("type",1+"");
        mPreenter.fetch(map,false, HttpUtiles.Change_Witchd,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        WithdBean withdBean = gson.fromJson(s, WithdBean.class);
        if (withdBean.getCode() == 200) {
            WithdBean.BodyBean body = withdBean.getBody();
            Intent intent = new Intent();
            intent.putExtra("bank_id",body.getBank_id());
            intent.putExtra("number",body.getNumber());
            setResult(1,intent);
            finish();
        }else{
            Toast.makeText(ctx, withdBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
