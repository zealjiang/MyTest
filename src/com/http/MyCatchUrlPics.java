package com.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyCatchUrlPics {

	/**
	 * ������··����ȡ ҳ��Դ��
	 * 
	 * @param pageUrl
	 * @param encoding
	 * @return
	 */
	public String getPageSource(String pageUrl, String encoding) {
		StringBuffer sb = new StringBuffer();
		try {
			// ����һURL����
			URL url = new URL(pageUrl);
			// ʹ��openStream�õ�һ���������ɴ˹���һ��BufferedReader����
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), encoding));
			String line;
			// ��ȡwww��Դ
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			in.close();
		} catch (Exception ex) {
			System.err.println("ex :" + ex);
		}
		return sb.toString();
	}
}
class SaxParseService extends DefaultHandler{

	private List<Picture> pictures = null;

	public List<Picture> getPictures(InputStream xmlStream) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SaxParseService handler = new SaxParseService();
		parser.parse(xmlStream, handler);
		return handler.getPictures();
	}

	public List<Picture> getPictures() {
		return pictures;
	} 


	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		pictures = new ArrayList<Picture>();  
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}
	
	
}
