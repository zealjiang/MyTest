package zealjiang.json;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonM01Test {

	@Test
	public void test(){
		GsonM01 gsonMo01 = new GsonM01();
		gsonMo01.setName("kechanghe");
		gsonMo01.setSex(true);
		gsonMo01.setAge(23);
		List<String> listName=new ArrayList<String>();
		listName.add("aaa");
		listName.add("bbb");
		listName.add("ccc");
		gsonMo01.setListName(listName);

		Gson gson = new GsonBuilder().create();
		String s= gson.toJson(gsonMo01);
		
		System.out.println("s-->"+s);

		GsonM01 gsonModDes=gson.fromJson(s, GsonM01.class);
		String name= gsonModDes.getName();
		boolean sex=gsonModDes.isSex();
		int age=gsonModDes.getAge();
		
		List<String> listN=gsonModDes.getListName();
		System.out.println("name: "+name+"  sex: "+sex+" age: "+age+" listN: "+listN);
	}
	
	@Test
	public void test2(){
		String json = toJson(new Date());
		System.out.println("json->" + json);
		
		Date date = fromJson(json);
		System.out.println(date.getTime());
	}
	

	public class UtilDateSerializer implements JsonSerializer<Date> {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}

	}
	
	public class UtilDateDeserializer implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return new Date(json.getAsJsonPrimitive().getAsLong());
		}

	}
	
	public String toJson(Date date) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Date.class, new UtilDateSerializer())
				.setDateFormat(DateFormat.LONG).create();

		return gson.toJson(date);
	}
	
	public Date fromJson(String json) {
		Gson gson = new GsonBuilder()
						.registerTypeAdapter(Date.class, new UtilDateDeserializer())
						.setDateFormat(DateFormat.LONG)
						.create();
		
		return gson.fromJson(json , Date.class);
	}
}
