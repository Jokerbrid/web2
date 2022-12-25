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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/QueryPosByname")
public class QueryPosByName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码UTF-8,响应格式为json;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");
        String pname=req.getParameter("p_name");
        List<position>user_list=new ArrayList<>();

            //后端操作：
        positionDao emD = new positionDao();
        user_list = emD.queryByPname(pname);
        //ajax gson数据：
        HashMap res=new HashMap();
        Gson gson=new Gson();
        res.put("ok", true);
         res.put("pos_list", user_list);
        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(res);
        resp.getWriter().append(re_json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}