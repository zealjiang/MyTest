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
 * @time 2016��4��22������1:14:55
 */
public class WaveFileRead {
	
	/**ģ��ʵʱ״̬����0x10�ĳ���*/
	private final int C0x10Length = 17;
	/**ģ��ʵʱ״̬����ID 0x10*/
	private final byte C0x10 = 0x10; 
	/**������Ϣ״̬����0x60�ĳ���*/
	private final int C0x60Length = 76;
	/**һҳ���ٰ�0x60������*/
	private int mPagePackets = 0;
	/**һҳ�����������*/
	private float mPageSecData = 0;
	/**�ļ������ҳ��*/
	private float mMaxPage = -1;
	/**1���ж��ٰ���������*/
	private int mSecPacket = -1;
	/**1���ж����ֽ�����*/
	private long mSecBytes = -1;
	/**�ļ��Ŀ�ʼʱ��*/
	private String mFileStartTime = "";
	/**�ļ�����ֹʱ��*/
	private String mFileEndTime = "";
	/**�ļ��ĵ�һ��ʱ��Ŀ�ʼλ��*/
	private long mFileStartPos = -1;
	/**�ļ�����ֹλ��*/
	private long mFileEndPos = -1;
	/**�ݹ�У׼�����˳��ж�����*/
	private int mRecursion = 0;
	/**�ݹ���õĴ���*/
	private int mRecursionNum = 0;
	
	private HotterUtil mHotterUtil = null;
	/**�ļ���������*/
	private MappedByteBuffer mMappedByteBuffer;
	/**�ļ���������,�˳�ʱ��¼close*/
	private FileChannel mFileChannel;

