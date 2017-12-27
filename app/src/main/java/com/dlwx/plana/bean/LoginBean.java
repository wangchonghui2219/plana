package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class LoginBean {

    /**
     * code : 200
     * result : 登录成功
     * body : {"token":"da33408a19747fb2399b0ae944fe3de4","phone":"15670598218","nickname":"1222","header_pic":"http://192.168.0.191/a_plan/Uploads/aplan.jpg","user_id":"1","exten_code":"670478","level_id":"2","level_name":"黄金会员"}
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
         * token : da33408a19747fb2399b0ae944fe3de4
         * phone : 15670598218
         * nickname : 1222
         * header_pic : http://192.168.0.191/a_plan/Uploads/aplan.jpg
         * user_id : 1
         * exten_code : 670478
         * level_id : 2
         * level_name : 黄金会员
         */

        private String token;
        private String phone;
        private String nickname;
        private String header_pic;
        private String user_id;
        private String exten_code;
        private String level_id;
        private String level_name;
        private int  is_paypwd;

        public int getIs_paypwd() {
            return is_paypwd;
        }

        public void setIs_paypwd(int is_paypwd) {
            this.is_paypwd = is_paypwd;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getExten_code() {
            return exten_code;
        }

        public void setExten_code(String exten_code) {
            this.exten_code = exten_code;
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
    }
}
