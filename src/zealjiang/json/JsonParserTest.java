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
	
	private void jsonToMap(){
        String test = "{HUE=0.0, SATURATION=-40.0, EXPOSURE=80.0, FADE=50.0, SHADOW=0.0, VIGNETTE=30.0, BRIGHTNESS=0.0, HIGHLIGHT=30.0, VIBRANCE=0.0, PRISM=30.0, TEMPERATURE=0.0, OFFSET=0.0, CONTRAST=40.0, TINT=40.0}";

        String newStr = test.substring(1,test.length()-1);
        String[] array = newStr.split(",");
        for (int i = 0; i < array.length; i++) {
			array[i].split("");
		}
    }
	
	public enum QhLayerAdjust {
	    BRIGHTNESS,
	    CONTRAST,
	    EXPOSURE,
	    OFFSET,
	    TEMPERATURE,
	    TINT,
	    SATURATION,
	    HUE,
	    VIBRANCE,
	    VIGNETTE,
	    PRISM,
	    FADE,
	    HIGHLIGHT,
	    SHADOW,
	    LAYER_ADJUST_MAX;

	    private QhLayerAdjust() {
	    }
	}
}
