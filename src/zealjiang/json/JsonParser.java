package zealjiang.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;




public class JsonParser {

	private static JsonParser instance;
	private com.google.gson.Gson defaultGson;
	private com.google.gson.Gson gson;
	
	public final static String TRUEVISIONCITY_SERVICEADDRESS3= "http://210.51.167.17/";
	
	public static class TaskFlag {
		
		public final static int FLAG_LDCITY=0x000E;//ldcity
	}
	
	private JsonParser() {
		defaultGson = gson = new GsonBuilder().create();
	}
	
	public static JsonParser getInstance() {
		if (instance == null) {
			instance = new JsonParser();
		}
		return instance;
	}
	
	
	public LDCityEntity parserJson(String json, int flag) throws JsonParseException, JSONException, NullPointerException {
		Class<? extends Object> clazz = null;
		if (json != null) {
		}
		switch (flag) {
		case TaskFlag.FLAG_LDCITY:
			gson = defaultGson;
			clazz = LDCityEntity.class;

			break;
		default:
			throw new RuntimeException(String.format("未找到适配的类型: %s", flag));
		}
		LDCityEntity bjs = null;
		if (flag == TaskFlag.FLAG_LDCITY ) {
			String json1 = setResult(json);
			bjs = (LDCityEntity) gson.fromJson(json1, clazz);
		}

		return bjs;
	}
	
	private String setResult(String json) throws JSONException {
		JSONObject jsons = new JSONObject();
		JSONArray a = new JSONArray(json);
		jsons.put("result", a);
		return jsons.toString();
	}
	
	

}
