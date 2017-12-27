package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/13/013.
 */

public class HelpDescBean {

    /**
     * body : {"content":"&lt;p&gt;\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t&lt;/p&gt;&lt;p&gt;1，引导买家购买过宝贝后有进行分享&lt;/p&gt;&lt;p&gt;2，分享内容较丰富真实，有购买引导性&lt;/p&gt;&lt;p&gt;3，最好是最新主题分享的话题，具体话题请进入淘分享的页面查看，如潮流流行服饰选购的话题。&lt;/p&gt;&lt;p&gt;4，有优秀分享的宝贝推荐在淘分享展示出来。&lt;/p&gt;&lt;p&gt;5，引导买家购买过宝贝后有进行分享&lt;/p&gt;&lt;p&gt;6，分享内容较丰富真实，有购买引导性&lt;/p&gt;&lt;p&gt;7，最好是最新主题分享的话题，具体话题请进入淘分享的页面查看，如潮流流行服饰选购的话题。&lt;/p&gt;&lt;p&gt;8，有优秀分享的宝贝推荐在淘分享展示出来。&lt;/p&gt;&lt;p&gt;\t\t\t\t\t\t\t\t\t\t\t\t\t\t&lt;/p&gt;","feed_id":"3","time":"2017-09-12","title":"如何使用A计划？"}
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
         * content : &lt;p&gt;															&lt;/p&gt;&lt;p&gt;1，引导买家购买过宝贝后有进行分享&lt;/p&gt;&lt;p&gt;2，分享内容较丰富真实，有购买引导性&lt;/p&gt;&lt;p&gt;3，最好是最新主题分享的话题，具体话题请进入淘分享的页面查看，如潮流流行服饰选购的话题。&lt;/p&gt;&lt;p&gt;4，有优秀分享的宝贝推荐在淘分享展示出来。&lt;/p&gt;&lt;p&gt;5，引导买家购买过宝贝后有进行分享&lt;/p&gt;&lt;p&gt;6，分享内容较丰富真实，有购买引导性&lt;/p&gt;&lt;p&gt;7，最好是最新主题分享的话题，具体话题请进入淘分享的页面查看，如潮流流行服饰选购的话题。&lt;/p&gt;&lt;p&gt;8，有优秀分享的宝贝推荐在淘分享展示出来。&lt;/p&gt;&lt;p&gt;														&lt;/p&gt;
         * feed_id : 3
         * time : 2017-09-12
         * title : 如何使用A计划？
         */

        private String content;
        private String feed_id;
        private String time;
        private String title;

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
