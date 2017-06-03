package edu.ql.dng.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import edu.ql.dng.model.User;

public class SAXFactory {
	public static List<User> read() {
		SAXParserFactory saxF = SAXParserFactory.newInstance();
		List<User> users =  new ArrayList<User>();
		String filePath = "user.xml";
		try {
			SAXParser parser = saxF.newSAXParser();
			SAXReadXML handler = new SAXReadXML();
			parser.parse(filePath, handler);
			users =  handler.getUsers();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static void write(User user){
	   List<User> users = read();
	   if(user==null) return;
	   if(users==null){
		   users = new ArrayList<User>();
	   }
       users.add(user);
	   SAXWriteXML.write(users);
	}
	public static void writeList(List<User> uList){
		 if(uList==null) return ;
		 SAXWriteXML.write(uList);
	}
	public static void main(String[] args) {
		List<User> users = read();
		System.out.println(users.toString());
		User user = new User();
		user.setCreateDate("2017-05-01");
		user.setEmail("12345@122.com");
		user.setInstallNum("installnum123");
		user.setInstruction("instruction123");
		user.setPassword("1231444");
		user.setUserName("nameDang");
		user.setRegin("ÖÐ¹ú");
		write(user);
	}
}
