package cdu.jhc.controller;

import cdu.jhc.model.AdminUser;
import cdu.jhc.service.AdminUserService;
import cdu.jhc.service.impl.AdminUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//后台：修改管理员用户信息前获取该管理员信息
@WebServlet("/admin/adminUser/modPre")

public class AdminUserModPreServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            //调用服务层方法使用客户端传递的id值查询用户
            AdminUser adminUser = adminUserService.get(Integer.parseInt(sid));
            //在请求范围内保存查询到的用户对象
            req.setAttribute("oldAdminUser", adminUser);
            //请求转发到修改用户页面
            req.getRequestDispatcher("mod.jsp").forward(req, resp);
        } else {
            //未获取到id参数，则无法获取用户信息，重定向到管理员用户列表界面
            resp.sendRedirect("list");
        }
    }
}
