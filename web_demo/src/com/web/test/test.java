package com.web.test;

import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        System.out.println("获取到的数据库链接为：" + DataSources.getConnection());
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select * from manager where m_id=? and m_password=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = connection.prepareStatement(sql);
        // 给sql语句赋值
        String user="10002",password="123456";
        ps.setString(1,user);
        ps.setString(2,password);
        // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
        ResultSet result = ps.executeQuery();//执行查询
        // 5、得到结果
        if(result.next()){//判断结果集中是否有下一条记录
            System.out.println("用户名："+result.getString("m_name"));
        }else{
            System.out.println("查无此人");
        }
    }
}
