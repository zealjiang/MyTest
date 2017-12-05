package com.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.byteArray.HotterUtil;
import com.byteArray.HotterUtil.OriginalPacket;
import com.time.ParseTime;
import com.time.TimeAdding;

/**
 * 
 * @author zealjiang
 * @time 2016年4月22日下午1:14:55
 */
public class WaveFileRead {
	
	/**模块实时状态命令0x10的长度*/
	private final int C0x10Length = 17;
	/**模块实时状态命令ID 0x10*/
	private final byte C0x10 = 0x10; 
	/**波形信息状态命令0x60的长度*/
	private final int C0x60Length = 76;
	/**一页多少包0x60的数据*/
	private int mPagePackets = 0;
	/**一页多少秒的数据*/
	private float mPageSecData = 0;
	/**文件的最大页码*/
	private float mMaxPage = -1;
	/**1秒有多少包波形数据*/
	private int mSecPacket = -1;
	/**1秒有多少字节数据*/
	private long mSecBytes = -1;
	/**文件的开始时间*/
	private String mFileStartTime = "";
	/**文件的终止时间*/
	private String mFileEndTime = "";
	/**文件的第一个时间的开始位置*/
	private long mFileStartPos = -1;
	/**文件的终止位置*/
	private long mFileEndPos = -1;
	/**递归校准调用退出判断条件*/
	private int mRecursion = 0;
	/**递归调用的次数*/
	private int mRecursionNum = 0;
	
	private HotterUtil mHotterUtil = null;
	/**文件搜索对象*/
	private MappedByteBuffer mMappedByteBuffer;
	/**文件搜索对象,退出时记录close*/
	private FileChannel mFileChannel;

	public static void main(String[] args) {
		WaveFileRead wfr = new WaveFileRead();
		//File read_file = new File("d:/1234.txt");		
		//"_20160418100305.YE"
		//File read_file = new File("_20160510135912.YE");
		//File read_file = new File("李志江13048118612569654_20160422103234.YE");
		File read_file = new File("李志江12555555_20160516153213.YE");
		
		
/*		System.out.println(Integer.valueOf("1", 16));
		long first = wfr.searchContentInFile(read_file,Integer.valueOf("10", 16).byteValue(),609);
		wfr.searchContentInFile2(read_file,Integer.valueOf("10", 16).byteValue(),609);
		
		wfr.lastIndexOfInFile(read_file,Integer.valueOf("10", 16).byteValue(),12406);
		wfr.lastIndexOfInFile2(read_file,Integer.valueOf("10", 16).byteValue(),12406);
		
		byte[] bytes = wfr.readFromTo(read_file,first,17+first);*/
		//byte[] bytes = wfr.readFromTo(read_file,0,10);
		//打印数组各字节内容
/*		for (int i = 0; i < bytes.length; i++) {
			System.out.println(i+" "+Integer.toHexString((bytes[i]&0xFF)));
		}*/
		
		/*System.out.println("--------------");
		long poi = wfr.calPoiSpecifiedTime(read_file,"2016-04-22 10:31:09");
		byte[] b_poi = wfr.readFromTo(read_file,poi,poi+17);
		System.out.println("b_poi length: "+b_poi.length);
		//打印数组各字节内容
		for (int i = 0; i < b_poi.length; i++) {
			System.out.println(i+": "+Integer.toHexString((b_poi[i]&0xFF)));
		}
		//转成时间
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < b_poi.length; i++) {
			list.add(b_poi[i]);			
		}
		String sStartTime = hu.readTimeFromBytes(list);
		System.out.println("sStartTime: "+sStartTime);
		
		//测试1秒钟有多少包波形数据
		wfr.calSecPacket(read_file);
		
		wfr.calStart60Poi(read_file);
		wfr.calEnd60Poi(read_file);
		wfr.getMaxPage(read_file,76*3);*/
/*		wfr.getMaxPageBytime(read_file,2.4932f);
		wfr.setPageSecData(2.4932f);*/
		wfr.getMaxPageBytime(read_file,1.0f);
		wfr.setPageSecData(1.0f);
/*		wfr.getPageBytesByTimeIntSec(read_file,1);
		wfr.getPageBytesByTimeIntSec(read_file,2);
		wfr.getPageBytesByTimeIntSec(read_file,4);*/
		System.out.println("//--------测试getPageBytesByTime--------------");
		//从此位置向前查找到第一个第一个0x10的位置
/*		long findPoi = wfr.lastIndexOfInFile(read_file,(byte) 0x10,46180);
		byte[] b_poi = wfr.readFromTo(read_file,findPoi,findPoi+wfr.C0x10Length);
		if(b_poi==null){
			return;
		}
		//取出时间
		String thisTime = wfr.byteArrayToTime(b_poi);
		System.out.println(findPoi+" thisTime :"+thisTime);*/
		
		//wfr.getPageBytesByTime(read_file,1337);
		
		wfr.getPageBytesByTime(read_file,48);
/*		for (int i = 200; i < 204; i++) {
			if(i%10==0){
				wfr.getPageBytesByTime(read_file,--i);
				i+=2;
			}else{
				wfr.getPageBytesByTime(read_file,i);
			}
		}*/


	}
	
