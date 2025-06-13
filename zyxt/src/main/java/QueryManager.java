import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryManager {

    // ⑴ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了预算，列出相应明细
    public static List<Object[]> queryBudgetDetails() {
        String sql = "SELECT `单据号`, `预算金额`, `预算人`, `预算日期`"+
                "FROM `作业预算表`" +
                "JOIN `单位代码表` ON `预算单位` = `单位代码表`.`单位代码` " +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `预算日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑵ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了结算，列出相应明细
    public static List<Object[]> querySettlementDetails() {
        String sql = "SELECT `作业结算表`.`单据号`, `结算金额`, `结算人`, `结算日期`, " +
                "`单位名称` AS `预算单位名称`, `施工内容`, " +
                "`开工日期`, `完工日期` " +
                "FROM `作业结算表`" +
                "JOIN `作业预算表` ON `作业预算表`.`单据号` = `作业结算表`.`单据号`" +
                "JOIN `单位代码表` ON `预算单位` = `单位代码`" +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `结算日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑶ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了结算，列出相应的材料费消耗明细
    public static List<Object[]> queryMaterialCostDetails() {
        String sql = "SELECT `作业结算表`.`单据号`, `结算金额`, `材料费明细表`.`物码`, `名称规格`, `消耗数量`, `单价`," +
                "(`消耗数量` * `单价`) AS 材料费用,`单位名称` " +
                "FROM `作业结算表`" +
                "JOIN `材料费明细表` ON `材料费明细表`.`单据号` = `作业结算表`.`单据号`" +
                "JOIN `物码表` ON `物码表`.`物码` = `材料费明细表`.`物码`" +
                "JOIN `作业预算表` ON `作业预算表`.`单据号` = `作业结算表`.`单据号`" +
                "JOIN `单位代码表` ON `作业预算表`.`预算单位` = `单位代码表`.`单位代码`" +
                "WHERE `单位名称` = '采油一矿二队'" +
                "AND `结算日期` BETWEEN '2018-05-01' AND '2018-05-28'";
        return executeQuery(sql);
    }

    // ⑷ 采油一矿二队2018-5-1到2018-5-28有哪些项目完成了入账，列出相应明细
    public static List<Object[]> queryAccountingDetails() {
        String sql = "SELECT `作业入账表`.`单据号`, `入账金额`, `入账人`, `入账日期`, " +
                "`结算金额`,  `施工内容`,`单位名称` " +
                "FROM `作业入账表`" +
                "JOIN `作业结算表` ON `作业入账表`.`单据号` = `作业结算表`.`单据号` " +
                "JOIN `作业预算表` ON `作业入账表`.`单据号` = `作业预算表`.`单据号` " +
                "JOIN `单位代码表` ON `作业预算表`.`预算单位` = `单位代码表`.`单位代码` " +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `入账日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return executeQuery(sql);
    }

    // ⑸ 列出采油一矿二队2018-5-1到2018-5-28总的预算金额
    public static double getTotalBudgetAmount() {
        String sql = "SELECT  SUM(预算金额) AS 总预算金额 " +
                "FROM `作业预算表`" +
                "JOIN `单位代码表` ON `预算单位` = `单位代码` " +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `预算日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑹ 列出采油一矿二队2018-5-1到2018-5-28总的结算金额
    public static double getTotalSettlementAmount() {
        String sql = "SELECT  SUM(结算金额) AS 总结算金额 " +
                "FROM `作业结算表`" +
                "JOIN `作业预算表` ON `作业预算表`.`单据号` = `作业结算表`.`单据号` " +
                "JOIN `单位代码表` ON `作业预算表`.`预算单位` = `单位代码` " +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `结算日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑺ 列出采油一矿二队2018-5-1到2018-5-28总的入账金额
    public static double getTotalAccountingAmount() {
        String sql = "SELECT SUM(入账金额) AS 总入账金额 " +
                "FROM `作业入账表`" +
                "JOIN `作业结算表` ON `作业结算表`.`单据号` = `作业入账表`.`单据号` " +
                "JOIN `作业预算表` ON `作业预算表`.`单据号` = `作业入账表`.`单据号`" +
                "JOIN `单位代码表` ON `预算单位` = `单位代码` " +
                "WHERE `单位名称` = '采油一矿二队' " +
                "AND `入账日期` BETWEEN '2018-05-01' AND '2018-05-28'";

        return getSingleValue(sql);
    }

    // ⑻ 列出采油一矿2018-5-1到2018-5-28总的入账金额
    public static double getTotalAccountingAmountForMine() {
        String sql = "SELECT SUM(入账金额) AS 总入账金额 " +
                "FROM `作业入账表`" +
                "JOIN `作业结算表` ON `作业结算表`.`单据号` = `作业入账表`.`单据号` " +
                "JOIN `作业预算表` ON `作业预算表`.`单据号` = `作业入账表`.`单据号`" +
                "JOIN `单位代码表` ON `预算单位` = `单位代码` " +
                "WHERE `单位名称` LIKE '采油一矿%' " +
                "AND `入账日期` BETWEEN '2018-05-01' AND '2018-05-28'";
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
        String sql = "SELECT `作业结算表`.`单据号`, `结算金额`, `结算日期` " +
                "FROM `作业结算表` " +
                "LEFT JOIN `作业入账表` ON `作业结算表`.`单据号` = `作业入账表`.`单据号` " +
                "WHERE `作业入账表`.`单据号` IS NULL " +
                "AND `结算日期` BETWEEN '2018-05-01' AND '2018-05-28'";
        return executeQuery(sql);
    }

    // ⑾ 列出采油一矿二队的所有项目，按入账金额从高到低排列
    public static List<Object[]> getProjectsOrderByAccountingAmount() {
        String sql = "SELECT `作业预算表`.`单据号`, `预算金额`, `结算金额` , `入账金额`, " +
                " `施工内容` , `预算日期`,`单位名称` " +
                "FROM `作业预算表` " +
                "LEFT JOIN `作业结算表` ON `作业预算表`.`单据号` = `作业结算表`.`单据号` " +
                "LEFT JOIN `作业入账表` ON `作业预算表`.`单据号` = `作业入账表`.`单据号` " +
                "JOIN `单位代码表` ON `作业预算表`.`预算单位` = `单位代码表`.`单位代码` " +
                "WHERE `单位代码表`.`单位名称` = '采油一矿二队' " +
                "ORDER BY COALESCE(`入账金额`,4) DESC";

        return executeQuery(sql);
    }

    // ⑿ 列出有哪些`作业结算表`实施了项目，并计算各单位所有项目结算金额总和
    public static List<Object[]> getConstructionUnitsWithTotalSettlement() {
        String sql = "SELECT  `施工单位`,SUM(结算金额) AS 总结算金额 " +
                "FROM `作业结算表` " +
                "GROUP BY `施工单位`";

        return executeQuery(sql);
    }

    // ⒀ 找出消耗了材料三且消耗超过了2000元的项目，列出相应消耗明细
    public static List<Object[]> getProjectsExceedingMaterialCost() {
        String sql = "SELECT `材料费明细表`.`单据号`, `施工内容`, `名称规格` AS `材料名称`, " +
                "`消耗数量`, `单价`, (`消耗数量` * `单价`) AS `材料费用` " +
                "FROM `材料费明细表` " +
                "JOIN `物码表` ON `材料费明细表`.`物码` = `物码表`.`物码`" +
                "JOIN `作业结算表` ON `作业结算表`.`单据号` = `材料费明细表`.`单据号` " +
                "WHERE `名称规格` = '材料三' " +
                "AND (`消耗数量` * `单价`) > 2000";

        return executeQuery(sql);
    }

    // ⒁ 作业公司二队参与了哪些项目
    public static List<Object[]> getProjectsBySecondTeam() {
        String sql = "SELECT `单据号`, `施工单位`, `施工内容`,`开工日期`, `完工日期`, `结算金额` " +
                "FROM `作业结算表` " +
                "WHERE `作业结算表`. `施工单位` = '作业公司作业二队'";

        return executeQuery(sql);
    }

    // ⒂ 作业公司一队和二队参与了哪些项目（利用union）
    public static List<Object[]> getProjectsByFirstAndSecondTeam() {
        String sql = "SELECT `作业结算表`.`单据号`, `施工单位`,`施工内容`, `开工日期`, `完工日期`, `结算金额` " +
                "FROM `作业结算表` " +
                "WHERE `作业结算表`. `施工单位` = '作业公司作业一队'" +
                "UNION " +
                "SELECT `作业结算表`.`单据号` , `施工单位`,`施工内容`, `开工日期`, `完工日期`, `结算金额` " +
                "FROM `作业结算表` " +
                "WHERE `作业结算表`. `施工单位` = '作业公司作业二队'";

        return executeQuery(sql);
    }

    // ⒃ 采油一矿的油井是哪些作业队参与施工的
    public static List<Object[]> getTeamsWorkingOnMine1OilWells() {
        String sql = "SELECT DISTINCT `单位名称`,`施工单位` " +
                "FROM `作业结算表`" +
                "JOIN `作业预算表` ON `作业结算表`.`单据号` = `作业预算表`.`单据号` " +
                "JOIN `油水井表` ON `作业预算表`.`井号` = `油水井表`.`井号` " +
                "JOIN `单位代码表` ON `作业预算表`.`预算单位` = `单位代码表`.`单位代码` " +
                "WHERE `单位名称` LIKE '采油一矿%' AND `井别` = '油井'";

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