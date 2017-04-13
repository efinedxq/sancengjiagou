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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
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
