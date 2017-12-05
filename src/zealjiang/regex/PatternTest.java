package zealjiang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import zealjiang.lrc.Sentence;

public class PatternTest {

	private static final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])");
	public static void main(String[] args) {
		String line2 = "[00:25.90]��������ȸ������������";
		String line = "?[?��������ȸ������������]";
		Matcher matcher = pattern.matcher(line); //ƥ�䣬pattern�����涨���ƥ����
		while (matcher.find()) {         //find()�൱���α��next()
			//System.out.println("line:"+line);
			//ƥ�䵽��ʱ�䣬����s
			String s = matcher.group();  
			System.out.println("s:"+s);
		}
	}
	
	@Test
	public void length(){
		Pattern pattern = Pattern.compile("[^0-9abcdefghjklmnprstuvwxyzABCDEFGHJKLMNPRSTUVWXYZ]{1,}");//
		String line = "132220ai";
		Matcher matcher = pattern.matcher(line); //ƥ�䣬pattern�����涨���ƥ����
		if(matcher.matches()){
			System.out.println("ƥ��ɹ�");
		}else{
			System.out.println("ƥ��ʧ��");
		}
/*		while (matcher.find()) {         //find()�൱���α��next()
			//System.out.println("line:"+line);
			//ƥ�䵽��ʱ�䣬����s
			String s = matcher.group();  
			System.out.println("s:"+s);
		}*/
	}
}
