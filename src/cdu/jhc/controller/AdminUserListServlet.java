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
import java.util.List;

//后台：查询、管理管理员用户列表
@WebServlet(urlPatterns = {"/admin/adminUser/list", "/admin/adminUser/query"})
public class AdminUserListServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        AdminUser condition = new AdminUser();
        condition.setName(req.getParameter("name"));
        condition.setStatus(req.getParameter("statusName"));
        System.out.println("--------------模糊查询条件：" + condition);
        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        int usersCount = adminUserService.count(condition);
        int pageCount = usersCount % pageSize == 0 ? usersCount / pageSize : usersCount / pageSize + 1;
        // 调用服务层方法获取到查询结果
        List<AdminUser> adminUserList = adminUserService.get(condition, page, pageSize);
        // 在请求范围内保存用户列表数据
        req.setAttribute("adminUsers", adminUserList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
