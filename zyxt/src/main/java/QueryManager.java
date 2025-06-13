import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryManager {

    // ⑴ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了预算，列出相应明细
    public static List<Object[]> queryBudgetDetails() {
        String sql = "SELECT yb.单据号, yb.预算金额, yb.预算人, yb.预算日期, " +
                "uc.单位名称 AS 预算单位名称, ow.井号, ow.井别 " +
                "FROM 作业预算表 yb " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "JOIN 油水井表 ow ON yb.井号 = ow.井号 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND yb.预算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑵ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了结算，列出相应明细
    public static List<Object[]> querySettlementDetails() {
        String sql = "SELECT js.单据号, js.结算金额, js.结算人, js.结算日期, " +
                "uc.单位名称 AS 预算单位名称, js.施工单位, js.施工内容, " +
                "js.开工日期, js.完工日期 " +
                "FROM 作业结算表 js " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND js.结算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑶ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了结算，列出相应的材料费消耗明细
    public static List<Object[]> queryMaterialCostDetails() {
        String sql = "SELECT js.单据号, js.结算金额, mf.物码, wm.名称规格, mf.消耗数量, mf.单价, " +
                "(mf.消耗数量 * mf.单价) AS 材料费用 " +
                "FROM 作业结算表 js " +
                "JOIN 材料费明细表 mf ON js.单据号 = mf.单据号 " +
                "JOIN 物码表 wm ON mf.物码 = wm.物码 " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND js.结算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑷ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了入账，列出相应明细
    public static List<Object[]> queryAccountingDetails() {
        String sql = "SELECT yr.单据号, yr.入账金额, yr.入账人, yr.入账日期, " +
                "js.结算金额, js.施工单位, js.施工内容 " +
                "FROM 作业入账表 yr " +
                "JOIN 作业结算表 js ON yr.单据号 = js.单据号 " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND yr.入账日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑸ 列出采油一矿二队2018-5-1到2018-5-28总的预算金额
    public static double getTotalBudgetAmount() {
        String sql = "SELECT SUM(预算金额) AS 总预算金额 " +
                "FROM 作业预算表 yb " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND yb.预算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑹ 列出采油一矿二队2018-5-1到2018-5-28总的结算金额
    public static double getTotalSettlementAmount() {
        String sql = "SELECT SUM(结算金额) AS 总结算金额 " +
                "FROM 作业结算表 js " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND js.结算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑺ 列出采油一矿二队2018-5-1到2018-5-28总的入账金额
    public static double getTotalAccountingAmount() {
        String sql = "SELECT SUM(入账金额) AS 总入账金额 " +
                "FROM 作业入账表 yr " +
                "JOIN 作业结算表 js ON yr.单据号 = js.单据号 " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "AND yr.入账日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑻ 列出采油一矿2018-5-1到2018-5-28总的入账金额
    public static double getTotalAccountingAmountForMine() {
        String sql = "SELECT SUM(入账金额) AS 总入账金额 " +
                "FROM 作业入账表 yr " +
                "JOIN 作业结算表 js ON yr.单据号 = js.单据号 " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 LIKE '采油一矿%' " +
                "AND yr.入账日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑼ 有哪些人员参与了入账操作
    public static List<String> getAccountingPersonnel() {
        String sql = "SELECT DISTINCT 入账人 FROM 作业入账表";
        List<String> personnel = new ArrayList<>();

        List<Object[]> results = executeQuery(sql);
        for (Object[] row : results) {
            personnel.add((String)row[0]);
        }

        return personnel;
    }

    // ⑽ 列出2018-5-1到2018-5-28进行了结算但未入账的项目
    public static List<Object[]> getSettledButNotAccountedProjects() {
        String sql = "SELECT js.单据号, js.结算金额, js.施工单位, js.施工内容, js.结算日期 " +
                "FROM 作业结算表 js " +
                "LEFT JOIN 作业入账表 yr ON js.单据号 = yr.单据号 " +
                "WHERE yr.单据号 IS NULL " +
                "AND js.结算日期 BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑾ 列出采油一矿二队的所有项目，按入账金额从高到低排列
    public static List<Object[]> getProjectsOrderByAccountingAmount() {
        String sql = "SELECT yb.单据号, yb.预算金额, js.结算金额, yr.入账金额, " +
                "js.施工单位, js.施工内容, yb.预算日期 " +
                "FROM 作业预算表 yb " +
                "LEFT JOIN 作业结算表 js ON yb.单据号 = js.单据号 " +
                "LEFT JOIN 作业入账表 yr ON yb.单据号 = yr.单据号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 = '采油一矿二队' " +
                "ORDER BY COALESCE(yr.入账金额, 0) DESC";

        return executeQuery(sql);
    }

    // ⑿ 列出有哪些施工单位实施了项目，并计算各单位所有项目结算金额总和
    public static List<Object[]> getConstructionUnitsWithTotalSettlement() {
        String sql = "SELECT 施工单位, SUM(结算金额) AS 总结算金额 " +
                "FROM 作业结算表 " +
                "GROUP BY 施工单位";

        return executeQuery(sql);
    }

    // ⒀ 找出消耗了材料三且消耗超过了2000元的项目，列出相应消耗明细
    public static List<Object[]> getProjectsExceedingMaterialCost() {
        String sql = "SELECT js.单据号, js.施工单位, js.施工内容, wm.名称规格 AS 材料名称, " +
                "mf.消耗数量, mf.单价, (mf.消耗数量 * mf.单价) AS 材料费用 " +
                "FROM 材料费明细表 mf " +
                "JOIN 物码表 wm ON mf.物码 = wm.物码 " +
                "JOIN 作业结算表 js ON mf.单据号 = js.单据号 " +
                "WHERE wm.名称规格 = '材料三' " +
                "AND (mf.消耗数量 * mf.单价) > 2000";

        return executeQuery(sql);
    }

    // ⒁ 作业公司二队参与了哪些项目
    public static List<Object[]> getProjectsBySecondTeam() {
        String sql = "SELECT 单据号, 施工单位, 施工内容, 开工日期, 完工日期, 结算金额 " +
                "FROM 作业结算表 " +
                "WHERE 施工单位 = '作业公司作业二队'";

        return executeQuery(sql);
    }

    // ⒂ 作业公司一队和二队参与了哪些项目（利用union）
    public static List<Object[]> getProjectsByFirstAndSecondTeam() {
        String sql = "SELECT 单据号, 施工单位, 施工内容, 开工日期, 完工日期, 结算金额 " +
                "FROM 作业结算表 " +
                "WHERE 施工单位 = '作业公司作业一队' " +
                "UNION " +
                "SELECT 单据号, 施工单位, 施工内容, 开工日期, 完工日期, 结算金额 " +
                "FROM 作业结算表 " +
                "WHERE 施工单位 = '作业公司作业二队'";

        return executeQuery(sql);
    }

    // ⒃ 采油一矿的油井是哪些作业队参与施工的
    public static List<Object[]> getTeamsWorkingOnMine1OilWells() {
        String sql = "SELECT DISTINCT js.施工单位 " +
                "FROM 作业结算表 js " +
                "JOIN 作业预算表 yb ON js.单据号 = yb.单据号 " +
                "JOIN 油水井表 ow ON yb.井号 = ow.井号 " +
                "JOIN 单位代码表 uc ON yb.预算单位 = uc.单位代码 " +
                "WHERE uc.单位名称 LIKE '采油一矿%' AND ow.井别 = '油井'";

        return executeQuery(sql);
    }

    // 执行查询的通用方法
    private static List<Object[]> executeQuery(String sql) {
        List<Object[]> resultList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                resultList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return resultList;
    }

    // 获取单个值的通用方法
    private static double getSingleValue(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double result = 0.0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return result;
    }
}