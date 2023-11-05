
import java.sql.*;

/*
public class Test {
    String driver = "com.mysql.cj.jdbc.Driver";  //驱动程序名称
    String url =  "jdbc:mysql://localhost:3306/studentsdb?user=root&password=123456";  //JDBC UI

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public void connect(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            String query = "SELECT coverUrl FROM books";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()){
                String coverUrl = rs.getString("coverUrl");
                System.out.println("Cover Url" + coverUrl);
            }

            rs.close();
            pstmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println("数据库连接异常" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.connect();
    }
}
*/

