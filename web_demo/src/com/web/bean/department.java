package com.web.bean;

public class department {
    private int d_id;//编号
    private String d_name;//名字
    private String d_describe;//部门描述

    public department() {
    }

    public department(int d_id, String d_name, String d_describe) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_describe = d_describe;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_describe() {
        return d_describe;
    }

    public void setD_describe(String d_describe) {
        this.d_describe = d_describe;
    }

    @Override
    public String toString() {
        return "Department_all{" +
                "d_id='" + d_id + '\'' +
                ", d_name='" + d_name + '\'' +
                ", d_describe='" + d_describe + '\'' +
                '}';
    }

}
