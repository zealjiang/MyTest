package zealjiang.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class IsChineseCharacter {

	public static void main(String[] args) {
		System.out.println(SearchCheckCity("中国"));
	}
	
	/**
	 * 搜索框输入规则校验
	 */
	public static boolean SearchCheckCity(String mobiles){

		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]+$");
		Matcher m = p.matcher(mobiles);
		if(!m.matches()){
			
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否存在汉字
	 */
	@Test
	public void hasChineseCharacter(){
		String changedText = "ww大家";
        Pattern p = Pattern.compile(".*[\u4e00-\u9fa5].*");//.*     0个以上任意字符
        //Pattern p = Pattern.compile(".*[\u4e00-\u9fa5].*|.*[\ud83c\udc00-\ud83c\udfff].*|.*[\ud83d\udc00-\ud83d\udfff].*|.*[\u2600-\u27ff].*");//包含表情符

        Matcher m = p.matcher(changedText);
        if(m.matches()){
            System.out.println("存在汉字");
        }else{
        	System.out.println("不存在汉字");
        }
	}
	
	/**
	 * 删除汉字部分
	 */
	@Test
	public void deleteChineseCharacter() {
		StringBuilder sb = new StringBuilder();
		String changedText = "ww大家bfddd";
		char[] chars = changedText.toCharArray();
		
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");//.*     0个以上任意字符
        Matcher m;
		for (int i = 0; i < chars.length; i++) {
			String schar = ((Character)chars[i]).toString();
			m = p.matcher(schar);
	        if(!m.matches()){
	        	sb.append(schar);
	        }
		}
		System.out.println(sb.toString());
	}
}
