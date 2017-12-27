package com.dlwx.plana.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.baselib.utiles.UploadPicUtiles;
import com.dlwx.baselib.view.CircleImageView;
import com.dlwx.plana.R;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.HeadBean;
import com.dlwx.plana.bean.PerMessBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料
 */
public class PerCenActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.ll_nick)
    LinearLayout llNick;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_per_cen);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("个人资料");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPerMess();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.ll_head, R.id.ll_nick, R.id.ll_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                UploadPicUtiles.showDia(ctx);
                break;
            case R.id.ll_nick:
                startActivity(new Intent(ctx,ChangeNickNameActivity.class));
                break;
            case R.id.ll_phone:
                startActivity(new Intent(ctx,GetPhoneCodeActivity.class));
                break;
        }
    }
    private void getPerMess() {
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.PerMess,"permess"+MyApplication.User_Token);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        PerMessBean perMessBean = gson.fromJson(s, PerMessBean.class);
        if (perMessBean.getCode() == 200) {
            PerMessBean.BodyBean body = perMessBean.getBody();
            Glide.with(ctx).load(body.getHeader_pic()).into(ivHead);
            tvNick.setText(body.getNickname());
            tvAccount.setText(body.getExten_code());
            tvPhone.setText(body.getPhone());
        }else{
            Toast.makeText(ctx, perMessBean.getResult(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
             switch (requestCode){
                        case 1:
                            UploadPicUtiles.startZoomPic((Activity) ctx,data,150,150,1,1);
                            break;
                         case 2:
                             UploadPicUtiles.startZoomPic((Activity) ctx,data,150,150,1,1);
                            break;
                         case 5:
                             File filePath = UploadPicUtiles.getFilePath(data, ctx);
                             wch(filePath);
                                UpHead(filePath);
                             break;
                    }
        }
    }

    private void UpHead(File file) {
        loading.show();
        OkGo.<String>post(HttpUtiles.UpHead)
                .params("token",MyApplication.User_Token)
                .params("header_pic",file)
                .params("type",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        loading.dismiss();
                        wch(response.body());
                        Gson gson = new Gson();
                        HeadBean headBean = gson.fromJson(response.body(), HeadBean.class);
                        if (headBean.getCode() == 200) {
                            HeadBean.BodyBean body = headBean.getBody();
                            sp.edit().putString(SpUtiles.Head_pic,body.getHeader_pic()).commit();
                            MyApplication.Head_Pic = body.getHeader_pic();
                            Glide.with(ctx).load(body.getHeader_pic()).into(ivHead);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        loading.dismiss();
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
