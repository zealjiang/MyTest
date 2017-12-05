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
		 * ������������ �趨���뷽ʽ
		 * 
		 */
		xmlpull.setInput(inputStream, "utf-8");
		/**
		 * pull����xml�� ��������
		 *     ��ȡ��xml��������������0 START_DOCUMENT;
			   ��ȡ��xml�Ľ�����������1 END_DOCUMENT ;
			   ��ȡ��xml�Ŀ�ʼ��ǩ��������2 START_TAG
			   ��ȡ��xml�Ľ�����ǩ��������3 END_TAG
			   ��ȡ��xml���ı���������4 TEXT
		 */
		int eventCode = xmlpull.getEventType();
		/**
		 * ֻҪ����¼����صĲ���1 ���Ǿ�һֱ��ȡxml�ļ�
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
			eventCode = xmlpull.next();//û�н���xml�ļ����Ƶ��¸����н���
			
			
		}
	
	

		return null;
	}
}

