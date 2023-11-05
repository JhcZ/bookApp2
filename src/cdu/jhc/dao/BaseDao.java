package cdu.jhc.dao;

import java.sql.*;

public class BaseDao {
    String driver = "com.mysql.cj.jdbc.Driver";  //驱动程序名称
    String url =  "jdbc:mysql://localhost:3306/mydb?user=root&password=123456";  //JDBC UI

    protected Connection conn;
    protected PreparedStatement pstmt;
    protected Statement stmt;
    protected ResultSet rs;

    public BaseDao(){
        connect();
    }

    //数据库连接
    public void connect(){
        try {
            Class.forName(driver).newInstance();     //加载驱动程序
            conn = DriverManager.getConnection(url);   //创建链接
        } catch (Exception e) {
            System.out.println("DAO连接数据库异常：" + e.getMessage());;
        }
    }

    public void close(){
        try{
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
            if(pstmt != null && !pstmt.isClosed()){
                pstmt.close();
            }
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("DAO关闭数据库异常：" + e.getMessage());
        }
    }
}
