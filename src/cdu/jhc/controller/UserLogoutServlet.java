package cdu.jhc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//前台：顾客注销
//后台：管理员注销
@WebServlet(urlPatterns = {"/admin/logout","/customer/logout"})
public class UserLogoutServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //会话失效
        HttpSession session = req.getSession();
        session.invalidate();
        //重定向到登录页面
        resp.sendRedirect("login.jsp");
    }
}
