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
import com.dlwx.plana.adapter.MessageCenterAdapter;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.MessListBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息中心
 */
public class MessageCenterTwoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;
    private String title;
    private int type;
    private List<MessListBean.BodyBean> body;

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        setContentView(R.layout.activity_message_center_two);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
        titleTxt.setText(title);

        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("type",type+"");
        mPreenter.fetch(map,true, HttpUtiles.Mess_List,"Mess_List"+type);


    }

    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ctx,WebActivity.class);
                intent.putExtra("url",body.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MessListBean messListBean = gson.fromJson(s, MessListBean.class);
        if (messListBean.getCode() == 200) {
            body = messListBean.getBody();
            lvList.setAdapter(new MessageCenterAdapter(ctx, body));
        }else{
            Toast.makeText(ctx, messListBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
