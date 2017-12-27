package com.dlwx.plana.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.TotalAndBalanBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 总资产
 */
public class TotalMoneyActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_commission)
    TextView tvCommission;
    @BindView(R.id.tv_upmonth)
    TextView tvUpmonth;
    @BindView(R.id.tv_nextmonth)
    TextView tvNextmonth;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_total_money);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("总资产");

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
        map.put("token", User_Token);
        mPreenter.fetch(map,true, HttpUtiles.TotalAndBalance,"TotalAndBalance"+User_Token);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        TotalAndBalanBean totalAndBalanBean = gson.fromJson(s, TotalAndBalanBean.class);
        if (totalAndBalanBean.getCode() == 200) {

            TotalAndBalanBean.BodyBean body = totalAndBalanBean.getBody();
            tvMoney.setText(body.getTotal_assets());
            tvCommission.setText(body.getBkge());
            tvNextmonth.setText(body.getNext_month());
            tvUpmonth.setText(body.getPre_month());
        }


    }
}
