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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return users;
	}
	public static void write(){
		
	}
	public static void update(){
		read();
		write();
	}
}
