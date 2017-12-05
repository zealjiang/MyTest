package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile2AscllFile {
	
	public static void main(String[] args) {
		try {
			readFile(new File("d:/2.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readFile(File srcfile) throws IOException {
		File desfile = new File("d:/a.txt");
		FileInputStream in = null;
		FileOutputStream localFileOutputStream = new FileOutputStream(desfile);

		try {
			int n;
			in = new FileInputStream(srcfile);
			byte[] buffer = new byte[1024];
		
			while (-1 != (n = in.read(buffer))) {
				for (int i = 0; i < n; i++) {		
					localFileOutputStream.write((Integer.toHexString(buffer[i] & 0xFF) + " ").getBytes());
				}	
			}
		} finally {
			try {
				if (localFileOutputStream != null) {
					localFileOutputStream.close();
				}
			} catch (IOException ioe) {

			}
			System.out.println("read over");
		}
	}
}
