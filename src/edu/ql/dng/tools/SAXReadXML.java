package edu.ql.dng.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.ql.dng.common.Constant;
import edu.ql.dng.model.User;

public class SAXReadXML extends DefaultHandler {
	private List<User> users = new ArrayList<User>();
	private User temp;
	private String element;
	private Stack elementStack = new Stack();

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String content = new StringBuffer().append(ch, start, length)
				.toString().trim();
		if (content.length() == 0)
			return;

		element = elementStack.peek().toString();
		switch (element) {
		case Constant.USERNAME:
			temp.setUserName(content);
			break;
		case Constant.REGIN:
			temp.setRegin(content);
			break;
		case Constant.EMAIL:
			temp.setEmail(content);
			break;
		case Constant.PASSWORD:
			temp.setPassword(content);
			break;
		case Constant.CREATEDATE:
			temp.setCreateDate(content);
			break;
		case Constant.INSTALLNUM:
			temp.setInstallNum(content);
			break;
		case Constant.INSTRUCTION:
			temp.setInstruction(content);
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		if (name.equals(Constant.USER)) {
			users.add(temp);
		}
		elementStack.pop();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (name.equals("book")) {
			temp = new User();
			temp.setId(attributes.getValue(0));
			elementStack.push(name);
		} else
			elementStack.push(name);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
