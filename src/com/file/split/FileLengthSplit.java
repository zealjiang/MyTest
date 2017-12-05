package com.file.split;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import com.byteArray.intToByteArray;

public class FileLengthSplit {

	public static void main(String[] args) {
		//splitByMaxSize(new File("e:/ccwj.RMVB"),100*1024*1024);
		String path = new File("e:/ccwj.RMVB").getAbsolutePath();
		int startIndex = path.lastIndexOf("\\");
		int endIndex = path.lastIndexOf(".");
		String filepath = "";
		String firstName = path.substring(0, startIndex+1);
		String fileName = path.substring(startIndex+1, endIndex);
		String lastName = path.substring(endIndex+1);
/*		System.out.println(new File("e:/ccwj.RMVB").getAbsolutePath());
		System.out.println(new File("e:/ccwj.RMVB").getAbsolutePath().lastIndexOf(".")+"");
		System.out.println(path.substring(0, startIndex+1));
		System.out.println(path.substring(startIndex+1, endIndex));
		System.out.println(path.substring(endIndex+1));
		filepath = firstName+new File("e:/ccwj.RMVB").getName()+"_"+11+".temp";
		System.out.println("filepath--->"+filepath);
		try {
			new File(filepath).createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path.substring(path.length()-1).equalsIgnoreCase("B"));
		System.out.println("1024b".substring(0,"1024b".length()-1));
		long tempSize = Long.valueOf("1024b".substring(0,"1024b".length()-1));
		System.out.println("  "+tempSize);*/
		

		splitByMaxSize(new File("F:/Guns.and.Roses.2012/Guns.and.Roses.2012.HDTV.720p.x264.AC3-DWR.mkv"),"1500m");
		List list = new ArrayList();
/*		list.add(new File("F:/Guns.and.Roses.2012/Guns.and.Roses_1.mkv"));
		list.add(new File("F:/Guns.and.Roses.2012/Guns.and.Roses_2.mkv"));
		list.add(new File("F:/Guns.and.Roses.2012/Guns.and.Roses_3.mkv"));
		FileMerge(new File("F:/Guns.and.Roses.2012/Guns.and.Roses.2012.HDTV.720p.x264.AC3-DWR.mkv"),list);*/
	}
	
	/**
	 * ��һ������ļ��ָ�������С���ļ���ÿ���ļ��Ĵ�С���Ϊsizeָ���Ĵ�С
	 * @param file Ҫ�ָ���Դ�ļ�
	 * @param size �û�ָ�������ָ���С,size�ĸ�ʽ��xxM��xxK,���磺32M��182k
	 */
	public static void splitByMaxSize(File file,String size){
		long realSize = -1;
		long tempSize = Long.valueOf(size.substring(0,size.length()-1));
		if(size.substring(size.length()-1).equalsIgnoreCase("m")){
			realSize = tempSize*1024*1024;
		}else if(size.substring(size.length()-1).equalsIgnoreCase("k")){
			realSize = tempSize*1024;
		}else{
			try {
				throw new SizeFormatException();
			} catch (SizeFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		//�ж��ļ��Ĵ�С�Ƿ�С��size�Ĵ�С
		long fileLength = file.length();
		if(fileLength<realSize){
			return;
		}
				
		//����ɽ��ļ��ָ��ɼ���
		long num = -1;
		if(fileLength%realSize==0){
			num = fileLength/realSize;
		}else{
			num = fileLength/realSize + 1;
		}
		String path = file.getAbsolutePath();
		int startIndex = path.lastIndexOf("\\");
		int endIndex = path.lastIndexOf(".");
		
		System.out.println("path--->"+path);//path--->e:\ccwj.RMVB
		
		
		try{
			//��ȡ�ļ�����
			String filepath = "";
			String firstName = path.substring(0, startIndex+1);
			String fileName = path.substring(startIndex+1, endIndex);
			String lastName = path.substring(endIndex+1);
			//�ָ��ļ�
			FileOutputStream fos = null;
			RandomAccessFile fis = null;
			for(int i=0;i<num;i++){
				filepath = firstName+file.getName()+"_"+i+".temp";
				fos = new FileOutputStream(filepath);
				fis = new RandomAccessFile(file, "r");
				fis.seek(realSize*i);
				byte[] b = new byte[1024];
				int readLength = 0;
				while((readLength = fis.read(b, 0, b.length))!= -1){
					if(fis.getFilePointer()<=(i+1)*realSize){
						fos.write(b, 0, readLength);
					}
				}
				
			}
			fos.flush();
			fis.close();
			fos.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void FileMerge(File file,List<File> fList){
		
		try{

			//�ж�Ҫ�ϲ����ļ��Ƿ����
			if(fList.size()<=1){
				throw new NumberFormatException();
			}
				
			for(int i=0;i<fList.size();i++){
				if(!fList.get(i).exists()){
					throw new FileNotFoundException();
				}
			}
			//�ϲ��ļ�
			RandomAccessFile fos = new RandomAccessFile(file,"rw");
			FileInputStream fis = null;
			byte[] buffer = new byte[1024];
			int readLength;
			for(int i=0;i<fList.size();i++){
				fis = new FileInputStream(fList.get(i));
				while((readLength = fis.read(buffer, 0, buffer.length))!= -1){
					fos.write(buffer,0,readLength);
				}
			}
			fos.close();
			fis.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
