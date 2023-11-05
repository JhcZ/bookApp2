package cdu.jhc.service;

import cdu.jhc.model.*;

import java.util.List;

public interface BookService {
    //获取指定id的某本图书
    Book get(int id);
    //获取所有图书
    List<Book> getAll();
    //分页模糊查询图书列表
    List<Book> get(Book condition, int page, int pageSize);
    //获取查询图书数量
    int count(Book condition);
    //添加图书
    boolean add(Book book);
    //修改图书
    boolean mod(Book book);
    //删除图书
    boolean del(int id);
}
