package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索
 */
public class SeachActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_seach)
    EditText etSeach;
    @BindView(R.id.btn_seach)
    Button btnSeach;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_seach);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("搜索");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.btn_seach)
    public void onViewClicked() {

        String seach = etSeach.getText().toString().trim();
        if (TextUtils.isEmpty(seach)) {
            vibrator.vibrate(50);

            return;
        }
        Intent intent = new Intent(ctx,SeachCenterActivity.class);
        intent.putExtra("seach",seach);
        startActivity(intent);

    }


}
