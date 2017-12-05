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
	 * ���������Ӱ����Ļlist
	 * @param source Դ��Ӱ��Ļ�ļ�
	 * @return ����Ӱ��Ļ�ļ�һ���е�װ��list�У����������list
	 */
	private static List FileToList(File source){
		
		List list = new ArrayList();	
		BufferedReader br = null;
		try {
			//�ж��ļ��Ƿ����
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
	 * �޸ĵ�Ӱ��Ļ�ļ���Indexֵ
	 * @param oldIndex �޸�֮ǰ��ԭ��Ӱ��Ļ�ĵ�һ��Indexֵ
	 * @param newIndex �޸�֮��ĵ�Ӱ��Ļ�ĵ�һ��Indexֵ
	 * @param fList ԭ��Ӱ�ļ����ݵ�List��ʽ 
	 * @return �����޸�Index���list
	 */
	private static List<String> modifyIndex(int oldIndex,int newIndex,List<String> fList){
		try{

			//�ж�Ҫ�޸ĵ��ļ��Ƿ����
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
	 * ��list�е�����д��File�ļ��У����������file�ļ�
	 * @param list ������Ӱ��Ļ��list�ļ�
	 * @param file Ҫ���صĵ�Ӱ��Ļ�ļ�
	 * @return ���ص�Ӱ��Ļ�ļ�file
	 */
	private static File ListToFile(List<String> list,File file){
		try{

			//�ж�Ҫ�޸ĵ��ļ��Ƿ����
			if(list.size()<=1){
				throw new NumberFormatException();
			}
	
			//�ϲ��ļ�
			RandomAccessFile fos = new RandomAccessFile(file,"rw");
			byte[] buffer;
			for(int i=0;i<list.size();i++){
				
			     //��ָ���Ƶ��ļ�β
			     long length =  fos.length();
			     fos.seek(length);
			     //д���ݵ��ļ�
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
	 * @param srcList ԭ��Ӱ�ļ����ݵ�list
	 * @param addTime �����ڵ�ʱ�������ӻ���ٵ�ʱ��(��λ������)��addTimeΪ������ʾ���ӣ�addTimeΪ������ʾ����
	 * @return �����޸ĵ�Ӱ��Ļ�ļ����list
	 */
	private static List modifyMoveTime(List<String> srcList,long addTime){
		try{

			//�ж�Ҫ�޸ĵ��ļ��Ƿ����
			if(srcList.size()<=1){
				throw new NumberFormatException();
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<srcList.size();i++){
				try{
										
					if(srcList.get(i).trim().substring(13, 16).equals("-->")){	
												
						//�õ�fromʱ��
						String from = srcList.get(i).substring(0, 12);
						//��fromʱ��ת���ɺ���
						long fromTime = Integer.valueOf(from.substring(0, 2))*3600*1000+
								Integer.valueOf(from.substring(3, 5))*60*1000+
								Integer.valueOf(from.substring(6, 8))*1000+
								Integer.valueOf(from.substring(9, 12));

						//��fromTimeʱ����addTime���,�����ء�01:06:13,386����ʽ��ʱ��
						long newFT = fromTime+addTime;
						String f = msToTime(newFT);	
						
						//�õ�toʱ��
						String to =srcList.get(i).substring(17, srcList.get(i).length());

						//��toʱ��ת���ɺ���
						long toTime = Integer.valueOf(to.substring(0, 2))*3600*1000+
								Integer.valueOf(to.substring(3, 5))*60*1000+
								Integer.valueOf(to.substring(6, 8))*1000+
								Integer.valueOf(to.substring(9, 12));
						
						//��fromTimeʱ����addTime���,�����ء�01:06:13,386����ʽ��ʱ��
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
	 * ������ת���ɡ�00:00:30,280��ʱ���ʽ
	 * @param millSeconds ����
	 * @return ����ת�����ʱ���ʽ
	 */
	private static String msToTime(long newFT){
		StringBuilder sb = new StringBuilder();
		//����Сʱ
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
		
		//�������
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
		
		
		//������
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
		
		//�������
		sb.append(String.valueOf(newFT).substring(String.valueOf(newFT).length()-3, String.valueOf(newFT).length()));
		
		return sb.toString();
	}
	
	
}
