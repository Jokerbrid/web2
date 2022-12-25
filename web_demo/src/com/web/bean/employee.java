package com.web.bean;

public class employee {
    private  String e_id;
    private String e_name;
    private String e_password;
    private String  e_card;
    private String e_sex;
    private String e_img;
    private int e_position;
    private int e_department;
    private String e_phone;
    private String e_time;
    private String position_name;
    private String department_name;
    public employee() {
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }



    public employee(String e_id, String e_name, String e_password, String e_card, String e_sex, String e_img, int e_position, int e_department, String e_phone) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_password = e_password;
        this.e_card = e_card;
        this.e_sex = e_sex;
        this.e_img = e_img;
        this.e_position = e_position;
        this.e_department = e_department;
        this.e_phone = e_phone;
    }

    public employee(String e_id, String e_name, String e_password, String e_card, String e_sex, String e_img, int e_position, int e_department, String e_phone, String e_time) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_password = e_password;
        this.e_card = e_card;
        this.e_sex = e_sex;
        this.e_img = e_img;
        this.e_position = e_position;
        this.e_department = e_department;
        this.e_phone = e_phone;
        this.e_time = e_time;
    }

    public employee(String e_id, String e_name, String e_password) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_password = e_password;
    }

    public employee(String e_id, String e_name, String e_password, String e_card, String e_sex, int e_position, int e_department, String e_phone) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_password = e_password;
        this.e_card = e_card;
        this.e_sex = e_sex;
        this.e_position = e_position;
        this.e_department = e_department;
        this.e_phone = e_phone;
    }

    @Override
    public String toString() {
        return "employee{" +
                "e_id='" + e_id + '\'' +
                ", e_name='" + e_name + '\'' +
                ", e_password='" + e_password + '\'' +
                ", e_card='" + e_card + '\'' +
                ", e_sex='" + e_sex + '\'' +
                ", e_img='" + e_img + '\'' +
                ", e_position=" + e_position +
                ", e_department=" + e_department +
                ", e_phone='" + e_phone + '\'' +
                ", e_time='" + e_time + '\'' +
                '}';
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_password() {
        return e_password;
    }

    public void setE_password(String e_password) {
        this.e_password = e_password;
    }

    public String getE_card() {
        return e_card;
    }

    public void setE_card(String e_card) {
        this.e_card = e_card;
    }

    public String getE_sex() {
        return e_sex;
    }

    public void setE_sex(String e_sex) {
        this.e_sex = e_sex;
    }

    public String getE_img() {
        return e_img;
    }

    public void setE_img(String e_img) {
        this.e_img = e_img;
    }

    public int getE_position() {
        return e_position;
    }

    public void setE_position(int e_position) {
        this.e_position = e_position;
    }

    public int getE_department() {
        return e_department;
    }

    public void setE_department(int e_department) {
        this.e_department = e_department;
    }

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }
}
