package com.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFile {

	public static void main(String[] args) {
		String dir = "G:/";
//		String fileName = "IP代理机.txt";
		String fileName = "4.mp3";
		System.out.println(fileCheckIsExist(dir,fileName));
	}
	//dir是指找到的位置，fileName是指要查找的文件名称
	static List<String> dirs = new ArrayList();
	public static String fileCheckIsExist(String dir, String fileName){
		String str= "";
		File file2 = new File(dir);
		if(file2.exists()){
			File[] files = file2.listFiles();
			if(files.length>0){
				for(int i=0;i<files.length;i++){
					//如果是一个目录标记
					if(files[i].isDirectory()){
						dirs.add(files[i].getPath());
					}
					//如果找到就返回
					if(files[i].getName().equals(fileName)){
						return str= files[i].getPath();
					}else{
					//如果没有找到就继续找
					}			
				}
				 if(dirs.size()>0){
					 for(int i=0;i<dirs.size();i++){
						 System.out.println(">>>>"+dirs.get(i));
						 fileCheckIsExist(dirs.get(i),fileName);
					 }
				 }else{
					
					 
				 }
				 return str = "在"+file2+"下没有找到你要查找的"+fileName;
			}else{
				str = "你查找的"+fileName+"没有在"+file2.getPath()+"这个目录下";
			}

		}else{
			str = "你查找的位置"+file2.getPath()+"不存在";
		}
/*		System.out.println("..>"+dirs.size());
		for(int i=0;i<dirs.size();i++){
			System.out.println("N >"+dirs.get(i));
		}*/
		return str;
	}
}
