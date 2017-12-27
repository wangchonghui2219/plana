package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.adapter.HelpCenAdapter;
import com.dlwx.plana.bean.HelpCenterBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;
    private List<HelpCenterBean.BodyBean> body;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_help_center);
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
        lvList.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private void getData() {
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,false, HttpUtiles.HelpCenter,"");

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        HelpCenterBean helpCenterBean = gson.fromJson(s, HelpCenterBean.class);
        if (helpCenterBean.getCode() == 200) {
            body = helpCenterBean.getBody();
            lvList.setAdapter(new HelpCenAdapter(ctx, body));
        }else{
            Toast.makeText(ctx, helpCenterBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HelpCenterBean.BodyBean bodyBean = body.get(position);
            Intent intent = new Intent(ctx,WebActivity.class);
            intent.putExtra("title",bodyBean.getTitle());
            intent.putExtra("url",bodyBean.getContent());
            startActivity(intent);

        }
    };
}
