package com.file;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.nio.ByteBuffer;

import java.nio.CharBuffer;

import java.nio.MappedByteBuffer;

import java.nio.channels.FileChannel;

import java.nio.charset.Charset;

import java.nio.charset.CharsetDecoder;


/**
 * 运行结果：  
	14235  
	Read time :24ms  
	Write time :21ms  
	我们把标注1和2语句注释掉，换成它们下面的被注释的那条语句，再来看运行效果。14235  
	Read time :2ms  
	Write time :0ms  

 * @author zealjiang
 * @time 2016年4月22日下午2:14:28
 */
public class MapMemeryBuffer {

       public static void main(String[] args) throws Exception {

              ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);

              byte[] bbb = new byte[14 * 1024 * 1024];

              FileInputStream fis = new FileInputStream("d:\\李志江13048118612569654_20160422103234.YE");

              FileOutputStream fos = new FileOutputStream("d:\\outFile.txt");

              FileChannel fc = fis.getChannel();

             

              long timeStar = System.currentTimeMillis();//得到当前的时间


              //fc.read(byteBuf);//1 读取
              MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());


              long timeEnd = System.currentTimeMillis();//得到当前的时间


              System.out.println("Read time :" + (timeEnd - timeStar) + "ms");

              timeStar = System.currentTimeMillis();


              //fos.write(bbb);// 写入
              mbb.flip();


              timeEnd = System.currentTimeMillis();

              System.out.println("Write time :" + (timeEnd - timeStar) + "ms");

              fos.flush();

              fc.close();

              fis.close();

       }

}

