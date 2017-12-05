package com.file;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.Test;

public class FileNameFileter {

	public static void main(String[] args) {
		list("e:/111");
	}
	
	public  static void delete(){
		
		File file = new File("e:/111");
	}
	
	@Test
	public void getName(){
		File f = new File("e:/www/1.jpg");
		System.out.println(f.getName());
		System.out.println(f.getParent());
		System.out.println(f.getPath());
	}
	
	 /**
	  * �г��ض�·���µ�txt�ļ�
	  * 
	  * @param directoryName
	  *            ·����
	  */
	 private static void list(String directoryName) {
		 
		  File dir = new File(directoryName);
		  // ȷ����·��ָ��һ��Ŀ¼
		  if (dir.exists() && dir.isDirectory()) {
			   // �г�������sfz_tmp��ͷ���ļ�
			   File[] files = dir.listFiles(new FilenameFilter() {
				    @Override
				    public boolean accept(File dir, String name) {
				     return name.startsWith("sfz_tmp");
				    }
			   });
			   
			   for(int i = 0;i<files.length;i++){
				   System.out.println(files[i].getName());
			   }
			   
			   for(int i = 0;i<files.length;i++){
					  if(files[i].isFile() && files[i].exists())
						  files[i].delete();
			   }
		  }
	 }
}
