package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class HomeAllData {

    /**
     * code : 200
     * result : 获取成功
     * body : {"banner":[{"banner_id":1,"url":"http://192.168.0.191/a_plan/jia/banner/1.jpg"},{"banner_id":2,"url":"http://192.168.0.191/a_plan/jia/banner/2.jpg"},{"banner_id":3,"url":"http://192.168.0.191/a_plan/jia/banner/3.jpg"},{"banner_id":4,"url":"http://192.168.0.191/a_plan/jia/banner/4.jpg"},{"banner_id":5,"url":"http://192.168.0.191/a_plan/jia/banner/5.jpg"}],"classify":[{"type":1,"url":"http://192.168.0.191/a_plan/jia/classify/1.png"},{"type":2,"url":"http://192.168.0.191/a_plan/jia/classify/2.png"},{"type":1,"url":"http://192.168.0.191/a_plan/jia/classify/3.png"}],"list":[{"good_id":1,"url":"http://192.168.0.191/a_plan/jia/list/1.jpg"},{"good_id":2,"url":"http://192.168.0.191/a_plan/jia/list/2.jpg"},{"good_id":3,"url":"http://192.168.0.191/a_plan/jia/list/3.jpg"},{"good_id":4,"url":"http://192.168.0.191/a_plan/jia/list/4.jpg"},{"good_id":5,"url":"http://192.168.0.191/a_plan/jia/list/5.jpg"},{"good_id":6,"url":"http://192.168.0.191/a_plan/jia/list/6.jpg"},{"good_id":7,"url":"http://192.168.0.191/a_plan/jia/list/7.jpg"},{"good_id":8,"url":"http://192.168.0.191/a_plan/jia/list/8.jpg"},{"good_id":9,"url":"http://192.168.0.191/a_plan/jia/list/9.jpg"},{"good_id":10,"url":"http://192.168.0.191/a_plan/jia/list/10.jpg"},{"good_id":11,"url":"http://192.168.0.191/a_plan/jia/list/11.jpg"},{"good_id":12,"url":"http://192.168.0.191/a_plan/jia/list/12.jpg"},{"good_id":13,"url":"http://192.168.0.191/a_plan/jia/list/13.jpg"},{"good_id":14,"url":"http://192.168.0.191/a_plan/jia/list/14.jpg"}]}
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
        private List<BannerBean> banner;
        private List<ClassifyBean> classify;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ClassifyBean> getClassify() {
            return classify;
        }

        public void setClassify(List<ClassifyBean> classify) {
            this.classify = classify;
        }



        public static class BannerBean {
            /**
             * banner_id : 1
             * url : http://192.168.0.191/a_plan/jia/banner/1.jpg
             */

            private int banner_id;
            private String url;
            private String tao_url;
            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTao_url() {
                return tao_url;
            }

            public void setTao_url(String tao_url) {
                this.tao_url = tao_url;
            }
        }

        public static class ClassifyBean {
            /**
             * type : 1
             * url : http://192.168.0.191/a_plan/jia/classify/1.png
             */

            private int type;
            private String url;
            private String  title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }


    }
}
