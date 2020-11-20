package com.example.diffutils;


public class UserBean {
    private int id;
    private String tit;
    private String msg;

    public UserBean(int id, String tit, String msg) {
        this.id = id;
        this.tit = tit;
        this.msg = msg;
    }

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
