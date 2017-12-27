package com.dlwx.plana.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.view.CircleImageView;
import com.dlwx.plana.R;
import com.dlwx.plana.adapter.MyGenerAdapter;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.MyExten;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.wxapi.QQUtiles;
import com.google.gson.Gson;
import com.tencent.tauth.Tencent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.Head_Pic;
import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 我的推广
 */
public class MyGeneraActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_mess)
    LinearLayout llMess;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_genera);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("邀请好友");
        getData();
        Glide.with(ctx).asBitmap().load("http://120.27.14.79/A_plan/apk/share.jpg").into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bmp, Transition<? super Bitmap> transition) {
                MyApplication.bitmap = bmp;
            }
        });
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick(R.id.ll_mess)
    public void onViewClicked() {
        startActivity(new Intent(ctx,GeneralizePerActivity.class));
    }
    /**
     * 获取数据
     */
    private void getData() {
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,true, HttpUtiles.MyGener,"mygener"+User_Token);
    }
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MyExten myExten = gson.fromJson(s, MyExten.class);
        if (myExten.getCode() == 200) {
            MyExten.BodyBean body = myExten.getBody();
            Glide.with(ctx).load(Head_Pic).into(ivHead);
            tvAccount.setText(body.getNickname());
            tvNum.setText("邀请人数："+body.getCount());
            List<MyExten.BodyBean.TicketBean> ticket = body.getTicket();
            MyGenerAdapter generAdapter = new MyGenerAdapter(ctx,ticket);
            lvList.setAdapter(generAdapter);
        }else{
            Toast.makeText(ctx, myExten.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQUtiles().uiListener);

    }
}
