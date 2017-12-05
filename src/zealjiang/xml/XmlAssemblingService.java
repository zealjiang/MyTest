package zealjiang.xml;

import java.util.HashMap;


public class XmlAssemblingService {


	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("zwmc", "软件设计工程师");
		map.put("gzdq", "海淀区");
		map.put("fbsj", "2012-08-18");
		
		String str = xmlAssembling(map);
		System.out.println("str: \n"+str);
	}
	
	public String xmlAssembling(String[][] str){
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("<?xml version=\"1.0\" encoding=\"GBK\"?><root><cond>");
		for(int i=0;i<str.length;i++){
			sbBuilder.append("<"+str[i][0]+">"+str[i][1]+"</"+str[i][0]+">");
		}
		String xml = sbBuilder.append("</cond></root>").toString();
		
		return xml;
	}
	
	
	public static String xmlAssembling(HashMap<String, String> map){
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n <root>\n  <cond>\n");
		
		
		for(int i=0;i<map.size();i++){
			sbBuilder.append("   <"+map.keySet().toArray()[i]+">"+map.get(map.keySet().toArray()[i])+
					"</"+map.keySet().toArray()[i]+">\n");
		}
		String xml = sbBuilder.append("  </cond>\n </root>").toString();
		
		return xml;
	}
}
