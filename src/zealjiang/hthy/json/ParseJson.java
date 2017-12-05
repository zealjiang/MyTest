package zealjiang.hthy.json;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class ParseJson {

	public static void main(String[] args) {
		CreateJson cj = new CreateJson();
		String json = cj.createJsonText1();
		
		ParseJson pj = new ParseJson();
		pj.parseJsonM1(json);
	}
	
	
	public void parseJsonM1(String json){
		
		//  {  
		//      "phone" : ["12345678", "87654321"], // 数组  
		//      "name" : "yuanzhifei89", // 字符串  
		//      "age" : 100, // 数值  
		//      "address" : { "country" : "china", "province" : "jiangsu" }, // 对象  
		//      "married" : false // 布尔值  
		//  }  
		  	  
		try {  
		    JSONTokener jsonParser = new JSONTokener(json);  
		    // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。  
		    // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）  
		    JSONObject person = (JSONObject) jsonParser.nextValue(); 
		    System.out.println("====根据key获取value======");
		    // 接下来的就是JSON对象的操作了  
		    JSONArray ja = person.getJSONArray("phone"); 
		    System.out.println("phone: "+ja.getString(0)+"  "+ja.getString(1));
		    String name = person.getString("name");  
		    System.out.println("name: "+name);
		    int age = person.getInt("age");  
		    System.out.println("age: "+age);
		    JSONObject jo = person.getJSONObject("address"); 
		    System.out.println("country: "+jo.getString("country"));
		    System.out.println("province: "+jo.getString("province"));
		    boolean married = person.getBoolean("married");  
		    System.out.println("married: "+married);
		    System.out.println("======迭代========");
		    
		    for (Iterator iter = person.keys(); iter.hasNext();) {
		    	   Object str = iter.next();   	   
		    	   System.out.println(str.toString()+" :"+person.get(str.toString()));
		    }
		    System.out.println("======取Name========");
		    String[] names = person.getNames(person);
		    for (int i=0;i<names.length;i++) {
		    	
		    	   System.out.println(names[i]+" :"+person.get(names[i]));
		    }
		    
		} catch (JSONException ex) {  
		    // 异常处理代码  
		}  

	}
}
