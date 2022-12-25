package com.web.bean;

public class position {
    private int p_id;
    private String p_name;
    private int d_id;
    private String p_describe;
    private  String department_name;

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public position(int p_id, String p_name, int d_id, String p_describe) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.d_id = d_id;
        this.p_describe = p_describe;
    }


    public position() {
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getP_describe() {
        return p_describe;
    }

    public void setP_describe(String p_describe) {
        this.p_describe = p_describe;
    }
}
