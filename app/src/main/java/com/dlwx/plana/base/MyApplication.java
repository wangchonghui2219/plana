package com.dlwx.plana.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.dlwx.baselib.utiles.LogUtiles;
import com.dlwx.baselib.utiles.SpUtiles;
import com.lzy.okgo.OkGo;
import com.tencent.tauth.Tencent;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class MyApplication extends Application {
    public static String User_Token;
    public static String Head_Pic;
    public static Tencent mTencent;
    public static String NickName;
    public static String Exten_Code;
    public static Bitmap bitmap;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        SharedPreferences sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
       User_Token = sp.getString(SpUtiles.User_Token,"");
        Head_Pic = sp.getString(SpUtiles.Head_pic,"");
        NickName = sp.getString(SpUtiles.NickName,"");
        mTencent = Tencent.createInstance("101424703",this);




//        AlibcTradeCommon.turnOnDebug();
//        AlibcTradeBiz.turnOnDebug();
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数
                //是否走强制H5的逻辑，为true时全部页面均为H5打开
                // ...
                LogUtiles.LogI("初始化成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                LogUtiles.LogI(code+msg);


            }
        });

    }
}
