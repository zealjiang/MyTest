package zealjiang.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import zealjiang.json.JsonParser.TaskFlag;

import com.file.ReadFileToString;
import com.google.gson.JsonParseException;

public class JsonParserTest {


	
	public ArrayList<TrueVisionCityEntity> parserCity(String json) {
		JsonParser jsonParser = JsonParser.getInstance();
		LDCityEntity ldCityEntity = null;
		ArrayList<TrueVisionCityEntity> trueVisionCityEntityList = new ArrayList<TrueVisionCityEntity>();
		try {
			ldCityEntity = (LDCityEntity) jsonParser.parserJson(json, TaskFlag.FLAG_LDCITY);
			List<LDCityEntity.item> items = ldCityEntity.result;
			for (LDCityEntity.item item : items) {
				TrueVisionCityEntity tve = new TrueVisionCityEntity();
				tve.setCityName(item.city_name);
				tve.setImageUrl(JsonParser.TRUEVISIONCITY_SERVICEADDRESS3 + item.cityLogo);
				tve.setAlpha(item.pinyin);
				tve.setCityNo(item.cityNo);
				trueVisionCityEntityList.add(tve);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return trueVisionCityEntityList;
	}
	
	public String readFileToString(String filePath) { 
		File f = new File(filePath);
		if(!f.exists())return "file not exist";
		String str = ReadFileToString.readFile(f, "");
		return str;
	}
	
	public static void main(String[] args) {
		JsonParserTest jpt = new JsonParserTest();
		String json = jpt.readFileToString("streetViewCity.txt");
		ArrayList<TrueVisionCityEntity> trueVisionCityEntityList 
		= jpt.parserCity(json);
		for(int i=0;i<5;i++){
			System.out.println(trueVisionCityEntityList.get(i).getCityName());
		}
		
	}
}
