package zealjiang.lrc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcParse  implements Serializable {

	private static final long serialVersionUID = -8076088611721915361L; //没用的，是消除警告而添加的
	public static List<Sentence> list = new ArrayList<Sentence>();   //里面装的是所有处理好的句子
	private static final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])");
	
	public LrcParse (File file) {
		init(file);
	}
	
	/**
	 * 根据文件来初始化
	 * 
	 * @param file
	 *            文件
	 */
	 public static void init(File file) {
		 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));  
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp).append("\n");
			}
			init(sb.toString());  //调用下面的，处理读取的歌词，把歌词一句一句分开并计算好时间
			
			//==================================
			//System.out.println("----------->打印读取的未处理的歌词" + sb.toString());			
			//System.out.println(list.size() + " ");
			
			//==================================
			for(int i=0; i<list.size(); i++) {
				
				System.out.println("---->打印已经处理好的歌词List" + list.get(i).toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * 最重要的一个方法，它根据读到的歌词内容 进行初始化，比如把歌词一句一句分开并计算好时间
	 * 在上面读取的歌词后就调用此方法进行处理，此方法不需要做任何修改
	 * 
	 * @param content  歌词内容
	 *          
	 */
	private static void init(String content) {
		// 如果歌词的内容为空,则后面就不用执行了
		// 直接显示歌曲名就可以了
		if (content == null || content.trim().equals("")) {
			list.add(new Sentence("没有找到歌词", Integer.MIN_VALUE,
					Integer.MAX_VALUE));
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new StringReader(content));
			
			String temp = null;
			Main main = new Main();//ZJ加
			while ((temp = br.readLine()) != null) {
				main.RSTFile(temp);//ZJ加
				//每读取每句歌词后调用下面的方法，根据这内容 以及标签的数量生成若干个Sentence对象
				parseLine(temp.trim());
				
			}
			br.close();
			list.add(new Sentence("我的音乐播放器", 0));
			
			// 读进来以后就排序了
			Collections.sort(list, new Comparator<Sentence>() {

				public int compare(Sentence o1, Sentence o2) {
					return (int) (o1.getFromTime() - o2.getFromTime());
				}
			});
			
			mergeTime();  //调用下面的方法，将每行的开始和末尾加上时间点
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 分析这一行的内容，根据这内容 以及标签的数量生成若干个Sentence对象 当此行
	 * 中的时间标签分布不在一起时，也要能分析出来 所以更改了一些实现
	 * 
	 * 
	 * @param line  这一行
	 *           
	 */
	private static void parseLine(String line) {

		if (line.equals("")) {  //如果这一行为空直接返回
			return;
		}
		
		//这个if是自己添加的，为了保存歌手的名字
		if(line.contains("[ar:")) {              //字符串包含"[ar:",表示歌手
			String singerName = line.substring(4, line.length()-1);
			Sentence.singerName = singerName;
			return ;
		}
		
		String content = "";  
		Matcher matcher = pattern.matcher(line); //匹配，pattern：上面定义的匹配码
	
		while (matcher.find()) {         //find()相当于游标的next()
			//System.out.println("line:"+line);
			//匹配到了时间，存于s
			String s = matcher.group();  
			//System.out.println("s:"+s);
			//保存取得歌词内容
			content = line.substring(line.lastIndexOf("]")+1,line.length());
			//System.out.println("content:"+content);
			//调用下面的函数，将时间字符串转换为毫秒数，存于t中
			long t = parseTime(s);  
			
			if (t != -1) {
				
				//System.out.println("匹配这一行且时间处理后后打印时间和歌词 : " + t + "----" + content);
				
                //一行的内容时间作为一个元素存于list数组
				list.add(new Sentence(content, t));
			}
		} 
	}
	/*
	 * 再处理每行内容，为了显示歌词同步，将每行的开始和末尾加上时间点
	 * 中间的歌词内容就是这两个时间点只差，末尾的时间点就是下一行开始
	 * 的时间
	 */	
	private static void mergeTime () {
		int i=0;
		for(i=0; i<list.size()-1; i++) {
			list.get(i).setToTime(list.get(i+1).getFromTime());
		}
		list.get(i).setToTime(Integer.MAX_VALUE);
	}
	
	
	
	/**
	 * 把如00:00.00这样的字符串转化成 毫秒数的时间，
	 * 比如 01:10.34就是一分钟加上10秒再加上340毫秒 
	 * 也就是返回70340毫秒
	 * 
	 * 此函数也不需要修改，直接调用
	 * 
	 * @param time
	 *            字符串的时间
	 * @return 此时间表示的毫秒
	 */
	private static long parseTime(String time) {
		
		String[] ss = time.split("\\:|\\.");  //遇到：或.就分割作为一个元素存于字符串数组ss
/*		for(int i=0;i<ss.length;i++){
			System.out.println("ss["+i+"]"+ss[i]);
		}
		System.out.println("------------------");*/
		// 如果 是两位以下，就非法了
		if (ss.length < 2) {
			return -1;
			
		} else if (ss.length == 2) {   // 如果正好两位，就算分秒
			try {
				int min = Integer.parseInt(ss[0]);
				int sec = Integer.parseInt(ss[1]);
				
				if (min < 0 || sec < 0 || sec >= 60) {
					throw new RuntimeException("数字不合法!");
				}
				// System.out.println("time" + (min * 60 + sec) * 1000L);
				return (min * 60 + sec) * 1000L; 
			} catch (Exception exe) {
				//System.out.println("====min");
				return -1;
			}
		} else if (ss.length == 3) {// 如果正好三位，就算分秒，十毫秒
			try {
				int min = Integer.parseInt(ss[0]);
				int sec = Integer.parseInt(ss[1]);
				int mm = Integer.parseInt(ss[2]);
				//System.out.println("min" + min+"sec"+sec+"mm"+mm);
				if (min < 0 || sec < 0 || sec >= 60 || mm < 0 || mm > 999) {
					throw new RuntimeException("数字不合法!");
				}
				// System.out.println("time" + (min * 60 + sec) * 1000L + mm *
				// 10);
				if(mm<99){
					mm = mm*10;
				}
				return (min * 60 + sec) * 1000L + mm;
			} catch (Exception exe) {
				return -1;
			}
		} else {// 否则也非法
			return -1;
		}
	}
	
	
	/**
	 * =====================得到当前正在播放的那一句的下标===================== 
	 * 
	 * 不可能找不到，因为最开头要加一句 自己的句子 ，所以加了以后就不可能找不到了
	 * 
	 * @return 下标
	 */
	public static int getNowSentenceIndex(long t) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isInTime(t)) {
				return i;
			}
		}
		// throw new RuntimeException("竟然出现了找不到的情况！");
		return -1;
	}
}
