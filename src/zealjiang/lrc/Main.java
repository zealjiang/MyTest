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
	 * 将一个file读成一个String类型
	 * @param file 源文件
	 * @return 返回file内容的String类型
	 */
	public static void  RSTFile(String str){
		
		StringBuilder sb = new StringBuilder();
		try {
			//目标文件
			RandomAccessFile ofile = new RandomAccessFile("e:/七里香.lrc","rw");
			
		     //将指针移到文件尾
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
