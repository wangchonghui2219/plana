package com.dlwx.baselib.utiles;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dlwx.baselib.model.ResultBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

/**
 * @作者 wch
 * @create at 2017/4/7/007.
 * @name
 */
public class VerificationCodeUtiles {
    private Context ctx;
    private String telepho;
    private int isregister;
    private CountDownUtiles utiles;
    public  VerificationCodeUtiles(Context ctx, String telepho, int isregister, CountDownUtiles utiles) {
        super();
        this.ctx = ctx;
        this.telepho = telepho;
        this.isregister = isregister;
        this.utiles = utiles;
    }

    public void sendVerificationCode(String url, final MyProgressLoading loading){
        OkGo.<String>get(url)
                .params("phone",telepho)
                .params("send_type",isregister)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Log.i("wch",response.body());
                        loading.dismiss();
                        Gson gson = new Gson();
                        ResultBean baseResult = gson.fromJson(response.body(), ResultBean.class);
                        if (baseResult.getCode() == 200) {
                            utiles.startCountDown();
                        }
                        Toast.makeText(ctx, baseResult.getResult(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        loading.dismiss();
                        super.onError(response);
                    }
                });

        }


}
