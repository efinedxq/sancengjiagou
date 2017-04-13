package edu.ql.dng.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowFilter.Entry;

import edu.ql.dng.model.User;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class MainWindow extends JFrame {
	private JPanel jpMain;
	private JLabel jlName;
	private JLabel jlRegin;
	private JLabel jlEmail;
	private JLabel jlDate;
	private JLabel jlInstall;
	private JLabel jlInstruction;
	private JLabel jlPassword;
	private JTextField jtfName;
	private JTextField jtfRegin;
	private JTextField jtfEmail;
	private JTextField jtfDate;
	private JTextArea jtaInstall;
	private JTextField jtfInstruction;
	private JTextField jtfPass;
	private JButton jbAdd;
	private JButton jbModify;
	private JButton jbDelet;
	private JButton jbQuery;
	
	private JTable jtShow;
	private JScrollPane scroll;
	private Object data[][];
	private Object columnName[] = {"客户名称","国家/地区","Email","申请日期","安装码","初始密码","计算机说明"};
    private ArrayList<User> users;
    private HashSet<Integer> removedRowIndices;
	
	
	public MainWindow() {
		super("启动密码管理系统");
		jpMain = new JPanel();

		jlName = new JLabel("客户名称：");
		jlRegin = new JLabel("国家/地区：");
		jlEmail = new JLabel("电子邮件：");
		jlDate = new JLabel("密码申请日期：");
		jlInstall = new JLabel("安装码：");
		jlInstruction = new JLabel("计算机说明：");
		jlPassword = new JLabel("初始密码：");

		jtfName = new JTextField();
		jtfRegin = new JTextField();
		jtfEmail = new JTextField();
		jtfDate = new JTextField();
		jtfInstruction = new JTextField();
		jtfPass = new JTextField();
		jtaInstall = new JTextArea();

		jbAdd = new JButton("添 加");
		jbModify = new JButton("修 改");
		jbDelet = new JButton("删 除");
		jbQuery = new JButton("查 询");

		//。。。获取数据
		getData();
		//建立模型
		TableModel model = new DefaultTableModel(data,columnName){
			//获取某列的数据类型
			public Class<?> getColumnClass(int c){
				return data[0][c].getClass();
			}
		};
		jtShow = new JTable(model){
			public boolean isCellEditable(int row, int column) {				// 表格不可编辑
				return false;
			}
		};
		//设置默认居中
		DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jtShow.setDefaultRenderer(Object.class, r);
		//行排序，行过滤
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
	    jtShow.setRowSorter(sorter);
	    
	    removedRowIndices = new HashSet<>();
	    final RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>() {
			public boolean include(
					Entry<? extends TableModel, ? extends Integer> entry) {
				return !removedRowIndices.contains(entry.getIdentifier());
			}
		};
	    jtShow.setRowHeight(30);
	    jtShow.setRowMargin(5);
	    jtShow.setRowSelectionInterval(0, 0);
	    jtShow.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    scroll = new JScrollPane(jtShow);
	  
		init();
	}

	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 700);
		setLocation(175, 20);

		jpMain.setLayout(null);

		jlName.setBounds(20, 20, 100, 20);
		jlRegin.setBounds(20, 60, 100, 20);
		jlEmail.setBounds(20, 100, 100, 20);
		jlDate.setBounds(20, 140, 100, 20);

		jlInstall.setBounds(500, 20, 100, 20);
		jlInstruction.setBounds(500, 100, 100, 20);
		jlPassword.setBounds(500, 140, 100, 20);

		jtfName.setBounds(120, 20, 200, 20);
		jtfRegin.setBounds(120, 60, 200, 20);
		jtfEmail.setBounds(120, 100, 200, 20);
		jtfDate.setBounds(120, 140, 200, 20);
		jtaInstall.setBounds(600, 20, 200, 70);
		jtfInstruction.setBounds(600, 100, 200, 20);
		jtfPass.setBounds(600, 140, 200, 20);

		jbAdd.setBounds(20, 180, 80, 20);
		jbModify.setBounds(240, 180, 80, 20);
		jbDelet.setBounds(500, 180, 80, 20);
		jbQuery.setBounds(720, 180, 80, 20);
        
		scroll.setBounds(20, 220, 780, 400);
		
		jpMain.add(jlName);
		jpMain.add(jlRegin);
		jpMain.add(jlEmail);
		jpMain.add(jlDate);
		jpMain.add(jlInstruction);
		jpMain.add(jlInstall);
		jpMain.add(jlPassword);

		jpMain.add(jtfName);
		jpMain.add(jtfRegin);
		jpMain.add(jtfEmail);
		jpMain.add(jtfDate);
		jpMain.add(jtfInstruction);
		jpMain.add(jtfPass);
		jpMain.add(jtaInstall);

		jpMain.add(jbAdd);
		jpMain.add(jbModify);
		jpMain.add(jbDelet);
		jpMain.add(jbQuery);

		jpMain.add(scroll);

		add(jpMain);

		setVisible(true);
	}
	
    //启动界面前获取所需数据，这里获取的是用户数据
	private void getData(){
		User user = new User();
		user.setId("1");
		user.setCreateDate("111");
		user.setEmail("222");
		user.setInstallNum("111");
		user.setInstruction("111");
		user.setRegin("111");
		user.setUserName("111");
		user.setPassword("1111");
		//用户数据
		users = new ArrayList<User>();
		users.add(user);
		
		data = new Object[users.size()][7];
		for(int i = 0; i < users.size(); i ++){
			data[i][0] = users.get(i).getUserName();
			data[i][1] = users.get(i).getRegin();
			data[i][2] = users.get(i).getEmail();
			data[i][3] = users.get(i).getCreateDate();
			data[i][4] = users.get(i).getInstallNum();
			data[i][5] = users.get(i).getPassword();
			data[i][6] = users.get(i).getInstruction();
		}
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