	public static void main(String[] args) {
		WaveFileRead wfr = new WaveFileRead();
		//File read_file = new File("d:/1234.txt");		
		//"_20160418100305.YE"
		//File read_file = new File("_20160510135912.YE");
		//File read_file = new File("��־��13048118612569654_20160422103234.YE");
		File read_file = new File("��־��12555555_20160516153213.YE");
		
		
/*		System.out.println(Integer.valueOf("1", 16));
		long first = wfr.searchContentInFile(read_file,Integer.valueOf("10", 16).byteValue(),609);
		wfr.searchContentInFile2(read_file,Integer.valueOf("10", 16).byteValue(),609);
		
		wfr.lastIndexOfInFile(read_file,Integer.valueOf("10", 16).byteValue(),12406);
		wfr.lastIndexOfInFile2(read_file,Integer.valueOf("10", 16).byteValue(),12406);
		
		byte[] bytes = wfr.readFromTo(read_file,first,17+first);*/
		//byte[] bytes = wfr.readFromTo(read_file,0,10);
		//��ӡ������ֽ�����
/*		for (int i = 0; i < bytes.length; i++) {
			System.out.println(i+" "+Integer.toHexString((bytes[i]&0xFF)));
		}*/
		
		/*System.out.println("--------------");
		long poi = wfr.calPoiSpecifiedTime(read_file,"2016-04-22 10:31:09");
		byte[] b_poi = wfr.readFromTo(read_file,poi,poi+17);
		System.out.println("b_poi length: "+b_poi.length);
		//��ӡ������ֽ�����
		for (int i = 0; i < b_poi.length; i++) {
			System.out.println(i+": "+Integer.toHexString((b_poi[i]&0xFF)));
		}
		//ת��ʱ��
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < b_poi.length; i++) {
			list.add(b_poi[i]);			
		}
		String sStartTime = hu.readTimeFromBytes(list);
		System.out.println("sStartTime: "+sStartTime);
		
		//����1�����ж��ٰ���������
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
		System.out.println("//--------����getPageBytesByTime--------------");
		//�Ӵ�λ����ǰ���ҵ���һ����һ��0x10��λ��
/*		long findPoi = wfr.lastIndexOfInFile(read_file,(byte) 0x10,46180);
		byte[] b_poi = wfr.readFromTo(read_file,findPoi,findPoi+wfr.C0x10Length);
		if(b_poi==null){
			return;
		}
		//ȡ��ʱ��
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
	 * ��ȡ�ļ��д�from��toָ�����ֽ�(�ֽڵ�ȡֵ��Χ�Ǵ�0��255)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:16:05
	 * @param file Ҫ��ȡ���ļ�
	 * @param from ��ȡ�ļ��Ŀ�ʼλ��(������ʼλ�õ��ֽڣ�ע�⣺�ļ�����ʼλ����0)
	 * @param to ��ȡ�ļ��Ľ���λ��(����������λ�õ��ֽ�)
	 * @return ���ش�from��toλ�õ��ֽ�(����from��toλ�õ��ֽ�)
	 *
	 */
	 private byte[] readFromTo(File file,long from,long to){
			
		 System.out.println("readFromTo from��"+from+" to: "+to);
		//�ж��ļ��Ƿ����
		if(null==file||file.exists()==false){
			System.out.println("�ļ�������");
			//throw new FileNotFoundException();
			return null;
		}
		//�жϿ�ʼ��λ�ú��ļ������ݳ����ǲ������ļ���
		if(from<0||from>file.length()){
			System.out.println("��������ʼλ��С��0������ļ�����");
			//throw new Exception("��������ʼλ��С��0������ļ�����");
			return null;
		}
		if(to>file.length()){
			System.out.println("�����ĳ��ȴ���ʼλ�ÿ�ʼ�����ļ�����");
			//throw new Exception("�����ĳ��ȴ���ʼλ�ÿ�ʼ�����ļ�����");
			return null;
		}
		//�жϿ�ʼ��λ�ú��ļ������ݳ����ǲ������ļ���
		if(from>to){
			System.out.println("��������ʼλ�ô��������Ľ���λ��");
			//throw new Exception("��������ʼλ��С��0������ļ�����");
			return null;
		}
		System.out.println("�ļ��Ĵ�С��"+file.length());
		byte[] buffer = null;
		try {
			
			FileInputStream in = new FileInputStream(file);
			in.skip(from);// �ļ�ָ��ǰһ�ֽ�
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
	 * ����ָ���������������ļ��е�λ��(��ʼλ����0,����ļ����жദָ�������ݣ����ص�һ��ƥ���λ��)
	 * ע�⣺�����ļ���С���Ϊ2G
	 * ����MappedByteBuffer.get(int index),indexΪ������ֵ,
	 * java Integer.MAX_VALUEΪ2147483647�������ѯ�ĵ�λ���ֽڣ�
	 * 2147483647�ֽ�ת����GBΪ2��Ҳ���ǵ����ļ����Ϊ2GByte,�������ֵ����
	 * �����
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param sFile ��ǰ��Ŀ·�����ļ�������
	 * @param searchByte Ҫ���ҵ�����
	 * @param from ��fromλ����������fromΪ-1��ʾ���ļ�ͷ������
	 * @return ���������ļ��е�һ�γ��ֵ�λ�ã��Ҳ�������-1
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
				System.out.println("searchContentInFile ��ǰ�����ң����ҵ�����"+searchByte+"���ļ��е�λ�� --- " + n);
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
				System.out.println("���µ�����startλ��Ϊ��"+start);
			}

			for (int i = start; i < mMappedByteBuffer.capacity(); i++) {
				if(searchByte==mMappedByteBuffer.get(i)){
					n = i;
					break;
				}
			}

			if (n > -1) {
				//System.out.println("searchContentInFile ��ǰ�����ң����ҵ�����" + searchByte + "���ļ��е�λ�� --- " + n);
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
	 * ����ָ���������������ļ��е�λ��(��ʼλ����0,����ļ����жദָ�������ݣ����ص�һ��ƥ���λ��)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param sFile ��ǰ��Ŀ·�����ļ�������
	 * @param searchByte Ҫ���ҵ��ֽ�
	 * @param from ��fromλ����������fromΪ-1��ʾ���ļ�ͷ������
	 * @return ���������ļ��е�һ�γ��ֵ�λ�ã��Ҳ�������-1
	 */
	public long searchContentInFile2(File waveFile,byte searchByte,long from){

		FileInputStream bis = null;
		BufferedInputStream sbs = null;
		try {
	    	bis = new FileInputStream(waveFile);
	    	sbs = new BufferedInputStream(bis);
			long poi = 0;//��ǰ��ȡ���ֽ����ļ��е�λ��
			if(from!=-1){
				sbs.skip(from);
				poi = from;
			}else{
				
			}
			int data = -1;//��ǰ��ȡ���ֽ�����
			while ((data = sbs.read()) != -1)//
			{
				//��ӡ������ֽ�����
/*				System.out.println(poi+" "+(Integer.toHexString(data).length()>1?
						Integer.toHexString(data):"0"+Integer.toHexString(data)));*/
				if(data==searchByte){
					System.out.println("searchContentInFile2 ��ǰ�����ң����ҵ�����"+searchByte+"���ļ��е�λ�� --- " + poi);
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
	 * ����ָ���������������ļ��е�λ��(��ʼλ����from,���fromΪ-1,����ļ�ĩβ��ǰ����;
	 * ����ļ����жദָ�������ݣ����ص�һ�����ҵ���ƥ���λ��)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param file Ҫ�������ļ�
	 * @param searchByte Ҫ���ҵ�����
	 * @param from ��fromλ������ǰ�����fromΪ-1��ʾ���ļ�β��ǰ����
	 * @return ���������ļ��е�һ�γ��ֵ�λ�ã��Ҳ�������-1
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
			//�˴���ε������׳����ڴ����
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);
			
			for (int i = bb.capacity()-1; i > -1; i--) {
				if(searchByte==bb.get(i)){
					n = i;
					break;
				}
			}
			
			if (n > -1)
				System.out.println("lastIndexOfInFile: �Ӻ���ǰ���ң����ҵ�����"+searchByte+"���ļ��е�λ�� --- " + n);
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
	 * ����ָ���������������ļ��е�λ��(��ʼλ����0,����ļ����жദָ�������ݣ��������һ��ƥ���λ��)
	 * @author zealjiang
	 * @date 2016��4��22�� ����1:52:05
	 * @param sFile ��ǰ��Ŀ·�����ļ�������
	 * @param searchByte Ҫ���ҵ��ֽ�
	 * @param from ��fromλ������ǰ�����fromΪ-1��ʾ���ļ�β��ǰ����
	 * @return ���������ļ������һ�γ��ֵ�λ�ã��Ҳ�������-1
	 */
	public long lastIndexOfInFile2(File waveFile,byte searchByte,long from){

		RandomAccessFile raf = null;
		try {
			if (raf == null) {
				raf = new RandomAccessFile(waveFile, "r");
			}


			// ��ָ���Ƶ��ļ�β
			long length = raf.length();
			long poi = length-1;//��ǰ��ȡ���ֽ����ļ��е�λ��
			if(from!=-1){
				raf.seek(from-1);
				poi = from-1;
			}else{
				raf.seek(length-1);
			}
			
			
			int data = -1;//��ǰ��ȡ���ֽ�����	
			while (poi>-1)
			{
				data = raf.read();
				if(data==searchByte){
					System.out.println("lastIndexOfInFile2 :�Ӻ���ǰ���ң����ҵ�����"+searchByte+"���ļ��е�λ�� --- " + poi);
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
	 * ��������ʱ���
	 * yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @author zealjiang
	 * @date 2016��4��22�� ����5:13:40
	 * @param startT ��ʼʱ��
	 * @param endT ����ʱ��
	 * @return ��������ʱ���(��λ����)
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
	 * �Ƚ�����ʱ���С 
	 * yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @author zealjiang
	 * @date 2016��4��22�� ����5:13:40
	 * @param startT ��ʼʱ��
	 * @param endT ����ʱ��
	 * @return ���startTС��endT����1,���startT����endT����-1,���startT����endT����0
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
			System.out.println("�Ƚ�����ʱ��ʱ��ʱ���ʽ����");
		}

		return boo;
	}
	
	/**
	 * ����1�볤��ʱ���ڰ��������ֽ�
	 * ˼·�����ļ������һ��ʱ���λ��-��ʼʱ��ĵ�λ��/(����ʱ��-��ʼʱ��)
	 * @author zealjiang
	 * @date 2016��4��22�� ����5:27:30
	 * @param startT yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @param sBytePoi
	 * @param endT yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @param eBytePoi
	 * @return long 1�볤��ʱ���ڰ��������ֽ�
	 *
	 */
	private long calByteSizeSec(String startT,long sBytePoi,String endT,long eBytePoi){
		long deltaT = calDaltaT(startT,endT);
		long deltaSize = eBytePoi - sBytePoi;
		long sizeB = deltaSize/deltaT;
		return sizeB;
	}
	
	/**
	 * ����1�볤��ʱ���ڰ��������ֽ�(ͨ����������������ֽڵĸ���)
	 * @author zealjiang
	 * @date 2016��5��11�� ����11:09:30
	 * @param waveFile ��ȡ���ݵ��ļ�
	 * @return long 1�볤��ʱ���ڰ��������ֽ�
	 *
	 */
	private long calByteSizeSecBySecSpace(File waveFile){
		//�ҵ���һ��0x10��λ��
		long first = searchContentInFile(waveFile,(byte) 0x10,-1);
		if(first==-1){
			System.out.println("����1�볤ʱ��ռ�����ֽڳ���  calPoiSpecifiedTime()");
			return -1;
		}
		//�ҵ���һ��0x10��λ��
		long second = searchContentInFile(waveFile,(byte) 0x10,first+1);
		if(second==-1){
			System.out.println("����1�볤ʱ��ռ�����ֽڳ���  calPoiSpecifiedTime()");
			return -1;
		}
		return second - first;
	}
	
	/**
	 * ��ȡ��ָ���ļ���ָ��λ�ÿ�ʼ������Ϊlength���ֽ�����
	 * @author zealjiang
	 * @date 2016��4��25�� ����9:50:04
	 * @param file ��ȡ���ݵ��ļ�
	 * @param start ��ʼ��λ��
	 * @param length ��ȡ���ݵĳ���
	 * @return ���ش�ָ���ļ���ָ��λ�ÿ�ʼ������Ϊlength���ֽ�����
	 * @throws Exception 
	 *
	 */
	private byte[] getByteArrayInFile(File file,long start,long length) throws Exception{
		//�ж��ļ��Ƿ����
		if(null==file||file.exists()==false){
			System.out.println("�ļ�������");
			throw new FileNotFoundException();
		}
		//�жϿ�ʼ��λ�ú��ļ������ݳ����ǲ������ļ���
		if(start<0||start>file.length()){
			System.out.println("��������ʼλ��С��0������ļ�����");
			throw new Exception("��������ʼλ��С��0������ļ�����");
		}
		if(start+length>file.length()){
			System.out.println("�����ĳ��ȴ���ʼλ�ÿ�ʼ�����ļ�����");
			throw new Exception("�����ĳ��ȴ���ʼλ�ÿ�ʼ�����ļ�����");
		}
		//��ȡ�ֽ�����
		
		
		
		return null;
	}
	
	/**
	 * ����ָ����ʱ������Ӧ���ļ���λ��
	 * ���裺
	 * 	1��������ļ�����ʼʱ�估��Ӧ����ʼλ�á��ļ�����ֹʱ�估��Ӧ����ֹλ��
	 * 	2�����Ҫ���ҵ�ʱ�������ʱ�䷶Χ�ڣ����в��ң�
	 *  3������1���ʱ����Ӧ���ļ�Ƭ�εĴ�С�������ָ��ʱ������ʼʱ��Ĳ�ֵ�������
	 * 	      ���ҳ�ָ����ʱ���Ӧ���ļ���λ��
	 * @author zealjiang
	 * @date 2016��4��25�� ����9:20:20
	 * @file �������ļ�
	 * @param time "yyyy-MM-dd HH:mm:ss"��ʱ���ʽ
	 * @return ��ȷ����һ����ȷ��λ��(��λ���ֽ�)����֮����-1
	 *
	 */
	public long calPoiSpecifiedTime(File file,String time){
		System.out.println("calPoiSpecifiedTime() Ҫ���ҵ�ʱ���ǣ�"+time);
		long poi = -1;
		
		//��һ�������
		//�ж��ļ��Ƿ����
		if(null==file||file.exists()==false){
			System.out.println("�ļ�������");
			return -1;
		}
		
		//�ڶ���
		//���ҳ��ļ�����ʼʱ�估��Ӧ����ʼλ��
		if(null==mFileStartTime||mFileStartTime.length()==0){
			//��ȡ��Ч���ݵ���ʼʱ��
			String[] startTimePoi = getStartTime(file);
			mFileStartPos = Long.valueOf(startTimePoi[0]);
			mFileStartTime = startTimePoi[1];
		}

		
		//�ж�ʱ���Ƿ���ڿ�ʼʱ��
		int compareStart = compareTime(mFileStartTime,time);
		if(compareStart==-1){
			return -1;
		}

		//���ҳ��ļ�����ֹʱ�估��Ӧ����ֹλ��
		if(null==mFileEndTime||mFileEndTime.length()==0){
			//��ȡ��Ч���ݵ���ֹʱ��
			String[] endTimePoi = getEndTime(file);
			mFileEndTime = endTimePoi[1];
		}

		//�ж�ʱ���Ƿ�С����ֹʱ��
		int compareEnd = compareTime(time,mFileEndTime);
		if(compareEnd==-1){
			return -1;
		}
		
		//������
		//����1�볤ʱ��ռ�����ֽ�
		if(mSecBytes==-1){
			//mSecBytes = calByteSizeSec(sStartTime,first,sEndTime,end);
			//System.out.println("����һ��1�볤ʱ��ռ�����ֽڣ�"+mSecBytes);
			mSecBytes = calByteSizeSecBySecSpace(file);
			//System.out.println("�������� 1�볤ʱ��ռ�����ֽڣ�"+mSecBytes);
		}
		if(mSecBytes==-1){
			System.out.println("����1�볤ʱ��ռ�����ֽڳ���  calPoiSpecifiedTime()");
			return -1;
		}
		
		//��������ʱ���
		long deltT = calDaltaT(mFileStartTime,time);
		
		//���ֵ�п��ܲ�׼(���磺�����д��ڳ�ģ��ʵʱ״̬���ݺ��ĵ����ݻ�����������ʱ)
		long findPoi = mFileStartPos + mSecBytes*deltT;
		//System.out.println("�ݴ�У��ǰ�� ָ��ʱ�� �ļ��Ŀ�ʼλ�ã�"+findPoi);
		//�Եõ������ݽ����ݴ�У��
		findPoi = faultTolerantCalibration(file,findPoi,time);
		System.out.println("�ݴ�У���� ָ��ʱ�� �ļ��Ŀ�ʼλ�ã�"+findPoi);
		return findPoi;
	}
	
	/**
	 * �ݴ�У�� ����ָ����ʱ������Ӧ���ļ���λ��
	 * ˼·�����Ҵ�findPoi����һ��0x10��λ�ã�ȡ���������ݵ�ʱ��thisTime��findTimeʱ��Ƚ�
	 * ���ݱȽϽ����
	 * ���һ��thisTime ���� findTime ����findPoi
	 * �������thisTime ���� findTime ��ǰ���ң�λ�ã�findPoi - (thisTime - findTime)*ÿ������ֽ� +10(����ֽ���)��
	 * �Ӵ�λ����ǰ���ҵ���һ����һ��0x10��λ�ã��ݹ����������������������ǰ���ң��ͼ����ݹ飬���Ϊ�����Ҿ��˳���û�ҵ�,
	 * ��ֹ���޵ݹ���������
	 * �������thisTime С�� findTime �����ң�λ�ã�findPoi + (thisTime - findTime)*ÿ������ֽ� -10(����ֽ���)
	 * �Ӵ�λ�������ҵ���һ����һ��0x10��λ�ã��ݹ�������������������������ң��ͼ����ݹ飬���Ϊ��ǰ���Ҿ��˳���û�ҵ�,
	 * ��ֹ���޵ݹ���������
	 * 
	 * ע�⣺��¼�ļ��п�����״̬ʱ�����Ϊһ�����������ڲ�����С��0x80���ֽڣ������
	 * ��������������Ҫ����һ������
	 * ˼·��
	 * 1����0x10ͷ��ȡ��С��0x80���ֽ�ǰ�����ٽ���һ��ʱ��Ķ�ȡ
	 * 2������ٴζ�ȡ��ʱ�䲻Ϊ�գ���������
	 * 3������ٴζ�ȡ��ʱ��Ϊ�գ���ǰ���ң��ҵ�һ����ȡ��ʱ�䲻Ϊ�յ�λ�ã�����¼��
	 * �˴���ʱ��T1
	 * 4��Ҫ���ҵ�ʱ��findTime��T1�Ƚ�
	 * ���һ��findTime-T1==0 ������T1��λ��
	 * �������findTime-T1<0 ����T1��ǰ����(������T1)��(findTime-T1)��0X10,
	 * ��ΪfindTime��λ��
	 * �������findTime-T1>0 ����T1������(������T1)��(T1-findTime)��0X10,
	 * ��ΪfindTime��λ��
	 * @author zealjiang
	 * @date 2016��4��25�� ����1:44:57
	 * @modify date 2016��5��11�� ����1:52:57
	 * @file �������ļ�
	 * @param findPoi �ҵ������ݵ���ʼλ��
	 * @param time Ҫ���ҵ�ʱ�� ��ʽ��"yyyy-MM-dd HH:mm:ss"��ʱ���ʽ
	 * @return ���û�ҵ�ָ����ʱ���Ӧ��λ�÷���-1
	 */
	private long faultTolerantCalibration(File file,long findPoi,String findTime){
		System.out.println("----faultTolerantCalibration-----begin");
		System.out.println("���õĴ��� : "+(++mRecursionNum));	
		System.out.println("Ҫ���ҵ�ʱ��: "+findTime);	
		System.out.println("�ݴ�У��ǰ��λ��: "+findPoi);	
		//���ҳ��ļ�����ʼʱ�估��Ӧ����ʼλ��
		
		/**
		 * �����ļ��пɴ��ڳ�������Ϣ��ģ��״̬��Ϣ���������Ϣ��ʹ��Ҫ���ҵ�
		 * λ�ñ�û��������Ϣʱ���ƣ��ʴ�ǰ������
		 */
		findPoi = searchContentInFile(file,(byte) 0x10,findPoi);
		byte[] b_poi = readFromTo(file,findPoi,findPoi+C0x10Length);
		//ȡ��ʱ��
		String thisTime = byteArrayToTime(b_poi);
		if("".equals(thisTime)){

			System.out.println("��ȡ�ļ��е�ʱ����� λ�ô�: "+findPoi+"��ʼ��17�ֽڳ���");	
			System.out.println("��ʼ�ݴ�");
			/**
			 * �ɴ�λ��ǰ���ң��ҵ�һ����ȡ��ʱ���λ��
			 */	
			while(true){
				//�Ӵ�λ����ǰ���ҵ���һ����һ��0x10��λ��
				findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi-1);
				b_poi = readFromTo(file,findPoi,findPoi+C0x10Length);
				if(b_poi==null){
					mRecursionNum = 0;
					mRecursion = 0;
					return -1;
				}
				//ȡ��ʱ��
				thisTime = byteArrayToTime(b_poi);
				if("".equals(thisTime)){					
				}else{
					break;
				}
			}
			
			//�Ƚ�
			int compareEnd = compareTime(findTime,thisTime);
			if(compareEnd==0){
				//thisTime ���� findTime ����findPoi
				mRecursionNum = 0;
				mRecursion = 0;
				
				System.out.println("�ݴ�У�����λ��: "+findPoi);
				System.out.println("----faultTolerantCalibration-----end");
				return findPoi;
			}else if(compareEnd==1){
				//thisTime ���� findTime
				//����1�볤ʱ��ռ�����ֽ�
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
				//�Ӵ�λ����ǰ���ҵ���һ����һ��0x10��λ��
				findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi);

				//�˳����ҵ����Ƶ�λ��
				mRecursionNum = 0;
				mRecursion = 0;
				return findPoi;

			}else if(compareEnd==-1){
				//thisTime С�� findTime
				//����1�볤ʱ��ռ�����ֽ�
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
				//�Ӵ�λ�������ҵ���һ����һ��0x10��λ��
				findPoi = searchContentInFile(file,(byte) 0x10,findPoi);

				//�˳�,�ҵ����Ƶ�λ��
				mRecursion = 0;
				mRecursionNum = 0;
				return findPoi;			
			}
			
			mRecursionNum = 0;
			mRecursion = 0;
			return findPoi;
		}
		
		
		System.out.println("У��ǰ���ĵ�һ��ʱ��: "+thisTime);	
		//�Ƚ�
		int compareEnd = compareTime(findTime,thisTime);
		if(compareEnd==0){
			//thisTime ���� findTime ����findPoi
			mRecursionNum = 0;
			mRecursion = 0;
			
			System.out.println("�ݴ�У�����λ��: "+findPoi);
			System.out.println("----faultTolerantCalibration-----end");
			return findPoi;
		}else if(compareEnd==1){
			//thisTime ���� findTime
			//����1�볤ʱ��ռ�����ֽ�
			if(mSecBytes==-1){
				mSecBytes = calByteSizeSecBySecSpace(file);
			}
			if(mSecBytes==-1){
				mRecursionNum = 0;
				mRecursion = 0;
				return -1;
			}			
			System.out.println("mSecBytes һ���ж����ֽڣ�"+mSecBytes+"  findPoi: "+findPoi);
			long deltaT = deltaTime(findTime,thisTime);
			findPoi = findPoi - deltaT*mSecBytes +10;
			//System.out.println("findPoi "+findPoi);
			//�Ӵ�λ����ǰ���ҵ���һ����һ��0x10��λ��
			findPoi = lastIndexOfInFile(file,(byte) 0x10,findPoi);
			 
			System.out.println("mRecursion: "+mRecursion);
			if(mRecursion==1){
				//�˳���û�ҵ�
				mRecursionNum = 0;
				mRecursion = 0;
				return -1;
			}
			mRecursion = -1;
		    //�ݹ���ã�������ǰ����
			faultTolerantCalibration(file,findPoi,findTime);
		}else if(compareEnd==-1){
			//thisTime С�� findTime
			//����1�볤ʱ��ռ�����ֽ�
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
			//�Ӵ�λ�������ҵ���һ����һ��0x10��λ��
			findPoi = searchContentInFile(file,(byte) 0x10,findPoi);
			if(mRecursion==-1){
				//�˳���û�ҵ�
				mRecursion = 0;
				mRecursionNum = 0;
				return -1;
			}
			mRecursion = 1;
		    //�ݹ���ã�����������
			faultTolerantCalibration(file,findPoi,findTime);
		}
		
		mRecursion = 0;
		mRecursionNum = 0;
		System.out.println("--����������------faultTolerantCalibration-------");
		return findPoi;
	}
	
	/**
	 * ָ��һҳ������Ϊ�����ֽڣ����������Ч���ݿ�ʼ�����ҳ��
	 * ˼·��
	 * 	 1���������ʼλ��
	 *   2���������ֹλ��
	 *   3�����ҳ�� =(��ֹλ��-��ʼλ��)/һҳ�����ֽ�
	 * @author zealjiang
	 * @date 2016��4��26�� ����3:29:29
	 * @param pageBytes һҳ�����ֽڵ�����
	 * @return ���ش˼�¼�ļ������ҳ��
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
	 * �������ҳ��
	 * ˼·��
	 * 	 1���������Ч���ݵ���ʼʱ��
	 *   2���������Ч���ݵ���ֹʱ��
	 *   3�����ҳ�� =(��ֹʱ��-��ʼʱ��)/һҳ����������
	 * @author zealjiang
	 * @date 2016��4��26�� ����3:29:29
	 * @param pageSec һҳ�����������
	 * @return ���ش˼�¼�ļ������ҳ��
	 *
	 */
	public float getMaxPageBytime(File file,float pageSec){
		//���ҳ��ļ�����ʼʱ�估��Ӧ����ʼλ��
		long first = searchContentInFile(file,(byte) 0x10,-1);
		if(first==-1){
			return -1;
		}
		byte[] startTb = readFromTo(file,first,C0x10Length+first);
		//ת��ʱ��
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < startTb.length; i++) {
			list.add(startTb[i]);			
		}
		String sStartTime = hu.readTimeFromBytes(list);
		
		//���ҳ��ļ�����ֹʱ�估��Ӧ����ֹλ��
		long end = lastIndexOfInFile(file,(byte) 0x10,-1);
		if(end==-1){
			return -1;
		}
		byte[] endTb = readFromTo(file,end,C0x10Length+end);
		//ת��ʱ��
		list.clear();
		for (int i = 0; i < endTb.length; i++) {
			list.add(endTb[i]);			
		}
		String sEndTime = hu.readTimeFromBytes(list);
		
		//��������ʱ���
		long deltT = calDaltaT(sStartTime,sEndTime);
		
		float maxPage = deltT/pageSec;
		
		mMaxPage = maxPage;
		System.out.println("���ҳ�룺"+maxPage);
		return maxPage;
	}
	
	/**
	 * ��ȡ�ļ��в������ݵĿ�ʼʱ�估λ��
	 * @author zealjiang
	 * @date 2016��4��27�� ����5:14:10
	 * @param file
	 * @return �ļ��в������ݵ���ʼλ��string[0](��λ�ֽ�)�Ϳ�ʼʱ������string[1]
	 *
	 */
	private String[] getStartTime(File file){
		//���ҳ��ļ�����ʼʱ�估��Ӧ����ʼλ��
		long mFileStartPos = searchContentInFile(file,(byte) 0x10,-1);
		if(mFileStartPos==-1){
			return null;
		}
		byte[] startTb = readFromTo(file,mFileStartPos,C0x10Length+mFileStartPos);
		//ת��ʱ��
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < startTb.length; i++) {
			list.add(startTb[i]);			
		}
		String mFileStartTime = hu.readTimeFromBytes(list);
		
		System.out.println("�ļ�����ʼʱ�䣺"+mFileStartTime);
		System.out.println("�ļ���ʼʱ���λ�ã�"+mFileStartPos);
		return new String[]{mFileStartPos+"",mFileStartTime};
	} 
	
	/**
	 * ��ȡ�ļ��в������ݵ���ֹʱ�估λ��
	 * @author zealjiang
	 * @date 2016��5��12�� ����3:47:10
	 * @param file
	 * @return �ļ��в������ݵ���ֹλ��string[0](��λ�ֽ�)����ֹʱ������string[1]
	 *
	 */
	private String[] getEndTime(File file){
		//���ҳ��ļ�����ֹʱ�估��Ӧ����ֹλ��
		long end = lastIndexOfInFile(file,(byte) 0x10,-1);
		if(end==-1){
			return null;
		}
		byte[] endTb = readFromTo(file,end,C0x10Length+end);
		//ת��ʱ��
		HotterUtil hu = new HotterUtil();
		ArrayList<Byte> list = new ArrayList<Byte>();
		for (int i = 0; i < endTb.length; i++) {
			list.add(endTb[i]);			
		}
		String mFileEndTime = hu.readTimeFromBytes(list);
		System.out.println("�ļ�����ֹʱ�䣺"+mFileEndTime);
		System.out.println("�ļ���ֹʱ���λ�ã�"+end);
		return new String[]{end+"",mFileEndTime};
	}
	
	/**
	 * �������ʼλ��
	 * ���������ֵ�һ��ģ��ʵʱ״̬���ݺ�����ٵĵ�һ��0x60�������ݵ�λ��
	 * @author zealjiang
	 * @date 2016��4��26�� ����3:38:47
	 * @return
	 *
	 */
	private long calStart60Poi(File waveFile){
		long first10 = searchContentInFile(waveFile,Integer.valueOf("10", 16).byteValue(),0);
		long first60 = searchContentInFile(waveFile,Integer.valueOf("60", 16).byteValue(),first10);
		
		System.out.println("��һ��0x10��λ�ã�"+first10);
		System.out.println("��һ��0x60��λ�ã�"+first60);
		return first60;
	}
	
	/**
	 * �������ֹλ��
	 * ���������ļ�ĩβ��ʼ��ǰ���ң����ֵ�һ��0x60�������ݵ�λ��
	 * @author zealjiang
	 * @date 2016��4��26�� ����3:38:47
	 * @return
	 *
	 */
	private long calEnd60Poi(File waveFile){
		//���ҳ��ļ�����ֹʱ�估��Ӧ����ֹλ��
		long end60 = lastIndexOfInFile(waveFile,(byte) 0x60,-1);
		System.out.println("���һ��0x60��λ�ã�"+end60);
		return end60;
	}
	
	/**
	 * ����һҳ�м�������
	 * ���Ƽ�ʹ�ã���Ϊһҳ����ʵ����2.34�������ݣ��˷����ᰴ2��3��������
	 * �����������´������
	 * @author zealjiang
	 * @date 2016��4��26�� ����5:44:39
	 *
	 */
	@Deprecated
	public void setPagePacketNum(int pagePackets){
		mPagePackets = pagePackets;
	}
	
	/**
	 * ��ȡ��ǰҳ���ֽ�����(ע�����ģ��ʵʱ״̬��Ϣ���ݼ�0x10���͵�����)
	 * �����������������Ҫԭ������Ϊ�������õ���mPagePackets(һҳ��������)���
	 * ���͵����������������
	 * replaced by <code>WaveFileRead.getPageBytesByTime(file,pageNum)</code>
	 * ˼·�� 1���ҵ��ļ���һ��0x10��λ�ã�����һҳ���ֽ������������ҵ���λ����ȡһҳ���ֽ�����
	 *      2�����ذ���0x10�����������ڵ�һҳ����
	 *      
	 * @author zealjiang  
	 * @date 2016��4��27�� ����9:39:56  
	 * @modify 2016��4��28�� ����9:53:56  
	 * @pageNum ��ǰҳ��(��ʼҳ��Ϊ1)
	 * @return ���ص�ǰҳ����ֽ�����
	 */
	@Deprecated
	public byte[] getPageBytesBySize(File file,int pageNum){
		//�ҵ��ļ���һ��0x10��λ��
		//���ҳ��ļ�����ʼʱ�估��Ӧ����ʼλ��
		long first = searchContentInFile(file,(byte) 0x10,-1);
		//�����һҳ�ж�������
		long pageBytes = mPagePackets*C0x60Length;
		long from = pageBytes*(pageNum-1)+first;
		long to = from+pageBytes;
		return readFromTo(file,from,to);
	}
	
	/**
	 * ����һҳ�м��������
	 * @author zealjiang
	 * @date 2016��4��26�� ����5:44:39
	 *
	 */
	public void setPageSecData(float pageSecData){
		mPageSecData = pageSecData;
	}
	
	/**
	 * ��ȡ��ǰҳ���ֽ�����(ע�����ģ��ʵʱ״̬��Ϣ���ݼ�0x10���͵�����)
	 * ע�⣺�����������֮ǰҪ�ȵ���setPageSecData()ΪmPageSecData��ֵ
	 * ˼·�� 1���ҵ��ļ���һ��0x10��λ�ã�����һҳ���ֽ������������ҵ���λ����ȡһҳ���ֽ�����
	 *      2�����ذ���0x10�����������ڵ�һҳ����
	 * @author zealjiang
	 * @date 2016��4��27�� ����9:39:56
	 * @pageNum ��ǰҳ��(��ʼҳ��Ϊ1)
	 * @return ���ذ�����ǰҳ����ֽ������PageBean����
	 */
	public PageBean getPageBytesByTime(File file,int pageNum){
		System.out.println("---���ҵ�ҳ��Ϊ�� "+pageNum);
		PageBean pb = new PageBean();
		if(null==mFileStartTime||mFileStartTime.length()==0){
			//��ȡ��Ч���ݵ���ʼʱ��
			String[] poiAndTime = getStartTime(file);
			mFileStartTime = poiAndTime[1];
		}
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼʱ��
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(mFileStartTime, (int)((pageNum-1)*mPageSecData));
		System.out.println("ָ��ҳ�����ݵĿ�ʼʱ�䣺"+sdateFrom);
		pb.setStartTime(sdateFrom);	
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼλ��
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
        //�����λ��ǰ����һ�����ݣ���λ��ǰһ��0x60���ݵ�λ��
        long from = lastIndexOfInFile(file, (byte) 0x60, pFrom);
        if(from==-1){
            from = pFrom;
        }

		//����ָ��pageNumҳ���Ӧ�Ľ���ʱ��
		int sec = mPageSecData%(int)mPageSecData>0?(int)mPageSecData+1:(int)mPageSecData;
		String sdateTo = ta.addStringDateSec(sdateFrom,sec);
		//���sdateTo�����ļ��Ľ���ʱ�䣬���ļ��Ľ���ʱ�丳ֵ��sdateTo
		if(mMaxPage<0){
			//�������ҳ��
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(null==mFileEndTime||mFileEndTime.length()==0){
			//��ȡ��Ч���ݵ���ʼʱ��
			String[] poiAndTime = getEndTime(file);
			mFileEndTime = poiAndTime[1];
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
		}
		//System.out.println("��ȡ���ݵ���ֹʱ�䣺"+sdateTo);
		pb.setEndTime(sdateTo);
		//����ָ��pageNumҳ���Ӧ�Ľ���λ��(��Ӧ��λ����0x10���ڵ�λ��)
		long pTo = calPoiSpecifiedTime(file,sdateTo);
		//System.out.println("����ֹʱ���λ�ã�"+pTo);
		byte[] pageBytes = readFromTo(file,from,pTo);
		pb.setContent(pageBytes);
				
		if(pageBytes==null){
			System.out.println("---end------getPageBytesByTime------------");
			return pb;
		}
		//��ӡ����ҳ������
		System.out.println("---------��ǰҳ�룺"+pageNum);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 76; i++) {//���ܺ������һ����76�ֽڵ�����
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
     * �˼�¼�ļ������ҳ��
     * �������ҳ�������ַ�����
     * ����һ��
     *        1����ȡ��¼�ļ��е�ʱ���ǣ�ȡ����ʼʱ��ͽ���ʱ��
     *        2������ʾ����Ŀ��/ֽ�٣����һҳ����ʾ�����������
     *        3��(����ʱ��-��ʼʱ��)/һҳ�����룬������ҳ��
     * ��������
     *        1����ȡ��Ч���ݿ�ʼ��λ��startP����Ч���ݽ�����λ��endP
     *        2������ʾ����Ŀ��/ֽ�٣����һҳ����ʾsec�������
     *        3������Hotter1�뷢��16�����ݣ���һ�����ݵĳ��ȣ������1���ж����ֽڵ�����
     *        4��(endP-startP)/((16*һ�����ݵĳ���)*sec),������ҳ��
     * �������ַ����ıȽϣ�
     *      ����һ������ʵ������������㣺
     *              ��ʾ����Ŀ��62.33mm/ֽ��25mm=2.4932��
     *              ��ʼʱ��10:31:09,����ʱ��10:36:30,ʱ�����321��
     *              321/2.4932=128.75ҳ�����������ҳ����128.75ҳ
     *      ������������ʵ������������㣺
     *              ��Ч���ݿ�ʼ��λ��608��Ч���ݽ�����λ��397482
     *              ��ʾ����Ŀ��62.33mm/ֽ��25mm=2.4932��
     *              1��16������*һ�����ݵĳ���76=1216�ֽ�����
     *              һҳ��2.4932*1216=3031.37�ֽڵ�����
     *              (397482-608)/3031.37 = 130.92ҳ
     *      ͨ���ȽϿ��Կ�������һ��ʱ������ʱ�䷽������ȷ
     *      �������г��ֵ�1��16������������ص����ݲ��Ǻܺã����1�벻��
     *      16�����ݶ���30�����ݣ���ô��������������ҳ�ͳ����ˣ�����ͨ��
     *      �ȽϷ���һ���á�
     * */
	
	/**
	 * ��ȡ��ǰҳ���ֽ�����(ע�����ģ��ʵʱ״̬��Ϣ���ݼ�0x10���͵�����),��λ��������,��һҳ��2.49�����ݣ�
	 * ʵ�ʻ�ȡ����3�������
	 * ע�⣺�����������֮ǰҪ�ȵ���setPageSecData()ΪmPageSecData��ֵ
	 * ˼·�� 1���ҵ��ļ���һ��0x10��λ�ã�����һҳ���ֽ������������ҵ���λ����ȡһҳ���ֽ�����
	 *      2�����ذ���0x10�����������ڵ�һҳ����
	 * @author zealjiang
	 * @date 2016��4��27�� ����9:39:56
	 * @pageNum ��ǰҳ��(��ʼҳ��Ϊ1)
	 * @return ���ص�ǰҳ����ֽ�����
	 */
	public byte[] getPageBytesByTimeIntSec(File file,int pageNum){
		
		//��ȡ��Ч���ݵ���ʼʱ��
		String[] poiAndTime = getStartTime(file);
		System.out.println("start time: "+poiAndTime[1]);
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼʱ��
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(poiAndTime[1], (int)((pageNum-1)*mPageSecData));
		System.out.println(pageNum+"ҳ time: "+sdateFrom);
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼλ��
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
		//����ָ��pageNumҳ���Ӧ�Ľ���ʱ��
		int sec = mPageSecData%(int)mPageSecData>0?(int)mPageSecData+1:(int)mPageSecData;	
		String sdateTo = ta.addStringDateSec(sdateFrom,sec);
		//���sdateTo�����ļ��Ľ���ʱ�䣬���ļ��Ľ���ʱ�丳ֵ��sdateTo
		if(mMaxPage<0){
			//�������ҳ��
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
		}
		//����ָ��pageNumҳ���Ӧ�Ľ���λ��(��Ӧ��λ����0x10���ڵ�λ��)
		long pTo = calPoiSpecifiedTime(file,sdateTo);
		byte[] pageBytes = readFromTo(file,pFrom,pTo);
		//��ӡ������ֽ�����
/*		for (int i = 0; i < pageBytes.length; i++) {
			System.out.println(i+": "+Integer.toHexString((pageBytes[i]&0xFF)));
		}*/
		return pageBytes;
	}
	
	/**
	 * 
	 * ��ȡ��ǰҳ���ֽ�����(ע�����ģ��ʵʱ״̬��Ϣ���ݼ�0x10���͵�����)��λ�Ǹ���float��,��һҳ��2.49�����ݣ�
	 * ʵ�ʻ�ȡ����2.49�������
	 * ע�⣺�����������֮ǰҪ�ȵ���setPageSecData()ΪmPageSecData��ֵ
	 * ˼·�� 1���ҵ��ļ���һ��0x10��λ�ã�����һҳ���ֽ������������ҵ���λ����ȡһҳ���ֽ�����
	 *      2�����ذ���0x10�����������ڵ�һҳ����
	 * @author zealjiang
	 * @date 2016��5��6�� ����10:09:56
	 * @pageNum ��ǰҳ��(��ʼҳ��Ϊ1)
	 * @return ���ص�ǰҳ����ֽ�����
	 */
	public byte[] getPageBytesByTimeFloatSec(File file,int pageNum){
		byte[] pageBytes = null;
		if(mSecPacket==-1){
			mSecPacket = calSecPacket(file);
		}

		//��ȡ��Ч���ݵ���ʼʱ��
		String[] poiAndTime = getStartTime(file);
		System.out.println("�����ļ��ĵ�1��ʱ��: "+poiAndTime[1]);
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼʱ��
		TimeAdding ta = new TimeAdding();		
		String sdateFrom = ta.addStringDateSec(poiAndTime[1], (int)((pageNum-1)*mPageSecData));
		System.out.println("��"+pageNum+"ҳ ��ʼʱ��: "+sdateFrom);
		//����ָ��pageNumҳ���Ӧ�Ŀ�ʼλ��
		long pFrom = calPoiSpecifiedTime(file,sdateFrom);
		
		//����pageNumС�������ж��ٰ�����
		float decimal = getDecimal((pageNum-1)*mPageSecData);
		//������������7.84��ȡ��������7
		int decimalPacket = (int)(mSecPacket*decimal);
		//�����һҳ�µĿ�ʼλ��
		pFrom = calFromAdd60Nums(file,pFrom,decimalPacket);
		
		//����ָ��pageNumҳ���Ӧ�Ľ���ʱ��	
		String sdateTo = ta.addStringDateSec(poiAndTime[1], (int)(pageNum*mPageSecData));
		
		System.out.println("��"+pageNum+"ҳ����ʱ��: "+sdateTo);
		
		long pTo = 0;
		//���sdateTo�����ļ��Ľ���ʱ�䣬���ļ��Ľ���λ�ø�����ֹλ��
		if(mMaxPage<0){
			//�������ҳ��
			mMaxPage = getMaxPageBytime(file,mPageSecData);
		}
		if(pageNum>=mMaxPage){
			sdateTo = mFileEndTime;
			pTo = file.length();
			
		}else{
			//����ָ��pageNumҳ���Ӧ�Ľ���λ��(��Ӧ��λ����0x10���ڵ�λ��)
			pTo = calPoiSpecifiedTime(file,sdateTo);
			System.out.println(" pTo: "+pTo);
			//����pageNum����ҳС�������ж��ٰ�����
			float decimalEnd = getDecimal(pageNum*mPageSecData);
			//������������7.84��ȡ��������7
			int decimalPacketEnd = (int)(mSecPacket*decimalEnd);
			//�����һҳ�µĿ�ʼλ��
			pTo = calFromAdd60Nums(file,pTo,decimalPacketEnd);
			
		}
		System.out.println("pFrom"+pFrom+" pTo: "+pTo);
		pageBytes = readFromTo(file,pFrom,pTo);
		//��ӡ������ֽ�����
/*		for (int i = 0; i < pageBytes.length; i++) {
			System.out.println(i+": "+Integer.toHexString((pageBytes[i]&0xFF)));
		}*/

		return pageBytes;
	}
	
	/**
	 * ����1���ж��ٰ�����
	 * ��������������0X10���͵�����֮���ж��ٸ�0X60������
	 * ע�⣺����MappedByteBuffer.get(int index),indexΪ������ֵ,
	 * java Integer.MAX_VALUEΪ2147483647�������ѯ�ĵ�λ���ֽڣ�
	 * 2147483647�ֽ�ת����GBΪ2��Ҳ���ǵ����ļ����Ϊ2GByte,�������ֵ����
	 * �����
	 *@author zealjiang
	 *@time 2016/5/5 18:20
	 */
	private int calSecPacket(File waveFile){
		//���ļ�ͷ��ʼ�ҵ���һ��0X10��λ��
		long first10 = searchContentInFile(waveFile,Integer.valueOf("10", 16).byteValue(),0);
		//�Ӵ�λ���������ң������ٴγ���0x10ʱֹͣ����¼�ڼ���ֵ�0X60�ĸ���
		int x60Num = 0;
		boolean boo = true;
		while(boo){
			try {

				FileInputStream fis = new FileInputStream(waveFile);
				FileChannel fc = fis.getChannel();

				long end = fc.size();
				int start = (int)first10+1;
				if(start==end){//�����ʼλ��Ϊ�ļ������λ�ã�˵���޷��ҵ�
					return 0;
				}
		
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, end);
						
				/**
				 * ע�⣺����MappedByteBuffer.get(int index),indexΪ������ֵ,
				 * java Integer.MAX_VALUEΪ2147483647�������ѯ�ĵ�λ���ֽڣ�
				 * 2147483647�ֽ�ת����GBΪ2��Ҳ���ǵ����ļ����Ϊ2GByte,�������ֵ����
				 * �����
				 */
				for (int i = start; i < bb.capacity(); i++) {
					if((byte) 0x60==bb.get(i)){
						x60Num++;
						continue;
					}	
					if((byte) 0x10==bb.get(i)){
						//����whileѭ��
						boo = false;
						break;
					}	
				}

				System.out.println("1����"+x60Num+"����������");

				fc.close();
				//����whileѭ��
				boo = false;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		mSecPacket = x60Num;
		return x60Num;
	}
	
	/**
	 * ����һ������������С������
	 * @author zealjiang
	 * @date 2016��5��6�� ����10:41:38
	 * @param floatNum
	 * @return С������
	 *
	 */
	private float getDecimal(float floatNum){
	
		BigDecimal bFloat = new BigDecimal(Float.toString(floatNum));
	    BigDecimal bInt = new BigDecimal(Float.toString((int)floatNum));
	    float decimal = bFloat.subtract(bInt).floatValue(); 
	    return decimal;

	}
	
	/**
	 * �����ڲ����ļ�waveFile�д�ָ����λ��from����󾭹�num��0X60���ݺ����λ��
	 * @author zealjiang
	 * @date 2016��5��6�� ����11:19:29
	 * @param waveFile
	 * @param from
	 * @param num
	 * @return �����µ���ʼλ��,ĩ�ҵ�����-1
	 *
	 */
	private long calFromAdd60Nums(File waveFile,long from,int num){
		long n = -1;
		//�Ӵ�λ���������ң������ٴγ���0x10ʱֹͣ����¼�ڼ���ֵ�0X60�ĸ���
		int x60Num = 0;
		boolean boo = true;
		while(boo){
			try {

				FileInputStream fis = new FileInputStream(waveFile);
				FileChannel fc = fis.getChannel();

				long end = fc.size();
				int start = (int)from;
				if(start==end){//�����ʼλ��Ϊ�ļ������λ�ã�˵���޷��ҵ�
					return n;
				}
		
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, end);
						
				/**
				 * ע�⣺����MappedByteBuffer.get(int index),indexΪ������ֵ,
				 * java Integer.MAX_VALUEΪ2147483647�������ѯ�ĵ�λ���ֽڣ�
				 * 2147483647�ֽ�ת����GBΪ2��Ҳ���ǵ����ļ����Ϊ2GByte,�������ֵ����
				 * �����
				 */
				for (int i = start; i < bb.capacity(); i++) {
					if((byte) 0x60==bb.get(i)){
						x60Num++;
						if((x60Num-1)==num){//��1ʱ��ΪҪ����num
							n = i;
							//����whileѭ��
							boo = false;
							break;
						}
					}	
				}
				
				fc.close();
				
				//����whileѭ��
				boo = false;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		System.out.println("����"+num+"��0X60���µ����λ����"+n);
		return n;
	}
	
	
	/**
	 * һҳ���ݶ���
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
     * ��0x10��ģ��ʵʱ�ֽ�����ת��ʱ���ַ���
     * @author zealjiang
     * @date 2016��5��11�� ����2:12:38
     * @param bytes
     * @return ������ֽ����ݺϷ�����ʱ���ַ��������򷵻�""
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
		for (int i = 0; i < 17; i++) {//���ܺ������һ����76�ֽڵ�����
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
	 * ��������ʱ���Ϊ������
	 * yyyy-MM-dd HH:mm:ss �����ʽ���ַ���ʱ��
	 * @author zealjiang
	 * @date 2016��5��11�� ����2:38:40
	 * @param startT ��ʼʱ��
	 * @param endT ����ʱ��
	 * @return ����ʱ���������룬ʱ���ʽ����ȷ����-1
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
