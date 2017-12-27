package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/27/027.
 */

public class PayResultBean {

    /**
     * body : alipay_sdk=alipay-sdk-php-20161101&app_id=2017092582591781&biz_content=%7B%22body%22%3A%22%E4%BC%9A%E5%91%98%E8%AE%A2%E5%8D%95%22%2C%22subject%22%3A%22%E6%B7%98%E4%BC%98%E8%B5%9A%E4%BC%9A%E5%91%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%2220170927083223fuyyA%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22700%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.27.14.79%2FA_plan%2Findex.php%2FUser%2FAlipay%2Freturn_url&sign_type=RSA&timestamp=2017-09-27+08%3A32%3A25&version=1.0&sign=HdRA1ZwUpvi67%2B5JMVZIF5Q%2F5zX1zpKRrv41PCSR2YPp%2FO6a%2BNnEGIBoXSkhiSGN4%2F%2BpLnM%2BpszGgE9C3vwUOkJf%2BjoS7eSAt9udwFeYDawvMey2RCv7eYTQj%2BGgq%2BRmIemLyw85YDVBKSXuOwnw9O4bIRAhc1tcnI9lxGdqM6tnVaCXPfjI5iAPCWGPvzw4VFGXSrlZh82BpgZRuxpCxVvZwExhiJoTgDPMcImXk3jRPuIBRCkUF0ASzvMJpCI7rXjz%2BAMIThFJJuQDOzYSpJX9op78UL7JR%2FX75ouYHkId2Cm%2FUFP6qfMiasgEIuPplFREwRf%2Ftyskg5p90%2FYd4g%3D%3D
     * code : 200
     * result : 操作成功
     */

    private String body;
    private int code;
    private String result;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
