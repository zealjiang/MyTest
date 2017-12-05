package com.file;

import java.io.File;

public class ListDirFiles {

	public static void main(String[] args) {
		ListDirFiles ldf = new ListDirFiles();
		ldf.listFiles("d:/");
	}
	
	
	/**
	 * 
	 * @author zealjiang
	 * @date 2016��4��19�� ����3:18:57
	 * @param dir
	 *
	 */
	private void listFiles(String directoryName){
		  File dir = new File(directoryName);
		  // ȷ����·��ָ��һ��Ŀ¼
		  if (dir.exists() && dir.isDirectory()) {
			   File[] files = dir.listFiles();
			   
			   for(int i = 0;i<files.length;i++){
				   System.out.println(files[i].getName());
			   }
		  }

	}
}
