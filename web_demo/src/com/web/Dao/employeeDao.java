package com.web.Dao;

import com.web.bean.employee;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class employeeDao {
    Connection connection = DataSources.getConnection();
    //添加：
    public boolean insert(employee em) {
        String sql = "insert into employee values(?,?,?,?,?,?,?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new Date());
            ps.setString(1,em.getE_id());
            ps.setString(2,em.getE_name());
            ps.setString(3,em.getE_password());
            ps.setString(4,em.getE_card());
            ps.setString(5,em.getE_sex());
            ps.setString(6,em.getE_img());
            ps.setInt(7,em.getE_position());
            ps.setInt(8,em.getE_department());
            ps.setString(9,em.getE_phone());
            ps.setString(10,time);

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
    //查询所有管理人员：
    public List<employee> queryAll(){
        List<employee>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select employee.*,positions.p_name,department.d_name from employee,positions,department where employee.e_position=positions.p_id and employee.e_department=department.d_id;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                String e_id=result.getString("e_id");
                String e_name=result.getString("e_name");
                String e_password=result.getString("e_password");
                String e_card=result.getString("e_card");
                String e_sex=result.getString("e_sex");
                String e_img=result.getString("e_img");
                int e_position= Integer.parseInt(result.getString("e_position"));
                int e_department= Integer.parseInt(result.getString("e_department"));
                String e_phone=result.getString("e_phone");
                String e_time=result.getString("e_time");
                employee em=new employee(e_id, e_name,e_password,
                        e_card,
                        e_sex,
                        e_img,
                        e_position,
                        e_department,
                        e_phone,
                        e_time
                );
                em.setDepartment_name(result.getString("d_name"));
                em.setPosition_name(result.getString("p_name"));
                list.add(em);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //修改：
    public boolean motify(employee em){
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "update employee set e_name=?,e_password=?,e_card=?,e_sex=?,e_position=?,e_department=?,e_phone=? where e_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, em.getE_name());
            ps.setString(2, em.getE_password());
            ps.setString(3,em.getE_card());
            ps.setString(4,em.getE_sex());
            ps.setInt(5,em.getE_position());
            ps.setInt(6,em.getE_department());
            ps.setString(7,em.getE_phone());
            ps.setString(8,em.getE_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("修改成功");
                return true;
            }else{
                System.out.println("修改失败");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    //删除：
    public boolean delete(employee em){
        // 2、定义查询的sql语句
        String sql = "delete from employee where e_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, em.getE_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println(em.getE_id()+"删除成功");
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

    public boolean queryManagerLogin(String username, String password) {
        // 2、定义查询的sql语句
        // select * from manager where m_id='10001' and m_password='123456';
        String sql = "select * from employee where e_id=? and e_password = ?;";
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
                System.out.println("用户名：" + result.getString("e_name"));
                return true;
            } else {
                System.out.println("查无此人");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
