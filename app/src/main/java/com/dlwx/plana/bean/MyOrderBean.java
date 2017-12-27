package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/14/014.
 */

public class MyOrderBean {

    /**
     * code : 200
     * result : 订单提交成功
     * body : {"order_no":"201709131029140vxHT","price":"3.00"}
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
         * order_no : 201709131029140vxHT
         * price : 3.00
         */

        private String order_no;
        private String price;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
