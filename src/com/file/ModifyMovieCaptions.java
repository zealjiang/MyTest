package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ModifyMovieCaptions {

	
	public static void main(String[] args) {
		
		File file = new File("G:/defaced-tronlegacy-b.eng.srt");
		File fileOut = new File("G:/defaced-tronlegacy-b.eng2.srt");
		List list1 = FileToList(file);
		List list2 = modifyIndex(1,1,list1);
		List list3 = modifyMoveTime(list2,62688);
		ListToFile(list3,fileOut);
	}
	
	/**
	 * 返回这个电影的字幕list
	 * @param source 源电影字幕文件
	 * @return 将电影字幕文件一行行地装入list中，并返回这个list
	 */
	private static List FileToList(File source){
		
		List list = new ArrayList();	
		BufferedReader br = null;
		try {
			//判断文件是否存在
			if(!source.exists())
				throw new FileNotFoundException();	
			br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));  

			String temp = null;
			while ((temp = br.readLine()) != null) {
				list.add(temp+"\n");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;	
	}
	
	/**
	 * 修改电影字幕文件的Index值
	 * @param oldIndex 修改之前的原电影字幕的第一个Index值
	 * @param newIndex 修改之后的电影字幕的第一个Index值
	 * @param fList 原电影文件内容的List格式 
	 * @return 返回修改Index后的list
	 */
	private static List<String> modifyIndex(int oldIndex,int newIndex,List<String> fList){
		try{

			//判断要修改的文件是否存在
			if(fList.size()<=1){
				throw new NumberFormatException();
			}
				
			for(int i=0;i<fList.size();i++){
				try{
					if(Integer.valueOf(fList.get(i).substring(0, fList.get(i).length()-1))==oldIndex){						
						fList.set(i, newIndex+"\n");
						oldIndex++;
						newIndex++;
					}
				}catch(Exception e){
					continue;
				}
			}
							
		}catch (Exception e) {
			e.printStackTrace();
		}

		return fList;
	}
	
	/**
	 * 将list中的内容写进File文件中，并返回这个file文件
	 * @param list 包含电影字幕的list文件
	 * @param file 要返回的电影字幕文件
	 * @return 返回电影字幕文件file
	 */
	private static File ListToFile(List<String> list,File file){
		try{

			//判断要修改的文件是否存在
			if(list.size()<=1){
				throw new NumberFormatException();
			}
	
			//合并文件
			RandomAccessFile fos = new RandomAccessFile(file,"rw");
			byte[] buffer;
			for(int i=0;i<list.size();i++){
				
			     //将指针移到文件尾
			     long length =  fos.length();
			     fos.seek(length);
			     //写内容到文件
			     buffer = list.get(i).getBytes();
			     fos.write(buffer);
			}
			fos.close();	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	/**
	 * 
	 * @param srcList 原电影文件内容的list
	 * @param addTime 在现在的时间上增加或减少的时间(单位：毫秒)，addTime为正数表示增加，addTime为负数表示减少
	 * @return 返回修改电影字幕文件后的list
	 */
	private static List modifyMoveTime(List<String> srcList,long addTime){
		try{

			//判断要修改的文件是否存在
			if(srcList.size()<=1){
				throw new NumberFormatException();
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<srcList.size();i++){
				try{
										
					if(srcList.get(i).trim().substring(13, 16).equals("-->")){	
												
						//得到from时间
						String from = srcList.get(i).substring(0, 12);
						//将from时间转换成毫秒
						long fromTime = Integer.valueOf(from.substring(0, 2))*3600*1000+
								Integer.valueOf(from.substring(3, 5))*60*1000+
								Integer.valueOf(from.substring(6, 8))*1000+
								Integer.valueOf(from.substring(9, 12));

						//将fromTime时间与addTime求和,并返回“01:06:13,386”格式的时间
						long newFT = fromTime+addTime;
						String f = msToTime(newFT);	
						
						//得到to时间
						String to =srcList.get(i).substring(17, srcList.get(i).length());

						//将to时间转换成毫秒
						long toTime = Integer.valueOf(to.substring(0, 2))*3600*1000+
								Integer.valueOf(to.substring(3, 5))*60*1000+
								Integer.valueOf(to.substring(6, 8))*1000+
								Integer.valueOf(to.substring(9, 12));
						
						//将fromTime时间与addTime求和,并返回“01:06:13,386”格式的时间
						long newTT = toTime+addTime;
						String t = msToTime(newTT);	
						srcList.set(i,f+" --> "+t+"\n");
						
					}
					
				}catch(Exception e){
					//e.printStackTrace();
					continue;
				}
			}
							
		}catch (Exception e) {
			e.printStackTrace();
		}

		return srcList;
	}
	
	/**
	 * 将毫秒转换成“00:00:30,280”时间格式
	 * @param millSeconds 毫秒
	 * @return 返回转换后的时间格式
	 */
	private static String msToTime(long newFT){
		StringBuilder sb = new StringBuilder();
		//计算小时
		if(newFT/(3600*1000)>0){
			if(newFT/(3600*1000)<10){
				sb.append("0"+newFT/(3600*1000));
			}else{
				sb.append(newFT/(3600*1000));
			}
		}else{
			sb.append("00");
		}
		sb.append(":");
		
		//计算分钟
		if(newFT/(1000*60)%60>0){
			if(newFT/(1000*60)%60<10){
				sb.append("0"+newFT/(1000*60)%60);
			}else{
				sb.append(newFT/(1000*60)%60);
			}
		}else{
			sb.append("00");
		}
		sb.append(":");
		
		
		//计算秒
		if(newFT/1000%3600%60>0){
			if(newFT/1000%3600%60<10){
				sb.append("0"+newFT/1000%3600%60);
			}else{
				sb.append(newFT/1000%3600%60);
			}
		}else{
			sb.append("00");
		}
		sb.append(",");
		
		//计算毫秒
		sb.append(String.valueOf(newFT).substring(String.valueOf(newFT).length()-3, String.valueOf(newFT).length()));
		
		return sb.toString();
	}
	
	
}
