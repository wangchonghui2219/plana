package com.dlwx.plana.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dlwx.baselib.base.BaseRecrviewAdapter;
import com.dlwx.baselib.view.MyRecyclerView;
import com.dlwx.plana.R;
import com.dlwx.plana.bean.ProductListbean;
import com.dlwx.plana.utiles.OpAlibcUtilesWeb;
import com.dlwx.plana.views.CustomLinearLayoutManager;

import java.util.List;

/**
 * Created by Administrator on 2017/10/28/028.
 */

public class PageAdapter extends PagerAdapter {
    private Context ctx;
    private List<ProductListbean.BodyBean.ListBean> list;
    private ProductListAdapter listAdapter;
    private int type = 0;
    public PageAdapter(Context ctx, List<ProductListbean.BodyBean.ListBean> lis) {
        this.ctx = ctx;
        this.list = lis;
        listAdapter = new ProductListAdapter(ctx, list);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.itme_page, null);
        ViewHolder vh = new ViewHolder(view);
        container.addView(view);

        if (type == 0) {
            CustomLinearLayoutManager manager = new CustomLinearLayoutManager(ctx);
            manager.setScrollEnabled(true);
            vh.lv_list.setLayoutManager(manager);
            vh.lv_list.setAdapter(listAdapter);
        }else{
            vh.lv_list.setHasFixedSize(true);
            listAdapter.notifyDataSetChanged();
        }
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
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private  class ViewHolder {
        public View rootView;
        public MyRecyclerView lv_list;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.lv_list = (MyRecyclerView) rootView.findViewById(R.id.lv_list);
        }

    }
    public void setChange(List<ProductListbean.BodyBean.ListBean> lis){
        type = 1;
        this.list = lis;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
