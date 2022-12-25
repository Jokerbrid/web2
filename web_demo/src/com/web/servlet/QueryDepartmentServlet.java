package com.web.servlet;
import com.google.gson.Gson;
import com.web.bean.department;
import com.web.Dao.Department_allDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/queryDepartment")
public class QueryDepartmentServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        // --创建dao的对象，然后调用方法查询所有的部门
        Department_allDao dao = new Department_allDao();
        List<department> dep_list = dao.queryDepartment();
        //创建一个HashMap集合对象
        HashMap result = new HashMap();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        result.put("dep_list", dep_list);
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}