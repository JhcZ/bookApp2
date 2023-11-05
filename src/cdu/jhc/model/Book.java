package cdu.jhc.model;

import cdu.jhc.util.ParseUtil;
import java.math.BigDecimal;
import java.sql.Date;

public class Book {
    private int id;
    private String title; //书名
    private String author; //作者
    private String press; //出版社
    private BigDecimal price; //价格
    private int sale; //折扣
    private int stock; //库存
    private String coverUrl; //封面图片路径
    private String info; //简介
    private Date publishDate; //出版日期，xxxx年x月
    private Date marketDate; //上架日期，xxxx年x月x日

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public BigDecimal getSalePrice(){
        return price.multiply(new BigDecimal(sale)).divide(new BigDecimal(100));
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {this.coverUrl = coverUrl;}

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = ParseUtil.parseDateLong(publishDate);
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public void setMarketDate(String marketDate) {
        this.marketDate = ParseUtil.parseDateLong(marketDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", price=" + price +
                ", sale=" + sale +
                ", stock=" + stock +
                ", coverUrl='" + coverUrl + '\'' +
                ", info='" + info + '\'' +
                ", publishDate=" + publishDate +
                ", marketDate=" + marketDate +
                '}';
    }
}
