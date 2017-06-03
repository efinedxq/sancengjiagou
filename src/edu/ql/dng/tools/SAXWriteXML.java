package edu.ql.dng.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import edu.ql.dng.common.Constant;
import edu.ql.dng.model.User;

public class SAXWriteXML {
	public static void write(List<User> userList) {
		String filePath = "user.xml";

		Result resultXml;
		try {
			resultXml = new StreamResult(new FileOutputStream(filePath));
			SAXTransformerFactory sff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			TransformerHandler th;
			th = sff.newTransformerHandler();
			th.setResult(resultXml);
			
			Transformer transformer = th.getTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // 编码格式是UTF-8
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 换行
			
			th.startDocument();
			AttributesImpl atts = new AttributesImpl();
			th.startElement("", "", Constant.USERS, atts);
			AttributesImpl att;
			for(int i = 0; i < userList.size(); i ++){
				User user = userList.get(i);
			    att = new AttributesImpl();
//				att.setValue(0, "" + i);
				th.startElement("", "", Constant.USER, atts);
				th.startElement("", "", Constant.USERNAME, atts);
                th.characters(user.getUserName().toCharArray(), 0, user.getUserName().length());
				th.endElement("", "", Constant.USERNAME);
				th.startElement("", "", Constant.REGIN, atts);
                th.characters(user.getRegin().toCharArray(), 0, user.getRegin().length());
				th.endElement("", "", Constant.REGIN);
				th.startElement("", "", Constant.EMAIL, atts);
                th.characters(user.getEmail().toCharArray(), 0, user.getEmail().length());
				th.endElement("", "", Constant.EMAIL);
				th.startElement("", "", Constant.CREATEDATE, atts);
                th.characters(user.getCreateDate().toCharArray(), 0, user.getCreateDate().length());
				th.endElement("", "", Constant.CREATEDATE);
				th.startElement("", "", Constant.INSTALLNUM, atts);
                th.characters(user.getInstallNum().toCharArray(), 0, user.getInstallNum().length());
				th.endElement("", "", Constant.INSTALLNUM);
				th.startElement("", "", Constant.INSTRUCTION, atts);
                th.characters(user.getInstruction().toCharArray(), 0, user.getInstruction().length());
				th.endElement("", "", Constant.INSTRUCTION);
				th.startElement("", "", Constant.PASSWORD, atts);
                th.characters(user.getPassword().toCharArray(), 0, user.getPassword().length());
				th.endElement("", "", Constant.PASSWORD);
				th.endElement("", "", Constant.USER);
			}
			
			th.endElement("","", Constant.USERS);
			th.endDocument();

		} catch (FileNotFoundException | TransformerConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
