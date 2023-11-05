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

//后台：修改管理员用户信息
@WebServlet("/admin/adminUser/mod")
public class AdminUserModServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取修改后的新用户信息
        req.setCharacterEncoding("utf-8");
        AdminUser adminUser = new AdminUser();
        adminUser.setId(Integer.parseInt(req.getParameter("id")));
        adminUser.setName(req.getParameter("name"));
        adminUser.setPassword(req.getParameter("password"));
        adminUser.setCreateTime(Long.parseLong(req.getParameter("createTime")));
        adminUser.setLastAccessTime(Long.parseLong(req.getParameter("lastAccessTime")));
        adminUser.setStatus(req.getParameter("status"));
        if (adminUserService.mod(adminUser)) {
            //修改成功，重定向至管理员用户列表界面
            resp.sendRedirect("list");
        } else {
            //修改失败，将请求转发至管理员用户修改界面
            req.setAttribute("adminUser", adminUser);
            req.getRequestDispatcher("mod.jsp").forward(req, resp);
        }
    }
}
