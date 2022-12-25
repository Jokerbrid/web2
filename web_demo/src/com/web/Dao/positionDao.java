package com.web.Dao;

import com.web.bean.position;
import com.web.util.DataSources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class positionDao {
    Connection connection = DataSources.getConnection();

    //查询所有管理人员：
    public List<position> queryAll(){
        List<position>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from position where m_id='10001' and m_password='123456';
        String sql = "select positions.*,d_name from positions,department where positions.d_id=department.d_id;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                int p_id=result.getInt("p_id");
                String p_name=result.getString("p_name");
                int d_id=result.getInt("d_id");
                String p_describe=result.getString("p_describe");
                String d_name=result.getString("d_name");
                position m=new position(p_id,p_name,d_id,p_describe);
                m.setDepartment_name(d_name);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //修改：
    public boolean motify(position m){
        // 2、定义查询的sql语句
        // select * from position where m_id='10001' and m_password='123456';
        String sql = "update positions set p_name=?,d_id=?,p_describe=?where p_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setString(1, m.getP_name());
            ps.setInt(2, m.getD_id());
            ps.setString(3,m.getP_describe());
            ps.setInt(4,m.getP_id());
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
    public boolean delete(position m){
        // 2、定义查询的sql语句
        String sql = "delete from positions where p_id=?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setInt(1, m.getP_id());
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println(m.getP_id()+"删除成功");
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
    //分页查询：
    public List<position> queryPageAll(int page){
        List<position>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from position where m_id='10001' and m_password='123456';
        String sql = "select positions.*,d_name from positions,department where positions.d_id=department.d_id limit ?,?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            if(page>=1)ps.setInt(1,(page-1)*5);
            else ps.setInt(1,0);
            ps.setInt(2,5);//pageSize

            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                int p_id=result.getInt("p_id");
                String p_name=result.getString("p_name");
                int d_id=result.getInt("d_id");
                String p_describe=result.getString("p_describe");
                String d_name=result.getString("d_name");
                position m=new position(p_id,p_name,d_id,p_describe);
                m.setDepartment_name(d_name);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(position de) {
        String sql = "insert into positions values(?,?,?,?);";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            // 给sql语句赋值
            ps.setInt(1,de.getP_id());
            ps.setString(2,de.getP_name());
            ps.setInt(3,de.getD_id());
            ps.setString(4,de.getP_describe());
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

    public List<position> queryByD_id(int id) {
        List<position>list=new ArrayList<>();
        // 2、定义查询的sql语句
        // select * from position where m_id='10001' and m_password='123456';
        String sql = "select positions.*,d_name from positions,department where positions.d_id=department.d_id and positions.d_id like ?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                int p_id=result.getInt("p_id");
                String p_name=result.getString("p_name");
                int d_id=result.getInt("d_id");
                String p_describe=result.getString("p_describe");
                String d_name=result.getString("d_name");
                position m=new position(p_id,p_name,d_id,p_describe);
                m.setDepartment_name(d_name);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<position> queryByPname(String pname) {
        List<position>list=new ArrayList<>();
        // 2、定义查询的sql语句
        String sql = "select positions.*,d_name from positions,department where positions.d_id=department.d_id and positions.p_name like ?;";
        // 3、获取sql语句对象  （通过链接来获取sql语句对象）
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+pname+"%");
            // 给sql语句赋值
            // 4、执行sql语句  （通过sql语句对象来执行sql语句 ）
            ResultSet result = ps.executeQuery();//执行查询
            // 5、得到结果
            while (result.next()) {//判断结果集中是否有下一条记录
                int p_id=result.getInt("p_id");
                String p_name=result.getString("p_name");
                int d_id=result.getInt("d_id");
                String p_describe=result.getString("p_describe");
                String d_name=result.getString("d_name");
                position m=new position(p_id,p_name,d_id,p_describe);
                m.setDepartment_name(d_name);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
