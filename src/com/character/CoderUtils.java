package com.character;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

public class CoderUtils {

	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}

	public static int char2ASCII(char c) {
		return (int) c;
	}

	public static String ascii2String(int[] ASCIIs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIs.length; i++) {
			sb.append((char) ascii2Char(ASCIIs[i]));
		}
		return sb.toString();
	}

	public static String ascii2String(String ASCIIs) {
		String[] ASCIIss = ASCIIs.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIss.length; i++) {
			sb.append((char) ascii2Char(Integer.parseInt(ASCIIss[i])));
		}
		return sb.toString();
	}

	public static int[] string2ASCII(String s) {// 字符串转换为ASCII码
		if (s == null || "".equals(s)) {
			return null;
		}

		char[] chars = s.toCharArray();
		int[] asciiArray = new int[chars.length];

		for (int i = 0; i < chars.length; i++) {
			asciiArray[i] = char2ASCII(chars[i]);
		}
		return asciiArray;
	}

	public static String getIntArrayString(int[] intArray) {
		return getIntArrayString(intArray, ",");
	}

	public static String getIntArrayString(int[] intArray, String delimiter) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intArray.length; i++) {
			sb.append(intArray[i]).append(delimiter);
		}
		return sb.toString();
	}

	public static String getASCII(int begin, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = begin; i < end; i++) {
			sb.append(i).append(":").append((char) i).append("/t");
			// sb.append((char) i).append("/t");
			if (i % 10 == 0) {
				sb.append("/n");
			}
		}
		return sb.toString();
	}

	public static String getCHASCII(int begin, int end) {
		return getASCII(19968, 40869);
	}

	public static void showASCII(int begin, int end) {
		for (int i = begin; i < end; i++) {
			// System.out.print(i + ":" + (char) i + "/t");
			System.out.print((char) i + "/t");
			if (i % 10 == 0) {
				System.out.println();
			}
		}
	}

	public static void showCHASCII() {
		showASCII(19968, 40869);
	}

	public static void showIntArray(int[] intArray) {
		showIntArray(intArray, ",");
	}

	public static void showIntArray(int[] intArray, String delimiter) {
		for (int i = 0; i < intArray.length; i++) {
		}
	}

	public static void createFile(String filePathAndName, String fileContent) throws IOException {

		String filePath = filePathAndName;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.createNewFile();
		}
		FileWriter resultFile = new FileWriter(myFilePath);
		PrintWriter myFile = new PrintWriter(resultFile);
		String strContent = fileContent;
		myFile.println(strContent);
		myFile.close();
		resultFile.close();
	}

	@Test
	public void getChineseString() {
		String carType = "长安牌SC7161A";
		char[] c = carType.toCharArray();
		int tp = 0;
		for (int i = 0; i < c.length; i++) {
			if ((int) c[i] > 10000) {
				tp += 2;
			} else {
				tp++;
			}
			System.out.println(i + " = " + (int) c[i]);
		}

	}

	// 判断是否为汉字
	@Test
	public void vd() {
		String carType = "长安牌*8&SC7161A";
		char[] chars = carType.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;

				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
					isGB2312 = true;
					System.out.println(i + " = " + (int) chars[i]);
					continue;
				}else{
					System.out.println(i + " = " + (int) chars[i]);
					break;
				}
			}else{
				System.out.println(i);
				break;
			}
		}
		//isGB2312;
	}
	
	
	public static int prefixChineseLength(String string) {
		char[] chars = string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;

				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
				}else{
					return i;
				}
			}else{
				return i;
			}
		}
		return string.length();
	}

	public static void main(String[] args) throws IOException {
		
		System.out.println(" "+prefixChineseLength("长安牌*8&SC7161A"));

		String s = "好好学习！天天向上！————笑的自然 2009年3月11日";
		System.out.println(string2ASCII(s));
		for (int i = 0; i < s.length(); i++) {
			// System.out.println((int)s.charAt(i));
		}
		System.out.println((char) 22909);

		/*
		 * showIntArray(string2ASCII(s), " "); System.out.println();
		 * System.out.println(ascii2String(string2ASCII(s)));
		 * 
		 * createFile("c://console_ch.txt", getCHASCII(0, 50000));
		 */
	}

}
