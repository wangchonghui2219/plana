package com.dlwx.plana.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseFragment;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.MessageCenterTwoActivity;
import com.dlwx.plana.adapter.MessageAdapter;
import com.dlwx.plana.bean.MessBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 消息
 */
public class MessageFragment extends BaseFragment {
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;
    private List<MessBean.BodyBean> body;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        getData();
    }

    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取未读消息
     */
    private void getData() {
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,true, HttpUtiles.Message,"message"+User_Token);

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MessBean messBean = gson.fromJson(s, MessBean.class);
        if (messBean.getCode() == 200) {
            body = messBean.getBody();
            MessageAdapter adapter = new MessageAdapter(ctx, body);
            lvList.setAdapter(adapter);

        }else{
            Toast.makeText(ctx, messBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MessBean.BodyBean bodyBean = body.get(position);
            Intent intent = new Intent(ctx, MessageCenterTwoActivity.class);
            intent.putExtra("title",bodyBean.getTitle());
            intent.putExtra("type",bodyBean.getType());
            startActivity(intent);
        }
    };
}
