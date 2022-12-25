package com.web.servlet;

import com.google.gson.Gson;
import com.web.bean.department;
import com.web.Dao.Department_allDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

//@WebServlet用于配置当前的请求访问的地址  /userUpdate
@WebServlet("/depUpdate")
public class UpdateDepartmentServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        //1、获取请求中提交的depname和dep describe   【使用getParameter方法根据key来获取value】
        String depid = request.getParameter("depid");
        String depname = request.getParameter("depname");
        String describe = request.getParameter("describe");
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //非空判断
        if (depid == null || depid.equals("")) {
            result.put("message", "部门编号不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (depname == null || depname.equals("")) {
            result.put("message", "部门名不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        if (describe == null || describe.equals("")) {
            result.put("message", "部门描述不能为空");
            //写出结果
            response.getWriter().append(gson.toJson(result));
            return;//结束方法
        }
        int id=Integer.valueOf(depid);
        //构建Manger 对象
        department departmentAll = new department(id, depname, describe);
        // 2、创建dao 的对象，然后调佣dao方法
        Department_allDao dao = new Department_allDao();
        boolean ok = dao.updateDepartment(departmentAll);
        // 3、判断是否修改成功
        if (ok) {
            result.put("ok", true);
        } else {
            result.put("ok", false);
            result.put("message", "部门修改失败");
        }
        //3、响应 --写出结果
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
