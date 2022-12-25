package com.web.Dao;

import com.web.bean.manager;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class managerDao{
    Connection connection = DataSources.getConnection();
    public boolean queryManagerLogin(String username,String password){
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select * from manager where m_id=? and m_password like ?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
            try {
                ps = connection.prepareStatement(sql);
                // 给sql语句赋值
                ps.setString(1, username);
                ps.setString(2, password);
                // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
                ResultSet result = ps.executeQuery();//执行查询
                // 5、得到结果
                if (result.next()) {//判断结果集中是否有下一条记录
                    System.out.println("用户名：" + result.getString("m_name"));
                    return true;
                } else {
                    System.out.println("查无此人");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return false;
    }

    //注册：
    public boolean insert(manager m) {
        String sql = "insert into manager(m_id,m_name,m_password,m_time) values(?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
            try {
                ps = connection.prepareStatement(sql);
                // 给sql语句赋值
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdf.format(new Date());
                ps.setString(1,m.getM_id());
                ps.setString(2,m.getM_name());
                ps.setString(3,m.getM_password());
                ps.setString(4, time);
                int result = ps.executeUpdate();
                if(result>0){
                    System.out.println("添加成功");
                    return true;
                }else{
                    System.out.println("添加失败");
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }
    //查询所有管理人员：
    public List<manager> queryManager(){
        List<manager>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select * from manager;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                String m_id=result.getString("m_id");
                String m_name=result.getString("m_name");
                String m_password=result.getString("m_password");
                String m_img=result.getString("m_img");
                String m_time=result.getString("m_time");
                String role=result.getString("role");
                manager m=new manager(m_id,m_name,m_password,m_img,m_time,role);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //修改：
    public boolean motify(manager m){
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "update manager set m_name=?,m_password=?where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, m.getM_name());
            ps.setString(2, m.getM_password());
            ps.setString(3,m.getM_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("添加成功");
                return true;
            }else{
                System.out.println("添加失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //删除：
    public boolean delete(manager m){
        // 2、定义查询的sql语句
        String sql = "delete from manager where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, m.getM_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println(m.getM_id()+"删除成功");
                return true;
            }else{
                System.out.println("删除失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
