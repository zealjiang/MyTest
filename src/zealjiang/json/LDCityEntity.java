package zealjiang.json;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class LDCityEntity {
	
	public List<item> result=null;
	
	public static class item  implements Serializable {
		public String cityLogo,city_name,pinyin,cityNo,newLogo;
	}
}
