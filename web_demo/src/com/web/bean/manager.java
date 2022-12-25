package com.web.bean;

public class manager {
    private String m_id;
    private String m_name;
    private String m_password;
    private String m_img;
    private String m_time;
    private String role;

    public manager(String m_id, String m_name, String m_password, String m_img, String m_time, String role) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_password = m_password;
        this.m_img = m_img;
        this.m_time = m_time;
        this.role = role;
    }

    public manager(String m_id, String m_name, String m_password, String m_img, String role) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_password = m_password;
        this.m_img = m_img;
        this.role = role;
    }

    public manager(String m_id, String m_name, String m_password) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_password = m_password;
    }

    public manager(String m_id) {
        this.m_id = m_id;
    }


    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_password() {
        return m_password;
    }

    public void setM_password(String m_password) {
        this.m_password = m_password;
    }

    public String getM_img() {
        return m_img;
    }

    public void setM_img(String m_img) {
        this.m_img = m_img;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "manager{" +
                "m_id='" + m_id + '\'' +
                ", m_name='" + m_name + '\'' +
                ", m_password='" + m_password + '\'' +
                ", m_img='" + m_img + '\'' +
                ", m_time='" + m_time + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
