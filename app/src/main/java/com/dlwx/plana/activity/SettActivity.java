package com.dlwx.plana.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.plana.R;
import com.dlwx.plana.base.MyApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_changepwd)
    LinearLayout llChangepwd;
    @BindView(R.id.ll_close)
    LinearLayout llClose;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sett);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("设置");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.ll_changepwd, R.id.ll_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_changepwd:
                startActivity(new Intent(ctx,ChangePWdActivity.class));
                break;
            case R.id.ll_close:
                new AlertDialog.Builder(ctx)
                        .setTitle("您即将退出本应用")
                        .setNegativeButton("再看一看",null)
                        .setPositiveButton("残忍离开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ctx,LoginActivity.class));
                                sp.edit().putString(SpUtiles.User_Token,"").commit();
                                MyApplication.User_Token = "";
                                finish();
                            }
                        }).show();
                break;
        }
    }
}
