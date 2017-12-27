package com.dlwx.plana.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.baselib.view.CircleImageView;
import com.dlwx.plana.R;
import com.dlwx.plana.adapter.VipAdapter;
import com.dlwx.plana.bean.VipMesBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.plana.base.MyApplication.Head_Pic;
import static com.dlwx.plana.base.MyApplication.NickName;
import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 升级会员
 */
public class UpVipAvtivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.iv_vip1)
    ImageView ivVip1;
    @BindView(R.id.iv_vip2)
    ImageView ivVip2;
    @BindView(R.id.iv_vip3)
    ImageView ivVip3;
    @BindView(R.id.iv_vip4)
    ImageView ivVip4;
    @BindView(R.id.tv_vip1)
    TextView tvVip1;
    @BindView(R.id.tv_vip2)
    TextView tvVip2;
    @BindView(R.id.tv_vip3)
    TextView tvVip3;
    @BindView(R.id.tv_vip4)
    TextView tvVip4;
    @BindView(R.id.iv_vipvv1)
    ImageView ivVipvv1;
    @BindView(R.id.iv_vipvv2)
    ImageView ivVipvv2;
    @BindView(R.id.iv_vipvv3)
    ImageView ivVipvv3;
    @BindView(R.id.iv_vipvv4)
    ImageView ivVipvv4;
    @BindView(R.id.gv_list)
    GridView gvList;
    @BindView(R.id.btn_nextvip)
    Button btnNextvip;
    @BindView(R.id.tv_name)
    TextView tvName;
    private String level_name;
    private String next_price;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_up_view_avtivity);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("会员升级");
        Glide.with(ctx).load(Head_Pic).into(ivHead);
        tvNickName.setText(NickName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMess();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private void getMess() {
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("token", User_Token);
        mPreenter.fetch(map, true, HttpUtiles.Vip_mess, "vip_mess" + User_Token);

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {

            getVip(s, gson);
        }else{
//            MyOrderBean myOrderBean = gson.fromJson(s, MyOrderBean.class);
//            if (myOrderBean.getCode() == 200) {
//                MyOrderBean.BodyBean body = myOrderBean.getBody();
//                String order_no = body.getOrder_no();
//                startActivity(new Intent(ctx,PayActivity.class).putExtra("order_no",order_no));
//            }else{
//                Toast.makeText(ctx, myOrderBean.getResult(), Toast.LENGTH_SHORT).show();
//            }
        }
    }
    private int HttpType = 1;
    private void getVip(String s, Gson gson) {

        VipMesBean vipMesBean = gson.fromJson(s, VipMesBean.class);
        if (vipMesBean.getCode() == 200) {
            VipMesBean.BodyBean body = vipMesBean.getBody();
            List<VipMesBean.BodyBean.LevelBean> level = body.getLevel();
            tvVip1.setText(level.get(0).getLevel_name());
            tvVip2.setText(level.get(1).getLevel_name());
            tvVip3.setText(level.get(2).getLevel_name());
            tvVip4.setText(level.get(3).getLevel_name());
            VipMesBean.BodyBean.MyLevelBean my_level = body.getMy_level();
            String level_id = my_level.getLevel_id();
            tvName.setText(my_level.getLevel_name());
            sp.edit().putString(SpUtiles.VipName,my_level.getLevel_name()).commit();
            next_level = body.getNext_level().getLevel_id();
            level_name = body.getNext_level().getLevel_name();
            next_price = body.getNext_level().getNext_price();

            if ("1".equals(level_id)) {
                ivVip1.setBackgroundResource(R.mipmap.icon_huiyuanvv);
                ivVipvv1.setVisibility(View.VISIBLE);
                ivVipvv2.setVisibility(View.GONE);
                ivVipvv3.setVisibility(View.GONE);
                ivVipvv4.setVisibility(View.GONE);

            }else if ("2".equals(level_id)) {
                Glide.with(ctx).load(R.mipmap.icon_huiyuanvv).into(ivVip2);
                ivVipvv2.setVisibility(View.VISIBLE);
                ivVipvv1.setVisibility(View.GONE);
                ivVipvv3.setVisibility(View.GONE);
                ivVipvv4.setVisibility(View.GONE);
            }else if ("3".equals(level_id)) {
                Glide.with(ctx).load(R.mipmap.icon_huiyuanvv).into(ivVip3);
                ivVipvv3.setVisibility(View.VISIBLE);
                ivVipvv2.setVisibility(View.GONE);
                ivVipvv1.setVisibility(View.GONE);
                ivVipvv4.setVisibility(View.GONE);
            }else if ("4".equals(level_id)) {
                Glide.with(ctx).load(R.mipmap.icon_huiyuanvv).into(ivVip4);
                ivVipvv4.setVisibility(View.VISIBLE);
                btnNextvip.setVisibility(View.GONE);
                ivVipvv2.setVisibility(View.GONE);
                ivVipvv3.setVisibility(View.GONE);
                ivVipvv1.setVisibility(View.GONE);
            }

            List<VipMesBean.BodyBean.PromoteShowBean> promote_show = body.getPromote_show();
            VipAdapter adapter = new VipAdapter(ctx,promote_show);
            gvList.setAdapter(adapter);

        }
    }

    @OnClick(R.id.btn_nextvip)
    public void onViewClicked() {
            getOtherId();

    }
    private String next_level;
    private void getOtherId() {


            Intent intent = new Intent(ctx,UpVipCenterActivity.class);
            intent.putExtra("next_level",next_level);
            intent.putExtra("level_name",level_name);
            intent.putExtra("next_price",next_price);
            startActivity(intent);






    }
}
