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
	 * ����ָ���������������ļ��е�λ��(��ʼλ����0)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param sFile ��ǰ��Ŀ·�����ļ�������
	 * @param searchByte Ҫ���ҵ�����
	 *
	 */
	public static void searchContentInFile(String sFile,String searchByte){

		Charset charset = Charset.forName("GB18030");
		CharsetDecoder decoder = charset.newDecoder();
		// ����������޹أ��˶��估���������Ʉh��

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
				System.out.println("���ҵ�����"+searchByte+"���ļ��е�λ�� --- " + n);
			else
				System.out.println(searchByte+" --- not found! ");

			fc.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
