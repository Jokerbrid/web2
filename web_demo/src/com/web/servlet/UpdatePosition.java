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

@WebServlet("/motifyPosition")
public class UpdatePosition extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");

        String p = req.getParameter("p_id");
        String p_name = req.getParameter("p_name");
        String d = req.getParameter("d_id");
        String p_describe = req.getParameter("p_describe");

        //改成Gson格式进行前后端分离：
        System.out.println(p);

        HashMap result=new HashMap();
        Gson gson=new Gson();
        if(p==null||p.equals("")){
            result.put("ok",false);
            result.put("message","编号不能为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(p_name==null||p_name.equals("")){
            result.put("ok",false);
            result.put("message","职位名不能为空");
            String re_json=gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        int p_id,d_id;
        if(p==null||p.equals(""))p_id=0;
        else p_id= Integer.parseInt(p);
        if(d==null||d.equals(""))d_id=0;
        else d_id= Integer.parseInt(d);
        positionDao posD= new positionDao();
        position pos=new position(p_id,p_name,d_id,p_describe);
        if(posD.motify(pos)){
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