	/**
	 * 读取文件中从from到to指定的字节(字节的取值范围是从0到255)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:16:05
	 * @param file 要读取的文件
	 * @param from 读取文件的开始位置(包含开始位置的字节，注意：文件的起始位置是0)
	 * @param to 读取文件的结束位置(不包含结束位置的字节)
	 * @return 返回从from到to位置的字节(包含from和to位置的字节)
	 *
	 */
	 private byte[] readFromTo(File file,long from,long to){
			
		 System.out.println("readFromTo from："+from+" to: "+to);
		//判断文件是否存在
		if(null==file||file.exists()==false){
			System.out.println("文件不存在");
			//throw new FileNotFoundException();
			return null;
		}
		//判断开始的位置和文件的内容长度是不否在文件中
		if(from<0||from>file.length()){
			System.out.println("搜索的起始位置小于0或大于文件长度");
			//throw new Exception("搜索的起始位置小于0或大于文件长度");
			return null;
		}
		if(to>file.length()){
			System.out.println("搜索的长度从起始位置开始大于文件长度");
			//throw new Exception("搜索的长度从起始位置开始大于文件长度");
			return null;
		}
		//判断开始的位置和文件的内容长度是不否在文件中
		if(from>to){
			System.out.println("搜索的起始位置大于搜索的结束位置");
			//throw new Exception("搜索的起始位置小于0或大于文件长度");
			return null;
		}
		System.out.println("文件的大小："+file.length());
		byte[] buffer = null;
		try {
			
			FileInputStream in = new FileInputStream(file);
			in.skip(from);// 文件指向前一字节
			int i = (int) (to-from);
			buffer = new byte[i];
			int len = in.read(buffer);
			String out_str = new String(buffer, 0, len);
			//System.out.println("len :" + len);
			//System.out.println("readFromTo :" + out_str);
			//System.out.println("buffer :" + new String(buffer));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer;
	}
	
	/**
	 * 查找指定的中文内容在文件中的位置(起始位置是0,如果文件中有多处指定的内容，返回第一个匹配的位置)
	 * 注意：单个文件大小最大为2G
	 * 由于MappedByteBuffer.get(int index),index为整型数值,
	 * java Integer.MAX_VALUE为2147483647；这里查询的单位是字节，
	 * 2147483647字节转换成GB为2，也就是单个文件最大为2GByte,超过这个值可能
	 * 会出错
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param sFile 当前项目路径的文件的名字
	 * @param searchByte 要查找的内容
	 * @param from 从from位置起向后，如果from为-1表示从文件头向后查找
	 * @return 此内容在文件中第一次出现的位置，找不到返回-1
	 */
	public long searchContentInFile(File file,byte searchByte,long from){


		//String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + sFile;

/*		long n = -1;
		try {

			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();

			long end = fc.size();
			int start = 0;
			if(from!=-1){
				start = (int)from;
			}
	
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, end);
					
			
			for (int i = start; i < bb.capacity(); i++) {
				if(searchByte==bb.get(i)){
					n = i;
					break;
				}
			}
			
			if (n > -1)
				System.out.println("searchContentInFile 从前向后查找：查找的内容"+searchByte+"在文件中的位置 --- " + n);
			else
				System.out.println(searchByte+" --- not found! ");

			bb.clear();
			fc.close();
			fis.close();

		} catch (Exception e) {
			System.out.println(e);
		}*/
		
		
		long n = -1;
		long end = 0;
		int start = 0;
		try {

			if(mMappedByteBuffer==null) {
				FileInputStream fis = new FileInputStream(file);
				mFileChannel = fis.getChannel();


				end = mFileChannel.size();
				mMappedByteBuffer = mFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, end);
			}
			end = mFileChannel.size();
			if (from != -1) {
				start = (int) from;
			}
			if(start>end){
				start = (int)(end-1)-(int)(mSecBytes*mPageSecData);
				System.out.println("重新调整的start位置为："+start);
			}

			for (int i = start; i < mMappedByteBuffer.capacity(); i++) {
				if(searchByte==mMappedByteBuffer.get(i)){
					n = i;
					break;
				}
			}

			if (n > -1) {
				//System.out.println("searchContentInFile 从前向后查找：查找的内容" + searchByte + "在文件中的位置 --- " + n);
			} else {
				System.out.println(searchByte + " --- not found! ");
			}

			//mFileChannel.close();

		} catch (Exception e) {
			try {
				mFileChannel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(e);
		}
		
		return n;
	}
	
	/**
	 * 查找指定的中文内容在文件中的位置(起始位置是0,如果文件中有多处指定的内容，返回第一个匹配的位置)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param sFile 当前项目路径的文件的名字
	 * @param searchByte 要查找的字节
	 * @param from 从from位置起向后，如果from为-1表示从文件头向后查找
	 * @return 此内容在文件中第一次出现的位置，找不到返回-1
	 */
	public long searchContentInFile2(File waveFile,byte searchByte,long from){

		FileInputStream bis = null;
		BufferedInputStream sbs = null;
		try {
	    	bis = new FileInputStream(waveFile);
	    	sbs = new BufferedInputStream(bis);
			long poi = 0;//当前读取的字节在文件中的位置
			if(from!=-1){
				sbs.skip(from);
				poi = from;
			}else{
				
			}
			int data = -1;//当前读取的字节内容
			while ((data = sbs.read()) != -1)//
			{
				//打印数组各字节内容
/*				System.out.println(poi+" "+(Integer.toHexString(data).length()>1?
						Integer.toHexString(data):"0"+Integer.toHexString(data)));*/
				if(data==searchByte){
					System.out.println("searchContentInFile2 从前向后查找：查找的内容"+searchByte+"在文件中的位置 --- " + poi);
					return poi;
				}
				poi++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis!=null){
					bis.close();
				}
				if(sbs!=null){
					sbs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(searchByte+" --- not found! ");
		return -1;
	}
	
	/**
	 * 查找指定的中文内容在文件中的位置(起始位置是from,如果from为-1,则从文件末尾向前查找;
	 * 如果文件中有多处指定的内容，返回第一个查找到的匹配的位置)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param file 要搜索的文件
	 * @param searchByte 要查找的内容
	 * @param from 从from位置起向前，如果from为-1表示从文件尾向前查找
	 * @return 此内容在文件中第一次出现的位置，找不到返回-1
	 */
	public long lastIndexOfInFile(File file,byte searchByte,long from){


		//String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + sFile;
		long n = -1;
		try {

			FileInputStream fis = new FileInputStream(file);
			FileChannel fc = fis.getChannel();

		    long sz = fc.size();
			if(from!=-1){
				sz = from;
			}
			//此处多次调用容易出现内存溢出
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);
			
			for (int i = bb.capacity()-1; i > -1; i--) {
				if(searchByte==bb.get(i)){
					n = i;
					break;
				}
			}
			
			if (n > -1)
				System.out.println("lastIndexOfInFile: 从后向前查找：查找的内容"+searchByte+"在文件中的位置 --- " + n);
			else
				System.out.println(searchByte+" --- not found! ");

			bb.clear();
			fc.close();
			fis.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return n;
	}
	
	/**
	 * 查找指定的中文内容在文件中的位置(起始位置是0,如果文件中有多处指定的内容，返回最后一个匹配的位置)
	 * @author zealjiang
	 * @date 2016年4月22日 下午1:52:05
	 * @param sFile 当前项目路径的文件的名字
	 * @param searchByte 要查找的字节
	 * @param from 从from位置起向前，如果from为-1表示从文件尾向前查找
	 * @return 此内容在文件中最后一次出现的位置，找不到返回-1
	 */
	public long lastIndexOfInFile2(File waveFile,byte searchByte,long from){

		RandomAccessFile raf = null;
		try {
			if (raf == null) {
				raf = new RandomAccessFile(waveFile, "r");
			}


			// 将指针移到文件尾
			long length = raf.length();
			long poi = length-1;//当前读取的字节在文件中的位置
			if(from!=-1){
				raf.seek(from-1);
				poi = from-1;
			}else{
				raf.seek(length-1);
			}
			
			
			int data = -1;//当前读取的字节内容	
			while (poi>-1)
			{
				data = raf.read();
				if(data==searchByte){
					System.out.println("lastIndexOfInFile2 :从后向前查找：查找的内容"+searchByte+"在文件中的位置 --- " + poi);
					return poi;
				}
				poi--;
				if(poi>-1){
					raf.seek(poi);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(raf!=null){
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(searchByte+" --- not found! ");
		return -1;
	}
	
	/**
	 * 计算两个时间差
	 * yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @author zealjiang
	 * @date 2016年4月22日 下午5:13:40
	 * @param startT 开始时间
	 * @param endT 结束时间
	 * @return 返回两个时间差(单位：秒)
	 */
	private long calDaltaT(String startT,String endT) {
		long delta = -1;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(startT);
			Date d2 = df.parse(endT);
			long diff = d2.getTime() - d1.getTime();
			delta =  diff/1000;

		} catch (Exception e) {
		}
		return delta;
	}
	
	/**
	 * 比较两个时间大小 
	 * yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @author zealjiang
	 * @date 2016年4月22日 下午5:13:40
	 * @param startT 开始时间
	 * @param endT 结束时间
	 * @return 如果startT小于endT返回1,如果startT大于endT返回-1,如果startT等于endT返回0
	 */
	private int compareTime(String startT,String endT) {
		int boo = -2;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(startT);
			Date d2 = df.parse(endT);
			long diff = d2.getTime() - d1.getTime();
			if(diff>0){
				boo = 1;
			}else if(diff<0){
				boo = -1;
			}else if(diff==0){
				boo = 0;
			}

		} catch (Exception e) {
			System.out.println("比较两个时间时，时间格式不对");
		}

		return boo;
	}
	
	/**
	 * 计算1秒长的时间内包含多少字节
	 * 思路：用文件中最后一个时间的位置-开始时间的的位置/(结束时间-开始时间)
	 * @author zealjiang
	 * @date 2016年4月22日 下午5:27:30
	 * @param startT yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @param sBytePoi
	 * @param endT yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @param eBytePoi
	 * @return long 1秒长的时间内包含多少字节
	 *
	 */
	private long calByteSizeSec(String startT,long sBytePoi,String endT,long eBytePoi){
		long deltaT = calDaltaT(startT,endT);
		long deltaSize = eBytePoi - sBytePoi;
		long sizeB = deltaSize/deltaT;
		return sizeB;
	}
	
	/**
	 * 计算1秒长的时间内包含多少字节(通过计算相邻两秒间字节的个数)
	 * @author zealjiang
	 * @date 2016年5月11日 上午11:09:30
	 * @param waveFile 获取内容的文件
	 * @return long 1秒长的时间内包含多少字节
	 *
	 */
	private long calByteSizeSecBySecSpace(File waveFile){
		//找到第一个0x10的位置
		long first = searchContentInFile(waveFile,(byte) 0x10,-1);
		if(first==-1){
			System.out.println("计算1秒长时间占多少字节出错  calPoiSpecifiedTime()");
			return -1;
		}
		//找到下一个0x10的位置
		long second = searchContentInFile(waveFile,(byte) 0x10,first+1);
		if(second==-1){
			System.out.println("计算1秒长时间占多少字节出错  calPoiSpecifiedTime()");
			return -1;
		}
		return second - first;
	}
	
	/**
	 * 获取从指定文件的指定位置开始，长度为length的字节数组
	 * @author zealjiang
	 * @date 2016年4月25日 上午9:50:04
	 * @param file 获取内容的文件
	 * @param start 开始的位置
	 * @param length 获取内容的长度
	 * @return 返回从指定文件的指定位置开始，长度为length的字节数组
	 * @throws Exception 
	 *
	 */
	private byte[] getByteArrayInFile(File file,long start,long length) throws Exception{
		//判断文件是否存在
		if(null==file||file.exists()==false){
			System.out.println("文件不存在");
			throw new FileNotFoundException();
		}
		//判断开始的位置和文件的内容长度是不否在文件中
		if(start<0||start>file.length()){
			System.out.println("搜索的起始位置小于0或大于文件长度");
			throw new Exception("搜索的起始位置小于0或大于文件长度");
		}
		if(start+length>file.length()){
			System.out.println("搜索的长度从起始位置开始大于文件长度");
			throw new Exception("搜索的长度从起始位置开始大于文件长度");
		}
		//获取字节数组
		
		
		
		return null;
	}
	
	/**
	 * 计算指定的时间所对应的文件的位置
	 * 步骤：
	 * 	1、计算出文件的起始时间及对应的起始位置、文件的终止时间及对应的终止位置
	 * 	2、如果要查找的时间在这个时间范围内，进行查找，
	 *  3、根据1秒的时长对应的文件片段的大小，计算出指定时间与起始时间的差值来计算出
	 * 	      查找出指定的时间对应的文件的位置
	 * @author zealjiang
	 * @date 2016年4月25日 上午9:20:20
	 * @file 搜索的文件
	 * @param time "yyyy-MM-dd HH:mm:ss"的时间格式
	 * @return 正确返回一个正确的位置(单位：字节)，反之返回-1
	 *
	 */
	public long calPoiSpecifiedTime(File file,String time){
		System.out.println("calPoiSpecifiedTime() 要查找的时间是："+time);
		long poi = -1;
		
		//第一步：检查
		//判断文件是否存在
		if(null==file||file.exists()==false){
			System.out.println("文件不存在");
			return -1;
		}
		
		//第二步
		//查找出文件的起始时间及对应的起始位置
		if(null==mFileStartTime||mFileStartTime.length()==0){
			//获取有效数据的起始时间
			String[] startTimePoi = getStartTime(file);
			mFileStartPos = Long.valueOf(startTimePoi[0]);
			mFileStartTime = startTimePoi[1];
		}

		
		//判断时间是否大于开始时间
		int compareStart = compareTime(mFileStartTime,time);
		if(compareStart==-1){
			return -1;
		}

		//查找出文件的终止时间及对应的终止位置
		if(null==mFileEndTime||mFileEndTime.length()==0){
			//获取有效数据的终止时间
			String[] endTimePoi = getEndTime(file);
			mFileEndTime = endTimePoi[1];
		}

		//判断时间是否小于终止时间
		int compareEnd = compareTime(time,mFileEndTime);
		if(compareEnd==-1){
			return -1;
		}
		
		//第三步
		//计算1秒长时间占多少字节
		if(mSecBytes==-1){
			//mSecBytes = calByteSizeSec(sStartTime,first,sEndTime,end);
			//System.out.println("方法一：1秒长时间占多少字节："+mSecBytes);
			mSecBytes = calByteSizeSecBySecSpace(file);
			//System.out.println("方法二： 1秒长时间占多少字节："+mSecBytes);
		}
		if(mSecBytes==-1){
			System.out.println("计算1秒长时间占多少字节出错  calPoiSpecifiedTime()");
			return -1;
		}
		
		//计算两个时间差
		long deltT = calDaltaT(mFileStartTime,time);
		
		//这个值有可能不准(比如：数据中存在除模块实时状态数据和心电数据还的其他数据时)
		long findPoi = mFileStartPos + mSecBytes*deltT;
		//System.out.println("容错校正前： 指定时间 文件的开始位置："+findPoi);
		//对得到的数据进行容错校正
		findPoi = faultTolerantCalibration(file,findPoi,time);
		System.out.println("容错校正后： 指定时间 文件的开始位置："+findPoi);
		return findPoi;
	}
	
	/**
	 * 容错校正 计算指定的时间所对应的文件的位置
	 * 思路：查找从findPoi向后第一个0x10的位置，取出这组数据的时间thisTime与findTime时间比较
	 * 根据比较结果：
	 * 情况一：thisTime 等于 findTime 返回findPoi
	 * 情况二：thisTime 大于 findTime 向前查找，位置：findPoi - (thisTime - findTime)*每秒多少字节 +10(误差字节数)，
	 * 从此位置向前查找到第一个第一个0x10的位置，递归调用这个方法，如果继续向前查找，就继续递归，如果为向后查找就退出，没找到,
	 * 防止无限递归的情况出现
	 * 情况二：thisTime 小于 findTime 向后查找，位置：findPoi + (thisTime - findTime)*每秒多少字节 -10(误差字节数)
	 * 从此位置向后查找到第一个第一个0x10的位置，递归调用这个方法，如果继续向后查找，就继续递归，如果为向前查找就退出，没找到,
	 * 防止无限递归的情况出现
	 * 
	 * 注意：记录文件中可能有状态时间包不为一个整包，或内部含有小于0x80的字节，会出现
	 * 解析出错的情况，要做进一步处理
	 * 思路：
	 * 1、从0x10头截取到小于0x80的字节前处，再进行一次时间的读取
	 * 2、如果再次读取的时间不为空，正常处理
	 * 3、如果再次读取的时间为空，向前查找，找到一个读取的时间不为空的位置，并记录下
	 * 此处的时间T1
	 * 4、要查找的时间findTime与T1比较
	 * 情况一：findTime-T1==0 ，返回T1的位置
	 * 情况二：findTime-T1<0 ，从T1向前查找(不包含T1)第(findTime-T1)个0X10,
	 * 即为findTime的位置
	 * 情况三：findTime-T1>0 ，从T1向后查找(不包含T1)第(T1-findTime)个0X10,
	 * 即为findTime的位置
	 * @author zealjiang
	 * @date 2016年4月25日 下午1:44:57
	 * @modify date 2016年5月11日 下午1:52:57
	 * @file 搜索的文件
	 * @param findPoi 找到的数据的起始位置
	 * @param time 要查找的时间 格式："yyyy-MM-dd HH:mm:ss"的时间格式
	 * @return 如果没找到指定的时间对应的位置返回-1
	 */
	private long faultTolerantCalibration(File file,long findPoi,String findTime){
		System.out.println("----faultTolerantCalibration-----begin");
		System.out.println("调用的次数 : "+(++mRecursionNum));	
		System.out.println("要查找的时间: "+findTime);	
		System.out.println("容错校正前的位置: "+findPoi);	
		//查找出文件的起始时间及对应的起始位置
		
		/**
		 * 由于文件中可存在除波形信息和模块状态信息外的其它信息，使得要查找的
		 * 位置比没有其它信息时后移，故从前向后查找
		 */
		findPoi = searchContentInFile(file,(byte) 0x10,findPoi);
		byte[] b_poi = readFromTo(file,findPoi,findPoi+C0x10Length);
		//取出时间
		String thisTime = byteArrayToTime(b_poi);
		if("".equals(thisTime)){

			System.out.println("获取文件中的时间出错 位置从: "+findPoi+"开始的17字节长度");	
			System.out.println("开始容错");
			/**
			 * 由此位置前查找，找到一个能取出时间的位置
			 */	
			while(true){
				//从此位置向前查找到第一个第一个0x10的位置
				findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi-1);
				b_poi = readFromTo(file,findPoi,findPoi+C0x10Length);
				if(b_poi==null){
					mRecursionNum = 0;
					mRecursion = 0;
					return -1;
				}
				//取出时间
				thisTime = byteArrayToTime(b_poi);
				if("".equals(thisTime)){					
				}else{
					break;
				}
			}
			
			//比较
			int compareEnd = compareTime(findTime,thisTime);
			if(compareEnd==0){
				//thisTime 等于 findTime 返回findPoi
				mRecursionNum = 0;
				mRecursion = 0;
				
				System.out.println("容错校正后的位置: "+findPoi);
				System.out.println("----faultTolerantCalibration-----end");
				return findPoi;
			}else if(compareEnd==1){
				//thisTime 大于 findTime
				//计算1秒长时间占多少字节
				if(mSecBytes==-1){
					mSecBytes = calByteSizeSecBySecSpace(file);
				}
				if(mSecBytes==-1){
					mRecursionNum = 0;
					mRecursion = 0;
					return -1;
				}		
				
				long deltaT = deltaTime(findTime,thisTime);
				findPoi = findPoi - deltaT*mSecBytes +10;
				//从此位置向前查找到第一个第一个0x10的位置
				findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi);

				//退出，找到估计的位置
				mRecursionNum = 0;
				mRecursion = 0;
				return findPoi;

			}else if(compareEnd==-1){
				//thisTime 小于 findTime
				//计算1秒长时间占多少字节
				if(mSecBytes==-1){
					mSecBytes = calByteSizeSecBySecSpace(file);
				}
				if(mSecBytes==-1){
					mRecursion = 0;
					mRecursionNum = 0;
					return -1;
				}			
				long deltaT = deltaTime(thisTime,findTime);
				findPoi = findPoi + deltaT*mSecBytes -10;
				//从此位置向后查找到第一个第一个0x10的位置
				findPoi = searchContentInFile(file,(byte) 0x10,findPoi);

				//退出,找到估计的位置
				mRecursion = 0;
				mRecursionNum = 0;
				return findPoi;			
			}
			
			mRecursionNum = 0;
			mRecursion = 0;
			return findPoi;
		}
		
		
		System.out.println("校正前向后的第一个时间: "+thisTime);	
		//比较
		int compareEnd = compareTime(findTime,thisTime);
		if(compareEnd==0){
			//thisTime 等于 findTime 返回findPoi
			mRecursionNum = 0;
			mRecursion = 0;
			
			System.out.println("容错校正后的位置: "+findPoi);
			System.out.println("----faultTolerantCalibration-----end");
			return findPoi;
		}else if(compareEnd==1){
			//thisTime 大于 findTime
			//计算1秒长时间占多少字节
			if(mSecBytes==-1){
				mSecBytes = calByteSizeSecBySecSpace(file);
			}
			if(mSecBytes==-1){
				mRecursionNum = 0;
				mRecursion = 0;
				return -1;
			}			
			System.out.println("mSecBytes 一秒有多少字节："+mSecBytes+"  findPoi: "+findPoi);
			long deltaT = deltaTime(findTime,thisTime);
			findPoi = findPoi - deltaT*mSecBytes +10;
			//System.out.println("findPoi "+findPoi);
			//从此位置向前查找到第一个第一个0x10的位置
			findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi);
			 
			System.out.println("mRecursion: "+mRecursion);
			if(mRecursion==1){
				//退出，没找到
				mRecursionNum = 0;
				mRecursion = 0;
				return -1;
			}
			mRecursion = -1;
		    //递归调用，继续向前查找
			faultTolerantCalibration(file,findPoi,findTime);
		}else if(compareEnd==-1){
			//thisTime 小于 findTime
			//计算1秒长时间占多少字节
			if(mSecBytes==-1){
				mSecBytes = calByteSizeSecBySecSpace(file);
			}
			if(mSecBytes==-1){
				mRecursion = 0;
				mRecursionNum = 0;
				return -1;
			}			
			long deltaT = deltaTime(thisTime,findTime);
			findPoi = findPoi + deltaT*mSecBytes -10;
			//从此位置向后查找到第一个第一个0x10的位置
			findPoi = searchContentInFile(file,(byte) 0x10,findPoi);
			if(mRecursion==-1){
				//退出，没找到
				mRecursion = 0;
				mRecursionNum = 0;
				return -1;
			}
			mRecursion = 1;
		    //递归调用，继续向后查找
			faultTolerantCalibration(file,findPoi,findTime);
		}
		
		mRecursion = 0;
		mRecursionNum = 0;
		System.out.println("--不正常结束------faultTolerantCalibration-------");
		return findPoi;
	}
	
	/**
	 * 指定一页的数据为多少字节，计算出从有效数据开始的最大页码
	 * 思路：
	 * 	 1、计算出起始位置
	 *   2、计算出终止位置
	 *   3、最大页码 =(终止位置-起始位置)/一页多少字节
	 * @author zealjiang
	 * @date 2016年4月26日 下午3:29:29
	 * @param pageBytes 一页多少字节的数据
	 * @return 返回此记录文件的最大页码
	 *
	 */
	public long getMaxPage(File file,int pageBytes){
		long first60 = calStart60Poi(file);
		long end60 = calEnd60Poi(file);
		long maxPageNum = (end60 - first60)%pageBytes>0 ? (end60 - first60)/pageBytes+1:(end60 - first60)/pageBytes;
		System.out.println("(end60 - first60)/pageBytes: "+(end60 - first60)/pageBytes);
		System.out.println("maxPageNum: "+maxPageNum);
		System.out.println("first60: "+first60);
		System.out.println("end60: "+end60);
		System.out.println("pageBytes: "+pageBytes);
		return maxPageNum;
	}
	
	/**
	 * 计算最大页码
	 * 思路：
	 * 	 1、计算出有效数据的起始时间
	 *   2、计算出有效数据的终止时间
	 *   3、最大页码 =(终止时间-起始时间)/一页多少秒数据
	 * @author zealjiang
	 * @date 2016年4月26日 下午3:29:29
	 * @param pageSec 一页多少秒的数据
	 * @return 返回此记录文件的最大页码
	 *
	 */
	public float getMaxPageBytime(File file,float pageSec){
		//查找出文件的起始时间及对应的起始位置
		long first = searchContentInFile(file,(byte) 0x10,-1);
		if(first==-1){
			return -1;
		}
		byte[] startTb = readFromTo(file,first,C0x10Length+first);
		//转成时间
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < startTb.length; i++) {
			list.add(startTb[i]);			
		}
		String sStartTime = hu.readTimeFromBytes(list);
		
		//查找出文件的终止时间及对应的终止位置
		long end = lastIndexOfInFile(file,(byte) 0x10,-1);
		if(end==-1){
			return -1;
		}
		byte[] endTb = readFromTo(file,end,C0x10Length+end);
		//转成时间
		list.clear();
		for (int i = 0; i < endTb.length; i++) {
			list.add(endTb[i]);			
		}
		String sEndTime = hu.readTimeFromBytes(list);
		
		//计算两个时间差
		long deltT = calDaltaT(sStartTime,sEndTime);
		
		float maxPage = deltT/pageSec;
		
		mMaxPage = maxPage;
		System.out.println("最大页码："+maxPage);
		return maxPage;
	}
	
	/**
	 * 获取文件中波形数据的开始时间及位置
	 * @author zealjiang
	 * @date 2016年4月27日 下午5:14:10
	 * @param file
	 * @return 文件中波形数据的起始位置string[0](单位字节)和开始时间数组string[1]
	 *
	 */
	private String[] getStartTime(File file){
		//查找出文件的起始时间及对应的起始位置
		long mFileStartPos = searchContentInFile(file,(byte) 0x10,-1);
		if(mFileStartPos==-1){
			return null;
		}
		byte[] startTb = readFromTo(file,mFileStartPos,C0x10Length+mFileStartPos);
		//转成时间
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < startTb.length; i++) {
			list.add(startTb[i]);			
		}
		String mFileStartTime = hu.readTimeFromBytes(list);
		
		System.out.println("文件的起始时间："+mFileStartTime);
		System.out.println("文件起始时间的位置："+mFileStartPos);
		return new String[]{mFileStartPos+"",mFileStartTime};
	} 
	
