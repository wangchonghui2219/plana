package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.UploadPicUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.bean.WithdBean;
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

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 微信首款码
 */
public class WxGatherActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.btm_submit)
    Button btmSubmit;
    private String money;
    private String bank_id;
    private String number;
    private String income_type;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        bank_id = intent.getStringExtra("bank_id");
        number = intent.getStringExtra("number");
        income_type = intent.getStringExtra("income_type");
        setContentView(R.layout.activity_wx_gather);
        ButterKnife.bind(this);
        if (!TextUtils.isEmpty(bank_id)) {
            Glide.with(ctx).load(number).into(ivPic);
        }
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("微信收款码");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.tv_change, R.id.btm_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                UploadPicUtiles.album(ctx);

                break;
            case R.id.btm_submit:
                //Todo
                startwithdraw();
                break;
        }
    }
    /**
     * 发起提现
     */
    private void startwithdraw() {
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("money",money);
        map.put("type","1");
        map.put("bank_id",bank_id);
        map.put("income_type",income_type);
        mPreenter.fetch(map,false, HttpUtiles.Withdraw,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {

        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
             switch (requestCode){
                        case 2:
                            String picAlbum = UploadPicUtiles.getPicAlbum(data, ctx);
                            File filepath = new File(picAlbum);
                            wch(filepath);
                            Glide.with(ctx).load(filepath).into(ivPic);
                            upPic(filepath);
                            break;
                    }
        }
    }

    private void upPic(File file) {
        wch(User_Token+";\n"+file+"\n");
        OkGo.<String>post(HttpUtiles.Change_Witchd)
                .params("token",User_Token)
                .params("number",file)
                .params("type","2")
                .params("img_type","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        wch(response.body());
                        Gson gson = new Gson();
                        WithdBean withdBean = gson.fromJson(response.body(), WithdBean.class);
                        if (withdBean.getCode() == 200) {
                            WithdBean.BodyBean body = withdBean.getBody();
                            bank_id = body.getBank_id();
                            number = body.getNumber();
                            Glide.with(ctx).load(number).into(ivPic);
                        }else{
                            Toast.makeText(ctx, withdBean.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
