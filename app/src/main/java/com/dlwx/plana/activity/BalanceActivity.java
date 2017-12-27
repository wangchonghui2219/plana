package com.dlwx.plana.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ResultBean;
import com.dlwx.plana.bean.TotalAndBalanBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.views.PieChartView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 可用余额
 */
public class BalanceActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_gener)
    TextView tvGener;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_forecast)
    TextView tvForecast;
    @BindView(R.id.tv_close)
    TextView tvClose;
    private AlertDialog show;
    private Intent intent;
    private TotalAndBalanBean.BodyBean body;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("可用余额");
        getData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private String income_type;
    private String title;

    @OnClick({R.id.tv_tixian, R.id.tv_close})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_tixian://佣金提现

                if (body.getBalance().equals("0")) {

                    return;
                }

                income_type = "1";
                title = "佣金提现";

                intent = new Intent(ctx, WithdrawPwdActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("income_type", income_type);
                startActivityForResult(intent,1);
//                isCanWitde(income_type);
                break;
            case R.id.tv_close://结算
                if (body.getPre_balance().equals("0")) {

                    return;
                }
                income_type = "2";
                title = "结算";
                intent = new Intent(ctx, WithdrawPwdActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("income_type", income_type);
                startActivityForResult(intent,1);
//                isCanWitde(income_type);
                break;
        }
    }



    private int HttpType;

    private void getData() {
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("token", User_Token);
        mPreenter.fetch(map, true, HttpUtiles.TotalAndBalance, "TotalAndBalance" + User_Token);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {

            data(s, gson);
        } else if(HttpType == 2){
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {



            } else {
//                showDia(resultBean.getResult());
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        wch(resultCode);
        if (requestCode == 1) {
            String resultdate = data.getStringExtra("resultdate");
            int light = data.getIntExtra("light", 1);
            showDia(resultdate,light);
        }

    }

    /**
     * 显示提示
     * @param result
     */
    private void showDia(String result,int light) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_hint, null);
        final PopupWindow popu = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);

       popu.setOutsideTouchable(true);
        backgroundAlpha(0.5f);
        ViewHolder vh = new ViewHolder(view);
        vh.tv_account.setText(result);
        if (light == 2) {
            vh.pieChartView.setVisibility(View.VISIBLE);
        }


        vh.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popu.dismiss();
                backgroundAlpha(1f);
            }
        });
        vh.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popu.dismiss();
                backgroundAlpha(1f);
            }
        });
        vh.pieChartView.setFanClickAbleData(
                new double[]{1,1},
                new int[]{Color.GRAY, Color.GREEN},0.08);
        popu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
       popu.showAtLocation(titleTxt, Gravity.CENTER,0,0);

    }

    private void data(String s, Gson gson) {
        TotalAndBalanBean totalAndBalanBean = gson.fromJson(s, TotalAndBalanBean.class);
        if (totalAndBalanBean.getCode() == 200) {

            body = totalAndBalanBean.getBody();
            tvMoney.setText(body.getBalance());
            tvGener.setText(body.getBkge());
            tvForecast.setText(body.getPre_balance());
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private class ViewHolder {
        public View rootView;
        public TextView tv_account;
        public Button btn_submit;
        public ImageView iv_close;
        public PieChartView pieChartView;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_account = (TextView) rootView.findViewById(R.id.tv_account);
            this.btn_submit = (Button) rootView.findViewById(R.id.btn_submit);
            this.iv_close = (ImageView) rootView.findViewById(R.id.iv_close);
            this.pieChartView = (PieChartView) rootView.findViewById(R.id.pieChartView);
        }

    }
}
