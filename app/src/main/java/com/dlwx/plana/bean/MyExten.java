package com.dlwx.plana.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16/016.
 */

public class MyExten {

    /**
     * code : 200
     * result : 获取成功
     * body : {"nickname":"186****1978","count":"0","ticket":[{"ticket_id":"1","exten_code":"161043","ticket":"600"}]}
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
         * nickname : 186****1978
         * count : 0
         * ticket : [{"ticket_id":"1","exten_code":"161043","ticket":"600"}]
         */

        private String nickname;
        private String count;
        private List<TicketBean> ticket;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<TicketBean> getTicket() {
            return ticket;
        }

        public void setTicket(List<TicketBean> ticket) {
            this.ticket = ticket;
        }

        public static class TicketBean {
            /**
             * ticket_id : 1
             * exten_code : 161043
             * ticket : 600
             */

            private String ticket_id;
            private String exten_code;
            private String ticket;

            public String getTicket_id() {
                return ticket_id;
            }

            public void setTicket_id(String ticket_id) {
                this.ticket_id = ticket_id;
            }

            public String getExten_code() {
                return exten_code;
            }

            public void setExten_code(String exten_code) {
                this.exten_code = exten_code;
            }

            public String getTicket() {
                return ticket;
            }

            public void setTicket(String ticket) {
                this.ticket = ticket;
            }
        }
    }
}
