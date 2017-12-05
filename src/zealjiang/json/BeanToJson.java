package zealjiang.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.google.gson.Gson;

public class BeanToJson {
	
	@Test
	public void toJson(){
        SubmitFinalJson.DataBean jsonData = new SubmitFinalJson.DataBean();
        SubmitFinalJson.DataBean.Customer customer = new SubmitFinalJson.DataBean.Customer();
        
        jsonData.setCustomer(customer);
        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        System.out.println("json: "+json);
	}
	
	@Test
	public void toJsonString(){
        ArrayList<HashMap<String,String>> timeList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 5; i++) {
            HashMap<String,String> map = new HashMap();
            map.put("TimeType",String.valueOf(i+1));//操作类型 1、2、3、4、5
            map.put("BeginTime","2017-05-16 10:05:03");//开始时间
            map.put("EndTime","2017-05-16 10:11:46");//结束时间
            timeList.add(map);
        }
        Gson gson = new Gson();
        String json = gson.toJson(timeList);
        System.out.println("json: "+json);
	}
}
