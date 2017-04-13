package edu.ql.dng.DB;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.JOptionPane;

public class DBBean {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String dbName = "";
	private String dbUser = "root";
	private String dbpass = "root123";

	public DBBean() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "jdbc驱动连接出错\n"+e.toString(), "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 连接
	 */
	public void prepareConnection() {

		String uri = "jdbc:mysql://localhost:3306/" + dbName
				+ "?characterEncoding=utf8&useSSL=true";
		try {
			con = DriverManager.getConnection(uri, dbUser, dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "数据库连接出错\n"+e.toString(), "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 
	 */
	void close() {
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "内部错误：" + e.toString(), "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
