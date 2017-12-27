package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/15/015.
 */

public class StatemBean {

    /**
     * body : {"balance":"1042.67","day_active":"1500","now_month":"582.67","pre_month":"460","today":"0","user_num":"2000","yestday":"319.3"}
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
         * balance : 1042.67
         * day_active : 1500
         * now_month : 582.67
         * pre_month : 460
         * today : 0
         * user_num : 2000
         * yestday : 319.3
         */

        private String balance;
        private String day_active;
        private String now_month;
        private String pre_month;
        private String today;
        private String user_num;
        private String yestday;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getDay_active() {
            return day_active;
        }

        public void setDay_active(String day_active) {
            this.day_active = day_active;
        }

        public String getNow_month() {
            return now_month;
        }

        public void setNow_month(String now_month) {
            this.now_month = now_month;
        }

        public String getPre_month() {
            return pre_month;
        }

        public void setPre_month(String pre_month) {
            this.pre_month = pre_month;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public String getUser_num() {
            return user_num;
        }

        public void setUser_num(String user_num) {
            this.user_num = user_num;
        }

        public String getYestday() {
            return yestday;
        }

        public void setYestday(String yestday) {
            this.yestday = yestday;
        }
    }
}
