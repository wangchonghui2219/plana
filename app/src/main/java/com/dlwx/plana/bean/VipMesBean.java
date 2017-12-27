package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class VipMesBean {

    /**
     * body : {"level":[{"level_id":"1","level_name":"普通会员"},{"level_id":"2","level_name":"黄金会员"},{"level_id":"3","level_name":"铂金会员"},{"level_id":"4","level_name":"钻石会员"}],"my_level":{"level_id":"1","level_name":"普通会员"},"next_level":{"level_id":"2","level_name":"黄金会员"},"promote_show":[{"img":"http://192.168.0.191/a_plan//Uploads/59b6682a05293x250.png","name":"客服服务"}]}
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
         * level : [{"level_id":"1","level_name":"普通会员"},{"level_id":"2","level_name":"黄金会员"},{"level_id":"3","level_name":"铂金会员"},{"level_id":"4","level_name":"钻石会员"}]
         * my_level : {"level_id":"1","level_name":"普通会员"}
         * next_level : {"level_id":"2","level_name":"黄金会员"}
         * promote_show : [{"img":"http://192.168.0.191/a_plan//Uploads/59b6682a05293x250.png","name":"客服服务"}]
         */

        private MyLevelBean my_level;
        private NextLevelBean next_level;
        private List<LevelBean> level;
        private List<PromoteShowBean> promote_show;

        public MyLevelBean getMy_level() {
            return my_level;
        }

        public void setMy_level(MyLevelBean my_level) {
            this.my_level = my_level;
        }

        public NextLevelBean getNext_level() {
            return next_level;
        }

        public void setNext_level(NextLevelBean next_level) {
            this.next_level = next_level;
        }

        public List<LevelBean> getLevel() {
            return level;
        }

        public void setLevel(List<LevelBean> level) {
            this.level = level;
        }

        public List<PromoteShowBean> getPromote_show() {
            return promote_show;
        }

        public void setPromote_show(List<PromoteShowBean> promote_show) {
            this.promote_show = promote_show;
        }

        public static class MyLevelBean {
            /**
             * level_id : 1
             * level_name : 普通会员
             */

            private String level_id;
            private String level_name;

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

        public static class NextLevelBean {
            /**
             * level_id : 2
             * level_name : 黄金会员
             */

            private String level_id;
            private String level_name;
            private String next_price;

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

            public String getNext_price() {
                return next_price;
            }

            public void setNext_price(String next_price) {
                this.next_price = next_price;
            }
        }

        public static class LevelBean {
            /**
             * level_id : 1
             * level_name : 普通会员
             */

            private String level_id;
            private String level_name;

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

        public static class PromoteShowBean {
            /**
             * img : http://192.168.0.191/a_plan//Uploads/59b6682a05293x250.png
             * name : 客服服务
             */

            private String img;
            private String name;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
