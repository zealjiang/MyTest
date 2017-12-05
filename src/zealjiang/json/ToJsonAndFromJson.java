package zealjiang.json;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ToJsonAndFromJson {

	@Test
	public void toFromJson(){
		Type listType = new TypeToken<List<String>>() {}.getType(); 
		System.out.println("listType-->"+listType);
		List<String> target = new LinkedList<String>(); 

		target.add("blah");

		Gson gson = new Gson(); 

		String json = gson.toJson(target, listType); 
		System.out.println("json-->"+json);

		List<String> target2 = gson.fromJson(json, listType);
		System.out.println("target2-->"+target2);
	}

}
