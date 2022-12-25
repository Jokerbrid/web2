package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.employeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/employeeLogin")
public class LoginEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数。
        //2.判断是否登陆成功
        //3.响应请求
        //设置编码UTF-8,响应格式为json;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        //后端操作：
       employeeDao md=new employeeDao();
        boolean ok = md.queryManagerLogin(username,password);
        //ajax gson数据：
        HashMap result=new HashMap();

        Gson gson=new Gson();

        if(ok){
//            resp.getWriter().append("login success!");
//            resp.sendRedirect("/web_demo/index.html");
// ajax 发送gson对象给前端
            result.put("ok",true);
            result.put("username",username);
        }else {
//            resp.getWriter().append("login failed!");
//            resp.sendRedirect("/web_demo/login.html");
            result.put("ok",false);
            result.put("message","用户名或密码错误！");
        }
        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(result);
        resp.getWriter().append(re_json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
