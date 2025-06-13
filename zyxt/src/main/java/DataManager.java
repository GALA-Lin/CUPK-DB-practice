import java.sql.*;

public class DataManager {

    // 添加数据 - 以作业预算表为例
    public static void addBudgetRecord(String billNo, String unitCode, String wellNo,
                                       double budgetAmount, String budgetPerson, Date budgetDate) {
        String sql = "INSERT INTO 作业预算表 (单据号, 预算单位, 井号, 预算金额, 预算人, 预算日期) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, billNo);
            ps.setString(2, unitCode);
            ps.setString(3, wellNo);
            ps.setDouble(4, budgetAmount);
            ps.setString(5, budgetPerson);
            ps.setDate(6, budgetDate);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    // 更新数据 - 以作业预算表为例
    public static void updateBudgetRecord(String billNo, String unitCode, String wellNo,
                                          double budgetAmount, String budgetPerson, Date budgetDate) {
        String sql = "UPDATE 作业预算表 SET 预算单位 = ?, 井号 = ?, 预算金额 = ?, " +
                "预算人 = ?, 预算日期 = ? WHERE 单据号 = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, unitCode);
            ps.setString(2, wellNo);
            ps.setDouble(3, budgetAmount);
            ps.setString(4, budgetPerson);
            ps.setDate(5, budgetDate);
            ps.setString(6, billNo);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    // 删除数据 - 以作业预算表为例
    public static void deleteBudgetRecord(String billNo) {
        String sql = "DELETE FROM 作业预算表 WHERE 单据号 = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, billNo);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
    }

    // 其他表的增删改类似实现...
}