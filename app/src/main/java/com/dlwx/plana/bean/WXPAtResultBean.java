package com.dlwx.plana.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/11/011.
 */

public class WXPAtResultBean {

    /**
     * body : {"appid":"wxe10e77f4bbd13789","noncestr":"TefHH3AZDjkYSE3M4onAIHvPKYPaWhpP","package":"Sign=WXPay","partnerid":"1489096732","sign":"96BA8257B617B8FC2727712C01E5B84A","timestamp":1507719063}
     * code : 200
     * result : 操作成功
     */

    private BodyBean body;
    private int code;
    private String result;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
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

    public static class BodyBean {
        /**
         * appid : wxe10e77f4bbd13789
         * noncestr : TefHH3AZDjkYSE3M4onAIHvPKYPaWhpP
         * package : Sign=WXPay
         * partnerid : 1489096732
         * sign : 96BA8257B617B8FC2727712C01E5B84A
         * timestamp : 1507719063
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String sign;
        private String timestamp;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
