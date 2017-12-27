package com.dlwx.plana.bean;

/**
 * Created by Administrator on 2017/9/12/012.
 */

public class HeadBean {

    /**
     * code : 200
     * result : 操作成功
     * body : {"header_pic":"http://192.168.0.191/a_plan//Uploads/59b7508fe3dd6.png"}
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
         * header_pic : http://192.168.0.191/a_plan//Uploads/59b7508fe3dd6.png
         */

        private String header_pic;

        public String getHeader_pic() {
            return header_pic;
        }

        public void setHeader_pic(String header_pic) {
            this.header_pic = header_pic;
        }
    }
}
