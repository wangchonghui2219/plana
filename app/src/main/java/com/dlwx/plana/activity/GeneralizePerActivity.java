package com.dlwx.plana.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.adapter.MyGenerListAdapter;
import com.dlwx.plana.bean.MyGenerListBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 推广人信息
 */
public class GeneralizePerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_generalize_per);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("我的推广");
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
        map.put("token",User_Token);
        mPreenter.fetch(map,true, HttpUtiles.MyGenerList,"MyGenerList"+User_Token);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MyGenerListBean myGenerListBean = gson.fromJson(s, MyGenerListBean.class);
        if (myGenerListBean.getCode() == 200) {
            List<MyGenerListBean.BodyBean> body = myGenerListBean.getBody();
            MyGenerListAdapter listAdapter = new MyGenerListAdapter(ctx,body);
            lvList.setAdapter(listAdapter);
        }else{
            Toast.makeText(ctx, myGenerListBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
