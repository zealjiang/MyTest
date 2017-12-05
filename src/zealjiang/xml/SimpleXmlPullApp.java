package zealjiang.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;



public class SimpleXmlPullApp
{

    public static void main (String args[])
        
    {
    	simpleXmlPullDemo();
    }
    
    
    public static void simpleXmlPullDemo(){
        try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			xpp.setInput( new StringReader ( "<foo>Hello World!</foo>" ) );
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
			     if(eventType == XmlPullParser.START_DOCUMENT) {
			         System.out.println("Start document");
			     } else if(eventType == XmlPullParser.END_DOCUMENT) {
			         System.out.println("End document");
			     } else if(eventType == XmlPullParser.START_TAG) {
			         System.out.println("Start tag "+xpp.getName());
			     } else if(eventType == XmlPullParser.END_TAG) {
			         System.out.println("End tag "+xpp.getName());
			     } else if(eventType == XmlPullParser.TEXT) {
			         System.out.println("Text "+xpp.getText());
			     }
			     eventType = xpp.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static List<HashMap<Object, Object>> ReadXmlByPull (InputStream inputStream)throws Exception
	{
		List<HashMap<Object, Object>> list = null;

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xmlpull = factory.newPullParser();
		/**
		 * 将输入流传入 设定编码方式
		 * 
		 */
		xmlpull.setInput(inputStream, "utf-8");
		/**
		 * pull读到xml后 返回数字
		 *     读取到xml的声明返回数字0 START_DOCUMENT;
			   读取到xml的结束返回数字1 END_DOCUMENT ;
			   读取到xml的开始标签返回数字2 START_TAG
			   读取到xml的结束标签返回数字3 END_TAG
			   读取到xml的文本返回数字4 TEXT
		 */
		int eventCode = xmlpull.getEventType();
		/**
		 * 只要这个事件返回的不是1 我们就一直读取xml文件
		 */
		while(eventCode!=XmlPullParser.END_DOCUMENT)
		{
			
			switch (eventCode)
			{
				case XmlPullParser.START_DOCUMENT:
				{
					System.out.println("START_DOCUMENT :"+xmlpull.getName());
					break;
				}
				case XmlPullParser.START_TAG:
				{

					break;
				}
					
				case XmlPullParser.END_TAG:
				{ 

					break;
				}
			}
			eventCode = xmlpull.next();//没有结束xml文件就推到下个进行解析
			
			
		}
	
	

		return null;
	}
}

