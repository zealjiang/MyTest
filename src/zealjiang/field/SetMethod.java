package zealjiang.field;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SetMethod {

	@Test
	public void mySet(){
		Gson1 gson1 = new Gson1();
		gson1.setName("kechanghe");
		gson1.setSex(true);
		gson1.setAge(23);
		
		Gson gson = new GsonBuilder().create();
		String s= gson.toJson(gson1);
		
		Gson1 outInstance = null;

		try {
			outInstance = Gson1.class.newInstance();
			JSONObject jsonObject = new JSONObject(s);
			for (Field f : Gson1.class.getDeclaredFields()) {

				f.setAccessible(true);
				String data = jsonObject.getString(f.getName());
				Object o = gson.fromJson(data, f.getType());
				f.set(outInstance, o);
				System.out.println("name:"+f.getName()+" value:"+o);

				
				f.set(outInstance, o);
			}
			
			System.out.println("------------------------------");
			System.out.println("outInstance:"+outInstance);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	


}
