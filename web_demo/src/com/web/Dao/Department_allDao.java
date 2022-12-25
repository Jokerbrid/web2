package com.web.Dao;

import com.web.bean.department;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Department_allDao {
    //定义一个方法来实现查询所有的Department
    Connection connection = DataSources.getConnection();
    public List<department> queryDepartment() {
        //创建一个集合用于存放数据
        List<department> list = new ArrayList<>();
        // 1、获取到数据库链接
        // 2、定义查询的sql语句
        String sql = "select * from department;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、循环从result得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                //得到一行的数据
                int d_id = result.getInt("d_id");
                String d_name = result.getString("d_name");
                String d_describe = result.getString("d_describe");
                //将数据封装到Java对象中
                department departmentAll = new department(d_id, d_name, d_describe);
                //将对象存放到list中
                list.add(departmentAll);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //返回集合
        return list;
    }

    //定义一个方法来实现修改操作【根据主键，修改name和describe】
    public boolean updateDepartment(department departmentAll) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "update department set d_name=?,d_describe=? where d_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, departmentAll.getD_name() );
            ps.setString(2, departmentAll.getD_describe() );
            ps.setInt(3, departmentAll.getD_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）  查询操作使用executeQuery  更新操作
            int result = ps.executeUpdate();//执行添加
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("操作成功");
                return true;
            } else {
                System.out.println("操作失败");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    ////定义一个方法来实现删除操作【根据id删除】
    public boolean deleteDepartment(int depid){
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义查询的sql语句
        String sql = "delete from department where d_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setInt(1, depid);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）  查询操作使用executeQuery  更新操作
            int result = ps.executeUpdate();//执行删除
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("删除成功");
                return true;
            } else {
                System.out.println("操作失败");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    //定义一个方法来实现添加操作
    public boolean insertDepartment(String id, String name, String describe) {
        // 1、获取到数据库链接
        Connection connection = DataSources.getConnection();
        System.out.println("获取到的数据库链接为：" + DataSources.getConnection());
        // 2、定义查询的sql语句
        String sql = "insert into department(d_id,d_name,d_describe) values(?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, describe);
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）  查询操作使用executeQuery  更新操作
            int result = ps.executeUpdate();//执行添加
            // 5、得到影响行数
            if (result > 0) {//判断影响行数是否大于0
                System.out.println("添加成功");
                return true;
            } else {
                System.out.println("添加失败");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
