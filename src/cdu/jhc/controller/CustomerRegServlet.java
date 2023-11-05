package cdu.jhc.controller;

import cdu.jhc.model.Customer;
import cdu.jhc.service.CustomerService;
import cdu.jhc.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//前台：普通用户(顾客)注册
@WebServlet(urlPatterns = {"/customer/reg"})
public class CustomerRegServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从客户端获取新用户信息
        req.setCharacterEncoding("utf-8");
        Customer customer = new Customer();
        customer.setName(req.getParameter("name"));
        customer.setPassword(req.getParameter("password"));
        //调用服务层方法添加用户
        customerService.register(customer);
        //注册成功，返回顾客登录页面
        resp.sendRedirect("login.jsp");
    }
}