	/**
	 * 获取文件中波形数据的终止时间及位置
	 * @author zealjiang
	 * @date 2016年5月12日 下午3:47:10
	 * @param file
	 * @return 文件中波形数据的终止位置string[0](单位字节)和终止时间数组string[1]
	 *
	 */
	private String[] getEndTime(File file){
		//查找出文件的终止时间及对应的终止位置
		long end = lastIndexOfInFile(file,(byte) 0x10,-1);
		if(end==-1){
			return null;
		}
		byte[] endTb = readFromTo(file,end,C0x10Length+end);
		//转成时间
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < endTb.length; i++) {
			list.add(endTb[i]);			
		}
		String mFileEndTime = hu.readTimeFromBytes(list);
		System.out.println("文件的终止时间："+mFileEndTime);
		System.out.println("文件终止时间的位置："+end);
		return new String[]{end+"",mFileEndTime};
	}
	
	/**
	 * 计算出起始位置
	 * 方法：出现第一个模块实时状态数据后面紧临的第一个0x60波形数据的位置
	 * @author zealjiang
	 * @date 2016年4月26日 下午3:38:47
	 * @return
	 *
	 */
	private long calStart60Poi(File waveFile){
		long first10 = searchContentInFile(waveFile,Integer.valueOf("10", 16).byteValue(),0);
		long first60 = searchContentInFile(waveFile,Integer.valueOf("60", 16).byteValue(),first10);
		
		System.out.println("第一个0x10的位置："+first10);
		System.out.println("第一个0x60的位置："+first60);
		return first60;
	}
	
	/**
	 * 计算出终止位置
	 * 方法：从文件末尾开始向前查找，出现第一个0x60波形数据的位置
	 * @author zealjiang
	 * @date 2016年4月26日 下午3:38:47
	 * @return
	 *
	 */
	private long calEnd60Poi(File waveFile){
		//查找出文件的终止时间及对应的终止位置
		long end60 = lastIndexOfInFile(waveFile,(byte) 0x60,-1);
		System.out.println("最后一个0x60的位置："+end60);
		return end60;
	}
	
	/**
	 * 设置一页有几包数据
	 * 不推荐使用，因为一页可能实际有2.34包的数据，此方法会按2或3包的整数
	 * 包来处理，导致存在误差
	 * @author zealjiang
	 * @date 2016年4月26日 下午5:44:39
	 *
	 */
	@Deprecated
	public void setPagePacketNum(int pagePackets){
		mPagePackets = pagePackets;
	}
	
	/**
	 * 获取当前页的字节数据(注意包含模块实时状态信息数据即0x10类型的数据)
	 * 这个方法被废弃的主要原因是因为方法里用到了mPagePackets(一页几包数据)这个
	 * 整型的数，计算会带来误差
	 * replaced by <code>WaveFileRead.getPageBytesByTime(file,pageNum)</code>
	 * 思路： 1、找到文件第一个0x10的位置，根据一页的字节数据量，从找到的位置起，取一页的字节数据
	 *      2、返回包含0x10类型数据在内的一页数据
	 *      
	 * @author zealjiang  
	 * @date 2016年4月27日 上午9:39:56  
	 * @modify 2016年4月28日 上午9:53:56  
	 * @pageNum 当前页码(起始页码为1)
	 * @return 返回当前页码的字节数组
	 */
	@Deprecated
	public byte[] getPageBytesBySize(File file,int pageNum){
		//找到文件第一个0x10的位置
		//查找出文件的起始时间及对应的起始位置
		long first = searchContentInFile(file,(byte) 0x10,-1);
		//计算出一页有多少数据
		long pageBytes = mPagePackets*C0x60Length;
		long from = pageBytes*(pageNum-1)+first;
		long to = from+pageBytes;
		return readFromTo(file,from,to);
	}
	
	/**
	 * 设置一页有几秒的数据
	 * @author zealjiang
	 * @date 2016年4月26日 下午5:44:39
	 *
	 */
	public void setPageSecData(float pageSecData){
		mPageSecData = pageSecData;
	}
	
	/**
	 * 获取当前页的字节数据(注意包含模块实时状态信息数据即0x10类型的数据)
	 * 注意：调用这个方法之前要先调用setPageSecData()为mPageSecData赋值
	 * 思路： 1、找到文件第一个0x10的位置，根据一页的字节数据量，从找到的位置起，取一页的字节数据
	 *      2、返回包含0x10类型数据在内的一页数据
	 * @author zealjiang
	 * @date 2016年4月27日 上午9:39:56
	 * @pageNum 当前页码(起始页码为1)
	 * @return 返回包含当前页码的字节数组的PageBean对象
	 */
	public PageBean getPageBytesByTime(File file,int pageNum){
		System.out.println("---查找的页码为： "+pageNum);
		PageBean pb = new PageBean();
		if(null==mFileStartTime||mFileStartTime.length()==0){
			//获取有效数据的起始时间
			String[] poiAndTime = getStartTime(file);
			mFileStartTime = poiAndTime[1];
		}
		//计算指定pageNum页码对应的开始时间
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(mFileStartTime, (int)((pageNum-1)*mPageSecData));
		System.out.println("指定页面数据的开始时间："+sdateFrom);
		pb.setStartTime(sdateFrom);	
		//计算指定pageNum页码对应的开始位置
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
        //如果此位置前还有一包数据，则定位到前一包0x60数据的位置
        long from = lastIndexOfInFile(file, (byte) 0x60, pFrom);
        if(from==-1){
            from = pFrom;
        }

		//计算指定pageNum页码对应的结束时间
		int sec = mPageSecData%(int)mPageSecData>0?(int)mPageSecData+1:(int)mPageSecData;
		String sdateTo = ta.addStringDateSec(sdateFrom,sec);
		//如果sdateTo大于文件的结束时间，将文件的结束时间赋值给sdateTo
		if(mMaxPage<0){
			//计算最大页码
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(null==mFileEndTime||mFileEndTime.length()==0){
			//获取有效数据的起始时间
			String[] poiAndTime = getEndTime(file);
			mFileEndTime = poiAndTime[1];
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
		}
		//System.out.println("获取数据的终止时间："+sdateTo);
		pb.setEndTime(sdateTo);
		//计算指定pageNum页码对应的结束位置(对应的位置是0x10所在的位置)
		long pTo = calPoiSpecifiedTime(file,sdateTo);
		//System.out.println("本终止时间的位置："+pTo);
		byte[] pageBytes = readFromTo(file,from,pTo);
		pb.setContent(pageBytes);
				
		if(pageBytes==null){
			System.out.println("---end------getPageBytesByTime------------");
			return pb;
		}
		//打印出本页的内容
		System.out.println("---------当前页码："+pageNum);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 76; i++) {//加密后的数据一包有76字节的数据
			if(i<10){
				sb.append("0"+i+" ");
			}else{
				sb.append(i+" ");
			}
						
		}

		for (int i = 0; i < pageBytes.length; i++) {
			String n = Integer.toHexString(pageBytes[i]&0XFF);		
			if("10".equals(n)||"60".equals(n)){
				sb.append("\n");
			}
			sb.append(n+" ");	
		}
    	
		System.out.println(sb.toString());	
		System.out.println("---end------getPageBytesByTime------------");
		return pb;
	}
	
	
    /**
     * 此记录文件的最大页码
     * 计算最大页码有两种方法：
     * 方法一：
     *        1、读取记录文件中的时间标记，取出开始时间和结束时间
     *        2、由显示区域的宽度/纸速，算出一页可显示多少秒的数据
     *        3、(结束时间-开始时间)/一页多少秒，算出最大页码
     * 方法二：
     *        1、读取有效数据开始的位置startP和有效数据结束的位置endP
     *        2、由显示区域的宽度/纸速，算出一页可显示sec秒的数据
     *        3、根据Hotter1秒发送16包数据，及一包数据的长度，计算出1秒有多少字节的数据
     *        4、(endP-startP)/((16*一包数据的长度)*sec),算出最大页码
     * 以上两种方法的比较：
     *      方法一、按真实情况来举例计算：
     *              显示区域的宽度62.33mm/纸速25mm=2.4932秒
     *              开始时间10:31:09,结束时间10:36:30,时间差是321秒
     *              321/2.4932=128.75页，最后算出最大页码是128.75页
     *      方法二、按真实情况来举例计算：
     *              有效数据开始的位置608有效数据结束的位置397482
     *              显示区域的宽度62.33mm/纸速25mm=2.4932秒
     *              1秒16包数据*一包数据的长度76=1216字节数据
     *              一页有2.4932*1216=3031.37字节的数据
     *              (397482-608)/3031.37 = 130.92页
     *      通过比较可以看出方法一用时间来除时间方法更精确
     *      方法二中出现的1秒16包数据这个隐藏的数据不是很好，如果1秒不是
     *      16包数据而是30包数据，那么方法二算出的最大页就出错了，所以通过
     *      比较方法一更好。
     * */
	
	/**
	 * 获取当前页的字节数据(注意包含模块实时状态信息数据即0x10类型的数据),单位是整数秒,如一页有2.49秒数据，
	 * 实际获取的是3秒的数据
	 * 注意：调用这个方法之前要先调用setPageSecData()为mPageSecData赋值
	 * 思路： 1、找到文件第一个0x10的位置，根据一页的字节数据量，从找到的位置起，取一页的字节数据
	 *      2、返回包含0x10类型数据在内的一页数据
	 * @author zealjiang
	 * @date 2016年4月27日 上午9:39:56
	 * @pageNum 当前页码(起始页码为1)
	 * @return 返回当前页码的字节数组
	 */
	public byte[] getPageBytesByTimeIntSec(File file,int pageNum){
		
		//获取有效数据的起始时间
		String[] poiAndTime = getStartTime(file);
		System.out.println("start time: "+poiAndTime[1]);
		//计算指定pageNum页码对应的开始时间
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(poiAndTime[1], (int)((pageNum-1)*mPageSecData));
		System.out.println(pageNum+"页 time: "+sdateFrom);
		//计算指定pageNum页码对应的开始位置
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
		//计算指定pageNum页码对应的结束时间
		int sec = mPageSecData%(int)mPageSecData>0?(int)mPageSecData+1:(int)mPageSecData;	
		String sdateTo = ta.addStringDateSec(sdateFrom,sec);
		//如果sdateTo大于文件的结束时间，将文件的结束时间赋值给sdateTo
		if(mMaxPage<0){
			//计算最大页码
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
		}
		//计算指定pageNum页码对应的结束位置(对应的位置是0x10所在的位置)
		long pTo = calPoiSpecifiedTime(file,sdateTo);
		byte[] pageBytes = readFromTo(file,pFrom,pTo);
		//打印数组各字节内容
/*		for (int i = 0; i < pageBytes.length; i++) {
			System.out.println(i+": "+Integer.toHexString((pageBytes[i]&0xFF)));
		}*/
		return pageBytes;
	}
	
	/**
	 * 
	 * 获取当前页的字节数据(注意包含模块实时状态信息数据即0x10类型的数据)单位是浮点float秒,如一页有2.49秒数据，
	 * 实际获取的是2.49秒的数据
	 * 注意：调用这个方法之前要先调用setPageSecData()为mPageSecData赋值
	 * 思路： 1、找到文件第一个0x10的位置，根据一页的字节数据量，从找到的位置起，取一页的字节数据
	 *      2、返回包含0x10类型数据在内的一页数据
	 * @author zealjiang
	 * @date 2016年5月6日 上午10:09:56
	 * @pageNum 当前页码(起始页码为1)
	 * @return 返回当前页码的字节数组
	 */
	public byte[] getPageBytesByTimeFloatSec(File file,int pageNum){
		byte[] pageBytes = null;
		if(mSecPacket==-1){
			mSecPacket = calSecPacket(file);
		}

		//获取有效数据的起始时间
		String[] poiAndTime = getStartTime(file);
		System.out.println("波形文件的第1个时间: "+poiAndTime[1]);
		//计算指定pageNum页码对应的开始时间
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(poiAndTime[1], (int)((pageNum-1)*mPageSecData));
		System.out.println("第"+pageNum+"页 开始时间: "+sdateFrom);
		//计算指定pageNum页码对应的开始位置
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
		
		//计算pageNum小数部分有多少包数据
		float decimal = getDecimal((pageNum-1)*mPageSecData);
		//假如计算出来是7.84，取整数部分7
		int decimalPacket = (int)(mSecPacket*decimal);
		//计算出一页新的开始位置
		pFrom = calFromAdd60Nums(file,pFrom,decimalPacket);
		
		//计算指定pageNum页码对应的结束时间	
		String sdateTo = ta.addStringDateSec(poiAndTime[1], (int)(pageNum*mPageSecData));
		
		System.out.println("第"+pageNum+"页结束时间: "+sdateTo);
		
		long pTo = 0;
		//如果sdateTo大于文件的结束时间，将文件的结束位置赋给终止位置
		if(mMaxPage<0){
			//计算最大页码
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
			pTo = file.length();
			
		}else{
			//计算指定pageNum页码对应的结束位置(对应的位置是0x10所在的位置)
			pTo = calPoiSpecifiedTime(file,sdateTo);
			System.out.println(" pTo: "+pTo);
			//计算pageNum结束页小数部分有多少包数据
			float decimalEnd = getDecimal(pageNum*mPageSecData);
			//假如计算出来是7.84，取整数部分7
			int decimalPacketEnd = (int)(mSecPacket*decimalEnd);
			//计算出一页新的开始位置
			pTo = calFromAdd60Nums(file,pTo,decimalPacketEnd);
			
		}
		System.out.println("pFrom"+pFrom+" pTo: "+pTo);
		pageBytes = readFromTo(file,pFrom,pTo);
		//打印数组各字节内容
/*		for (int i = 0; i < pageBytes.length; i++) {
			System.out.println(i+": "+Integer.toHexString((pageBytes[i]&0xFF)));
		}*/

		return pageBytes;
	}
	
	/**
	 * 计算1秒有多少包数据
	 * 方法：计算两个0X10类型的数据之间有多少个0X60的数据
	 * 注意：由于MappedByteBuffer.get(int index),index为整型数值,
	 * java Integer.MAX_VALUE为2147483647；这里查询的单位是字节，
	 * 2147483647字节转换成GB为2，也就是单个文件最大为2GByte,超过这个值可能
	 * 会出错
	 *@author zealjiang
	 *@time 2016/5/5 18:20
	 */
	private int calSecPacket(File waveFile){
		//从文件头开始找到第一个0X10的位置
		long first10 = searchContentInFile(waveFile,Integer.valueOf("10", 16).byteValue(),0);
		//从此位置起向后查找，至到再次出现0x10时停止，记录期间出现的0X60的个数
		int x60Num = 0;
		boolean boo = true;
		while(boo){
			try {

				FileInputStream fis = new FileInputStream(waveFile);
				FileChannel fc = fis.getChannel();

				long end = fc.size();
				int start = (int)first10+1;
				if(start==end){//如果开始位置为文件的最后位置，说明无法找到
					return 0;
				}
		
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, end);
						
				/**
				 * 注意：由于MappedByteBuffer.get(int index),index为整型数值,
				 * java Integer.MAX_VALUE为2147483647；这里查询的单位是字节，
				 * 2147483647字节转换成GB为2，也就是单个文件最大为2GByte,超过这个值可能
				 * 会出错
				 */
				for (int i = start; i < bb.capacity(); i++) {
					if((byte) 0x60==bb.get(i)){
						x60Num++;
						continue;
					}	
					if((byte) 0x10==bb.get(i)){
						//跳出while循环
						boo = false;
						break;
					}	
				}

				System.out.println("1秒有"+x60Num+"包波形数据");

				fc.close();
				//跳出while循环
				boo = false;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		mSecPacket = x60Num;
		return x60Num;
	}
	
	/**
	 * 计算一个浮点型数的小数部分
	 * @author zealjiang
	 * @date 2016年5月6日 上午10:41:38
	 * @param floatNum
	 * @return 小数部分
	 *
	 */
	private float getDecimal(float floatNum){
	
		BigDecimal bFloat = new BigDecimal(Float.toString(floatNum));
	    BigDecimal bInt = new BigDecimal(Float.toString((int)floatNum));
	    float decimal = bFloat.subtract(bInt).floatValue(); 
	    return decimal;

	}
	
	/**
	 * 计算在波形文件waveFile中从指定的位置from起，向后经过num包0X60数据后的新位置
	 * @author zealjiang
	 * @date 2016年5月6日 上午11:19:29
	 * @param waveFile
	 * @param from
	 * @param num
	 * @return 返回新的起始位置,末找到返回-1
	 *
	 */
	private long calFromAdd60Nums(File waveFile,long from,int num){
		long n = -1;
		//从此位置起向后查找，至到再次出现0x10时停止，记录期间出现的0X60的个数
		int x60Num = 0;
		boolean boo = true;
		while(boo){
			try {

				FileInputStream fis = new FileInputStream(waveFile);
				FileChannel fc = fis.getChannel();

				long end = fc.size();
				int start = (int)from;
				if(start==end){//如果开始位置为文件的最后位置，说明无法找到
					return n;
				}
		
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, end);
						
				/**
				 * 注意：由于MappedByteBuffer.get(int index),index为整型数值,
				 * java Integer.MAX_VALUE为2147483647；这里查询的单位是字节，
				 * 2147483647字节转换成GB为2，也就是单个文件最大为2GByte,超过这个值可能
				 * 会出错
				 */
				for (int i = start; i < bb.capacity(); i++) {
					if((byte) 0x60==bb.get(i)){
						x60Num++;
						if((x60Num-1)==num){//减1时因为要经过num
							n = i;
							//跳出while循环
							boo = false;
							break;
						}
					}	
				}
				
				fc.close();
				
				//跳出while循环
				boo = false;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		System.out.println("经过"+num+"包0X60后新的起点位置是"+n);
		return n;
	}
	
	
	/**
	 * 一页数据对象
	 * Created by zealjiang on 2016/5/4 14:43.
	 */
	class PageBean {

	    private byte[] content;
	    private String startTime;
	    private String endTime;

	    public byte[] getContent() {
	        return content;
	    }

	    public void setContent(byte[] content) {
	        this.content = content;
	    }

	    public String getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(String startTime) {
	        this.startTime = startTime;
	    }

	    public String getEndTime() {
	        return endTime;
	    }

	    public void setEndTime(String endTime) {
	        this.endTime = endTime;
	    }
	}
	
    public static String bytesToHexString(ArrayList<Byte> src) {

        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.size() <= 0) {
            return null;
        }
        for (int i = 0; i < src.size(); i++) {
            int v = src.get(i) & 0xFF;
            String hv = Integer.toHexString(v);

            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(" ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    
    /**
     * 将0x10的模块实时字节数组转成时间字符串
     * @author zealjiang
     * @date 2016年5月11日 下午2:12:38
     * @param bytes
     * @return 传入的字节数据合法返回时间字符串，否则返回""
     *
     */
    private String byteArrayToTime(byte[] bytes){
    	if(bytes==null){
    		return "";
    	}
    	if(mHotterUtil==null){
    		mHotterUtil = new HotterUtil();
    	}
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < bytes.length; i++) {
			list.add(bytes[i]);			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 17; i++) {//加密后的数据一包有76字节的数据
			if(i<10){
				sb.append("0"+i+" ");
			}else{
				sb.append(i+" ");
			}
						
		}
		for (int i = 0; i < bytes.length; i++) {
			String n = Integer.toHexString(bytes[i]&0XFF);		
			if("10".equals(n)||"60".equals(n)){
				sb.append("\n");
			}
			sb.append(n+" ");
		}
		System.out.println(sb.toString());
		
		String sStartTime = mHotterUtil.readTimeFromBytes(list);
		return sStartTime;
    }
    
	/**
	 * 计算两个时间差为多少秒
	 * yyyy-MM-dd HH:mm:ss 这个格式的字符串时间
	 * @author zealjiang
	 * @date 2016年5月11日 下午2:38:40
	 * @param startT 开始时间
	 * @param endT 结束时间
	 * @return 两个时间相差多少秒，时间格式不正确返回-1
	 */
	private long deltaTime(String startT,String endT) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date ds = df.parse(startT);
			Date de = df.parse(endT);
			long diff = de.getTime() - ds.getTime();
			System.out.println("diff_sec : " + diff/1000);
			return diff/1000;
		} catch (Exception e) {
			return -1;
		}
	}
}
