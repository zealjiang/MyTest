import java.util.HashMap;
import java.util.Map;


public class MapTest {

	public static void main(String[] args) {
		Map map = new HashMap();
		
		map.put("dong", "dons");
		map.put("dong", "and");
		map.put("dong", "addnd");
		System.out.println("map>>"+map.get("dong").toString());
	}
}
