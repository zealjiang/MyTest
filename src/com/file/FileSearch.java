package com.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileSearch {

	public static void main(String[] args) throws IOException {

		System.out.println(System.getProperty("user.dir") + System.getProperty("file.separator"));
		searchContentInFile("b.txt","z");
	}
	
	/**
	 * 查找指定的中文内容在文件中的位置(起始位置是0)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param sFile 当前项目路径的文件的名字
	 * @param searchByte 要查找的内容
	 *
	 */
	public static void searchContentInFile(String sFile,String searchByte){

		Charset charset = Charset.forName("GB18030");
		CharsetDecoder decoder = charset.newDecoder();
		// 如果与中文无关，此二句及其后相关语句可刪除

		String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + sFile;

		try {

			FileInputStream fis = new FileInputStream(fileName);
			FileChannel fc = fis.getChannel();

			int sz = (int) fc.size();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

			CharBuffer cb = decoder.decode(bb);

			String s = String.valueOf(cb);

			int n = s.indexOf(searchByte);
			if (n > -1)
				System.out.println("查找的内容"+searchByte+"在文件中的位置 --- " + n);
			else
				System.out.println(searchByte+" --- not found! ");

			fc.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
