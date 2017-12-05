package com.file.split;

import java.io.File;
import java.io.RandomAccessFile;

import org.junit.Test;

public class SplitImageUtil {


	/**
	 * <p>����ļ�</p>
	 * @param file Դ�ļ�
	 * @param count ��ֵ��ļ�����
	 * @throws Exception
	 */
	public static void split(String file ,int count) throws Exception
	{		
		
		RandomAccessFile raf = new RandomAccessFile(new File(file),"r");
		long length = raf.length();
		
		long theadMaxSize =  length / count; //ÿ�ݵĴ�С 1024 * 1000L;
		raf.close();

		long offset = 0L;
		for(int i=0;i< count-1;i++) //���ﲻȥ�������һ��
		{
			long fbegin = offset;
			long fend = (i+1) * theadMaxSize;
			offset =write(file,i,fbegin,fend);
		}

		if(length- offset>0) //��ʣ��Ķ�д�����һ��
			write(file,count-1,offset,length);
	}
	/**
	 * <p>ָ��ÿ���ļ��ķ�Χд�벻ͬ�ļ�</p>
	 * @param file Դ�ļ�
	 * @param index �ļ�˳���ʶ
	 * @param begin ��ʼָ��λ��
	 * @param end ����ָ��λ��
	 * @return
	 * @throws Exception
	 */
	private static long write(String file,int index,long begin,long end) throws Exception
	{
		RandomAccessFile in = new RandomAccessFile(new File(file),"r");
		RandomAccessFile out = new RandomAccessFile(new File(file+"_"+index+".tmp"),"rw");
		byte[] b = new byte[1024];
		int n=0;
		in.seek(begin);//��ָ��λ�ö�ȡ

		while(in.getFilePointer() <= end && (n= in.read(b))!=-1)
		{
			out.write(b, 0, n);
		}
		long endPointer =in.getFilePointer();
		in.close();
		out.close();
		return endPointer;
	}
	/**
	 * <p>�ϲ��ļ�</p>
	 * @param file ָ���ϲ�����ļ�
	 * @param tempFiles �ָ�ǰ���ļ���
	 * @param tempCount �ļ�����
	 * @throws Exception
	 */
	public static void merge(String file,String tempFiles,int tempCount) throws Exception
	{
		RandomAccessFile ok = new RandomAccessFile(new File(file),"rw");

		for(int i=0;i<tempCount;i++)
		{
			RandomAccessFile read = new RandomAccessFile(new File(tempFiles+"_"+i+".tmp"),"r");
			byte[] b = new byte[1024];
			int n=0;
			while((n=read.read(b))!= -1)
			{
				ok.write(b, 0, n);
			}
			read.close();
		}
		ok.close();
	}
	
	@Test
	public void testSplit()throws Exception
	{
		SplitImageUtil.split("e:/ccwj.RMVB", 5);
	}
	@Test
	public void testMerge() throws Exception
	{
		SplitImageUtil.merge("e:/ccwj.RMVB", "e:/ccwj.RMVB", 5);
	}

}