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

	private static final long serialVersionUID = -8076088611721915361L; //û�õģ��������������ӵ�
	public static List<Sentence> list = new ArrayList<Sentence>();   //����װ�������д���õľ���
	private static final Pattern pattern = Pattern.compile("(?<=\\[).*?(?=\\])");
	
	public LrcParse (File file) {
		init(file);
	}
	
	/**
	 * �����ļ�����ʼ��
	 * 
	 * @param file
	 *            �ļ�
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
			init(sb.toString());  //��������ģ������ȡ�ĸ�ʣ��Ѹ��һ��һ��ֿ��������ʱ��
			
			//==================================
			//System.out.println("----------->��ӡ��ȡ��δ����ĸ��" + sb.toString());			
			//System.out.println(list.size() + " ");
			
			//==================================
			for(int i=0; i<list.size(); i++) {
				
				System.out.println("---->��ӡ�Ѿ�����õĸ��List" + list.get(i).toString());
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
	 * ����Ҫ��һ�������������ݶ����ĸ������ ���г�ʼ��������Ѹ��һ��һ��ֿ��������ʱ��
	 * �������ȡ�ĸ�ʺ�͵��ô˷������д����˷�������Ҫ���κ��޸�
	 * 
	 * @param content  �������
	 *          
	 */
	private static void init(String content) {
		// �����ʵ�����Ϊ��,�����Ͳ���ִ����
		// ֱ����ʾ�������Ϳ�����
		if (content == null || content.trim().equals("")) {
			list.add(new Sentence("û���ҵ����", Integer.MIN_VALUE,
					Integer.MAX_VALUE));
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new StringReader(content));
			
			String temp = null;
			Main main = new Main();//ZJ��
			while ((temp = br.readLine()) != null) {
				main.RSTFile(temp);//ZJ��
				//ÿ��ȡÿ���ʺ��������ķ��������������� �Լ���ǩ�������������ɸ�Sentence����
				parseLine(temp.trim());
				
			}
			br.close();
			list.add(new Sentence("�ҵ����ֲ�����", 0));
			
			// �������Ժ��������
			Collections.sort(list, new Comparator<Sentence>() {

				public int compare(Sentence o1, Sentence o2) {
					return (int) (o1.getFromTime() - o2.getFromTime());
				}
			});
			
			mergeTime();  //��������ķ�������ÿ�еĿ�ʼ��ĩβ����ʱ���
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * ������һ�е����ݣ����������� �Լ���ǩ�������������ɸ�Sentence���� ������
	 * �е�ʱ���ǩ�ֲ�����һ��ʱ��ҲҪ�ܷ������� ���Ը�����һЩʵ��
	 * 
	 * 
	 * @param line  ��һ��
	 *           
	 */
	private static void parseLine(String line) {

		if (line.equals("")) {  //�����һ��Ϊ��ֱ�ӷ���
			return;
		}
		
		//���if���Լ���ӵģ�Ϊ�˱�����ֵ�����
		if(line.contains("[ar:")) {              //�ַ�������"[ar:",��ʾ����
			String singerName = line.substring(4, line.length()-1);
			Sentence.singerName = singerName;
			return ;
		}
		
		String content = "";  
		Matcher matcher = pattern.matcher(line); //ƥ�䣬pattern�����涨���ƥ����
	
		while (matcher.find()) {         //find()�൱���α��next()
			//System.out.println("line:"+line);
			//ƥ�䵽��ʱ�䣬����s
			String s = matcher.group();  
			//System.out.println("s:"+s);
			//����ȡ�ø������
			content = line.substring(line.lastIndexOf("]")+1,line.length());
			//System.out.println("content:"+content);
			//��������ĺ�������ʱ���ַ���ת��Ϊ������������t��
			long t = parseTime(s);  
			
			if (t != -1) {
				
				//System.out.println("ƥ����һ����ʱ�䴦�����ӡʱ��͸�� : " + t + "----" + content);
				
                //һ�е�����ʱ����Ϊһ��Ԫ�ش���list����
				list.add(new Sentence(content, t));
			}
		} 
	}
	/*
	 * �ٴ���ÿ�����ݣ�Ϊ����ʾ���ͬ������ÿ�еĿ�ʼ��ĩβ����ʱ���
	 * �м�ĸ�����ݾ���������ʱ���ֻ�ĩβ��ʱ��������һ�п�ʼ
	 * ��ʱ��
	 */	
	private static void mergeTime () {
		int i=0;
		for(i=0; i<list.size()-1; i++) {
			list.get(i).setToTime(list.get(i+1).getFromTime());
		}
		list.get(i).setToTime(Integer.MAX_VALUE);
	}
	
	
	
	/**
	 * ����00:00.00�������ַ���ת���� ��������ʱ�䣬
	 * ���� 01:10.34����һ���Ӽ���10���ټ���340���� 
	 * Ҳ���Ƿ���70340����
	 * 
	 * �˺���Ҳ����Ҫ�޸ģ�ֱ�ӵ���
	 * 
	 * @param time
	 *            �ַ�����ʱ��
	 * @return ��ʱ���ʾ�ĺ���
	 */
	private static long parseTime(String time) {
		
		String[] ss = time.split("\\:|\\.");  //��������.�ͷָ���Ϊһ��Ԫ�ش����ַ�������ss
/*		for(int i=0;i<ss.length;i++){
			System.out.println("ss["+i+"]"+ss[i]);
		}
		System.out.println("------------------");*/
		// ��� ����λ���£��ͷǷ���
		if (ss.length < 2) {
			return -1;
			
		} else if (ss.length == 2) {   // ���������λ���������
			try {
				int min = Integer.parseInt(ss[0]);
				int sec = Integer.parseInt(ss[1]);
				
				if (min < 0 || sec < 0 || sec >= 60) {
					throw new RuntimeException("���ֲ��Ϸ�!");
				}
				// System.out.println("time" + (min * 60 + sec) * 1000L);
				return (min * 60 + sec) * 1000L; 
			} catch (Exception exe) {
				//System.out.println("====min");
				return -1;
			}
		} else if (ss.length == 3) {// ���������λ��������룬ʮ����
			try {
				int min = Integer.parseInt(ss[0]);
				int sec = Integer.parseInt(ss[1]);
				int mm = Integer.parseInt(ss[2]);
				//System.out.println("min" + min+"sec"+sec+"mm"+mm);
				if (min < 0 || sec < 0 || sec >= 60 || mm < 0 || mm > 999) {
					throw new RuntimeException("���ֲ��Ϸ�!");
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
		} else {// ����Ҳ�Ƿ�
			return -1;
		}
	}
	
	
	/**
	 * =====================�õ���ǰ���ڲ��ŵ���һ����±�===================== 
	 * 
	 * �������Ҳ�������Ϊ�ͷҪ��һ�� �Լ��ľ��� �����Լ����Ժ�Ͳ������Ҳ�����
	 * 
	 * @return �±�
	 */
	public static int getNowSentenceIndex(long t) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isInTime(t)) {
				return i;
			}
		}
		// throw new RuntimeException("��Ȼ�������Ҳ����������");
		return -1;
	}
}
