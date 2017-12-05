package zealjiang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import zealjiang.lrc.Sentence;

public class PatternTest {

	private static final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])");
	public static void main(String[] args) {
		String line2 = "[00:25.90]怂恿了麻雀和香樟的暧昧";
		String line = "?[?怂恿了麻雀和香樟的暧昧]";
		Matcher matcher = pattern.matcher(line); //匹配，pattern：上面定义的匹配码
		while (matcher.find()) {         //find()相当于游标的next()
			//System.out.println("line:"+line);
			//匹配到了时间，存于s
			String s = matcher.group();  
			System.out.println("s:"+s);
		}
	}
	
	@Test
	public void length(){
		Pattern pattern = Pattern.compile("[^0-9abcdefghjklmnprstuvwxyzABCDEFGHJKLMNPRSTUVWXYZ]{1,}");//
		String line = "132220ai";
		Matcher matcher = pattern.matcher(line); //匹配，pattern：上面定义的匹配码
		if(matcher.matches()){
			System.out.println("匹配成功");
		}else{
			System.out.println("匹配失败");
		}
/*		while (matcher.find()) {         //find()相当于游标的next()
			//System.out.println("line:"+line);
			//匹配到了时间，存于s
			String s = matcher.group();  
			System.out.println("s:"+s);
		}*/
	}
}
