package com.dlwx.plana.activity;

import android.content.Intent;

import com.ali.auth.third.ui.context.CallbackContext;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;

public class ProductWebActivity extends BaseActivity {
    private String url;
    private String title;
    @Override
    protected void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");

    }
    @Override
    protected void initData() {



    }
    //     //这里是使用百川默认的WebView实现的，url打开淘宝商品的url



    @Override
    protected void initListener() {

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

}
