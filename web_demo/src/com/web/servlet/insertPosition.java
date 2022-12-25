package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.positionDao;
import com.web.bean.position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/insertPosition")
public class insertPosition extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数。
        //2.判断是否登陆成功
        //3.响应请求
        //设置编码UTF-8,响应格式为json;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");
        String p_id= req.getParameter("id");
        String name=req.getParameter("name");
        String d=req.getParameter("department");
        String describe=req.getParameter("describe");
        int department=0,id=0;
        if(d==null||d.equals("")) department=0;
            else department=Integer.parseInt(d);
        //后端操作：
        if(p_id==null||p_id.equals("")) id=0;
            else id= Integer.parseInt(p_id);
        //ajax gson数据：
        HashMap result=new HashMap();
        Gson gson=new Gson();
        if(p_id==null||p_id.equals("")){
            result.put("ok",false);
            result.put("message","编号不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(name==null||name.equals("")){
            result.put("ok",false);
            result.put("message","职称名不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        positionDao posD=new positionDao();
        position pos=new position(id,name,department,describe);
        if(posD.insert(pos)){
// ajax 发送gson对象给前端
            result.put("ok",true);
            result.put("message","添加成功");
        }else {
            result.put("ok",false);
            result.put("message","该职位已经存在错误！");
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
