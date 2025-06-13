import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.Date;

public class MainFrame extends JFrame {

    // 构造函数
    public MainFrame() {
        setTitle("采油厂油水井作业成本管理系统");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建菜单栏
        createMenuBar();

        // 创建主面板，使用选项卡
        createTabbedPane();
    }

    // 创建菜单栏
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // 数据管理菜单
        JMenu dataMenu = new JMenu("数据管理");
        JMenuItem addBudgetItem = new JMenuItem("添加预算记录");
        JMenuItem updateBudgetItem = new JMenuItem("修改预算记录");
        JMenuItem deleteBudgetItem = new JMenuItem("删除预算记录");

        addBudgetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBudgetDialog();
            }
        });

        updateBudgetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateBudgetDialog();
            }
        });

        deleteBudgetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteBudgetDialog();
            }
        });

        dataMenu.add(addBudgetItem);
        dataMenu.add(updateBudgetItem);
        dataMenu.add(deleteBudgetItem);

        // 查询菜单
        JMenu queryMenu = new JMenu("数据查询");
        JMenuItem query1Item = new JMenuItem("采油一矿二队预算明细");
        JMenuItem query2Item = new JMenuItem("采油一矿二队结算明细");
        JMenuItem query3Item = new JMenuItem("采油一矿二队材料费明细");
        JMenuItem query4Item = new JMenuItem("采油一矿二队入账明细");
        JMenuItem query5Item = new JMenuItem("采油一矿二队总预算金额");
        JMenuItem query6Item = new JMenuItem("采油一矿二队总结算金额");
        JMenuItem query7Item = new JMenuItem("采油一矿二队总入账金额");
        JMenuItem query8Item = new JMenuItem("采油一矿总入账金额");
        JMenuItem query9Item = new JMenuItem("入账操作人员");
        JMenuItem query10Item = new JMenuItem("已结算未入账项目");
        JMenuItem query11Item = new JMenuItem("采油一矿二队项目排序");
        JMenuItem query12Item = new JMenuItem("施工单位结算汇总");
        JMenuItem query13Item = new JMenuItem("材料三超支项目");
        JMenuItem query14Item = new JMenuItem("作业公司二队项目");
        JMenuItem query15Item = new JMenuItem("作业公司一队和二队项目");
        JMenuItem query16Item = new JMenuItem("采油一矿油井施工单位");

        query1Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿二队预算明细", QueryManager.queryBudgetDetails());
            }
        });

        query2Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿二队结算明细", QueryManager.querySettlementDetails());
            }
        });

        query3Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿二队材料费明细", QueryManager.queryMaterialCostDetails());
            }
        });

        query4Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿二队入账明细", QueryManager.queryAccountingDetails());
            }
        });

        query5Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = QueryManager.getTotalBudgetAmount();
                JOptionPane.showMessageDialog(null, "采油一矿二队总预算金额: " + total);
            }
        });

        query6Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = QueryManager.getTotalSettlementAmount();
                JOptionPane.showMessageDialog(null, "采油一矿二队总结算金额: " + total);
            }
        });

        query7Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = QueryManager.getTotalAccountingAmount();
                JOptionPane.showMessageDialog(null, "采油一矿二队总入账金额: " + total);
            }
        });

        query8Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = QueryManager.getTotalAccountingAmountForMine();
                JOptionPane.showMessageDialog(null, "采油一矿总入账金额: " + total);
            }
        });

        query9Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> personnel = QueryManager.getAccountingPersonnel();
                String result = "参与入账操作的人员:\n";
                for (String person : personnel) {
                    result += person + "\n";
                }
                JOptionPane.showMessageDialog(null, result);
            }
        });

        query10Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("已结算未入账项目", QueryManager.getSettledButNotAccountedProjects());
            }
        });

        query11Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿二队项目排序", QueryManager.getProjectsOrderByAccountingAmount());
            }
        });

        query12Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("施工单位结算汇总", QueryManager.getConstructionUnitsWithTotalSettlement());
            }
        });

        query13Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("材料三超支项目", QueryManager.getProjectsExceedingMaterialCost());
            }
        });

        query14Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("作业公司二队项目", QueryManager.getProjectsBySecondTeam());
            }
        });

        query15Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("作业公司一队和二队项目", QueryManager.getProjectsByFirstAndSecondTeam());
            }
        });

        query16Item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueryResults("采油一矿油井施工单位", QueryManager.getTeamsWorkingOnMine1OilWells());
            }
        });

        queryMenu.add(query1Item);
        queryMenu.add(query2Item);
        queryMenu.add(query3Item);
        queryMenu.add(query4Item);
        queryMenu.add(query5Item);
        queryMenu.add(query6Item);
        queryMenu.add(query7Item);
        queryMenu.add(query8Item);
        queryMenu.add(query9Item);
        queryMenu.add(query10Item);
        queryMenu.add(query11Item);
        queryMenu.add(query12Item);
        queryMenu.add(query13Item);
        queryMenu.add(query14Item);
        queryMenu.add(query15Item);
        queryMenu.add(query16Item);

        menuBar.add(dataMenu);
        menuBar.add(queryMenu);

        setJMenuBar(menuBar);
    }

    // 创建选项卡面板
    private void createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // 预算管理选项卡
        JPanel budgetPanel = new JPanel();
        budgetPanel.setLayout(new BorderLayout());
        budgetPanel.add(new JLabel("预算管理面板"), BorderLayout.CENTER);
        tabbedPane.addTab("预算管理", budgetPanel);

        // 结算管理选项卡
        JPanel settlementPanel = new JPanel();
        settlementPanel.setLayout(new BorderLayout());
        settlementPanel.add(new JLabel("结算管理面板"), BorderLayout.CENTER);
        tabbedPane.addTab("结算管理", settlementPanel);

        // 入账管理选项卡
        JPanel accountingPanel = new JPanel();
        accountingPanel.setLayout(new BorderLayout());
        accountingPanel.add(new JLabel("入账管理面板"), BorderLayout.CENTER);
        tabbedPane.addTab("入账管理", accountingPanel);

        // 查询结果显示选项卡
        JPanel queryResultPanel = new JPanel();
        queryResultPanel.setLayout(new BorderLayout());
        queryResultPanel.add(new JLabel("查询结果显示"), BorderLayout.CENTER);
        tabbedPane.addTab("查询结果", queryResultPanel);

        add(tabbedPane);
    }

    // 显示查询结果
    private void showQueryResults(String title, List<Object[]> results) {
        // 创建表头
        String[] headers = null;
        if (!results.isEmpty()) {
            headers = new String[results.get(0).length];
            for (int i = 0; i < headers.length; i++) {
                headers[i] = "字段" + (i + 1);
            }
        }

        // 创建表格数据
        Object[][] data = new Object[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            data[i] = results.get(i);
        }

        // 创建表格
        JTable table = new JTable(data, headers);

        // 显示在对话框中
        JFrame frame = new JFrame(title);
        frame.setSize(800, 600);
        frame.add(new JScrollPane(table));
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    // 主方法
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}

