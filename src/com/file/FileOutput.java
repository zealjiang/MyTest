package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOutput {

	public static void main(String[] args) {
		
		String str = "这只是一个测试";
		fileOutput(str);
	}
	
	public static void fileOutput(String str){
		
		File out_file = new File("d:/hhh.SQL");
		FileOutputStream fos = null;
		try {
		fos = new FileOutputStream(out_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int k=0;k<1;k++){
//		   db.execSQL(test3);	
			byte[] buffer = str.trim().getBytes();

			try {
				fos.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void testWriteFrom(){
	      byte[] b = {'h', 'e', 'l', 'l', 'o'};
	      try {

	         // create a new output stream
	         OutputStream os = new FileOutputStream("test.txt");

	         // craete a new input stream
	         InputStream is = new FileInputStream("test.txt");

	         // write something
	         os.write(b, 0, 3);

	         // read what we wrote
	         for (int i = 0; i < 3; i++) {
	            System.out.print("" + (char) is.read());
	         }

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	}
}
