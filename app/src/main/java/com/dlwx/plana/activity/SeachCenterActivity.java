package com.dlwx.plana.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.base.BaseRecrviewAdapter;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.view.MyPopuWindow;
import com.dlwx.plana.R;
import com.dlwx.plana.adapter.ProductListAdapter;
import com.dlwx.plana.bean.ProductListbean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.dlwx.plana.utiles.OpAlibcUtilesWeb;
import com.dlwx.plana.wxapi.QQUtiles;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索结果
 */
public class SeachCenterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    RecyclerView lvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_sales)
    TextView tvSales;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_default)
    TextView tvDefault;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    private String seach;
    private List<ProductListbean.BodyBean.ListBean> list = new ArrayList<>();
    private ProductListAdapter listAdapter;
    private int type;

    @Override
    protected void initView() {
        seach = getIntent().getStringExtra("seach");
        type = getIntent().getIntExtra("type", 0);
        setContentView(R.layout.activity_seach_center);
        ButterKnife.bind(this);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多
        if (type == 0) {
            llSort.setVisibility(View.VISIBLE);
        }else{
            llSort.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {
        list.clear();
        getData();
        initTabBar(toolbar);
        titleTxt.setText(seach);
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        lvList.setLayoutManager(manager);
        listAdapter = new ProductListAdapter(ctx, list);
        lvList.setAdapter(listAdapter);
    }

    @Override
    protected void initListener() {
        refreshLayout.setRefreshHeader(new FalsifyFooter(ctx));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                page = 1;
                list.clear();
                getData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
                page++;
                getData();
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


    private int page = 1;

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private void getData() {
        wch(type);
        HashMap<String, String> map = new HashMap<>();
        if (type == 0) {
            map.put("search", seach);
            map.put("page", page + "");
            map.put("sort", sort + "");
            mPreenter.fetch(map, false, HttpUtiles.Seach_List, "Product_list_Home");


        } else {
            map.put("type", type + "");
            map.put("page", page + "");
            map.put("sort", sort + "");
            mPreenter.fetch(map, false, HttpUtiles.Product_List, "Product_list_Home");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQUtiles().uiListener);

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ProductListbean productListbean = gson.fromJson(s, ProductListbean.class);
        if (productListbean.getCode() == 200) {
            list.addAll(productListbean.getBody().getList());
            listAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ctx, productListbean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private int sort = 4;

    @OnClick({R.id.tv_default, R.id.tv_sales, R.id.tv_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_default://默认排序
                sort = 4;
                page = 1;
                list.clear();
                getData();
                break;
            case R.id.tv_sales://销量排序
                sort = 1;
                page = 1;
                list.clear();
                getData();
                break;
            case R.id.tv_price://价格排序
                showPopu();
                break;
        }
    }

    /**
     * 显示价格排序窗体
     */
    private void showPopu() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.popu_seach_sort_price, null);
        final MyPopuWindow popu = new MyPopuWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        final ViewHolder vh = new ViewHolder(view);

        popu.setOutsideTouchable(true);
        popu.setBackgroundDrawable(new ColorDrawable(0x77000000));
        popu.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float y = event.getY();
                float touthY = Math.abs(y);
                int viewH = Math.abs(vh.ll_price.getHeight());
                if (touthY > viewH) {
                    popu.dismiss();
                    return true;
                }
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    popu.dismiss();
//                    return true;
//                }
                return false;
            }
        });
        popu.showAsDropDown(findViewById(R.id.ll_sort));
        /**
         * 就爱个从低到高
         */
        vh.tv_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = 2;
                page = 1;
                list.clear();
                popu.dismiss();
                getData();
            }
        });
        /**
         * 价格从高到低
         */
         vh.tv_tall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort = 3;
                page = 1;
                list.clear();
                popu.dismiss();
                getData();
            }
        });

    }


      class ViewHolder {
        public View rootView;
        public TextView tv_low;
        public TextView tv_tall;
        public LinearLayout ll_price;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_low = (TextView) rootView.findViewById(R.id.tv_low);
            this.tv_tall = (TextView) rootView.findViewById(R.id.tv_tall);
            this.ll_price = (LinearLayout) rootView.findViewById(R.id.ll_price);
        }

    }
}
