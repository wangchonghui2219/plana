package com.dlwx.plana.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseFragment;
import com.dlwx.baselib.base.BaseRecrviewAdapter;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SetBanner;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.SeachCenterActivity;
import com.dlwx.plana.adapter.ProductListAdapter;
import com.dlwx.plana.bean.HomeAllData;
import com.dlwx.plana.bean.ProductListbean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.utiles.OpAlibcUtilesWeb;
import com.dlwx.plana.views.CustomLinearLayoutManager;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner bannerview;
    @BindView(R.id.iv_home_pic1)
    ImageView ivHomePic1;
    @BindView(R.id.iv_home_pic2)
    ImageView ivHomePic2;
    @BindView(R.id.iv_home_pic3)
    ImageView ivHomePic3;
    @BindView(R.id.tv_tab_title)
    TabLayout tvTabTitle;
    @BindView(R.id.lv_list)
    RecyclerView lvList;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private List<ProductListbean.BodyBean.ListBean> list = new ArrayList<>();
    private ProductListAdapter listAdapter;
    private Intent intent;
    private List<HomeAllData.BodyBean.ClassifyBean> classify;
    private List<HomeAllData.BodyBean.BannerBean> banner;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多
    }

    @Override
    protected void initDate() {

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ctx);
        manager.setScrollEnabled(true);
        lvList.setLayoutManager(manager);
        tvTabTitle.addTab(tvTabTitle.newTab().setText("精选"));//添加tab选项卡
        tvTabTitle.addTab(tvTabTitle.newTab().setText("女装"));
        tvTabTitle.addTab(tvTabTitle.newTab().setText("男装"));
        tvTabTitle.addTab(tvTabTitle.newTab().setText("家居"));
        tvTabTitle.addTab(tvTabTitle.newTab().setText("数码"));
//        lvList.setNestedScrollingEnabled(false);
        listAdapter = new ProductListAdapter(ctx, list);
        lvList.setAdapter(listAdapter);
        type = 1;
        page = 1;
        HttpType = 2;
        getData();

    }

    @Override
    protected void initListener() {
        tvTabTitle.setOnTabSelectedListener(tabSeletedListener);
//        lvList.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                float y = tvTabTitle.getY();
//                boolean b = recyclerView.canScrollVertically(-1);
////                wch("ss"+y);
//                if (!b) {
//
//                     lvList.setNestedScrollingEnabled(false);
//
//                }else{
//
//                    lvList.setNestedScrollingEnabled(true);
//                    appBarLayout.setExpanded(false);
//                }
//            }
//        });



        refreshLayout.setRefreshHeader(new FalsifyFooter(ctx));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
//            getProduct();

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
                page++;
                getProduct();
            }
        });
            bannerview.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    wch(position);
                    OpAlibcUtilesWeb.getAliBc((Activity)ctx,banner.get(position-1).getTao_url());
                }
            });
        listAdapter.setOnItemClickListener(new BaseRecrviewAdapter.OnItemClickListener() {
            @Override
            public void setOnClick(int position) {
                ProductListbean.BodyBean.ListBean listBean = list.get(position);
                if (!TextUtils.isEmpty(listBean.getCoupon_click_url())) {


                    OpAlibcUtilesWeb.getAliBc((Activity)ctx,listBean.getCoupon_click_url());
                }else{
                    OpAlibcUtilesWeb.getAliBc((Activity)ctx,listBean.getItem_url());

                }

            }
        });
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


    @OnClick({ R.id.iv_home_pic1, R.id.iv_home_pic2, R.id.iv_home_pic3})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_home_pic1:
                intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("seach",classify.get(0).getTitle());
                intent.putExtra("type",classify.get(0).getType());
                startActivity(intent);
                break;
            case R.id.iv_home_pic2:
                intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("seach",classify.get(1).getTitle());
                intent.putExtra("type",classify.get(1).getType());
                startActivity(intent);
                break;
            case R.id.iv_home_pic3:
               intent = new Intent(ctx,SeachCenterActivity.class);
                intent.putExtra("seach",classify.get(2).getTitle());
                intent.putExtra("type",classify.get(2).getType());
                startActivity(intent);
                break;
        }
    }

    private TabLayout.OnTabSelectedListener tabSeletedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            wch(position);
            type = position+1;
            page = 1;
            HttpType = 2;
            list.clear();
            getProduct();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
    private int HttpType;
    private int type = 1;
    private int page = 1;
    private void getData() {
        HttpType =1;
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,true, HttpUtiles.Home_AllData,"Home_AllData");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {

            get(s, gson);
        }else{
            ProductListbean listBean = gson.fromJson(s, ProductListbean.class);
            if (listBean.getCode() == 200) {
                if ( listBean.getBody().getList().size()== 0) {
                    page --;
                }
                list.addAll(listBean.getBody().getList());
               listAdapter.notifyDataSetChanged();
            }
        }
    }

    private void get(String s, Gson gson) {
        HomeAllData homeAllData = gson.fromJson(s, HomeAllData.class);
        if (homeAllData.getCode() == 200) {
            HomeAllData.BodyBean body = homeAllData.getBody();
            banner = body.getBanner();
            List<String> listBanner = new ArrayList<>();
            for (int i = 0; i < banner.size(); i++) {
                listBanner.add(banner.get(i).getUrl());
            }
            SetBanner.startBanner(ctx,bannerview,listBanner);
            classify = body.getClassify();
            Glide.with(ctx).load(classify.get(0).getUrl()).into(ivHomePic1);
            Glide.with(ctx).load(classify.get(1).getUrl()).into(ivHomePic2);
            Glide.with(ctx).load(classify.get(2).getUrl()).into(ivHomePic3);
            HttpType = 2;
            page = 1;
            list.clear();
            getProduct();

        }else{
            Toast.makeText(ctx, homeAllData.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getProduct() {
        HashMap<String, String> map = new HashMap<>();
        map.put("type",type+"");
        map.put("page",page+"");
        mPreenter.fetch(map,false, HttpUtiles.Product_List,"Product_list_Home");
    }
}
