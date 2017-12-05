package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class ReadFileToString {

	public static void main(String[] args) throws IOException {

		// File read_file = new File("d:/1.txt");
		File read_file = new File("d:/1234.txt");
		// readFile(read_file);

		String s = readFile(read_file, null).trim();
		System.out.println("s  ..." + s);
		String[] sql = splitStringArray(s);
		String[] array = subString(sql);

		// System.out.println(alignMethod(sql[7]));
		/*
		 * File desfile = new File("e:/random.txt"); for(String ss:array){
		 * RandomAccessFileTest.addToFile(ss, desfile); }
		 */

		/*
		 * String sss= "sssss"+"\n"+"aaaaaaaaa"; System.out.println(sss); String
		 * ks = alignMethod(sss); System.out.println(ks);
		 */

		String str = "/*���ģ���ģ�ش��ģ���ģ�ش��ģ���ģ��sfsdafdsf�����ѩ��";
		String new_str = subString(str);
		System.out.println("src : " + str);
		System.out.println("new : " + new_str);

		// readFileFromTo(read_file);
	}

	/**
	 * 
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:13:25
	 * @param file
	 * @throws IOException
	 *
	 */
	public void readFileFromTo(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int len = in.read(buffer, 3, 8);
		String out_str = new String(buffer, 0, len);
		System.out.println("readFromTo :" + out_str);
	}

	public static String readFile(File file) {

		String out_str = "";
		StringBuilder sb = new StringBuilder();

		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int n;
			try {
				while ((n = in.read(buffer)) != -1) {
					out_str = new String(buffer, 0, n);
					sb.append(out_str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s_temp = sb.toString();
		// ���ļ����ݵĵ�һ��?��ȥ��
		// s_temp = sb.toString().substring(1);

		System.out.println("s_temp :" + s_temp);
		return s_temp;
	}

	public static String readFile(File file, String charater) {
		if (charater == null || charater.equals(""))
			charater = "UTF-8";
		String out_str = "";
		StringBuilder sb = new StringBuilder();

		try {
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int n;
			try {
				while ((n = in.read(buffer)) != -1) {
					// ע�⣺���ԭ�ļ�û�б��룬��new StringʱҲ���ܱ��룬����new String(buffer,0,n);
					out_str = new String(buffer, 0, n, charater);
					sb.append(out_str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s_temp = sb.toString();
		// ���ļ����ݵĵ�һ��?��ȥ��
		// s_temp = sb.toString().substring(1);

		System.out.println("s_temp :" + s_temp);
		return s_temp;
	}

	public static String[] splitStringArray(String str) {
		String[] sql = str.split(";");
		return sql;
	}

	public static String alignMethod(String str) {
		String s = null;
		if (str.contains("\r\n")) {
			s = str.replace("\r\n", "");
		} else if (str.contains("\n\r")) {
			s = str.replace("\n\r", "");
		} else if (str.contains("\n")) {
			s = str.replace("\n", "");
		}

		return s;
	}

	public static String[] subString(String[] str) {
		String[] newArray = new String[str.length];
		for (int k = 0; k < str.length; k++) {
			int end = str[k].trim().lastIndexOf("*/");
			if (end != -1) {
				newArray[k] = str[k].substring(end + 2);
			} else {
				newArray[k] = str[k];
			}

		}
		return newArray;
	}

	public static String subString(String str) {

		int end = str.lastIndexOf("/");
		String newArray = str.substring(end + 1);
		System.out.println("end :" + end);
		return newArray;
	}

	@Test
	public void readLineOneTimeFromFile() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // ���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
		try {
			String str = "";
			String str1 = "";
			fis = new FileInputStream("ʡ�м���.txt");// FileInputStream
			// ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
			isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ͨ���ַ���������,
			br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new
											// InputStreamReader�Ķ���
			while ((str = br.readLine()) != null) {
				str1 += str + "\n";
			}
			// ����ȡ��һ�в�Ϊ��ʱ,�Ѷ�����str��ֵ����str1
			System.out.println(str1);// ��ӡ��str1
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ���ָ���ļ�");
		} catch (IOException e) {
			System.out.println("��ȡ�ļ�ʧ��");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
