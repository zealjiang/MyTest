package zealjiang.json;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;



public class JsonObject {

	@Test
	public void JsonArrayTest(){

		
			String jsonstr = "{\"name\":\"tom\",\"age\":\"13\",\"address\":[{\"city\":\"changsha\",\"street\":\"no2\"},{\"city\":\"beijing\",\"street\":\"no5\"}]}";
			try {
				boolean b = jsonstr.startsWith("\ufeff");
				System.out.println("b-->"+b);

				//"\ufeff" ��ʾ�ո�

				JSONObject jsonObj = new JSONObject(jsonstr);
				System.out.println("jsonObj-->"+jsonObj);
				//name,age��ֵ��һ���ַ������Կ���ֱ�ӻ�ȡ
				String name = jsonObj.getString("name");
				System.out.println("name:"+name);
				String age = jsonObj.getString("age");
				System.out.println("age:"+age);
				
				String address = jsonObj.getString("address");
				System.out.println("address:"+address);
				
				
				//address��ֵ��һ��json������������Ҫ�Ȼ�ȡjson����
				JSONArray addrArrays = jsonObj.getJSONArray("address");

				//addrArrays��������������JSONObject������������ѭ��ȡ��
				for(int i=0;i<addrArrays.length();i++){
					JSONObject addrJsonObj = addrArrays.getJSONObject(i);
					String city = addrJsonObj.getString("city");
					System.out.println("city"+(i+1)+":"+city);
					String street = addrJsonObj.getString("street");
					System.out.println("street"+(i+1)+":"+street);
				}
				
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void getInt(){
		String json = "{\"name\":\"�����ر�������\",\"cityno\":\"00853\"}";
		try {
			JSONObject jso = new JSONObject(json);
			int i = jso.getInt("cityno");
			System.out.println("i--->"+i);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
   

}
