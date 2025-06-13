import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Contest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/zyxt";
        String user = "root";
        String password = "041225";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("数据库连接成功！");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}