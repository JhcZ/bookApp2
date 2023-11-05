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

@WebServlet(urlPatterns = {"/admin/book/list", "/admin/book/query"})
public class BookListServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从客户端获取模糊查询条件
        req.setCharacterEncoding("utf-8"); //处理中文乱码
        Book condition = new Book();
        condition.setTitle(req.getParameter("title"));
        condition.setAuthor(req.getParameter("author"));
        condition.setPress(req.getParameter("press"));
        System.out.println("--------------模糊查询条件："
                + condition.getTitle() + ","
                + condition.getAuthor() + ","
                + condition.getPress());
        // 从客户端获取分页信息
        int page = 1;
        String sPage = req.getParameter("p");
        if (sPage != null && !"".equals(sPage)) {
            page = Integer.parseInt(req.getParameter("p"));
        }
        int pageSize = 5;
        //获取当前查询条件下的图书数量
        int booksCount = bookService.count(condition);
        //计算总页数
        //int pageCount = booksCount % pageSize == 0 ? booksCount / pageSize : (booksCount + pageSize - 1) / pageSize;
        int pageCount = (booksCount + pageSize - 1) / pageSize;
        // 调用服务层方法获取图书的模糊查询结果
        List<Book> bookList = bookService.get(condition, page, pageSize);
        // 在请求范围内保存图书列表数据
        req.setAttribute("books", bookList);
        req.setAttribute("p", page);
        req.setAttribute("pCount", pageCount);
        // 页面跳转：请求转发至图书列表页面
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
