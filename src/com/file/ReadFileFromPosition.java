package com.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * 从指定的位置开始读取文件 文件任意位置读取 一般有两种方法： 1、使用FileInputStream类 ， skip指定位置
 * 2、使用RandomAccessFile类，seek指定位置
 * 
 * @author zealjiang
 * @time 2016年4月21日下午7:04:05
 */
public class ReadFileFromPosition {

	public static void main(String[] args) {
		readFromPos1();
		readFromPos2();
	}

	public static void readFromPos1() {
		long from = 4 + 1;// 从该字节开始读，自己测注意中文是两个字节
		try {
			File file = new File("d:/b.txt");
			FileInputStream bis = new FileInputStream(file);
			bis.skip(from - 1);// 文件指向前一字节
			@SuppressWarnings("resource")
			// 指定文件位置读取的文件流
			InputStream sbs = new BufferedInputStream(bis);
			// 存入文件，以便检测
			File file1 = new File("d:/b1.txt");
			OutputStream os = null;
			try {
				os = new FileOutputStream(file1);
				byte buffer[] = new byte[4 * 1024];
				int len = 0;
				while ((len = sbs.read(buffer)) != -1)//
				{
					os.write(buffer, 0, len);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void readFromPos2() {

		RandomAccessFile raf = null;

		try {
			File file = new File("d:/b.txt");
			if (raf == null) {
				raf = new RandomAccessFile(file, "rw");
			}

			// 将指针移到文件尾
			long length = 4;
			raf.seek(length);

			// 存入文件，以便检测
			File file1 = new File("d:/b2.txt");
			OutputStream os = null;
			try {
				os = new FileOutputStream(file1);
				byte buffer[] = new byte[4 * 1024];
				int len = 0;
				while ((len = raf.read(buffer)) != -1)//
				{
					os.write(buffer, 0, len);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
