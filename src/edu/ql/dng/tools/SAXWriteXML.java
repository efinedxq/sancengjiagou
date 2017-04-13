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
	// 元素层次，用于控制XML缩进
	private static int level = 0;
	// 每个层次父级缩进4个空格，即一个tab
	private static String tab = "    ";
	// 系统换行符，Windows为："\n"，Linux/Unix为："/n"
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
            //设置输出采用的编码方式  
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");  
            //是否自动添加额外的空白  
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
            //是否忽略xml声明  
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
            //设置schema和名称空间  
//            atts.addAttribute("", "",Constant.NAME_SPACE , String.class.getName(), Constant.SCHEMA);  
            handler.startElement("", "", rootElement, atts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	//元素里面会嵌套子节点，因此元素的开始和结束分开写  
    //如：<a><b>bcd</b></a>  
    private void startElement(String objectElement, AttributesImpl attrs)  
            throws SAXException {  
        if(attrs == null){  
            attrs = new AttributesImpl();  
        }  
        level++;  
        appendTab();  
        if (objectElement != null) {  
            //注意，如果atts.addAttribute设置了属性，则会输出如：<a key="key" value="value">abc</a>格式  
            //如果没有设置属性，则输出如：<a>abc</a>格式  
            handler.startElement("", "", objectElement, attrs);  
        }     
    }  
      
    //正常元素结束标记，如：</a>  
    private void endElement(String objectElement) throws SAXException{  
        level--;  
        appendTab();  
        if (objectElement != null) {  
            handler.endElement("", "", objectElement);  
        }  
    }  
  
    //自封闭的空元素，如<a key="key" value="value"/>，不用换行，写在一行时XML自动会自封闭  
    private void endEmptyElement(String objectElement) throws SAXException{  
        handler.endElement("", "", objectElement);  
    }  
      
    //无子节点的元素成为属性，如<a>abc</a>  
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
            // 文档结束,同步到磁盘  
            handler.endDocument();  
            outStream.close();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    //Tab缩进，SAX默认不自动缩进，因此需要手动根据元素层次进行缩进控制  
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
          
        //写oes:NotificationID  
        writeAttribute(Convert.convertString(Constant.NOTIFICATION_ID), Convert.convertString(props.get(Constant.NOTIFICATION_ID)));  
        keys.remove(Constant.NOTIFICATION_ID);  
        //写oes:NotificationType  
        writeAttribute(Convert.convertString(Constant.NOTIFICATION_TYPE), Convert.convertString(event.getNotificationType()));  
        //写oes:timeStamp  
        writeAttribute(Convert.convertString(Constant.TIME_STAMP), Convert.convertString(props.get(Constant.TIME_STAMP)));  
        keys.remove(Constant.TIME_STAMP);  
          
        //写<oes:Appendix>节点  
        startElement(Constant.APPENDIX, null);  
        //写oes:MapItem  
        atts = new AttributesImpl();  
        atts.addAttribute("", "", Convert.convertString(Constant.KEY), String.class.getName(), Convert.convertString(props.get(Constant.KEY)));  
        keys.remove(Constant.KEY);  
        atts.addAttribute("", "", Convert.convertString(Constant.VALUE), String.class.getName(), Convert.convertString(props.get(Constant.VALUE)));  
        keys.remove(Constant.VALUE);  
        startElement(Constant.MAP_ITEM, atts);  
        //结束oes:MapItem，由于MapItem是个自封闭的元素，需要特殊处理  
        endEmptyElement(Constant.MAP_ITEM);  
        keys.remove(Constant.MAP_ITEM);  
        //结束oes:MapItem节点  
        endElement(Constant.APPENDIX);  
        keys.remove(Constant.APPENDIX);  
          
        //写oes:Content节点  
        startElement(Constant.CONTENT, null);  
        keys.remove(Constant.CONTENT);  
          
        //写alarmNew节点  
        atts = new AttributesImpl();  
        atts.addAttribute("", "", Convert.convertString(Constant.SYSTEM_DN), String.class.getName(), Convert.convertString(props.get(Constant.SYSTEM_DN)));  
        startElement(Constant.ALARM_NEW, atts);  
        keys.remove(Constant.ALARM_NEW);  
          
        //写Alarm节点内的属性  
        for(String key : keys){  
            writeAttribute(Convert.convertString(key), Convert.convertString(props.get(key)));  
        }  
          
        //结束alarmNew节点  
        endElement(Constant.ALARM_NEW);  
    }  
}
