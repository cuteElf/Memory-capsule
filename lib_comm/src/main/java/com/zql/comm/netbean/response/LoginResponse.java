package com.zql.comm.netbean.response;

import java.io.Serializable;


public class LoginResponse implements Serializable {

    public static final int LOGIN_SUCCESS = 203;

    public static final int LOGIN_ERROR_NP = 208;

    public static final int LOGUP_REPET = 201;

    public static final int LOGUP_SUCCESS = 0;


    /**
     * usertitle : 我好穷啊
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyLCJvcmlnX2lhdCI6MTU4MDI5NjAwNCwiZXhwIjoxNTgwMzgyNDA0LCJ1c2VybmFtZSI6InpxbHN3YSJ9.A99E9vKQ5_epQ32FPCmlFiLAN24mo1oezwaWVYkux1w
     * code : 203
     * msg : login_success
     * userimage : xxxx
     * sex : 男
     * nickname : 尽途
     */

    private String usertitle;
    private String token;
    private int code;
    private String msg;
    private String sex;
    private String nickname;
    private String userimage;

    public String getUsertitle() {
        return usertitle;
    }

    public void setUsertitle(String usertitle) {
        this.usertitle = usertitle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
}
