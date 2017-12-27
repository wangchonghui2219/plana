package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class ResultBean {


    /**
     * code : 204
     * result : 等级不够，您无法执行提现
     * body : {"light":1}
     */

    private int code;
    private String result;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * light : 1
         */

        private int light;

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }
    }
}
