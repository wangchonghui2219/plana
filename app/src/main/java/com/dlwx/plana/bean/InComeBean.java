package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14/014.
 */

public class InComeBean {

    /**
     * code : 200
     * result : 获取成功
     * body : [{"income_id":"4","price":138,"detail":"购买商品","create_time":"2017-09-14 14:20:51"},{"income_id":"5","price":138,"detail":"购买商品","create_time":"2017-09-14 14:20:51"},{"income_id":"2","price":53.58,"detail":"商品收益2","create_time":"2017-09-14 11:30:02"},{"income_id":"3","price":138,"detail":"购买商品","create_time":"2017-09-12 14:20:51"},{"income_id":"1","price":60.12,"detail":"dddffff","create_time":"2017-09-01 11:28:44"}]
     */

    private int code;
    private String result;
    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * income_id : 4
         * price : 138
         * detail : 购买商品
         * create_time : 2017-09-14 14:20:51
         */

        private String income_id;
        private double price;
        private String detail;
        private String create_time;

        public String getIncome_id() {
            return income_id;
        }

        public void setIncome_id(String income_id) {
            this.income_id = income_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
