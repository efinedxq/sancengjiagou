package edu.ql.dng.tools;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import edu.ql.dng.common.Constant;
import edu.ql.dng.model.User;

public class SAXWriteXML {
	SAXTransformerFactory fac = (SAXTransformerFactory) SAXTransformerFactory
			.newInstance();
	private TransformerHandler handler = null;
	private OutputStream outStream = null;
	private String fileName;
	private AttributesImpl atts;
	private String rootElement;
	// Ԫ�ز�Σ����ڿ���XML����
	private static int level = 0;
	// ÿ����θ�������4���ո񣬼�һ��tab
	private static String tab = "    ";
	// ϵͳ���з���WindowsΪ��"\n"��Linux/UnixΪ��"/n"
	private static final String separator = System.getProperties()
			.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1 ? "\n"
			: "/n";

	public SAXWriteXML(String fileName, String rootElement) {
		this.fileName = fileName;
		this.rootElement = rootElement;
		init();
	}
	
	public void init() {  
        try {  
            handler = fac.newTransformerHandler();  
            Transformer transformer = handler.getTransformer();  
            //����������õı��뷽ʽ  
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
            //�Ƿ��Զ���Ӷ���Ŀհ�  
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
            //�Ƿ����xml����  
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");  
            outStream = new FileOutputStream(fileName);  
            Result resultxml = new StreamResult(outStream);  
            handler.setResult(resultxml);  
            atts = new AttributesImpl();  
            start();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	private void start() {  
        try {  
            handler.startDocument();  
            //����schema�����ƿռ�  
//            atts.addAttribute("", "",Constant.NAME_SPACE , String.class.getName(), Constant.SCHEMA);  
            handler.startElement("", "", rootElement, atts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	//Ԫ�������Ƕ���ӽڵ㣬���Ԫ�صĿ�ʼ�ͽ����ֿ�д  
    //�磺<a><b>bcd</b></a>  
    private void startElement(String objectElement, AttributesImpl attrs)  
            throws SAXException {  
        if(attrs == null){  
            attrs = new AttributesImpl();  
        }  
        level++;  
        appendTab();  
        if (objectElement != null) {  
            //ע�⣬���atts.addAttribute���������ԣ��������磺<a key="key" value="value">abc</a>��ʽ  
            //���û���������ԣ�������磺<a>abc</a>��ʽ  
            handler.startElement("", "", objectElement, attrs);  
        }     
    }  
      
    //����Ԫ�ؽ�����ǣ��磺</a>  
    private void endElement(String objectElement) throws SAXException{  
        level--;  
        appendTab();  
        if (objectElement != null) {  
            handler.endElement("", "", objectElement);  
        }  
    }  
  
    //�Է�յĿ�Ԫ�أ���<a key="key" value="value"/>�����û��У�д��һ��ʱXML�Զ����Է��  
    private void endEmptyElement(String objectElement) throws SAXException{  
        handler.endElement("", "", objectElement);  
    }  
      
    //���ӽڵ��Ԫ�س�Ϊ���ԣ���<a>abc</a>  
    private void writeAttribute(String key, String value) throws SAXException{  
        atts.clear();  
        level++;  
        appendTab();  
        handler.startElement("", "", key, atts);  
        handler.characters(value.toCharArray(), 0, value.length());  
        handler.endElement("", "", key);  
        level--;  
    }  
  
    public void end() {  
        try {  
            handler.endElement("", "", rootElement);  
            // �ĵ�����,ͬ��������  
            handler.endDocument();  
            outStream.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    //Tab������SAXĬ�ϲ��Զ������������Ҫ�ֶ�����Ԫ�ز�ν�����������  
    private void appendTab() throws SAXException{  
        String indent = separator + "    ";  
        for(int i = 1 ; i< level; i++){  
           indent += tab;  
       }  
        handler.characters(indent.toCharArray(), 0, indent.length());  
    }  
      
    public void writeNotification(User user) throws SAXException{  
          
        level = 0;  
        startElement(Constant.USER, null);  
          
        //дoes:NotificationID  
        writeAttribute(Convert.convertString(Constant.NOTIFICATION_ID), Convert.convertString(props.get(Constant.NOTIFICATION_ID)));  
        keys.remove(Constant.NOTIFICATION_ID);  
        //дoes:NotificationType  
        writeAttribute(Convert.convertString(Constant.NOTIFICATION_TYPE), Convert.convertString(event.getNotificationType()));  
        //дoes:timeStamp  
        writeAttribute(Convert.convertString(Constant.TIME_STAMP), Convert.convertString(props.get(Constant.TIME_STAMP)));  
        keys.remove(Constant.TIME_STAMP);  
          
        //д<oes:Appendix>�ڵ�  
        startElement(Constant.APPENDIX, null);  
        //дoes:MapItem  
        atts = new AttributesImpl();  
        atts.addAttribute("", "", Convert.convertString(Constant.KEY), String.class.getName(), Convert.convertString(props.get(Constant.KEY)));  
        keys.remove(Constant.KEY);  
        atts.addAttribute("", "", Convert.convertString(Constant.VALUE), String.class.getName(), Convert.convertString(props.get(Constant.VALUE)));  
        keys.remove(Constant.VALUE);  
        startElement(Constant.MAP_ITEM, atts);  
        //����oes:MapItem������MapItem�Ǹ��Է�յ�Ԫ�أ���Ҫ���⴦��  
        endEmptyElement(Constant.MAP_ITEM);  
        keys.remove(Constant.MAP_ITEM);  
        //����oes:MapItem�ڵ�  
        endElement(Constant.APPENDIX);  
        keys.remove(Constant.APPENDIX);  
          
        //дoes:Content�ڵ�  
        startElement(Constant.CONTENT, null);  
        keys.remove(Constant.CONTENT);  
          
        //дalarmNew�ڵ�  
        atts = new AttributesImpl();  
        atts.addAttribute("", "", Convert.convertString(Constant.SYSTEM_DN), String.class.getName(), Convert.convertString(props.get(Constant.SYSTEM_DN)));  
        startElement(Constant.ALARM_NEW, atts);  
        keys.remove(Constant.ALARM_NEW);  
          
        //дAlarm�ڵ��ڵ�����  
        for(String key : keys){  
            writeAttribute(Convert.convertString(key), Convert.convertString(props.get(key)));  
        }  
          
        //����alarmNew�ڵ�  
        endElement(Constant.ALARM_NEW);  
    }  
}
