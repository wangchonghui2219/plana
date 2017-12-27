package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class MessBean {

    /**
     * code : 200
     * result : 获取成功
     * body : [{"type":1,"title":"系统消息","is_read":7,"last":"ddd"},{"type":2,"title":"内部消息","is_read":0,"last":"无内部消息"},{"type":3,"title":"消息","is_read":0,"last":"无内部消息"}]
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
         * type : 1
         * title : 系统消息
         * is_read : 7
         * last : ddd
         */

        private int type;
        private String title;
        private int is_read;
        private String last;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }
}
