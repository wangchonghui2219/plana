package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.LoadWEBUtiles;
import com.dlwx.plana.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String url;
    private String title;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        LoadWEBUtiles webUtiles = new LoadWEBUtiles(ctx);
        webUtiles.setListViewData(url, webView, null);

    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);

    }

    @Override
    protected void initListener() {
//        LoadWEBUtiles loadWEBUtiles = new LoadWEBUtiles(ctx);
//        loadWEBUtiles.setListViewData(url, webView, null);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


}
