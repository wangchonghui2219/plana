package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class PerMessBean {

    /**
     * body : {"balance":"0","exten_code":"161043","header_pic":"http://120.27.14.79/a_plan//Uploads/59c619631f6b5.png","is_paypwd":"1","level_id":"1","level_name":"普通会员","nickname":"哈哈哈哈","phone":"186****978","token":"4cb7295f39e3f4f05881b670c2fec018","total_assets":"0","user_id":"4"}
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
         * exten_code : 161043
         * header_pic : http://120.27.14.79/a_plan//Uploads/59c619631f6b5.png
         * is_paypwd : 1
         * level_id : 1
         * level_name : 普通会员
         * nickname : 哈哈哈哈
         * phone : 186****978
         * token : 4cb7295f39e3f4f05881b670c2fec018
         * total_assets : 0
         * user_id : 4
         */

        private String balance;
        private String exten_code;
        private String header_pic;
        private String is_paypwd;
        private String level_id;
        private String level_name;
        private String nickname;
        private String phone;
        private String token;
        private String total_assets;
        private String user_id;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getExten_code() {
            return exten_code;
        }

        public void setExten_code(String exten_code) {
            this.exten_code = exten_code;
        }

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }

        public String getIs_paypwd() {
            return is_paypwd;
        }

        public void setIs_paypwd(String is_paypwd) {
            this.is_paypwd = is_paypwd;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTotal_assets() {
            return total_assets;
        }

        public void setTotal_assets(String total_assets) {
            this.total_assets = total_assets;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
