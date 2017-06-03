package edu.ql.dng.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QueryWin extends JFrame{
	private JPanel jpMain;
	private JLabel jlName;
	private JLabel jlRegin;
	private JLabel jlEmail;
	private JLabel jlInstall;
	private JLabel jlInstruction;
	private JTextField jtfName;
	private JTextField jtfRegin;
	private JTextField jtfEmail;
	private JTextArea jtaInstall;
	private JTextField jtfInstruction;
	private JButton jbSubmit;
	private JButton jbCancel;
	
	public QueryWin() {
		// TODO Auto-generated constructor stub
		super("请确认查询条件");
		jpMain = new JPanel();

		jlName = new JLabel("客户名称：");
		jlRegin = new JLabel("国家/地区：");
		jlEmail = new JLabel("电子邮件：");
		jlInstall = new JLabel("安装码：");
		jlInstruction = new JLabel("计算机说明：");
		
		jtfName = new JTextField();
		jtfRegin = new JTextField();
		jtfEmail = new JTextField();
		jtfInstruction = new JTextField();
		jtaInstall = new JTextArea();
		
		jbSubmit = new JButton("提 交");
		jbCancel = new JButton("取 消");
		
		jbSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				submitListener(e);
			}
		});
		jbCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelListener(e);
			}
		});
		init();
	}
	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 300);
		setLocation(400, 250);
		jpMain.setLayout(null);
		
		jlName.setBounds(20, 20, 100, 20);
		jlRegin.setBounds(20, 60, 100, 20);
		jlEmail.setBounds(20, 100, 100, 20);
		jlInstruction.setBounds(20, 140, 100, 20);
		jlInstall.setBounds(340,20,80,20);
		
		jtfName.setBounds(100, 20, 200, 20);
		jtfRegin.setBounds(100, 60, 200, 20);
		jtfEmail.setBounds(100, 100, 200, 20);
		jtfInstruction.setBounds(100, 140, 200, 20);
		jtaInstall.setBounds(400, 20, 200, 140);
		
		jbSubmit.setBounds(180, 200, 80, 20);
		jbCancel.setBounds(450, 200, 80, 20);
		
		jpMain.add(jlName);
		jpMain.add(jlRegin);
		jpMain.add(jlEmail);
		jpMain.add(jlInstruction);
		jpMain.add(jlInstall);

		jpMain.add(jtfName);
		jpMain.add(jtfRegin);
		jpMain.add(jtfEmail);
		jpMain.add(jtfInstruction);
		jpMain.add(jtaInstall);

		jpMain.add(jbSubmit);
		jpMain.add(jbCancel);
		
		add(jpMain);
         
		setVisible(true);
	}
	
	private void submitListener(ActionEvent e){
		
	}
	private void cancelListener(ActionEvent e){
		
	}
	
	public static void main(String[] args) {
		new QueryWin();
	}
}
