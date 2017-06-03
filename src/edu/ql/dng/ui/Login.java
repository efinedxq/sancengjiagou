package edu.ql.dng.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ql.dng.common.Constant;

public class Login extends JFrame{
	private JPanel jpLogin;
	private JLabel jlPass;
	private JTextField jtfPass;
	private JButton jbSubmit;
	
	public Login(){
		super("用户管理系统-登录");
		jpLogin = new JPanel();
		jlPass = new JLabel("请输入管理员密码：");
		jtfPass = new JTextField();
		jbSubmit = new JButton("登 录");
		init();
	}
	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocation(500, 200);
		jpLogin.setLayout(null);
		
		jlPass.setBounds(50, 20, 150, 20);
		jtfPass.setBounds(60, 55, 180, 20);
		jbSubmit.setBounds(110, 90, 80, 20);
		
		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				btnActionEvent(e);	
			}
		});
		
		jpLogin.add(jlPass);
		jpLogin.add(jtfPass);
		jpLogin.add(jbSubmit);
		
		add(jpLogin);
		setVisible(true);
	}
	
	public void btnActionEvent(ActionEvent e) {
		String pw = jtfPass.getText();
		if(pw.equals(Constant.PW)){
			this.dispose();
			new MainWindow();
		}
		else if(pw.isEmpty()){
			JOptionPane.showMessageDialog(null, "不能为空", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "密码错误", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
