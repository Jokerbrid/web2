package com.web.servlet;


import com.google.gson.Gson;
import com.web.Dao.employeeDao;
import com.web.bean.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/motifyEmployee")
public class UpdateEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");

        String id = req.getParameter("e_id");
        String username = req.getParameter("e_username");
        String password = req.getParameter("e_password");
        String card = req.getParameter("e_card");
        String sex = req.getParameter("e_sex");
        String p = req.getParameter("e_position");
        String d = req.getParameter("e_department");
        String phone = req.getParameter("e_phone");
        int position=0,department=0;
        if(p==null||p.equals(""))position=0;
        else position= Integer.parseInt(p);
        if(d==null||d.equals(""))department=0;
        else department= Integer.parseInt(d);
        //改成Gson格式进行前后端分离：

        HashMap result=new HashMap();
        Gson gson=new Gson();
        if(id==null||id.equals("")){
            result.put("ok",false);
            result.put("message","用户编号不能为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(card==null||card.equals("")){
            result.put("ok",false);
            result.put("message","用户身份号不能为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        employeeDao emd=new employeeDao();
        employee em= new employee(id,username,password,card,sex,position,department,phone);
        if(emd.motify(em)){
            result.put("ok",true);
            result.put("message","修改成功");
        }else{
            result.put("ok",false);
            result.put("message","修改失败");
        }
        String rs_gson=gson.toJson(result);
        resp.getWriter().append(rs_gson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
