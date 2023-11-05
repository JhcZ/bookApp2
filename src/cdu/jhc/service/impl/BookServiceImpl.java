package cdu.jhc.service.impl;

import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.BookDao;
import cdu.jhc.dao.impl.BookDaoImpl;
import cdu.jhc.model.Book;
import cdu.jhc.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService{
    BookDao bookDao = new BookDaoImpl();

    @Override
    public Book get(int id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.query(null);
    }

    @Override
    public List<Book> get(Book condition, int page, int pageSize) {
        return bookDao.query(condition,(page - 1) * pageSize,pageSize);
    }

    @Override
    public int count(Book condition) {
        return bookDao.count(condition);
    }

    @Override
    public boolean add(Book book) {
        return bookDao.insert(book) == 1;
    }

    @Override
    public boolean mod(Book book) {
        return bookDao.update(book) == 1;
    }

    @Override
    public boolean del(int id) {
        return bookDao.delete(id) == 1;
    }
}
