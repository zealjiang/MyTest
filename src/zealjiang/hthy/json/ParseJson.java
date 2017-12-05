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
		//      "phone" : ["12345678", "87654321"], // ����  
		//      "name" : "yuanzhifei89", // �ַ���  
		//      "age" : 100, // ��ֵ  
		//      "address" : { "country" : "china", "province" : "jiangsu" }, // ����  
		//      "married" : false // ����ֵ  
		//  }  
		  	  
		try {  
		    JSONTokener jsonParser = new JSONTokener(json);  
		    // ��ʱ��δ��ȡ�κ�json�ı���ֱ�Ӷ�ȡ����һ��JSONObject����  
		    // �����ʱ�Ķ�ȡλ����"name" : �ˣ���ônextValue����"yuanzhifei89"��String��  
		    JSONObject person = (JSONObject) jsonParser.nextValue(); 
		    System.out.println("====����key��ȡvalue======");
		    // �������ľ���JSON����Ĳ�����  
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
		    System.out.println("======����========");
		    
		    for (Iterator iter = person.keys(); iter.hasNext();) {
		    	   Object str = iter.next();   	   
		    	   System.out.println(str.toString()+" :"+person.get(str.toString()));
		    }
		    System.out.println("======ȡName========");
		    String[] names = person.getNames(person);
		    for (int i=0;i<names.length;i++) {
		    	
		    	   System.out.println(names[i]+" :"+person.get(names[i]));
		    }
		    
		} catch (JSONException ex) {  
		    // �쳣�������  
		}  

	}
}
