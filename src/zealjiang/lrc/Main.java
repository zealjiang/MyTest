package zealjiang.lrc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class Main {

	public static void main(String[] args) {
		LrcParse lrc = new LrcParse(new File("e:/qilixiang.lrc"));
		int i = lrc.getNowSentenceIndex(101);
		String content = lrc.list.get(i).getContent();
		System.out.println("content :"+content);
	}
	
	/**
	 * ��һ��file����һ��String����
	 * @param file Դ�ļ�
	 * @return ����file���ݵ�String����
	 */
	public static void  RSTFile(String str){
		
		StringBuilder sb = new StringBuilder();
		try {
			//Ŀ���ļ�
			RandomAccessFile ofile = new RandomAccessFile("e:/������.lrc","rw");
			
		     //��ָ���Ƶ��ļ�β
		     long length =  ofile.length();
		     ofile.seek(length);	
			byte[] buffer = (str+"\n").getBytes();
			ofile.write(buffer);
			ofile.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