// 添加预算记录对话框
class AddBudgetDialog extends JDialog {
    public AddBudgetDialog() {
        setTitle("添加预算记录");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // 表单元素
        JLabel billNoLabel = new JLabel("单据号:");
        JTextField billNoField = new JTextField();

        JLabel unitCodeLabel = new JLabel("预算单位代码:");
        JTextField unitCodeField = new JTextField();

        JLabel wellNoLabel = new JLabel("井号:");
        JTextField wellNoField = new JTextField();

        JLabel amountLabel = new JLabel("预算金额:");
        JTextField amountField = new JTextField();

        JLabel personLabel = new JLabel("预算人:");
        JTextField personField = new JTextField();

        JLabel dateLabel = new JLabel("预算日期:");
        JTextField dateField = new JTextField("yyyy-MM-dd");

        JButton addButton = new JButton("添加");
        JButton cancelButton = new JButton("取消");

        panel.add(billNoLabel);
        panel.add(billNoField);
        panel.add(unitCodeLabel);
        panel.add(unitCodeField);
        panel.add(wellNoLabel);
        panel.add(wellNoField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(personLabel);
        panel.add(personField);
        panel.add(dateLabel);
        panel.add(dateField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String billNo = billNoField.getText();
                    String unitCode = unitCodeField.getText();
                    String wellNo = wellNoField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    String person = personField.getText();
                    Date date = Date.valueOf(dateField.getText());

                    DataManager.addBudgetRecord(billNo, unitCode, wellNo, amount, person, date);
                    JOptionPane.showMessageDialog(null, "添加成功!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "输入有误: " + ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}

// 修改预算记录对话框
class UpdateBudgetDialog extends JDialog {
    public UpdateBudgetDialog() {
        setTitle("修改预算记录");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);

        // 表单元素
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel billNoLabel = new JLabel("单据号:");
        JTextField billNoField = new JTextField();

        JLabel unitCodeLabel = new JLabel("预算单位代码:");
        JTextField unitCodeField = new JTextField();

        JLabel wellNoLabel = new JLabel("井号:");
        JTextField wellNoField = new JTextField();

        JLabel amountLabel = new JLabel("预算金额:");
        JTextField amountField = new JTextField();

        JLabel personLabel = new JLabel("预算人:");
        JTextField personField = new JTextField();

        JLabel dateLabel = new JLabel("预算日期:");
        JTextField dateField = new JTextField("yyyy-MM-dd");

        JButton queryButton = new JButton("查询");
        JButton updateButton = new JButton("更新");
        JButton cancelButton = new JButton("取消");

        panel.add(billNoLabel);
        panel.add(billNoField);
        panel.add(unitCodeLabel);
        panel.add(unitCodeField);
        panel.add(wellNoLabel);
        panel.add(wellNoField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(personLabel);
        panel.add(personField);
        panel.add(dateLabel);
        panel.add(dateField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(queryButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 这里应该实现根据单据号查询现有记录并填充表单
                // 为简化示例，暂时不实现
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String billNo = billNoField.getText();
                    String unitCode = unitCodeField.getText();
                    String wellNo = wellNoField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    String person = personField.getText();
                    Date date = Date.valueOf(dateField.getText());

                    DataManager.updateBudgetRecord(billNo, unitCode, wellNo, amount, person, date);
                    JOptionPane.showMessageDialog(null, "更新成功!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "输入有误: " + ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}

// 删除预算记录对话框
class DeleteBudgetDialog extends JDialog {
    public DeleteBudgetDialog() {
        setTitle("删除预算记录");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel billNoLabel = new JLabel("单据号:");
        JTextField billNoField = new JTextField();

        panel.add(billNoLabel);
        panel.add(billNoField);

        JButton deleteButton = new JButton("删除");
        JButton cancelButton = new JButton("取消");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String billNo = billNoField.getText();

                    DataManager.deleteBudgetRecord(billNo);
                    JOptionPane.showMessageDialog(null, "删除成功!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "删除失败: " + ex.getMessage());
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}