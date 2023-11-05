package cdu.jhc.controller;

import cdu.jhc.model.Book;
import cdu.jhc.service.BookService;
import cdu.jhc.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 前台：显示图书列表，即首页
@WebServlet("/customer/book/list")
public class HomeServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用服务层方法获取图书的模糊查询结果
        List<Book> bookList = bookService.getAll();
        // 在请求范围内保存图书列表数据
        req.setAttribute("books", bookList);
        // 页面跳转：请求转发至图书列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
