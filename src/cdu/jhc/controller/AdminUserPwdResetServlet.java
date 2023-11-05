package cdu.jhc.controller;

import cdu.jhc.service.AdminUserService;
import cdu.jhc.service.impl.AdminUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//后台：管理员重置其他管理员或自己的登录密码
@WebServlet(urlPatterns = {"/admin/adminUser/reset","/admin/reset"})
public class AdminUserPwdResetServlet extends HttpServlet {
    AdminUserService adminUserService = new AdminUserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //使用客户端传递的id值在数据库中重置密码
            adminUserService.resetPwd(Integer.parseInt(sid));
        }
        //重定向到管理员用户列表界面
        resp.sendRedirect("list");
    }
}
