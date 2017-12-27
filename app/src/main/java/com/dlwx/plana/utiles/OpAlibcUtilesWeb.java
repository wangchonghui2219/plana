package com.dlwx.plana.utiles;

import android.app.Activity;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.dlwx.baselib.utiles.LogUtiles;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class OpAlibcUtilesWeb {

    public static void getAliBc(Activity activity, String url) {
        LogUtiles.LogI( url);
        AlibcPage page = new AlibcPage(url);
        //阿里百川打开web方式，有 淘宝客户端，H5,等等
        AlibcShowParams showParams = new AlibcShowParams(OpenType.H5, false);
        //这里是淘客参数，具体是啥我也不知道，感觉官网确实挺坑的，注释什么的都太少了
        AlibcTaokeParams taokeParams = new AlibcTaokeParams();

        int id = AlibcTrade.show(activity, page, showParams, taokeParams, null, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
                LogUtiles.LogI("百川拦截操作成功信息回调：" + alibcTradeResult.toString() + "--");
            }



            @Override
            public void onFailure(int code, String msg) {
                //打开电商组件，用户操作错误信息回调。code：错误码；msg：错误信息
                LogUtiles.LogI("错误码：" + code + "错误信息：" + msg);

            }

            @Override
            protected void finalize() throws Throwable {
                LogUtiles.LogI("dasdas");
                super.finalize();
            }
        });
    }

}
