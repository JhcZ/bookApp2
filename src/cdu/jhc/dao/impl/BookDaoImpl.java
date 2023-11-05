package cdu.jhc.dao.impl;

import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.BookDao;
import cdu.jhc.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao{
    @Override
    public Book findById(int id) {
        Book condition = new Book();
        condition.setId(id);
        List<Book> bookList = query(condition);
        if(bookList != null && bookList.size() == 1){
            return bookList.get(0);
        }
        return null;
    }

    @Override
    public List<Book> query(Book condition) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book_table";
        if(condition != null){
            sql += " WHERE 1=1";
            if(condition.getId() != 0){
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getTitle() != null && !condition.getTitle().isEmpty()) {
                sql += " AND title LIKE '%" + condition.getTitle() + "%'";
            }
            if (condition.getAuthor() != null && !condition.getAuthor().isEmpty()) {
                sql += " AND author LIKE '%" + condition.getAuthor() + "%'";
            }
            if (condition.getPress() != null && !condition.getPress().isEmpty()) {
                sql += " AND press LIKE '%" + condition.getPress() + "%'";
            }
        }
        sql += " ORDER BY marketDate DESC";

        System.out.println("DAO查询find(condition) : " + sql);

        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setSale(rs.getInt("sale"));
                book.setStock(rs.getInt("stock"));
                book.setCoverUrl(rs.getString("coverUrl"));
                book.setInfo(rs.getString("info"));
                book.setPublishDate(rs.getDate("publishDate"));
                book.setMarketDate(rs.getDate("marketDate"));
                bookList.add(book);
            }
        }catch (SQLException e){
            System.out.println("DAO查询图书出错：" + sql + "," + e.getMessage());
        }
        return bookList;
    }

    @Override
    public List<Book> query(Book condition, int start, int num) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book_table";

        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getTitle() != null && !condition.getTitle().isEmpty()) {
                sql += " AND title LIKE '%" + condition.getTitle() + "%'";
            }
            if (condition.getAuthor() != null && !condition.getAuthor().isEmpty()) {
                sql += " AND author LIKE '%" + condition.getAuthor() + "%'";
            }
            if (condition.getPress() != null && !condition.getPress().isEmpty()) {
                sql += " AND press LIKE '%" + condition.getPress() + "%'";
            }
        }
        sql += " ORDER BY marketDate DESC LIMIT ?,?";

        System.out.println("DAO查询find(condition, start, num) : " + sql);

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setSale(rs.getInt("sale"));
                book.setStock(rs.getInt("stock"));
                book.setCoverUrl(rs.getString("coverUrl"));
                book.setInfo(rs.getString("info"));
                book.setPublishDate(rs.getDate("publishDate"));
                book.setMarketDate(rs.getDate("marketDate"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询图书出错：" + sql + "," + e.getMessage());
        }
        return bookList;
    }

    @Override
    public int insert(Book book) {
        int rows = 0;
        String sql = "INSERT INTO book_table(title,author,press,price,sale,stock,coverUrl,info,publishDate,marketDate) VALUES(?,?,?,?,?,?,?,?,?,?) ";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPress());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.setInt(5, book.getSale());
            pstmt.setInt(6, book.getStock());
            pstmt.setString(7, book.getCoverUrl());
            pstmt.setString(8, book.getInfo());
            pstmt.setDate(9, book.getPublishDate());
            pstmt.setDate(10, book.getMarketDate());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO添加图书出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(Book book) {
        int rows = 0;
        String sql = "UPDATE book_table SET title=?,author=?,press=?,price=?,sale=?,stock=?,coverUrl=?,info=?,publishDate=?,marketDate=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPress());
            pstmt.setBigDecimal(4, book.getPrice());
            pstmt.setInt(5, book.getSale());
            pstmt.setInt(6, book.getStock());
            pstmt.setString(7, book.getCoverUrl());
            pstmt.setString(8, book.getInfo());
            pstmt.setDate(9, book.getPublishDate());
            pstmt.setDate(10, book.getMarketDate());
            pstmt.setInt(11, book.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改图书出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM book_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除图书出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Book condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM book_table WHERE (1=1) ";
        if (condition != null) {
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getTitle() != null && !condition.getTitle().isEmpty()) {
                sql += " AND title LIKE '%" + condition.getTitle() + "%'";
            }
            if (condition.getAuthor() != null && !condition.getAuthor().isEmpty()) {
                sql += " AND author LIKE '%" + condition.getAuthor() + "%'";
            }
            if (condition.getPress() != null && !condition.getPress().isEmpty()) {
                sql += " AND press LIKE '%" + condition.getPress() + "%'";
            }
        }
        System.out.println("DAO查询count(con): " + sql);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询图书数量出错：" + sql + "," + e.getMessage());
        }
        return num;
    }
}
