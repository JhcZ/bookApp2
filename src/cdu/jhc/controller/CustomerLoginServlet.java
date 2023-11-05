package cdu.jhc.controller;

import cdu.jhc.model.Customer;
import cdu.jhc.model.User;
import cdu.jhc.service.CustomerService;
import cdu.jhc.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 前台：顾客登录
@WebServlet(urlPatterns = {"/customer/login"})
public class CustomerLoginServlet extends HttpServlet{
    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取用户登录信息
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        // 服务器端输入验证
        if (user.getName() == null || user.getPassword() == null || user.getName().isEmpty() || user.getPassword().isEmpty()) {
            // 登录失败
            System.out.println("Servlet 登录失败: 验证失败" + user);
            resp.sendRedirect("login.jsp");
            return;
        }
        // 验证码输入验证
        String inputCode = req.getParameter("inputCode");
        if (inputCode == null || inputCode.isEmpty()) {
            // 登录失败
            System.out.println("Servlet 登录失败: 验证码为空");
            resp.sendRedirect("login.jsp");
            return;
        }
        // 获取会话对象
        HttpSession session = req.getSession();
        // 验证验证码
        String validCode = (String) session.getAttribute("validCode");
        if (!inputCode.equalsIgnoreCase(validCode)) {
            // 登录失败
            System.out.println("Servlet 登录失败: 验证码错误");
            System.out.println("输入的验证码：" + inputCode);
            System.out.println("会话中的验证码：" + validCode);
            resp.sendRedirect("login.jsp");
            return;
        }
        // 调用服务层方法获取是否存在登录用户帐户
        Customer customer = customerService.check(user);
        if (customer == null) {
            // 登录失败
            System.out.println("Servlet 顾客登录失败: 数据库查询失败" + customer);
            resp.sendRedirect("login.jsp");
            return;
        }
        // 检查帐户状态是否正常
        if (!customerService.checkStatus(customer)) {
            // 登录失败
            System.out.println("Servlet 用户登录失败: 帐户状态异常" + customer);
            resp.sendRedirect("login.jsp");
            return;
        }
        // 登录成功
        System.out.println("Servlet 用户登录成功: " + customer);
        // 更新用户访问时间
        customerService.modAccessTime(customer);
        // 在会话范围内保存登录用户信息
        session.setAttribute("customer", customer);
        // 顾客用户登录成功，页面跳转至图书列表页面
        resp.sendRedirect(req.getContextPath() + "/customer/book/list");
    }
}
