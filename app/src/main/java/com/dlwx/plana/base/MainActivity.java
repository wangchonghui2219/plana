package com.dlwx.plana.base;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlwx.baselib.base.BaseActivity;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.view.BottomNavigationViewHelper;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.LoginActivity;
import com.dlwx.plana.activity.SeachActivity;
import com.dlwx.plana.adapter.HomePageAdapter;
import com.dlwx.plana.fragment.HomeFragment;
import com.dlwx.plana.fragment.MessageFragment;
import com.dlwx.plana.fragment.MyFragment;
import com.dlwx.plana.fragment.StatementFragment;
import com.dlwx.plana.wxapi.QQUtiles;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.baselib.utiles.UploadPicUtiles.GET_Permission_Albm_code;
import static com.dlwx.plana.base.MyApplication.User_Token;

/**
 * 首页
 */
public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_viewpage)
    ViewPager vpViewpage;
    @BindView(R.id.bottom_navigation_container)
    BottomNavigationView bottomNavigationContainer;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_seach)
    LinearLayout ll_seach;
    @BindView(R.id.tv_seach)
    TextView tvSeach;
    List<Fragment> fragments;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new StatementFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MyFragment());
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationContainer);
        bottomNavigationContainer.setOnNavigationItemSelectedListener(this);
        bottomNavigationContainer.getMenu().getItem(0).setCheckable(true);
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager(), fragments);
        vpViewpage.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ctx, new String[]{
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE},
                    1000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(User_Token)) {
            view.setVisibility(View.VISIBLE);
            ll_seach.setVisibility(View.VISIBLE);
            vpViewpage.setCurrentItem(0);
            bottomNavigationContainer.getMenu().getItem(0).setChecked(true);
        } else {
            if (indext == 0) {

                view.setVisibility(View.VISIBLE);
                ll_seach.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
                ll_seach.setVisibility(View.GONE);
            }
            vpViewpage.setCurrentItem(indext);
        }
        if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ctx, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    GET_Permission_Albm_code);
        }
    }

    private int indext = 0;

    @Override
    protected void initListener() {
        vpViewpage.setOnPageChangeListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home://首页
                indext = 0;
                view.setVisibility(View.VISIBLE);
                ll_seach.setVisibility(View.VISIBLE);
                vpViewpage.setCurrentItem(0);
                break;
            case R.id.item_release://报表
                indext = 1;
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));

                } else {

                    view.setVisibility(View.GONE);
                    ll_seach.setVisibility(View.GONE);
                    vpViewpage.setCurrentItem(1);
                }
                break;
            case R.id.item_message://消息
                indext = 2;
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));

                } else {

                    view.setVisibility(View.GONE);
                    ll_seach.setVisibility(View.GONE);
                    vpViewpage.setCurrentItem(2);
                }
                break;
            case R.id.item_my://个人中心
                indext = 3;
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));

                } else {

                    view.setVisibility(View.GONE);
                    ll_seach.setVisibility(View.GONE);
                    vpViewpage.setCurrentItem(3);


                }
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationContainer.getMenu().getItem(position).setChecked(true);
        if (position == 0) {
            view.setVisibility(View.VISIBLE);
            ll_seach.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
            ll_seach.setVisibility(View.GONE);
            if (TextUtils.isEmpty(User_Token)) {
                startActivity(new Intent(ctx, LoginActivity.class));

            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQUtiles().uiListener);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String permission = permissions[0];
        if (requestCode == GET_Permission_Albm_code) {
            if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            }
        }
    }


    @OnClick(R.id.tv_seach)
    public void onViewClicked() {
        startActivity(new Intent(ctx,SeachActivity.class));
    }
}
