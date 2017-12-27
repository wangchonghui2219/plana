package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13/013.
 */

public class HelpCenterBean {

    /**
     * body : [{"feed_id":"3","time":"2017-09-12","title":"如何使用A计划？"},{"feed_id":"2","time":"2017-09-12","title":"什么是A计划？"}]
     * code : 200
     * result : 获取成功
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
         * feed_id : 3
         * time : 2017-09-12
         * title : 如何使用A计划？
         */

        private String feed_id;
        private String time;
        private String title;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFeed_id() {
            return feed_id;
        }

        public void setFeed_id(String feed_id) {
            this.feed_id = feed_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
