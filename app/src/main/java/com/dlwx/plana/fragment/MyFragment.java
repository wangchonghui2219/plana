package com.dlwx.plana.fragment;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.baselib.base.BaseFragment;
import com.dlwx.baselib.presenter.Presenter;
import com.dlwx.baselib.utiles.SpUtiles;
import com.dlwx.baselib.utiles.UpVersionUtiles;
import com.dlwx.baselib.view.CircleImageView;
import com.dlwx.plana.R;
import com.dlwx.plana.activity.BalanceActivity;
import com.dlwx.plana.activity.HelpCenterActivity;
import com.dlwx.plana.activity.LoginActivity;
import com.dlwx.plana.activity.MyGeneraActivity;
import com.dlwx.plana.activity.PerCenActivity;
import com.dlwx.plana.activity.SettActivity;
import com.dlwx.plana.activity.TotalMoneyActivity;
import com.dlwx.plana.activity.UpVipAvtivity;
import com.dlwx.plana.activity.WebActivity;
import com.dlwx.plana.base.MyApplication;
import com.dlwx.plana.bean.PerMessBean;
import com.dlwx.plana.bean.ServiceBean;
import com.dlwx.plana.bean.VersionBean;
import com.dlwx.plana.utiles.HttpUtiles;
import com.google.gson.Gson;
import com.lzy.okgo.db.CacheManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.ll_mygeneralize)
    LinearLayout llMygeneralize;
    @BindView(R.id.ll_vipup)
    LinearLayout llVipup;
    @BindView(R.id.tv_totalnum)
    TextView tvTotalnum;
    @BindView(R.id.ll_totalmoney)
    LinearLayout llTotalmoney;
    @BindView(R.id.tv_balancenum)
    TextView tvBalancenum;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.ll_school)
    LinearLayout llSchool;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_versionup)
    LinearLayout llVersionup;
    @BindView(R.id.ll_clean)
    LinearLayout llClean;
    @BindView(R.id.ll_sett)
    LinearLayout llSett;
    @BindView(R.id.ll_helpcenter)
    LinearLayout llHelpcenter;
    @BindView(R.id.ll_vip)
    LinearLayout ll_vip;
    @BindView(R.id.tv_levelname)
    TextView tv_levelname;
    Unbinder unbinder;
    private String levelName;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(MyApplication.User_Token)) {
            String head_pic = sp.getString(SpUtiles.Head_pic, "");
            String nickName = sp.getString(SpUtiles.NickName, "");
            String terrace = sp.getString(SpUtiles.terrace, "");
            levelName = sp.getString(SpUtiles.VipName, "");
            Glide.with(ctx).load(head_pic).into(ivHead);
            tvAccount.setText(nickName);
            tvNickName.setText("邀请码："+terrace);
            ll_vip.setVisibility(View.VISIBLE);
            tv_levelname.setText(levelName);
            getPerMess();
        }
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListener() {

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

    @OnClick({R.id.ll_head, R.id.ll_mygeneralize, R.id.ll_vipup, R.id.ll_totalmoney,
            R.id.ll_balance, R.id.ll_school, R.id.ll_service, R.id.ll_versionup,
            R.id.ll_clean, R.id.ll_sett, R.id.ll_helpcenter})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.ll_head:          //头像
                if (TextUtils.isEmpty(MyApplication.User_Token)) {

                    startActivity(new Intent(ctx, LoginActivity.class));
                } else {
                    startActivity(new Intent(ctx, PerCenActivity.class));
                }
                break;
            case R.id.ll_mygeneralize:  //我的推广
                if (!"普通会员".equals(levelName)) {

                    startActivity(new Intent(ctx, MyGeneraActivity.class));
                }else{
                    Toast.makeText(ctx, "请升级会员来邀请好友", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_vipup:         //会员升级
                startActivity(new Intent(ctx, UpVipAvtivity.class));

                break;
            case R.id.ll_totalmoney:    //总资产
                startActivity(new Intent(ctx, TotalMoneyActivity.class));
                break;
            case R.id.ll_balance:       //可用余额
                startActivity(new Intent(ctx, BalanceActivity.class));
                break;
            case R.id.ll_school:        //内部学堂
                Intent intent = new Intent(ctx, WebActivity.class);
                intent.putExtra("feed_id", "").putExtra("title", "内部学堂");
                intent.putExtra("url", HttpUtiles.School);
                startActivity(intent);
                break;
            case R.id.ll_service:       //客服
                getService();

                break;
            case R.id.ll_versionup:     //版本升级
                Version();
                break;
            case R.id.ll_clean:         //清理缓存
                clearCache();
                break;
            case R.id.ll_sett:          //设置
                startActivity(new Intent(ctx, SettActivity.class));
                break;
            case R.id.ll_helpcenter:    //帮助中心
                startActivity(new Intent(ctx, HelpCenterActivity.class));
                break;
        }
    }
    private int HttpType;
    private int versionCode;
    private void Version() {
        HttpType = 2;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(ctx.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            Map<String,String> map = new HashMap<>();
            map.put("version_no", versionCode +"");


            mPreenter.fetch(map,true,HttpUtiles.upVersion,"upVersion");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearCache() {
        new AlertDialog.Builder(ctx)
                .setMessage("将删除所有的图片和数据，删除后不可恢复")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        boolean clear = CacheManager.getInstance().clear();
                        if (clear) {
                            Toast.makeText(ctx, "清除缓存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ctx, "当前没有缓存信息", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消",null)
                .show();

    }
    private void getService() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,true,HttpUtiles.Service,"Service");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {

            ServiceBean serviceBean = gson.fromJson(s, ServiceBean.class);
            if (serviceBean.getCode() == 200) {
                ServiceBean.BodyBean body = serviceBean.getBody();
                showPopu(body.getName(),body.getQq());
            }
        }else if(HttpType == 0){
            PerMessBean perMessBean = gson.fromJson(s, PerMessBean.class);
            if (perMessBean.getCode() == 200) {
                PerMessBean.BodyBean body = perMessBean.getBody();
                Glide.with(ctx).load(body.getHeader_pic()).into(ivHead);
                tvTotalnum.setText(body.getTotal_assets());
                tvBalancenum.setText(body.getBalance());
            }else{
                Toast.makeText(ctx, perMessBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            VersionBean version = gson.fromJson(s, VersionBean.class);
            if (version.getCode() == 200) {
                VersionBean.BodyBean body = version.getBody();
                int new_num = body.getNew_num();

                if (new_num > versionCode) {
                    UpVersionUtiles versionUtiles = new UpVersionUtiles(ctx);
                    versionUtiles.showVersionDia(body.getDownurl());
                }else {
                    Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 显示客服
     */
    private void showPopu(String name, final String qq) {
        final WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
        final View view = LayoutInflater.from(ctx).inflate(R.layout.popu_item, null);
        ViewHolder vh = new ViewHolder(view);
        final PopupWindow popu = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popu.setTouchable(true);
        popu.setOutsideTouchable(true);
        popu.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popu.showAtLocation(ivHead, Gravity.CENTER,0,0);
        vh.tv_name.setText("客服经理："+name);
        vh.tv_qq.setText("QQ："+qq);
        vh.tv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 得到剪贴板管理器
                ClipboardManager cmb = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(qq);
                Toast.makeText(ctx, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        popu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);


    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_qq;
        public TextView tv_copy;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_qq = (TextView) rootView.findViewById(R.id.tv_qq);
            this.tv_copy = (TextView) rootView.findViewById(R.id.tv_copy);
        }

    }

    private void getPerMess() {
        HttpType = 0;
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.PerMess,"permess"+MyApplication.User_Token);
    }
}
