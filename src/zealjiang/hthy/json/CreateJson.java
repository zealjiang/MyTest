package zealjiang.hthy.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;



public class CreateJson {

	public static void main(String[] args) {

		CreateJson cj = new CreateJson();
		System.out.println(cj.createJsonText1());
		System.out.println(cj.createJsonText2());
	}
	
	public String createJsonText1(){
		
		// 假设现在要创建这样一个json文本  
		//  {  
		//      "phone" : ["12345678", "87654321"], // 数组  
		//      "name" : "yuanzhifei89", // 字符串  
		//      "age" : 100, // 数值  
		//      "address" : { "country" : "china", "province" : "jiangsu" }, // 对象  
		//      "married" : false // 布尔值  
		//  }  
		  
		try {  
		    // 首先最外层是{}，是创建一个对象  
		    JSONObject person = new JSONObject();  
		    // 第一个键phone的值是数组，所以需要创建数组对象  
		    JSONArray phone = new JSONArray();  
		    phone.put("12345678").put("87654321");  
		    person.put("phone", phone);  
		  
		    person.put("name", "yuanzhifei89");  
		    person.put("age", 100);  
		    // 键address的值是对象，所以又要创建一个对象  
		    JSONObject address = new JSONObject();  
		    address.put("country", "china");  
		    address.put("province", "jiangsu");  
		    person.put("address", address);    
		    person.put("married", false);  
		    
		    
		    return person.toString();
		} catch (JSONException ex) {  
		    // 键为null或使用json不支持的数字格式(NaN, infinities)  
		    throw new RuntimeException(ex);  
		} 
	}
	
	public String createJsonText2(){
		
		try {  
			    JSONStringer jsonText = new JSONStringer();  
			    // 首先是{，对象开始。object和endObject必须配对使用  
			    jsonText.object();  
			      
			    jsonText.key("phone");  
			    // 键phone的值是数组。array和endArray必须配对使用  
			    jsonText.array();  
			    jsonText.value("12345678").value("87654321");  
			    jsonText.endArray();  
			      
			    jsonText.key("name");  
			    jsonText.value("yuanzhifei89");  
			    jsonText.key("age");  
			    jsonText.value(100);  
			      
			    jsonText.key("address");  
			    // 键address的值是对象  
			    jsonText.object();  
			    jsonText.key("country");  
			    jsonText.value("china");  
			    jsonText.key("province");  
			    jsonText.value("jiangsu");  
			    jsonText.endObject();  
			      
			    jsonText.key("married");  
			    jsonText.value(false);  
			      
			    // }，对象结束  
			    jsonText.endObject();  
			    
			    return jsonText.toString();
			} catch (JSONException ex) {  
			    throw new RuntimeException(ex);  
			    
			}  

	}
}
