package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class MyGenerListBean {

    /**
     * code : 200
     * result : 获取成功
     * body : [{"nickname":"fxt","phone":"159****8071","level_id":"3","header_pic":"http://192.168.0.191/a_plan//Uploads/150521123921.png","level_name":"铂金会员"},{"nickname":"186****1978","phone":"186****1978","level_id":"4","header_pic":"http://192.168.0.191/a_plan//Uploads/59b7580c7b864.png","level_name":"钻石会员"},{"nickname":"156****8216","phone":"156****8216","level_id":"2","header_pic":"http://192.168.0.191/a_plan/Uploads/aplan.jpg","level_name":"黄金会员"}]
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
         * nickname : fxt
         * phone : 159****8071
         * level_id : 3
         * header_pic : http://192.168.0.191/a_plan//Uploads/150521123921.png
         * level_name : 铂金会员
         */

        private String nickname;
        private String phone;
        private String level_id;
        private String header_pic;
        private String level_name;

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

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }
    }
}
