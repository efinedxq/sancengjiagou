package edu.ql.dng.ui;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.xml.internal.bind.v2.util.XmlFactory;

import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowFilter.Entry;

import edu.ql.dng.model.User;
import edu.ql.dng.tools.SAXFactory;
import edu.ql.dng.tools.SAXWriteXML;
import sun.swing.table.DefaultTableCellHeaderRenderer;

public class MainWindow extends JFrame {
	private List<User> users;
	private int ROW = -1;
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
	private Object columnName[] = { "�ͻ�����", "����/����", "Email", "��������", "��װ��", "��ʼ����", "�����˵��" };
	private HashSet<Integer> removedRowIndices;

	public MainWindow() {
		super("�����������ϵͳ");
		jpMain = new JPanel();

		jlName = new JLabel("�ͻ����ƣ�");
		jlRegin = new JLabel("����/������");
		jlEmail = new JLabel("�����ʼ���");
		jlDate = new JLabel("�����������ڣ�");
		jlInstall = new JLabel("��װ�룺");
		jlInstruction = new JLabel("�����˵����");
		jlPassword = new JLabel("��ʼ���룺");

		jtfName = new JTextField();
		jtfRegin = new JTextField();
		jtfEmail = new JTextField();
		jtfDate = new JTextField();
		jtfInstruction = new JTextField();
		jtfPass = new JTextField();
		jtaInstall = new JTextArea();

		jbAdd = new JButton("�� ��");
		jbModify = new JButton("�� ��");
		jbDelet = new JButton("ɾ ��");
		jbQuery = new JButton("�� ѯ");

		// ��������ȡ����
		getData();
		// ����ģ��
		TableModel model = new DefaultTableModel(data, columnName) {
			// ��ȡĳ�е���������
			public Class<?> getColumnClass(int c) {
				return data[0][c].getClass();
			}
		};
		jtShow = new JTable(model) {
			public boolean isCellEditable(int row, int column) { // ��񲻿ɱ༭
				return false;
			}
		};
		// ����Ĭ�Ͼ���
		DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jtShow.setDefaultRenderer(Object.class, r);
		// �������й���
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
		jtShow.setRowSorter(sorter);

		removedRowIndices = new HashSet<>();
		final RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>() {
			public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
				return !removedRowIndices.contains(entry.getIdentifier());
			}
		};
		jtShow.setRowHeight(30);
		jtShow.setRowMargin(5);
		jtShow.setRowSelectionInterval(0, 0);
		jtShow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int sr;
                if ((sr = jtShow.getSelectedRow()) == -1) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
//                	System.out.println(e.getX()+"  "+jtShow.getSelectedRow());
                	ROW = jtShow.getSelectedRow();
                	User u = users.get(ROW); 
                	jtfName.setText(u.getUserName());
                	jtfDate.setText(u.getCreateDate());
                	jtfEmail.setText(u.getEmail());
                	jtfInstruction.setText(u.getInstruction());
                	jtaInstall.setText(u.getInstallNum());
                	jtfRegin.setText(u.getRegin());
                	jtfPass.setText(u.getPassword());
                }
            }
		});
		scroll = new JScrollPane(jtShow);

		jbAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addListener(e);
			}
		});
		jbDelet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteListener(e);
			}
		});
		jbModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
              modifyListener(e);
			}
		});
		jbQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                 queryListener(e);
			}
		});
		init();
	}
    //��ƽ���
	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 700);
		setLocation(175, 20);

		jpMain.setLayout(null);

		jlName.setBounds(20, 20, 100, 20);
		jlRegin.setBounds(20, 60, 100, 20);
		jlEmail.setBounds(20, 100, 100, 20);
		jlInstruction.setBounds(20, 140, 100, 20);

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

	// ��������ǰ��ȡ�������ݣ������ȡ�����û�����
	private void getData() {
		// �û�����
		users = SAXFactory.read();

		if (users == null)
			users = new ArrayList<User>();

		data = new Object[users.size()][7];
		for (int i = 0; i < users.size(); i++) {
			data[i][0] = users.get(i).getUserName();
			data[i][1] = users.get(i).getRegin();
			data[i][2] = users.get(i).getEmail();
			data[i][3] = users.get(i).getCreateDate();
			data[i][4] = users.get(i).getInstallNum();
			data[i][5] = users.get(i).getPassword();
			data[i][6] = users.get(i).getInstruction();
		}
	}
 
	
	//���ü���
	private void addListener(ActionEvent e){
        User user = new User();
		String userName = jtfName.getText();
		String regin = jtfRegin.getText();
		String email = jtfEmail.getText();
		String createDate = jtfDate.getText();
		String installNum = jtaInstall.getText();
		String instruction = jtfInstruction.getText();
		String password = jtfPass.getText();
        
		if(userName.isEmpty()){
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(regin.isEmpty()){
			JOptionPane.showMessageDialog(null, "��������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(email.isEmpty()){
			JOptionPane.showMessageDialog(null, "���䲻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(createDate.isEmpty()){
			JOptionPane.showMessageDialog(null, "���ڲ���Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(installNum.isEmpty()){
			JOptionPane.showMessageDialog(null, "��װ�벻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(instruction.isEmpty()){
			JOptionPane.showMessageDialog(null, "�����˵������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(password.isEmpty()){
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		user.setUserName(userName);
		user.setCreateDate(createDate);
		user.setEmail(email);
		user.setInstallNum(installNum);
		user.setInstruction(instruction);
		user.setPassword(password);
		user.setRegin(regin);
        SAXFactory.write(user);
        validate(); 
	}
	private void deleteListener(ActionEvent e){
		users.remove(ROW);
		SAXFactory.writeList(users);
		validate(); 
	}

	private void modifyListener(ActionEvent e) {
		User user = new User();
		String userName = jtfName.getText();
		String regin = jtfRegin.getText();
		String email = jtfEmail.getText();
		String createDate = jtfDate.getText();
		String installNum = jtaInstall.getText();
		String instruction = jtfInstruction.getText();
		String password = jtfPass.getText();
        
		if(userName.isEmpty()){
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(regin.isEmpty()){
			JOptionPane.showMessageDialog(null, "��������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(email.isEmpty()){
			JOptionPane.showMessageDialog(null, "���䲻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(createDate.isEmpty()){
			JOptionPane.showMessageDialog(null, "���ڲ���Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(installNum.isEmpty()){
			JOptionPane.showMessageDialog(null, "��װ�벻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(instruction.isEmpty()){
			JOptionPane.showMessageDialog(null, "�����˵������Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		if(password.isEmpty()){
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
			return ;
		}
		user.setUserName(userName);
		user.setCreateDate(createDate);
		user.setEmail(email);
		user.setInstallNum(installNum);
		user.setInstruction(instruction);
		user.setPassword(password);
		user.setRegin(regin);
		user.setId(ROW+"");
		users.remove(ROW);
		users.add(user);
		SAXFactory.writeList(users);
	}

	private void queryListener(ActionEvent e) {
         new QueryWin();
	}
	public static void main(String[] args) {
		new MainWindow();
	}
}
