package cdu.jhc.controller;

import cdu.jhc.model.UserStatus;
import cdu.jhc.service.CustomerService;
import cdu.jhc.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//后台管理普通用户（顾客）：修改顾客帐户状态，解冻或冻结
@WebServlet(urlPatterns = {"/admin/customer/freeze", "/admin/customer/active"})
public class CustomerModStatusServlet extends HttpServlet{

    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid != null && !sid.equals("")) {
            int id = Integer.parseInt(sid);
            if (req.getServletPath().contains("freeze")) {
                customerService.modStatus(id, UserStatus.FREEZE);
            } else if (req.getServletPath().contains("active")) {
                customerService.modStatus(id, UserStatus.NORMAL);
            } else {
                customerService.modStatus(id, UserStatus.UNKNOWN);
            }
        }
        //重定向到顾客列表管理界面
        resp.sendRedirect("list");
    }
}
