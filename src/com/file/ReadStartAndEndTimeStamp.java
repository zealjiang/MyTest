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
 * 读取波形记录文件中的第一个时间戳和最后一个时间戳及其
 * 所对应的在文件中的位置
 * @author zealjiang
 * @time 2016年4月22日上午9:30:28
 */
public class ReadStartAndEndTimeStamp {

	/**
	 * 
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:12:32
	 * @throws FileNotFoundException
	 *
	 */
    private void getFirstAndLastTimeStamp() throws FileNotFoundException{
    	File waveFile = new File("李志江13048118612569654_20160422103234.YE");
    	FileTimestampPoi ftpStart = new FileTimestampPoi();
    	FileTimestampPoi ftpEnd = new FileTimestampPoi();
    	
    	FileInputStream bis = new FileInputStream(waveFile);
    	BufferedInputStream sbs = new BufferedInputStream(bis);
		try {
			int poi = 0;//当前读取的字节在文件中的位置
			int data = -1;//当前读取的字节内容
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

		     //将指针移到文件尾
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
	 * 查找指定的中文内容在文件中的位置(起始位置是0)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param sFile 当前项目路径的文件的名字
	 * @param searchByte 要查找的字节
	 * @return 找到返回其所在文件中的位置，反之返回-1
	 */
	public static int searchContentInFile(File waveFile,byte searchByte){

		try {
	    	FileInputStream bis = new FileInputStream(waveFile);
	    	BufferedInputStream sbs = new BufferedInputStream(bis);
			int poi = 0;//当前读取的字节在文件中的位置
			int data = -1;//当前读取的字节内容
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
     * 记录波形文件中时间戳对就的时间和位置
     * @author zealjiang
     * @time 2016年4月22日上午11:10:14
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
