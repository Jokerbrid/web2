package com.web.Dao;

import com.web.bean.department;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class departmentDao {
    Connection connection = DataSources.getConnection();
    //插入
    public boolean insert(department de) {
        String sql = "insert into department(d_id,d_name,d_describe) values(?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
            try {
                ps = connection.prepareStatement(sql);
                // 给sql语句赋值
                ps.setInt(1,de.getD_id());
                ps.setString(2,de.getD_name());
                ps.setString(3,de.getD_describe());
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
    //查询all
    public List<department> queryAll(){
        List<department>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from department where m_id='10001' and m_password='123456';
        String sql = "select * from department;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                int d_id=result.getInt("d_id");
                String d_name=result.getString("d_name");
                String d_describe=result.getString("d_describe");
                department m=new department(d_id,d_name,d_describe);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //修改：
    public boolean motify(department de){
        // 2、定义查询的sql语句
        // select * from department where m_id='10001' and m_password='123456';
        String sql = "update department set d_name=?,d_describe=?where d_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, de.getD_name());
            ps.setString(2,de.getD_describe());
            ps.setInt(3,de.getD_id());
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
    public boolean delete(department m){
        // 2、定义查询的sql语句
        String sql = "delete from department where m_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setInt(1, m.getD_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println(m.getD_id()+"删除成功");
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

    //获取每个部门的职位数量
    public HashMap geDepEmpNums() {
        // 结果map
        HashMap resultMap = new HashMap();
        // 创建两个集合
        List<String> d_names = new ArrayList<>();
        List<Integer> p_nums = new ArrayList<>();
        // 1、通过驱动管理者的get方法获取数据库链接
        Connection connection = DataSources.getConnection();
        // 2、定义sql语句
        String sql = "select tab.*,de.d_name from department de,(\n" +
                "select d_id,count(d_id) as nums from positions ps group by d_id\n" +
                ") tab\n" +
                "where de.d_id=tab.d_id;";
        // 3、通过链接来获取sql语句对象
        PreparedStatement statement;
        try {
            // 创建预编译的sql语句对象，提供sql语句
            statement = connection.prepareStatement(sql);
            // 4、通过sql语句对象来执行sql语句【添加、删除、修改】executeUpdate
            ResultSet result = statement.executeQuery();// 结果集对象 ResultSet
            // 从ResultSet中遍历结果
            while (result.next()) {// 判断是否有下一条数据
                // d_id | nums | d_name
                p_nums.add(result.getInt(2));
                d_names.add(result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //返回集合
        resultMap.put("d_names", d_names);
        resultMap.put("p_nums", p_nums);
        return resultMap;
    }
}
