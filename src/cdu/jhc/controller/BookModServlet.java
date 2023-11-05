package cdu.jhc.controller;

import cdu.jhc.model.Book;
import cdu.jhc.service.BookService;
import cdu.jhc.service.impl.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//修改图书
@WebServlet("/admin/book/mod")
public class BookModServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置图像文件保存路径
        String path = "/cover";
        //获取图像文件保存路径对应的真实物理地址
        String saveDir = req.getServletContext().getRealPath(path);
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //使用commons-fileupload组件分别处理表单域和文件
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        // Create a new file upload handler
        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);
        // Parse the request 解析请求
        List<DiskFileItem> fileItems = upload.parseRequest(req);

        // 使用commons-fileupload组件分别处理表单域和文件
        // 客户端传递的新添加的图书信息将封装在下面的book对象中
        Book book = new Book();
        for(DiskFileItem item : fileItems) {
            if (!item.isFormField()) {
                // 获取表单中除文件以外的其他控件值
                if (item.getFieldName().equals("id")) {
                    book.setId(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("title")) {
                    book.setTitle(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("author")) {
                    book.setAuthor(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("press")) {
                    book.setPress(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("price")) {
                    book.setPrice(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("sale")) {
                    book.setSale(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("stock")) {
                    book.setStock(Integer.parseInt(item.getString()));
                }
                if (item.getFieldName().equals("info")) {
                    book.setInfo(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("publishDate")) {
                    book.setPublishDate(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("marketDate")) {
                    book.setMarketDate(new String(item.getString().getBytes(StandardCharsets.ISO_8859_1),
                            StandardCharsets.UTF_8));
                }
                if (item.getFieldName().equals("coverUrl")) {
                    book.setCoverUrl(item.getString());
                }
            } else {
                if (item.getSize() > 0) {
                    // 获取上传文件的原文件名
                    String fileName = item.getName();
                    if (fileName != null) {
                        // 使用当前时间设置新文件名，保留原文件扩展名
                        fileName = new Date().getTime() + "." + FilenameUtils.getExtension(fileName);
                    }
// 在服务器端保存图像，注意在out目录下的cover中查看，而不是源代码的cover目录中查看上传结果
                    item.write(Path.of(saveDir + "//" + fileName));
                    // 数据库中需要保存上传文件的相对路径，形如: /bookApp1/cover/xxx.png
                    book.setCoverUrl(req.getContextPath() + path + "/" + fileName);
                }
            }
        }
        //调用服务层方法修改指定图书
        if (bookService.mod(book)) {
            //修改成功，重定向至图书列表界面
            resp.sendRedirect("list");
        } else {
            //修改失败，将请求转发至图书修改界面
            req.setAttribute("book", book);
            req.getRequestDispatcher("mod.jsp").forward(req, resp);
        }
    }
}
