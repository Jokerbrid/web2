package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.managerDao;
import com.web.bean.manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");


        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //改成Gson格式进行前后端分离：

        HashMap result=new HashMap();
        Gson gson=new Gson();
        manager m=new manager(id,username,password);
        if(id==null||id.equals("")||username==null||password==null){
            result.put("ok",false);
            result.put("message","账户不为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        managerDao md = new managerDao();
        if(!md.queryManagerLogin(id,"%")) {
            boolean ok = md.insert(m);
            if (ok) {
                System.out.println("注册信息：" + id + "----" + username + "--" + password + "---");
//                resp.sendRedirect("/web_demo/login.html");
                result.put("ok",true);
                result.put("message",username+"注册成功");
            } else {
                result.put("ok",false);
                result.put("message","注册失败");
                System.out.println("注册失败");
//                resp.sendRedirect("/web_demo/register.html");
            }
        }else {
            result.put("ok",false);
            result.put("message","该用户名已经被注册了");
            System.out.println("用户名已存在");
//            resp.sendRedirect("/web_demo/register.html");
        }
        String rs_gson=gson.toJson(result);
        resp.getWriter().append(rs_gson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
