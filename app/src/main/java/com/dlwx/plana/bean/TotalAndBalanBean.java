package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/14/014.
 */

public class TotalAndBalanBean {

    /**
     * body : {"balance":"0","bkge":"0","next_month":"0","pre_balance":"0","pre_month":"0","total_assets":"0"}
     * code : 200
     * result : 获取成功
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
         * balance : 0
         * bkge : 0
         * next_month : 0
         * pre_balance : 0
         * pre_month : 0
         * total_assets : 0
         */

        private String balance;
        private String bkge;
        private String next_month;
        private String pre_balance;
        private String pre_month;
        private String total_assets;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBkge() {
            return bkge;
        }

        public void setBkge(String bkge) {
            this.bkge = bkge;
        }

        public String getNext_month() {
            return next_month;
        }

        public void setNext_month(String next_month) {
            this.next_month = next_month;
        }

        public String getPre_balance() {
            return pre_balance;
        }

        public void setPre_balance(String pre_balance) {
            this.pre_balance = pre_balance;
        }

        public String getPre_month() {
            return pre_month;
        }

        public void setPre_month(String pre_month) {
            this.pre_month = pre_month;
        }

        public String getTotal_assets() {
            return total_assets;
        }

        public void setTotal_assets(String total_assets) {
            this.total_assets = total_assets;
        }
    }
}
