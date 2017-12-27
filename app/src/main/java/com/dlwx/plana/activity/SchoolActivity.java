package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ContentBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dlwx.plana.R.id.tv_content;

public class SchoolActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(tv_content)
    TextView tvContent;
    private String feed_id;
    private String title;
    private String url;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        feed_id = intent.getStringExtra("feed_id");
        url = intent.getStringExtra("url");
        setContentView(R.layout.activity_school);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);
        getData();
    }



    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private void getData() {
        Map<String,String> map = new HashMap<>();
        if (!TextUtils.isEmpty(feed_id)) {
            map.put("fieed_id",feed_id);
        }
        mPreenter.fetch(map,true,url,url+feed_id);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ContentBean contentBean = gson.fromJson(s, ContentBean.class);
        if (contentBean.getCode() == 200) {
            ContentBean.BodyBean body = contentBean.getBody();
            String content = body.getContent();
            Spanned spanned = Html.fromHtml(content);
            tvContent.setText(Html.fromHtml(spanned.toString()));
            tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
        }else{
            Toast.makeText(ctx, contentBean.getResult(), Toast.LENGTH_SHORT).show();
        }

    }
}
