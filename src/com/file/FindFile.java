package com.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFile {

	public static void main(String[] args) {
		String dir = "G:/";
//		String fileName = "IP�����.txt";
		String fileName = "4.mp3";
		System.out.println(fileCheckIsExist(dir,fileName));
	}
	//dir��ָ�ҵ���λ�ã�fileName��ָҪ���ҵ��ļ�����
	static List<String> dirs = new ArrayList();
	public static String fileCheckIsExist(String dir, String fileName){
		String str= "";
		File file2 = new File(dir);
		if(file2.exists()){
			File[] files = file2.listFiles();
			if(files.length>0){
				for(int i=0;i<files.length;i++){
					//�����һ��Ŀ¼���
					if(files[i].isDirectory()){
						dirs.add(files[i].getPath());
					}
					//����ҵ��ͷ���
					if(files[i].getName().equals(fileName)){
						return str= files[i].getPath();
					}else{
					//���û���ҵ��ͼ�����
					}			
				}
				 if(dirs.size()>0){
					 for(int i=0;i<dirs.size();i++){
						 System.out.println(">>>>"+dirs.get(i));
						 fileCheckIsExist(dirs.get(i),fileName);
					 }
				 }else{
					
					 
				 }
				 return str = "��"+file2+"��û���ҵ���Ҫ���ҵ�"+fileName;
			}else{
				str = "����ҵ�"+fileName+"û����"+file2.getPath()+"���Ŀ¼��";
			}

		}else{
			str = "����ҵ�λ��"+file2.getPath()+"������";
		}
/*		System.out.println("..>"+dirs.size());
		for(int i=0;i<dirs.size();i++){
			System.out.println("N >"+dirs.get(i));
		}*/
		return str;
	}
}
