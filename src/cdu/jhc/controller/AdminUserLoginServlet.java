package cdu.jhc.controller;

import cdu.jhc.model.AdminUser;
import cdu.jhc.model.User;
import cdu.jhc.service.AdminUserService;
import cdu.jhc.service.impl.AdminUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//后台：管理员登录
@WebServlet(urlPatterns = {"/admin/login"})
public class AdminUserLoginServlet extends HttpServlet{
    AdminUserService adminUserService = new AdminUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取用户登录信息
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        //服务器端输入验证
        if (user.getName() == null || user.getPassword() == null || user.getName().isEmpty() || user.getPassword().isEmpty()) {
            //登录失败
            System.out.println("Servlet 管理员登录失败: 验证失败" + user);
            resp.sendRedirect("login.jsp");
            return;
        }
        //验证码输入验证
        String inputCode = req.getParameter("inputCode");
        if (inputCode == null || inputCode.isEmpty()) {
            //登录失败
            System.out.println("Servlet 管理员登录失败: 验证码为空");
            resp.sendRedirect("login.jsp");
            return;
        }
        //获取会话对象
        HttpSession session = req.getSession();
        //验证验证码
        String validCode = (String) session.getAttribute("validCode");
        if (!inputCode.equalsIgnoreCase(validCode)) {
            //登录失败
            System.out.println("Servlet 管理员登录失败: 验证码错误");
            System.out.println("用户输入的验证码：" + inputCode);
            System.out.println("会话中的验证码：" + validCode);
            resp.sendRedirect("login.jsp");
            return;
        }
        //调用服务层方法检查是否存在登录用户数据
        AdminUser admin = adminUserService.getLoginUser(user);
        if (admin == null) {
            //登录失败
            System.out.println("Servlet 管理员登录失败: 数据库查询失败" + admin);
            resp.sendRedirect("login.jsp");
            return;
        }
        // 调用服务层方法检查帐户状态是否正常
        if (!adminUserService.checkStatus(admin)) {
            //登录失败
            System.out.println("Servlet 管理员登录失败: 帐户状态异常" + admin);
            resp.sendRedirect("login.jsp");
            return;
        }
        //登录成功
        System.out.println("Servlet 管理员登录成功: " + admin);
        //在会话范围内保存登录用户信息
        session.setAttribute("admin", admin);
        //页面重定向至后台管理平台
        resp.sendRedirect("book/list");
    }
}
