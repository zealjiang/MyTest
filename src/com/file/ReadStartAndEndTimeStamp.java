package com.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * ��ȡ���μ�¼�ļ��еĵ�һ��ʱ��������һ��ʱ�������
 * ����Ӧ�����ļ��е�λ��
 * @author zealjiang
 * @time 2016��4��22������9:30:28
 */
public class ReadStartAndEndTimeStamp {

	/**
	 * 
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:12:32
	 * @throws FileNotFoundException
	 *
	 */
    private void getFirstAndLastTimeStamp() throws FileNotFoundException{
    	File waveFile = new File("��־��13048118612569654_20160422103234.YE");
    	FileTimestampPoi ftpStart = new FileTimestampPoi();
    	FileTimestampPoi ftpEnd = new FileTimestampPoi();
    	
    	FileInputStream bis = new FileInputStream(waveFile);
    	BufferedInputStream sbs = new BufferedInputStream(bis);
		try {
			int poi = 0;//��ǰ��ȡ���ֽ����ļ��е�λ��
			int data = -1;//��ǰ��ȡ���ֽ�����
			while ((data = sbs.read()) != -1)//
			{
				if(data==16){
					ftpStart.setPosition(poi);
					//sbs.read(b, off, len)
				}
				poi++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
    	
    	
    	
		   RandomAccessFile raf = null ;

		   try {
			if(raf==null){
				   raf = new RandomAccessFile(waveFile,"r");
			}

		     //��ָ���Ƶ��ļ�β
		     long length =  raf.length();
		     raf.seek(length);

		     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * ����ָ���������������ļ��е�λ��(��ʼλ����0)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param sFile ��ǰ��Ŀ·�����ļ�������
	 * @param searchByte Ҫ���ҵ��ֽ�
	 * @return �ҵ������������ļ��е�λ�ã���֮����-1
	 */
	public static int searchContentInFile(File waveFile,byte searchByte){

		try {
	    	FileInputStream bis = new FileInputStream(waveFile);
	    	BufferedInputStream sbs = new BufferedInputStream(bis);
			int poi = 0;//��ǰ��ȡ���ֽ����ļ��е�λ��
			int data = -1;//��ǰ��ȡ���ֽ�����
			while ((data = sbs.read()) != -1)//
			{
				if(data==searchByte){
					return poi;
				}
				poi++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return -1;
	}
    
    /**
     * ��¼�����ļ���ʱ����Ծ͵�ʱ���λ��
     * @author zealjiang
     * @time 2016��4��22������11:10:14
     */
    class FileTimestampPoi{
    	private String time;
    	private int position;
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public int getPosition() {
			return position;
		}
		public void setPosition(int position) {
			this.position = position;
		}
    	   	
    }
}
