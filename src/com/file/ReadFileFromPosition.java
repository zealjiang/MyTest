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
 * ��ָ����λ�ÿ�ʼ��ȡ�ļ� �ļ�����λ�ö�ȡ һ�������ַ����� 1��ʹ��FileInputStream�� �� skipָ��λ��
 * 2��ʹ��RandomAccessFile�࣬seekָ��λ��
 * 
 * @author zealjiang
 * @time 2016��4��21������7:04:05
 */
public class ReadFileFromPosition {

	public static void main(String[] args) {
		readFromPos1();
		readFromPos2();
	}

	public static void readFromPos1() {
		long from = 4 + 1;// �Ӹ��ֽڿ�ʼ�����Լ���ע�������������ֽ�
		try {
			File file = new File("d:/b.txt");
			FileInputStream bis = new FileInputStream(file);
			bis.skip(from - 1);// �ļ�ָ��ǰһ�ֽ�
			@SuppressWarnings("resource")
			// ָ���ļ�λ�ö�ȡ���ļ���
			InputStream sbs = new BufferedInputStream(bis);
			// �����ļ����Ա���
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

			// ��ָ���Ƶ��ļ�β
			long length = 4;
			raf.seek(length);

			// �����ļ����Ա���
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
