package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.departmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/queryDepEmpNums")
public class QueryDepEmpNumsServlet extends HttpServlet {

    //接收请求的处理方法
    @Override          //  请求:request     响应:response
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        //把响应的编码设置为UTF-8，以及响应的格式设为json
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        // --创建dao的对象，然后调用方法查询所有的管理员
        departmentDao dao = new departmentDao();
        HashMap result = dao.geDepEmpNums();
        //创建一个Gson对象
        Gson gson = new Gson();
        //2、响应-json数据的结果
        response.getWriter().append(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}