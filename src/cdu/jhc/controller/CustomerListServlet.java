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
import java.util.List;

//后台管理普通用户（顾客）列表
@WebServlet(urlPatterns = {"/admin/customer/list", "/admin/customer/query"})
public class CustomerListServlet extends HttpServlet{
    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Customer condition = new Customer();
        condition.setName(req.getParameter("name"));
        condition.setStatus(req.getParameter("statusName"));
        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        int usersCount = customerService.count(condition);
        int pageCount = usersCount % pageSize == 0 ? usersCount / pageSize : usersCount / pageSize + 1;
        // 从模型层获取到查询结果
        List<Customer> customerList = customerService.get(condition, page, pageSize);
        // 在请求范围内保存用户列表数据
        req.setAttribute("customers", customerList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
