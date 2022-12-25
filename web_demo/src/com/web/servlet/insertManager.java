package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.managerDao;
import com.web.bean.manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
@WebServlet("/insertManager")
public class insertManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数。
        //2.判断是否登陆成功
        //3.响应请求
        //设置编码UTF-8,响应格式为json;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");
        String id=req.getParameter("id");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String img=req.getParameter("img");
        String role=req.getParameter("role");
        //后端操作：
        managerDao md=new managerDao();
        manager m=new manager(id,username,password,img,role);
        //ajax gson数据：
        HashMap result=new HashMap();
        Gson gson=new Gson();
        if(id==null||id.equals("")){
            result.put("ok",false);
            result.put("message","编号不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(username==null||username.equals("")){
            result.put("ok",false);
            result.put("message","用户名不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(password==null||password.equals("")){
            result.put("ok",false);
            result.put("message","密码不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(md.insert(m)){
// ajax 发送gson对象给前端
            result.put("ok",true);
            result.put("username",username);
        }else {
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
