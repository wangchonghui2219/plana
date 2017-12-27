package com.dlwx.plana.activity;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.HelpDescBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助中心详情
 */
public class HelpDescActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String feed_id;

    @Override
    protected void initView() {
        feed_id = getIntent().getStringExtra("feed_id");
        setContentView(R.layout.activity_help_desc);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("帮助中心");
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
        map.put("feed_id",feed_id);
        mPreenter.fetch(map,true, HttpUtiles.Help_desc,"help_desc"+feed_id);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        HelpDescBean helpDescBean = gson.fromJson(s, HelpDescBean.class);
        if (helpDescBean.getCode() == 200) {
            HelpDescBean.BodyBean body = helpDescBean.getBody();
            tvTitle.setText(body.getTitle());
            tvDate.setText(body.getTime());
            Spanned spanned = Html.fromHtml(body.getContent());
            tvContent.setText(Html.fromHtml(spanned+""));
            tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        }else{
            Toast.makeText(ctx, helpDescBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
