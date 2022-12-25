package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataSources {
    static String url = "jdbc:mysql://localhost:3306/web?serverTimezone=UTC";
    static String user = "root";
    static String password = "dx1234love";
    static {
        // 使用反射来加载jdbc的驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 一个获取链接的静态方法
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 一个关闭链接的静态方法
    public static void closeConnection(Connection con) {
        // 非空校验
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
