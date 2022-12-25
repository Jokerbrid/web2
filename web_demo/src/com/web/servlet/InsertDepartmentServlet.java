package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.Department_allDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

//@WebServlet用于配置当前的请求访问的地址  /userRegister
@WebServlet("/depInsert")
public class InsertDepartmentServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求中提交的username和userpassword   【使用getParameter方法根据key来获取value】
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        String depid = request.getParameter("depid");
        String depname = request.getParameter("depname");
        String describe = request.getParameter("describe");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson=new Gson();
        //非空判断
        if (depid == null || depid.equals("")) {
            result.put("message","用户编号不能为空");
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (depname == null || depname.equals("")) {
            result.put("message","用户编号不能为空");
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (describe == null || describe.equals("")) {
            result.put("message","用户编号不能为空");
            response.getWriter().append(gson.toJson(result));
            //response.sendRedirect("/WebDemo/register.html");
            return;//结束方法
        }
        // 2、创建dao 的对象，然后调佣dao方法
        Department_allDao dao = new Department_allDao();
        boolean ok = dao.insertDepartment(depid, depname, describe);

        // 3、判断是否注册成功  【admin-重复】
        if (ok) {
            //3、响应 --注册成功
//            response.sendRedirect("/WebDemo/login.html");
            result.put("ok", true);
            result.put("depid", depid);
        } else {
            //3、响应 --注册失败
//            response.sendRedirect("/WebDemo/register.html");
            result.put("ok", false);
        }
        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(result);
        response.getWriter().append(re_json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}