package com.web.servlet;

import com.google.gson.Gson;
import com.web.Dao.employeeDao;
import com.web.bean.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
@WebServlet("/insertEmployee")
@MultipartConfig//支持文件上传数据
public class insertEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数。
        //2.判断是否登陆成功
        //3.响应请求
        //设置编码UTF-8,响应格式为json;
        req.setCharacterEncoding("UTF-8");//请求对象编码设置
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");
        String id = req.getParameter("id");//非空判断
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String card = req.getParameter("card");//非空判断
        String sex = req.getParameter("sex");
        String p = req.getParameter("position");
        String d = req.getParameter("department");
        String phone = req.getParameter("phone");
        //文件上传：
        Part file_part=req.getPart("file_part");
        String imgname=uploadFile(file_part);
        int position=0,department=0;
        if(p==null||p.equals(""))position=0;
        else position= Integer.parseInt(p);
        if(d==null||d.equals(""))department=0;
        else department= Integer.parseInt(d);
        //ajax gson数据：
        HashMap result=new HashMap();
        Gson gson=new Gson();
        //后端操作：
        if(id==null||id.equals("")){
            result.put("ok",false);
            result.put("message","员工编号不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        if(card==null||card.equals("")){
            result.put("ok",false);
            result.put("message","员工身份号不能为空");
            String re_json = gson.toJson(result);
            resp.getWriter().append(re_json);
            return;
        }
        employeeDao emd=new employeeDao();
        employee em=new employee(id,username,password,card,sex,imgname,position,department,phone);
        boolean ok = emd.insert(em);
        if(ok){
// ajax 发送gson对象给前端
            result.put("ok",true);
            result.put("message","添加成功");
        }else {
            result.put("ok",false);
            result.put("message","添加失败");
        }
        // 把集合数据转换为json字符串，然后通过响应写出
        String re_json = gson.toJson(result);
        resp.getWriter().append(re_json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    //上传操作，返回文件名
    public String uploadFile(Part fileItem) throws IOException {
        // 文件区域为空
        if (fileItem == null) {
            return null;
        }
        //-- 获取输入流
        BufferedInputStream bis = new BufferedInputStream(fileItem.getInputStream());
        //-- 构建文件输出路径及输出流
        //获取文件名：   上下文的描述
        String Disposition = fileItem.getHeader("Content-Disposition");
        //格式   form-data; name="fileItem"; filename="ui-sam.jpg"
        // System.out.println(Disposition);
        int start = Disposition.indexOf("filename=\"") + 10;
        int end = Disposition.lastIndexOf("\"");
        //截取文件名
        String fileName = Disposition.substring(start, end);
        // 文件名为空
        if (fileName.equals("")) {
            return null;
        }
        String path = "D:\\file\\" + fileName;
        //构建输出流
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        //-- io流操作
        byte[] buff = new byte[1024];
        int len = 0;//字节数
        //循环读写
        while ((len = bis.read(buff)) != -1) {
            //写出
            bos.write(buff, 0, len);
            //刷新
            bos.flush();
        }
        //释放资源
        bis.close();
        bos.close();
        //返回文件名
        return fileName;
    }
}

