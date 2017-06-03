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
		super("�û�����ϵͳ-��¼");
		jpLogin = new JPanel();
		jlPass = new JLabel("���������Ա���룺");
		jtfPass = new JTextField();
		jbSubmit = new JButton("�� ¼");
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
				// TODO �Զ����ɵķ������
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
			JOptionPane.showMessageDialog(null, "����Ϊ��", "����",
					JOptionPane.WARNING_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null, "�������", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
