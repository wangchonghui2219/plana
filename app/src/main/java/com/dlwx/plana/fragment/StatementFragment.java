package com.dlwx.plana.fragment;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseFragment;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.InComeDetailActivity;
import com.dlwx.plana.bean.StatemBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 报表
 */
public class StatementFragment extends BaseFragment{

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_benyue)
    TextView tvBenyue;
    @BindView(R.id.tv_ult)
    TextView tvUlt;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_daynum)
    TextView tvDaynum;
    @BindView(R.id.tv_mothnum)
    TextView tvMothnum;
    Unbinder unbinder;
    private StatemBean.BodyBean body;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_statement;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
            getData();
    }

    @Override
    protected void initListener() {
        tabLayout.setOnTabSelectedListener(onTabSelectedListener);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.ll_detail)
    public void onViewClicked() {
        startActivity(new Intent(ctx,InComeDetailActivity.class));

    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            View customView = tab.getCustomView();

            //TODO
            if (position == 0) {

                tvIncome.setText("今日预估收入：￥"+body.getToday());
            }else{
                tvIncome.setText("昨日预估收入：￥"+body.getYestday());

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
    private void getData() {
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,true, HttpUtiles.Member,"Member"+User_Token);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        StatemBean statemBean = gson.fromJson(s, StatemBean.class);
        if (statemBean.getCode() == 200) {
            body = statemBean.getBody();

            tvMoney.setText("￥"+ body.getBalance());
            tvBenyue.setText("￥"+ body.getNow_month());
            tvUlt.setText("￥"+ body.getPre_month());
            tvIncome.setText("今日预估收入：￥"+body.getToday());
            tvDaynum.setText(body.getDay_active());
            tvMothnum.setText(body.getUser_num());

        }
    }



}
