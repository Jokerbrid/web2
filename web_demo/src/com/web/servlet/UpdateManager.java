package com.web.servlet;


import com.google.gson.Gson;
import com.web.Dao.managerDao;
import com.web.bean.manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/motifyUser")
public class UpdateManager extends HttpServlet {
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
        if(id==null||id.equals("")){
            result.put("ok",false);
            result.put("message","用户编号不能为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        managerDao md = new managerDao();
        manager m=new manager(id,username,password);
        if(md.motify(m)){
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
