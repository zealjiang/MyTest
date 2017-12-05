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
	  * 列出特定路径下的txt文件
	  * 
	  * @param directoryName
	  *            路径名
	  */
	 private static void list(String directoryName) {
		 
		  File dir = new File(directoryName);
		  // 确定该路径指向一个目录
		  if (dir.exists() && dir.isDirectory()) {
			   // 列出所有以sfz_tmp开头的文件
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
