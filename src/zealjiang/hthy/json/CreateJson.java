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
		
		// ��������Ҫ��������һ��json�ı�  
		//  {  
		//      "phone" : ["12345678", "87654321"], // ����  
		//      "name" : "yuanzhifei89", // �ַ���  
		//      "age" : 100, // ��ֵ  
		//      "address" : { "country" : "china", "province" : "jiangsu" }, // ����  
		//      "married" : false // ����ֵ  
		//  }  
		  
		try {  
		    // �����������{}���Ǵ���һ������  
		    JSONObject person = new JSONObject();  
		    // ��һ����phone��ֵ�����飬������Ҫ�����������  
		    JSONArray phone = new JSONArray();  
		    phone.put("12345678").put("87654321");  
		    person.put("phone", phone);  
		  
		    person.put("name", "yuanzhifei89");  
		    person.put("age", 100);  
		    // ��address��ֵ�Ƕ���������Ҫ����һ������  
		    JSONObject address = new JSONObject();  
		    address.put("country", "china");  
		    address.put("province", "jiangsu");  
		    person.put("address", address);    
		    person.put("married", false);  
		    
		    
		    return person.toString();
		} catch (JSONException ex) {  
		    // ��Ϊnull��ʹ��json��֧�ֵ����ָ�ʽ(NaN, infinities)  
		    throw new RuntimeException(ex);  
		} 
	}
	
	public String createJsonText2(){
		
		try {  
			    JSONStringer jsonText = new JSONStringer();  
			    // ������{������ʼ��object��endObject�������ʹ��  
			    jsonText.object();  
			      
			    jsonText.key("phone");  
			    // ��phone��ֵ�����顣array��endArray�������ʹ��  
			    jsonText.array();  
			    jsonText.value("12345678").value("87654321");  
			    jsonText.endArray();  
			      
			    jsonText.key("name");  
			    jsonText.value("yuanzhifei89");  
			    jsonText.key("age");  
			    jsonText.value(100);  
			      
			    jsonText.key("address");  
			    // ��address��ֵ�Ƕ���  
			    jsonText.object();  
			    jsonText.key("country");  
			    jsonText.value("china");  
			    jsonText.key("province");  
			    jsonText.value("jiangsu");  
			    jsonText.endObject();  
			      
			    jsonText.key("married");  
			    jsonText.value(false);  
			      
			    // }���������  
			    jsonText.endObject();  
			    
			    return jsonText.toString();
			} catch (JSONException ex) {  
			    throw new RuntimeException(ex);  
			    
			}  

	}
}
