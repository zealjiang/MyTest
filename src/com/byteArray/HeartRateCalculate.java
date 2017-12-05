package com.byteArray;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.byteArray.HotterUtil.DecryptPacketListener;
import com.byteArray.HotterUtil.OriginalPacket;
import com.sort.QuickSort;


/**
 * 通过第二导联ECG II波形数据在一定时间内波峰的个数来计算心率
 * 通过波形数组(包含模块实时状态信息和波形信息)来计算
 * 前10秒来找出ECG II波形的波峰值
 * 步骤： 1、得到成包的数据
 *      2、还原为原始的数据
 *      3、如果是时间数据保存开始时间
 *     	4、取到ECG II波形数据，以ECG II 的8个字节数据为一个单位存入List中
 *      5、有一个线程不断地从List中取出数据(暂时不从List中删除)
 *      6-1、创建一个有两个属性的对象A，属性1：R波所在字节数组所属List中的位置P2;属性2：R波所
 *      在字节数组中的位置P1;
 *      6-2、创建一个只能存储两个对象我的仓库B，如果仓库B已满，即已为size为2，计算心率，之后
 *      清空步骤5List中仓库B中第2个对象在List中的位置及之前的所有对象，再清空仓库中的第一个对象;
 *      找到一个R波，将其对象A加入仓库B中，
 *      6-2、将取出的数据放入查找R波的方法中，如果找到R波，记录下这个R波所对应的在8个ECG II字节
 *      数组中的位置P1及在List中的位置P2，同理找到下一个R波的位置P3和P4
 *      计算一个心跳间隔多少字节：
 *      N = (P4-P2-1)*8+(8-P1)+P3
 *      每个波形每秒有16包*8个字节的数据 16*8=128，1分钟就有128*60=7680字节
 *      Rate = 7680/N
 *      
 *      下面是查找心电波R位置的方法：
 *      7、找到最大值，与之前保存的最大值比较，将比较后的新的最大值保存
 *      8、如果检测到时间数据包，判断从开始到现在是否大于10秒，如果大于则结束测量ECG II波形的波峰值
 *      9、将最大值的4/5作为参考值
 *      
 *      计算心跳次数的方法：
 *      方法一：
 *      以6秒为一个时间段，计算出6秒内出现的心跳数，乘10就得出了1分钟的心跳数
 *      假如：6秒检测到有6个心跳，那么1分钟就有6*10=60个心跳
 *      假如：6秒检测到有7个心跳，那么1分钟就有7*10=70个心跳
 *      误差是70-60=10
 *      方法二：
 *      计算两个心跳之间有多少包的0x60数据，根据每秒接收16包数据，算出两个心跳
 *      间隔占多长时间，用60/间隔得出1分钟的心跳数
 *      假如：两个心跳之间有10包数据，60/(10/16)=96
 *      假如：两个心跳之间有10包数据，60/(11/16)=87
 *      误差是96-87=7
 *      方法三：
 *      每个波形每秒有16包*8个字节的数据 16*8=128，1分钟就有128*60=7680字节
 *      (注意：ECG II在一包解密数据中的位置是9~16)
 *      假如：第一个心跳在第2包数据数据的10位，第二个心跳在第15包数据的第15位，两个心跳
 *      的间隔为(16-10)+(15-2-1)*8+(15-9)+1=109,7680/109=70
 *      假如：第一个心跳在第2包数据数据的11位，第二个心跳在第15包数据的第15位，两个心跳
 *      的间隔为(16-11)+(15-2-1)*8+(15-9)+1=108,7680/108=71
 *      误差是71-70=1
 *      
 *      比较以上3种算法，方法三的误差是最小的，所在选用方法三来计算心率
 *      
 * @author zealjiang
 * @time 2016年5月13日下午2:54:12
 */
public class HeartRateCalculate implements DecryptPacketListener{
	

    /**大于等于参考值的点中相对与当前查找的点的之前的点*/
	private PosVal mLast = null;
	/**第一个找到的大于参考值的点*/
	private PosVal mFirst = null;
	/**R波点*/
	private PosVal mMax = null;
	private PosVal mSecond = null;
	private PosVal mTempMax = null;
	private PosVal referencePv1 = new PosVal();
	private PosVal referencePv2 = new PosVal();
	private PosVal maxPv = new PosVal();
	/**创建一个只能存储两个对象我的仓库*/
	private ArrayList<PosVal> mPosValList = new ArrayList<PosVal>();
	/**参考波形值,如果波形值大于等于参考值，可认为是心跳*/
	private int mReferenceValue = 106;
	/**当前正在查找心率的包的位置
	 * 思路：mPosValList为空时，表示一个波峰还没找到，mThePacketId为-1，当找到了第一个
	 * 波峰时mThePacketId=0，mPosValList[0]中第一个波峰的包ID也为0，
	 * 此后开始，每有一新包数据被载入查找波峰mThePacketId++，直到
	 * 找到第二个波峰时，将mPosValList[1]的包ID设置为mThePacketId；计算完心率后
	 * 将mPosValList[0]删除，将mPosValList[1]的包ID设置为0同时将mThePacketId设置为0；
	 */
	private int mThePacketId = -1;
	
	/**用于模拟发送数据*/
	private Timer timer;
	
    /**下面来做一个盒子来存放发来的波形数据 by zealjiang @2016/5/12 17:51*/
    private ArrayList<byte[]> mListBox = new ArrayList<byte[]>();
    private HotterUtil mHotterUtil = new HotterUtil();
    
    /**
     * 用来存储R值的list,长度超过20后，找出正确的R波值，计算出参考心率值
     *  by zealjiang @2016/5/19 15:38
     */
    private ArrayList<byte[]> mListRBox = new ArrayList<byte[]>();
    
    
	//用来存储一段连续的要分析的波值
	ArrayList<Integer> listInt = new ArrayList<Integer>();
	//用来存储一段连续的要分析的波值的长度
	private int mAnalysis = 0;
	//用来存储R点
	ArrayList<Integer> listR = new ArrayList<Integer>();
	//用来存储所有的QRS点，每个QRS段之前用0分隔
	ArrayList<Integer> listQRSs = new ArrayList<Integer>();
	
	StringBuffer sb = new StringBuffer();
		
	public static void main(String[] args) {

		HeartRateCalculate hrc = new HeartRateCalculate();
		//一包多个波峰点测试
		//hrc.test();
		
/*		//测试2
        //仿十二导Holter发送数据测试
		//生成模拟数据
		hrc.simulateSend();
		//发送模拟数据
		hrc.startSend();
		//开启分析心率功能
		hrc.start();*/
		
/*		//测试3
 * 		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(99);
		list.add(98);
		list.add(99);
		list.add(98);
		list.add(98);
		list.add(96);
		list.add(95);
		list.add(95);
		list.add(94);
		list.add(96);
		list.add(94);
		list.add(97);
		list.add(102);
		list.add(116);
		list.add(128);
		list.add(136);
		list.add(121);
		list.add(88);
		int n = hrc.getRValueByFrequency(list);
		System.out.println("getRValueByFrequency: "+n);
		int n2 = hrc.getRValueByOrder(list);
		System.out.println("getRValueByOrder: "+n2);*/
		
/*		//测试4
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sdataError1 = "109 121 101 113 118 115 117 121 053 064 055 075 070 081 092 084"
				+ " 107 104 118 141 158 143 069 023 005 244 233 236 227 227 227 227"
				+ " 227 227 227 227 227 227 227 227 227 227 238 239 255 025 035 068"
				+ " 077 096 115 110 124 121 126 137 128 145 139 158 167 153 163 160"
				+ " 170 175 168 168 186 180 181 190 184 197 188 193 205 197 212 209"
				+ " 209 215 205 220 209 209 217 206 220 221 221 221 194 137 069 031"
				+ " 026 041 112 105 038 006 016 046 036 003 018 017 035 036 044 050"
				+ " 046 041 048 055 059 072 066 090 084 096 115 114 128 121 128 135"
				+ " 124 142 127 128 131 115 120 112";
		
		String sdataError2 = "092 082 090 087 078 090 074 081 049 038 050 051 048 061 046 056"
				+ " 054 048 054 036 038 030 021 027 010 013 021 016 005 015 000 003"
				+ " 252 246 002 243 252 253 249 011 006 018 023 017 027 014 024 017"
				+ " 013 025 010 019 017 009 016 252 000 249 243 000 244 255 000 254"
				+ " 013 003 006 015 015 006 038 079 127 081 006 004 253 016 021 016"
				+ " 027 015 027 026 020 032 020 032 035 031 046 037 047 045 042 054"
				+ " 043 050 051 046 058 045 054 052 042 052 039 045 041 035 046 035"
				+ " 039 047 048 044 054 045 055 054 050 065 055 065 069 064 077 067"
				+ " 077 076 072 083 071 080 083 078";
				 
		String sdataCorrect = "092 092 092 092 092 090 092 093 092 093 118 169 199 146 083 072"
				+ " 084 092 096 098 101 099 099 099 099 103 103 104 106 105 108 108"
				+ " 109 112 112 116 116 117 119 116 118 118 117 117 114 111 106 105"
				+ " 103 100 099 095 095 095 094 097 095 094 096 096 096 096 096 096"
				+ " 098 098 099 098 099 098 098 098 096 098 098 097 099 097 099 097"
				+ " 102 100 102 104 102 102 099 098 096 094 094 093 093 095 093 093"
				+ " 095 095 093 095 092 107 150 194 173 102 071 078 090 096 096 098"
				+ " 095 097 098 098 098 101 101 104 104 106 108 108 111 113 115 116";
		String[] array = sdataCorrect.split(" ");
		for (int i = 0; i < array.length; i++) {
			//System.out.println(i+": "+array[i]);
			//System.out.println(i+": "+Integer.valueOf(array[i]));
			list.add(Integer.valueOf(array[i]));
		}

		hrc.getRValue(list,96);
		
		System.out.println();
		for (int i = 0; i < hrc.listR.size(); i++) {
			System.out.print(hrc.listR.get(i)+",");
		}
		System.out.println();
		for (int i = 0; i < hrc.listQRSs.size(); i++) {
			System.out.print(hrc.listQRSs.get(i)+",");
		}
		
		System.out.println();
		int n = hrc.getRValueByFrequency(hrc.listR);
		System.out.println("getRValueByFrequency: "+n);
		int n2 = hrc.getRValueByOrder(hrc.listR);
		System.out.println("getRValueByOrder: "+n2);*/
		
		//测试从文件中读数据算心率
		hrc.mHotterUtil.setDecryptPacketListener(hrc);
		hrc.readFileThread.start();
	}
	
	private void start(){
		
		RateThread rt = new RateThread();
		rt.setRunning(true);
		rt.start();
	}
	
	private void test(){
		byte[] bytes;
		//正弦波
		bytes = new byte[]{(byte)160,(byte)170,(byte)180,
		(byte)190,(byte)200,(byte)210,(byte)200,(byte)190,(byte)180};
		calHearRateNums(bytes,200);

		bytes = new byte[]{(byte)190,(byte)200,(byte)211,(byte)207,(byte)192};
		calHearRateNums(bytes,200);
		
		bytes = new byte[]{(byte)197,(byte)205,(byte)212,(byte)192};
		calHearRateNums(bytes,200);
		
		bytes = new byte[]{(byte)197,(byte)205,(byte)213,(byte)201};
		calHearRateNums(bytes,200);
		
		bytes = new byte[]{(byte)197,(byte)206,(byte)196};
		calHearRateNums(bytes,200);	
		//方波
		bytes = new byte[]{(byte)160,(byte)197,(byte)205,(byte)205,(byte)196};
		calHearRateNums(bytes,200);
	}
	
	private void startSend(){
		timer = new Timer();
		//每隔62.5毫秒发送一包数据，一秒正好是16包数据，但是毫秒要求是整型数值
		timer.schedule(ttFz, 1000, 62);
	}
	
	//仿真加密数据列表
    ArrayList<ArrayList<Byte>> mSmLists;
    /**
     *生成仿真数据并填加进mList中
     *@author zealjiang
     *created at 2016/4/15 9:13
     */
    private void simulateSend(){

        String sData1 = "60 80 de de de e0 de dd dd 80 de dc db dc dc dc db 80 d9 dd e2 e2 e0 e2 e2 80 e2 e0 e2 db db db db 80 db db dc dc d6 d5 d5 80 d7 d5 d5 d5 d6 d6 d5 80 d5 d6 d5 d6 d6 d6 db 80 db db db dc db db dc 80 dd dd dd dd dd db db 80 dd a9";
        String sData2 = "60 80 de dd de de e0 e0 df 80 df dc dc dc dd e0 df 80 dd dd e2 e2 e2 e3 e2 80 e2 e0 e3 dd dc dd de 80 de de dd e1 d8 d8 da 80 da da dd da db d7 d6 80 d7 d8 d8 da d9 db db 80 db dc dc dd dd dd dd 80 dd db dd dd dd dd de 80 de 99";
        String sData3 = "60 80 e1 e1 e1 de df df df 80 df de e0 dd dd de dd 80 de dd e2 e2 e2 e2 e2 80 e2 e2 e2 df df df de 80 e0 df df df dc dc dd 80 dc dc dc da dd d9 da 80 da d9 da db da da dd 80 de dd de dd de de dd 80 de de de de de de de 80 de de";
        String sData4 = "60 80 df e1 e1 df de de df 80 de dd dd db db da da 80 da d8 e2 e3 e2 e2 e3 80 e2 e2 e2 dd df df dd 80 df dd dd dd da db db 80 da db da da da da da 80 da d8 da d8 d8 d8 dd 80 dd dd dd dd dd dd dd 80 de e0 de de de de de 80 de a4";
        String sData5 = "60 80 de df df df df df e1 80 e1 da db dd dd dd de 80 de e1 e3 e3 e3 e6 e3 80 e0 e2 e2 dd df df df 80 df dd df df da db db 80 dd dd db dd dd d8 da 80 da da da db db db dd 80 dd de de de e0 e0 e2 80 de de de e0 e0 e0 e1 80 e1 f0";
        String sData6 = "60 80 e2 e2 e2 e1 df df de 80 de e1 e0 dd dd d6 d5 80 d5 d3 e2 e3 e2 e3 e5 80 e3 e3 e3 df df df df 80 df dd dd dd dd dd db 80 db db d8 d8 d8 dd db 80 da da d8 d7 d7 d5 e2 80 e2 e0 e0 dd dd db db 80 e3 e3 e1 e0 de de de 80 dc c4";
        String sData7 = "60 80 de df de de de df e1 80 df d5 d3 d2 d3 d2 d5 80 d5 d6 e3 e3 e3 e5 e5 80 e3 e5 e5 dd df dd df 80 df df df df d8 d8 d8 80 da d8 d8 da da d7 d7 80 d5 d7 d5 d5 d7 d7 dd 80 db db db db db dd dd 80 dc dc dc dc dc dc de 80 dc c1";
        String sData8 = "60 90 de de eb fc 80 ed d6 f0 d6 d3 d5 fe c3 dd b0 80 da c1 eb f6 e6 b1 80 80 9e d5 e6 e5 f4 fe ba c1 f4 85 b3 d0 de ee 90 80 e5 b5 ad 8f ae d7 da 8f 87 b2 c9 a2 ab b9 da 9c d8 f7 ac d3 ab d2 ce b8 db d8 e9 94 b5 9b e2 80 d4 f1";
        String sData9 = "60 80 db e0 e3 e1 e0 e0 e0 80 e1 ca d6 db d8 d8 d8 80 d9 db e7 e9 e7 e9 e9 80 e7 e7 e7 db e7 ea ea 80 ea ec eb ec c7 db e4 80 e7 e9 ea ea ed ca d6 80 dc dc de df de e0 d2 80 db dd dd df dd df df 80 d7 dc de dc dc dc dc 80 de c2";
        String sData10 = "60 80 e3 e1 e4 e3 e6 e4 e6 80 e6 db dc de dd e0 de 80 e3 e3 e7 e7 e7 e7 e7 80 e7 e7 e7 ec ec ee ee 80 f1 f1 f1 f3 ee ee f1 80 f1 f6 f5 f8 fc e0 e1 80 e3 e3 e6 e7 e9 eb e0 80 e0 e0 e2 e3 e3 e5 e5 80 de de df df e1 df e1 80 e2 fd";
        String sData11 = "60 80 e4 e4 e6 e6 e7 e8 e8 80 e9 e3 e3 e4 e5 ea ea 80 ee f1 e6 e6 e7 e6 e6 80 e6 e6 e6 f4 f4 f6 f8 c0 f8 fb fd fd fe fe 80 9f 83 85 8a 8b 8d ec ee 80 f0 f3 f5 f9 fc ff e7 80 e8 e8 ea ed ed f2 f2 80 e2 e2 e4 e4 e5 e7 e7 80 e9 fc";
        String sData12 = "60 80 e9 e9 ea e7 e6 e6 e5 80 e5 f0 f2 f3 f2 f2 ec 80 eb e6 e6 e3 e2 e1 e2 80 e1 e1 e2 fd fa f9 f5 f0 f1 ee e9 e8 8c 8a 89 81 84 ff f9 f2 ee ff ff 81 82 ff fb f6 ef ea f4 80 f5 f5 f5 f3 f0 ed e7 80 e9 ea eb eb e8 e7 e5 80 e4 b7";
        String sData13 = "60 80 e2 e3 e2 e2 e2 df e0 80 df e3 e0 db db da d8 80 d7 d5 e4 e4 e4 e4 e5 80 e4 e5 e4 e5 e3 e2 e0 80 df df df df e7 e3 e0 80 dd db da da d8 e4 e1 80 dd da da d8 d8 d6 e6 80 e1 e0 de dd dd db db 80 e1 e1 de de dc dc dc 80 da 93";
        String sData14 = "60 80 df e0 df e0 e0 e0 df 80 df d7 d5 d7 d7 d7 d8 80 d7 d7 e4 e4 e4 e5 e5 80 e5 e5 e5 dd df dd df 80 df df e0 e0 d8 da d9 80 db db dc dc db d6 d6 80 d6 d6 d8 d9 d9 d7 dd 80 db db db db dd db dd 80 dc da da dc da dc dc 80 da f1";
        String sData15 = "60 80 e0 e0 dd e0 df e0 e0 80 e0 d9 d8 d8 d9 d9 dc 80 da dc e7 e5 e5 e5 e4 80 e4 e4 e3 e1 e1 e1 e1 80 e1 e1 e1 e1 dd dd dd 80 e0 de de df df d9 d9 80 da da da da db db dd 80 db de de de de de df 80 dc dc dc dd dd dd dd 80 dd e9";
        String sData16 = "60 80 e2 df df e0 e1 e1 e0 80 e2 dc d9 da da da db 80 db de e3 e3 e3 e3 e3 80 e3 e3 e3 e2 e2 e2 e2 80 e2 e0 e2 e0 df df df 80 df dd dd dd dd db d9 80 da dc da da da da de 80 de df de df df df e0 80 df dd dd dd dd dd dd 80 dd 81";
        String sData17 = "60 80 e3 df e0 e0 e0 e2 e3 80 e5 e0 dd e0 e0 e1 e4 80 e6 e6 e3 e4 e4 e4 de 80 e0 e0 e0 e2 e0 e0 e0 80 dd de e0 e0 df dd dd 80 df db df df df dc da 80 dc dc da dc de de e0 80 df e0 df e1 e1 e2 e2 80 dd dd dd dd df e0 e2 80 e0 cd";
        String sData18 = "60 80 e3 e0 e0 dd dd df dd 80 df e0 de dd d8 d8 d6 80 d6 d8 e1 e1 e3 e1 e1 80 e1 e1 e3 de de de dd 80 dd dd dd de dd db da 80 d7 d7 d8 d7 d8 da da 80 d7 d6 d6 d6 d6 d6 df 80 df de da da da da da 80 df dd db da da da da 80 da d6";
        String sData19 = "60 80 df df df df df df dd 80 df d8 d8 d8 d8 d8 d8 80 d8 d8 e1 e1 e1 e1 e1 80 e1 e4 f2 dd db dd db 80 db dd de ef d7 d7 d7 80 d7 d7 d8 da e9 d6 d6 80 d6 d6 d6 d6 d6 d9 da 80 da d9 da da da da d4 80 d8 d8 da da da db d9 80 d5 b8";
        String sData20 = "60 80 e6 f7 ff f0 d7 d3 d7 9c dc f7 b9 dd c3 e9 c4 80 c8 d3 e7 c1 87 94 ca 80 e2 e5 e7 f8 d6 85 85 90 ab cb d6 e1 85 f8 b3 c0 a4 8d a4 bc d2 f6 a6 83 b7 a4 b7 b3 c2 d2 ea 87 9a c5 b3 da c9 ce d5 8e e0 82 aa 9e e7 d1 d1 80 d6 b8";
        String sData21 = "60 80 de df dc de df de de 80 df d9 dc db dc dc dd 80 dd de e4 e5 e4 e2 e2 80 e2 e2 e2 e4 e4 e4 e4 80 e4 e6 e6 e6 de e3 e3 80 e6 e6 e7 ea ea d8 db 80 db dd dd de e0 e0 da 80 da da da dc dc dc dd 80 d7 d9 d7 d7 d8 d8 da 80 da 9a";
        String sData22 = "60 80 df df df df df df e1 80 e1 de de de e1 e0 e1 80 e4 e6 e2 e2 e2 e2 e2 80 e2 e2 e2 e7 e7 e9 e9 80 ea eb ec ee ea eb ec 80 ee f1 f1 f6 f8 e0 e1 80 e3 e2 e5 e6 e9 eb dd 80 dd dd e0 e0 e2 e3 e3 80 da da db db db dd dd 80 df c2";
        String sData23 = "60 80 e1 e4 e4 e4 e5 e5 e5 80 e5 e7 ec ec ee f0 f1 80 f2 f2 e2 e4 e2 e4 e2 80 e2 e2 e2 ef f2 f4 f7 c0 f8 f8 f9 f9 f9 ff 80 9f 85 86 87 88 86 ec f1 80 f3 f8 f9 fb fe fe e5 80 e7 ea eb ed f0 ef f1 80 df e0 e0 e2 e3 e5 e4 80 e4 d3";
        String sData24 = "60 80 e5 e5 e3 e4 e3 e0 df 80 de f3 f2 f1 ef ee e8 80 e3 e1 e2 e2 e2 e1 e2 80 e2 e1 e1 f7 f5 f2 ef b0 ea e6 e3 df 86 83 fd 80 f7 f1 e9 e4 de fd fc 80 f9 f3 ed e7 e2 de f1 80 f1 ee eb e8 e3 e0 de 80 e5 e5 e4 e4 e1 df de 80 db cd";
        String sData25 = "60 80 de de de de de dc de 80 de dd dc dc da dc d9 80 da dc e0 e0 de e0 e0 80 de e0 e0 dc da d9 d9 80 d9 d9 d9 d9 da d7 d4 80 d3 d3 d1 d1 d3 db da 80 d8 d6 d5 d4 d5 d5 de 80 de dc db dc db db db 80 db db db d9 d9 d9 d9 80 d9 f4";
        String sData26 = "60 80 dc db dc dc de dc de 80 df d9 d9 d9 da dc da 80 dc dc de de e0 e0 e0 80 e0 e0 e0 d9 d9 d9 da 80 da da da dd d1 d1 d4 80 d4 d4 d4 d4 d5 d5 d5 80 d5 d5 d6 d5 d5 d6 db 80 db db db db db db db 80 d9 d9 d9 d9 d9 d9 d9 80 d9 ca";
        String sData27 = "60 80 de dc dc dc dc de db 80 dc dc da dc da dc dd 80 dc dc de e0 e0 e0 de 80 e0 df df db db db db 80 db db da dc d5 d6 d6 80 d6 d6 d6 d6 d7 d6 d6 80 d6 d6 d7 d7 d6 d7 db 80 db db d9 db db db db 80 d9 d9 d9 da da da da 80 da f8";
        String sData28 = "60 80 de dd df dd dd dd dd 80 dd dd dc dd dd da da 80 db da e1 df e1 df df 80 df df df db db db dc 80 dc da da da d6 d7 d7 80 d6 d6 d6 d6 d6 d6 d7 80 d7 d5 d6 d6 d6 d7 db 80 db dc dc da da da da 80 da d9 da da da da da 80 da 83";
        String sData29 = "60 80 dd dd dd dd df df dd 80 df db dc dc df e1 e2 80 e1 e4 df e1 df e2 e2 80 df dc de db db db db 80 dd db da db d6 d7 d8 80 d8 da d8 d8 da d6 d7 80 d7 da db da da db db 80 db db dd dd de dd de 80 da db db dc de de de 80 df ef";
        String sData30 = "60 80 df df df dd dd dc dd 80 e0 e4 e1 df dd da d8 80 d7 d8 de de de df df 80 df e1 e1 db db db db 80 db db db db db d9 d8 80 d9 d6 d5 d5 d5 db da 80 d8 d8 d7 d5 d5 d5 e0 80 de de dd d9 d9 d9 db 80 df df de dc db db db 80 db bb";
        String sData31 = "60 80 de db d9 db dd dd dd 80 dd d7 d5 d3 d4 d6 d8 80 d6 d9 e1 df df df e2 80 e2 e0 e2 db db db db 80 db db db db d5 d5 d5 80 d5 d5 d6 d6 d6 d5 d5 80 d4 d4 d5 d4 d6 d6 d9 80 d9 d9 d8 d9 d9 d9 d9 80 db d9 d9 d9 d9 d9 d9 80 d9 b0";
        String sData32 = "60 80 db e0 e7 f2 fe fd e5 f0 d1 d6 e7 fb 99 da dd 81 9c cd ee f0 e7 d9 98 81 ff a7 d7 e7 f3 fa ef c2 96 ef 8a b7 df f3 87 81 8b ca ae 9d 8e d6 e3 9e fb 96 bc cb 81 a8 d5 bc db ec 85 bc d3 93 cb f0 d6 d9 e3 f3 a0 b5 8d 80 db a2";


        ArrayList<String> sList = new ArrayList<String>();
        sList.add(sData1);
        sList.add(sData2);
        sList.add(sData3);
        sList.add(sData4);
        sList.add(sData5);
        sList.add(sData6);
        sList.add(sData7);
        sList.add(sData8);
        sList.add(sData9);
        sList.add(sData10);
        sList.add(sData11);
        sList.add(sData12);
        sList.add(sData13);
        sList.add(sData14);
        sList.add(sData15);
        sList.add(sData16);
        sList.add(sData17);
        sList.add(sData18);
        sList.add(sData19);
        sList.add(sData20);
        sList.add(sData21);
        sList.add(sData22);
        sList.add(sData23);
        sList.add(sData24);
        sList.add(sData25);
        sList.add(sData26);
        sList.add(sData27);
        sList.add(sData28);
        sList.add(sData29);
        sList.add(sData30);
        sList.add(sData31);
        sList.add(sData32);


        mSmLists = new ArrayList<ArrayList<Byte>>();
        for (int i = 0; i < sList.size(); i++) {
            String sd = sList.get(i);
            ArrayList list = new ArrayList<Byte>();
            String[] sArray = sd.split(" ");
            for (int j = 0; j < sArray.length; j++) {
                int hex = Integer.valueOf(sArray[j], 16);
                list.add((byte)hex);
            }
            mSmLists.add(list);
        }
    }
	

    int order = 0;
    /**模拟发送仿真数据*/
    TimerTask ttFz = new TimerTask() {
        @Override
        public void run() {
        	//System.out.println("------------order: "+order);
        	//用来储存ECGII解密数据的数组
            byte[] bytes8Ecgii = new byte[8];
            //顺序重复获取模拟数据
            ArrayList list = mSmLists.get(order % mSmLists.size());

            OriginalPacket op = mHotterUtil.handlePacket(list);
            ArrayList<Byte> dlist = op.getData();
            int start = 8;
            for (int i = start; i < 16; i++) {
            	bytes8Ecgii[i-start] = dlist.get(i);   
            	//System.out.println(i+" "+(dlist.get(i)&0xff));
            }
            
            //用来储存由ECGII解密数据构成的数组
            mListBox.add(bytes8Ecgii);
            
           
            //打印
/*            StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < 64; i++) {
    			if(i<10){
    				sb.append("00"+i+" ");
    			}else if(i<100){
    				sb.append("0"+i+" ");
    			}else{
    				sb.append(i+" ");
    			}   						
    		}
    		sb.append("\n");
            for (int i = 0; i < dlist.size(); i++) {
            	int n = (dlist.get(i)&0xff);
    			if(n<10){
    				sb.append("00"+n+" ");
    			}else if(n<100){
    				sb.append("0"+n+" ");
    			}else{
    				sb.append(n+" ");
    			}            	
            }
            sb.append("\n");
            System.out.println(sb.toString());*/
            
            
            order++;
            
            //重置
            if(order==mSmLists.size()){
            	order = 0;
            }
            
            //System.out.println("-------"+new Date());
        }
    };

	
	
	/**
	 * 查找R波
	 * 通过第二导联ECG II波形数据在一定时间内波峰的个数来计算心率
	 * 通过波形数组(包含模块实时状态信息和波形信息)来计算
	 * 前10秒来找出ECG II波形的波峰值
	 * 步骤： 1、得到成包的数据
	 *      2、还原为原始的数据
	 *      3、如果是时间数据保存开始时间
	 *     	4、取到ECG II波形数据，以ECG II 的8个字节数据为一个单位存入List中
	 *      5、有一个线程不断地从List中取出数据(暂时不从List中删除)
	 *      6-1、创建一个有两个属性的对象A，属性1：R波所在字节数组所属List中的位置P2;属性2：R波所
	 *      在字节数组中的位置P1;
	 *      6-2、创建一个只能存储两个对象我的仓库B，如果仓库B已满，即已为size为2，计算心率，之后
	 *      清空步骤5List中仓库B中第2个对象在List中的位置及之前的所有对象，再清空仓库中的第一个对象;
	 *      找到一个R波，将其对象A加入仓库B中，
	 *      6-2、将取出的数据放入查找R波的方法中，如果找到R波，记录下这个R波所对应的在8个ECG II字节
	 *      数组中的位置P1及在List中的位置P2，同理找到下一个R波的位置P3和P4
	 *      计算一个心跳间隔多少字节：
	 *      N = (P4-P2-1)*8+(8-P1)+P3
	 *      每个波形每秒有16包*8个字节的数据 16*8=128，1分钟就有128*60=7680字节
	 *      Rate = 7680/N
	 *      
	 *      下面是查找心电波R位置的方法：
	 *      7、找到最大值，与之前保存的最大值比较，将比较后的新的最大值保存
	 *      8、如果检测到时间数据包，判断从开始到现在是否大于10秒，如果大于则结束测量ECG II波形的波峰值
	 *      9、将最大值的4/5作为参考值
	 *      
	 *      计算心跳次数的方法：
	 *      方法一：
	 *      以6秒为一个时间段，计算出6秒内出现的心跳数，乘10就得出了1分钟的心跳数
	 *      假如：6秒检测到有6个心跳，那么1分钟就有6*10=60个心跳
	 *      假如：6秒检测到有7个心跳，那么1分钟就有7*10=70个心跳
	 *      误差是70-60=10
	 *      方法二：
	 *      计算两个心跳之间有多少包的0x60数据，根据每秒接收16包数据，算出两个心跳
	 *      间隔占多长时间，用60/间隔得出1分钟的心跳数
	 *      假如：两个心跳之间有10包数据，60/(10/16)=96
	 *      假如：两个心跳之间有10包数据，60/(11/16)=87
	 *      误差是96-87=7
	 *      方法三：
	 *      每个波形每秒有16包*8个字节的数据 16*8=128，1分钟就有128*60=7680字节
	 *      (注意：ECG II在一包解密数据中的位置是9~16)
	 *      假如：第一个心跳在第2包数据数据的10位，第二个心跳在第15包数据的第15位，两个心跳
	 *      的间隔为(16-10)+(15-2-1)*8+(15-9)+1=109,7680/109=70
	 *      假如：第一个心跳在第2包数据数据的11位，第二个心跳在第15包数据的第15位，两个心跳
	 *      的间隔为(16-11)+(15-2-1)*8+(15-9)+1=108,7680/108=71
	 *      误差是71-70=1
	 *      
	 *      比较以上3种算法，方法三的误差是最小的，所在选用方法三来计算心率
	 *      
	 *      实现：
	 * @author zealjiang
	 * @date 2016年5月13日 下午2:41:45
	 * @param bytes
	 * @param referenceValue
	 *
	 */
	private void calHearRateNums(byte[] bytes,int referenceValue){

		System.out.println("calHearRateNums ------开始-----");
/*		mListBox.add(bytes);
		int packetPoi = mListBox.size()-1;*/
		
		if(mThePacketId==-1){
			
		}else{
			mThePacketId++;
		}

		/**
		 * 假定8个字节的数据最多只有一个R波
		 * 此假设一会要改
		 */
		for (int i = 0; i < bytes.length; i++) {
			int b1 = bytes[i] & 0xFF;
			System.out.println("b1: "+b1);
/*			System.out.println("mTempMax: "+mTempMax);
			System.out.println("mLast: "+mLast);
			System.out.println("mMax: "+mMax);*/
			
			//情况一：只有max点大于参考值
			if(b1>=referenceValue){
				if(mFirst==null){
					//已经找出了第一个符合心跳的值
					mFirst = new PosVal();
					mFirst.setBytePoi(i);
					mFirst.setValue(b1);
					
					mLast = new PosVal();
					mLast.setBytePoi(i);
					mLast.setValue(b1);
					
					mTempMax = new PosVal();
					//mTempMax.setPacketPoi(mThePacketId);
					mTempMax.setBytePoi(i);
					mTempMax.setValue(b1);
					continue;
				}
			}else{
				if(mTempMax!=null){
					mMax = mTempMax;	
					//将最大点添加到PosValList中
					addMaxToPosValList();					
					//重新开始
					mLast = null;
					mFirst = null;
					mMax = null;
					mSecond = null;
					mTempMax = null;
					continue;
				}
			}
			
			
			//已经找出了第一个符合心跳的值
			if(mLast!=null){
				if(mMax==null){
					//说明还没有找到最大值，还在爬山
					if(b1>mLast.value){
						//正在爬
						mTempMax = new PosVal();
						//mTempMax.setPacketPoi(packetPoi);
						mTempMax.setBytePoi(i);
						mTempMax.setValue(b1);

						mLast = new PosVal();
						mLast.setBytePoi(i);
						mLast.setValue(b1);
						continue;
					}else{
						if(mTempMax!=null){
							/*
							 * 找到了最大点，
							 * 此处不添加到仓库mPosValList中的原因：
							 * 是因为在情况一中已经做过处理，即如果是最大点已经将其
							 * 添加到了mPosValList中
							 * 这个流程是一个点顺序走下来的
							 */
							mMax = mTempMax;
							mLast.setBytePoi(i);
							mLast.setValue(b1);
							
							//情况一：只有max点大于参考值
							if(b1>=referenceValue){

							}else{
								//重新开始
								mLast = null;
								mFirst = null;
								mMax = null;
								mSecond = null;
								mTempMax = null;
								continue;						
							}
							continue;
						}else{
							System.out.println("未找到最大值前，第"+i+"个点"+b1+"出现了小于"
								+ "它前面点的情况");
							break;
						}				
					}
				}else{
					//说明已经找到最大值，正在下山
					if(b1<mLast.value){
						if(b1>referenceValue){
							//继续下山
							mLast.setBytePoi(i);
							mLast.setValue(b1);
						}else{
							//重新开始
							mLast = null;
							mFirst = null;
							mMax = null;
							mSecond = null;
							mTempMax = null;
							continue;	
						}
					}
				}
				
			}
		}
		
/*		for (int j = 0; j < mPosValList.size(); j++) {
			System.out.println("位置： "+mPosValList.get(j).bytePoi+
					" 值："+(mPosValList.get(j).getValue() & 0xFF));
		}*/
		System.out.println("calHearRateNums------------结束------");
	}
	
	/**
	 * 当存储R波的仓库满时要做的处理
	 * @author zealjiang
	 * @date 2016年5月13日 下午3:19:04
	 *
	 */
	private void addMaxToPosValList(){
		/**
		 * 如果仓库mPosValList已满，即已为size为2，计算心率，之后
		 * 清空mListBox中仓库mPosValList中第2个对象在mListBox中的
		 * 位置及此位置之前的所有对象，
		 * 再清空仓库mPosValList的第一个对象;
		 */
		if(mPosValList.size()==0){
			System.out.println("找到第一个最大值的点: "+mMax.getValue()+"  位置: "+mThePacketId);
			if(mThePacketId==-1){
				mThePacketId = 0;
			}			
			mMax.setPacketPoi(mThePacketId);
			//添加到仓库mPosValList中
			mPosValList.add(mMax);	
		}else if(mPosValList.size()==1){
			System.out.println("找到第二个最大值的点: "+mMax.getValue()+"  位置: "+mThePacketId);			
			mMax.setPacketPoi(mThePacketId);
			//添加到仓库mPosValList中
			mPosValList.add(mMax);
			
			//判断mPosValList仓库是否已满，已满则处理,不满这个方法什么也不执行
			handlePosValListFull();
		}
	}
	
	/**
	 * 当存储R波的仓库满时要做的处理
	 * @author zealjiang
	 * @date 2016年5月13日 下午3:19:04
	 *
	 */
	private void handlePosValListFull(){
		/**
		 * 如果仓库mPosValList已满，即已为size为2，计算心率，之后
		 * 清空mListBox中仓库mPosValList中第2个对象在mListBox中的
		 * 位置及此位置之前的所有对象，
		 * 再清空仓库mPosValList的第一个对象;
		 */
		if(mPosValList.size()==2){
			System.out.println("mPosValList.get(0): "+mPosValList.get(0).getValue());
			System.out.println("mPosValList.get(1): "+mPosValList.get(1).getValue());
			//计算心率
			int rate = calHearRate(mPosValList.get(0),mPosValList.get(1));
			//将心率传递出去
			setHearRate(rate);
			//清空mListBox中仓库mPosValList中第2个对象在mListBox中的位置及此位置之前的所有对象
			//int ppos = mPosValList.get(1).getPacketPoi();
					
			//deleteThePosBefore(mListBox,ppos);		
			//清空仓库mPosValList
			mPosValList.remove(0);
			//重新将仓库中的波峰点包id设置为0
			mThePacketId = 0;
			mPosValList.get(0).setPacketPoi(mThePacketId);
		}
	}
	
	
	/**
	 * 计算心率
	 * @author zealjiang
	 * @date 2016年5月13日 下午2:19:43
	 * @param pvFirst 两个相邻的R波峰中前一个R波对象
	 * @param pvSecond 两个相邻的R波峰中后一个R波对象
	 * @return 返回计算出的心率
	 *
	 */
	private int calHearRate(PosVal pvFirst,PosVal pvSecond){
		if(null==pvFirst){
			System.out.println("calHearRate()  pvFirst为null");
			return -1;
		}
		if(null==pvSecond){
			System.out.println("calHearRate()  pvSecond为null");
			return -1;
		}
		/**
		 * 	取出pvFirst的bytePoi值，即这个R波所对应的在8个ECG II字节
		 *  数组中的位置P1以及packetPoi值，即在List中的位置P2，
		 *  同理找到pvSecond中的bytePoi P3和和packetPoi P4
		 *  计算一个心跳间隔多少字节：
		 *  N = (P4-P2-1)*8+(8-P1)+P3
		 *  每个波形每秒有16包*8个字节的数据 16*8=128，1分钟就有128*60=7680字节
		 *  Rate = 7680/N
		 */
		
		int N = (pvSecond.packetPoi-pvFirst.packetPoi)*8+(8-pvFirst.bytePoi)+pvSecond.bytePoi;
		int rate = 8*16*60/N;
		return rate;
	}
	
	/**
     * 执行计算心率的线程
     *
     * @author zealjiang
     *         created at 2016/3/29 15:33
     */
    class RateThread extends Thread {

    	boolean isRunning;
        public RateThread() {

            isRunning = false;

        }
        
        public void setRunning(boolean isRunning){
        	this.isRunning = isRunning;
        }


        @Override
        public void run() {

            /**
             * by zealjiang @2016/5/3 17:54
             * 经实际测试发现，使用mHolterDraw.draw(mOp,surfaceHolder);方法是一个耗时的操作，大概在20ms左右
             * 如果mOp数据是连续发过来的，两个mOp发送过来的时间短于20ms，就会出现丢包的现象（mHolterDraw.draw还在绘上
             * 一次发来的数据，又发来了新的数据，后面又来了新的数据，这时就出现了数据还没来的及绘制，就被覆盖掉了）
             *
             * 解决方法：准备一个盒子，把发来的数据先放到盒子里，然后从盒子里取数据来绘,绘完一组数据，再从盒子里取下一组
             * 数据
             * 注意：每来一页新的数据都要先清空盒子
             */
            while (isRunning) {
                try {
 
                    if(mListBox.size()!=0){
                        /**
                         * 如果盒子不为空，工作员从盒子里取出数据来处理，工作员每取出一个数据都要进行处理，处理完后如果盒子不为空
                         * 再从盒子里取下一个数据来处理，直到盒子里数据为空时停止
                         */
                        byte[] bytes = mListBox.get(0);
                        mListBox.remove(0);
                        calHearRateNums(bytes,mReferenceValue);
                        
                        //用于测试4，保存一段连续的波形值
                        if(listInt.size()<16*8*20){
                        	for (int j = 0; j < bytes.length; j++) {
                        		int b1 = bytes[j] & 0xFF;
                        		listInt.add(b1);
							}            	
                        }else{
                        	//运行测试4
                        	System.out.println("------运行测试4-----");
                    		getRValue(listInt,96);
                    		
                    		System.out.println();
                    		for (int i = 0; i < listR.size(); i++) {
                    			System.out.print(listR.get(i)+",");
                    		}
                    		System.out.println();
                    		for (int i = 0; i < listQRSs.size(); i++) {
                    			System.out.print(listQRSs.get(i)+",");
                    		}
                    		listInt.clear();
                        }
                        
                    }else{
                        Thread.sleep(20);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }

        }


    }
	
    
    /**
     * R波对象信息
     * 
	 * 创建一个有三个属性的对象A，属性1：R波所在字节数组所属List中的位置P2;属性2：R波所
	 * 在字节数组中的位置P1;属性3：R波的值    
     * @author zealjiang
     * @time 2016年5月13日上午10:51:22
     */
	class PosVal{
		/**当前对象值在字节数组中的位置*/
		private int bytePoi;
		/**字节数组在List中的位置*/
		private int packetPoi;
		/**R波对应的值*/
		private int value;
		
		public int getBytePoi() {
			return bytePoi;
		}
		public void setBytePoi(int bytePoi) {
			this.bytePoi = bytePoi;
		}
		public int getPacketPoi() {
			return packetPoi;
		}
		public void setPacketPoi(int packetPoi) {
			this.packetPoi = packetPoi;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}	
	}
	
	/**
	 * 删除list中指定位置之前的所有数据(位置从0开始)
	 * @author zealjiang
	 * @date 2016年5月13日 上午11:02:05
	 * @param list
	 * @param pos
	 * @return
	 *
	 */
	public boolean deleteThePosBefore(ArrayList list,int pos){
		if(list==null){
			System.out.println("deleteThePosBefore(): 传入的list为null");
			return false;
		}
		if(pos<1){
			System.out.println("deleteThePosBefore(): pos<1");
			return false;
		}
		int size = list.size();
		if(pos>=size){
			System.out.println("deleteThePosBefore(): pos>=size");
			return false;
		}
		
		//删除第pos个之前的所有数据(位置从0开始)
		for (int i = 0; i < pos; i++) {
			list.remove(0);
		}	
		//System.out.println("deleteThePosBefore() 成功");
		return true;
	}
	
	/**
	 * 当有新的心率产生时，此方法会被调用
	 * @author zealjiang
	 * @date 2016年5月13日 下午2:38:50
	 * @param rate 心率
	 *
	 */
	private void setHearRate(int rate){
		System.out.println("心率为："+rate);
	}
	
	/**
	 * 计算参考值
	 * 下面是从正常的心电波形中获取的ECG II数据，进行解密后的数据
	 * 序号     000 001 002 003 004 005 006 007 008 009 010 011 012 013 014 015 016 017 018 019 020 021 022 023 024 025 026 027 028 029 030 031 032 033 034 035 036 037 038 039 040 041 042 043 044 045 046 047 048 049 050 051 052 053 054 055 056 057 058 059 060 061 062 063 
		    128 128 128 128 128 128 128 128 121 120 115 117 108 105 100 092 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 094 086 088 086 083 088 083 086 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 088 085 085 090 087 090 090 087 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 092 087 091 090 088 093 090 091 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 093 090 094 090 093 093 090 093 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 088 090 090 088 091 087 088 088 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 085 090 085 088 088 085 090 085 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 093 093 091 094 099 093 093 091 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 086 087 082 084 086 081 087 081 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 086 084 082 087 081 106 155 192 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 158 081 065 072 083 092 088 092 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 092 089 094 089 094 094 091 097 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 094 097 098 097 105 102 110 111 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 108 110 115 110 115 115 110 115 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 107 106 102 095 098 090 092 092 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 087 092 087 091 091 088 094 090 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 
			128 128 128 128 128 128 128 128 093 094 091 097 091 095 096 093 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 128 


		再来一组从正常心电波中解密数据中提取出来的ECGII的数据：
		从解密数据中提取出ECGII的数据
		序号  008 009 010 011 012 013 014 015
			
			092 092 092 092 092 090 092 093
			092 093 118 169 199 146 083 072
			084 092 096 098 101 099 099 099
			099 103 103 104 106 105 108 108
			109 112 112 116 116 117 119 116
			118 118 117 117 114 111 106 105
			103 100 099 095 095 095 094 097
			095 094 096 096 096 096 096 096
			098 098 099 098 099 098 098 098
			096 098 098 097 099 097 099 097
			102 100 102 104 102 102 099 098
			096 094 094 093 093 095 093 093
			095 095 093 095 092 107 150 194
			173 102 071 078 090 096 096 098
			095 097 098 098 098 101 101 104
			104 106 108 108 111 113 115 116
	 * 通过观察以及联系波形的基准线0x60,可知，在静息状态下，心电波在基线附近正负10上下波动即(96+10与96-10)之间，
	 * P段在基线上方(0~10之间以一个驼峰桥的图形样式波动)，T段在基线上方(0~30之间以一个驼峰桥的图形样式波动)，
	 * QRS段，QR为一直上升波形，RS段为一直下降波形，其中Q点在基线处附近，S段在基线下方大约-30处,
	 * Q处向前3个点应该处在基线位置附近，S处向后4个点也应该处在基线位置附近,R点
	 * 
	 * @param bytes 要分析的字节数组
	 * @param base 基线的值
	 * @author zealjiang
	 * @date 2016年5月16日 下午7:28:53
	 *QRS 段
	 */
	
	/**存储一段byte数据，分析是否符合QRS*/
	private ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
	/**存储R值的数组*/
	private ArrayList<Integer> mRPointsList = new ArrayList<Integer>();
	/**上一次点的纵坐标*/
	int lastVal = 0;
	int mBase = 0x60&0xff;
	/**可认为符合QRS段的最小高度*/
	int mBaseQRSHeight = mBase+40;
	private void calReferencePv(byte[] bytes){
		for (int i = 0; i < bytes.length; i++) {
			int b1 = bytes[i] & 0xFF;
			
			
			if(lastVal==0){	
				//还没有出现第一次高于基线波动正常范围的值
				if(b1-mBase>10){
					tempArrayList.add(b1);
					lastVal = b1;
				}
			}else{
				//已经出现第一次高于基线波动正常范围的值
				if(b1>lastVal){
					tempArrayList.add(b1);
					lastVal = b1;
				}else{
					//判断是否是QRS波
					if(lastVal>mBaseQRSHeight){
						
					}
				}
			}	

			lastVal = b1;
		}
	}
	
	/**
	 * 通过找出出现次数最多的数来找出正确的R值
	 * 目的：从一系列R值里找出在正负10的范围内出现次数最多的那个数，即要找的R值
	 * 写这个方法的原因是：由于杂波和干扰的影响，在R波中会出现一些非正常的R值，
	 * 只能剔除掉这些非正常的R值，才能找出正常的R值，从正常的R值来计算出一个准确的
	 * 参考值
	 * 实现思路：
	 * 方法一：
	 * 假设有20个R值的list，这20个R值里存在非正确的R值(即有干扰的R波值)
	 * 将这20个R值从小到大做个排序，取出排序后中间位置的那个R值，即为正常的R值
	 * 
	 * 方法二：
	 * 假设有20个R值的list1，这20个R值里存在非正确的R值(即有干扰的R波值)，
	 * 创建一个与R值list1长度相同的list2,用来保存每个R值出现的次数，次数用Num表示
	 * 最初Num=0
	 * 步骤1：取出第一个R值
	 * 步骤2：依次遍历数组中所有的R值，如果 R-10<R'<R+10，就将Num+1,
	 * 遍历完成后将Num放入list2中，将Num重新置为0，
	 * 再取出第二个值，重复步骤2
	 * 找出list2中最大的那个数的位置，从list1中取出此位置的R值，即为在正负10的范围内
	 * 出现次数最多的那个数
	 * @author zealjiang
	 * @date 2016年5月19日 下午2:10:52
	 * @param list R值list
	 * @return 在正负10的范围内出现次数最多的那个数
	 *
	 */
	private int getRValueByFrequency(ArrayList<Integer> list){
		int range = 10;
		ArrayList<Integer> freqList = new ArrayList<Integer>();
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			int r = list.get(i);
			for (int j = 0; j < list.size(); j++) {
				int rCompare = list.get(j);
				if(rCompare<r+range&&rCompare>r-range){
					num++;
				}
			}
			freqList.add(num);
			num = 0;
		}
		//找出freqList中最大的那个数的位置
		int max = 0;int maxPos = 0;
		for (int i = 0; i < freqList.size(); i++) {
			int value = freqList.get(i);
			if(value>max){
				max = value;
				maxPos = i;
			}
			//System.out.println(list.get(i)+" 出现次数："+value);
		}
		
		return list.get(maxPos);
	}
	
	/**
	 * 通过排序来找出正确的R值
	 * 目的：从一系列R值里找出在正负10的范围内出现次数最多的那个数，即要找的R值
	 * 写这个方法的原因是：由于杂波和干扰的影响，在R波中会出现一些非正常的R值，
	 * 只能剔除掉这些非正常的R值，才能找出正常的R值，从正常的R值来计算出一个准确的
	 * 参考值
	 * 实现思路：
	 * 方法一：
	 * 假设有20个R值的list，这20个R值里存在非正确的R值(即有干扰的R波值)
	 * 将这20个R值从小到大做个排序，取出排序后中间位置的那个R值，即为正常的R值
	 * @author zealjiang
	 * @date 2016年5月19日 下午2:49:34
	 * @param list R值list
	 * @return
	 *
	 */
	private int getRValueByOrder(ArrayList<Integer> list){
		int[] rArray = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			rArray[i] = list.get(i);
		}
		QuickSort.quickSort(rArray);
		return rArray[list.size()/2];
	}
	
	

	/**
	 * 查找所有的波峰R点的值，无论是干扰的还是正常的
	 * 思路：创建一个List,记录下所有的找到的R点；再创建一个List
	 * 记录下一段要分析的数据
	 * 下面是一个正常的心电图的大概样子
	 *          *
	 * 		   * *
	 * 		  *   *	  
	 * *******     * ****
	 *              *
	 * 分析：从正常的两组心电图中取出的ECG II的QRS段数据：(单位：十进制)
	 * 第一组：97,102,116,128,136,121,96,79,76,82,88,93,96;
	 * 第二组：93,118,169,199,146,083,072,084,92,96;
	 * 找到的规律:
	 * 1、由于我们采用的基线值为96,所以Q点的数据值在96附近(假定为±5);
	 * 2、连续上升的点或连续下降的点，两连续点差值的绝对值大于等于3
	 * 3、S点的值比基线值要小，也比Q点要小
	 * 4、连续上升点的个数至少为3，连续下降点的个数也至少为3
	 * 成人正常心率为60～100次/分钟，那么1秒跳1到1.6下，我们假定20
	 * 个心跳里面存在正常的心跳，那么就分析20秒的数据。1秒的数据有16*8=128个
	 * 字节，20秒就有128*20=2560字节的数据。分析这20秒的数据，如果找出的R点
	 * 不足20个，假如有N个，20-N>10,就再取2560字节数据，如果20-N<10,就再取
	 * 2560/2字节的数据分析(根据实际需要取相应的数据量)
	 * @author zealjiang
	 * @date 2016年5月23日 下午5:23:23
	 *
	 */
	private void getRValue(ArrayList<Integer> listData,int baseValue){
		//用来存储一个QRS波，用于观察
		ArrayList<Integer> listQRS = new ArrayList<Integer>();
		Point lastP = new Point();
		Point qPoint = new Point();
		Point rPoint = new Point();
		Point sPoint = new Point();
		final int BaseOffset = 5;
		
		while(listData.size()>0){
			//分析一个QRS波的起始点，如果没有找到start为-1，找到为其它值
			int start = -1;
			//重新开始查找QRS
			lastP.clear();
			qPoint.clear();
			rPoint.clear();
			sPoint.clear();
			
			if(listQRS.size()>=5){
				System.out.println();
				for (int j = 0; j < listQRS.size(); j++) {
					int v = listQRS.get(j);
					listQRSs.add(v);
					System.out.print(v+",");
				}
				listQRSs.add(0);
			}
			listQRS.clear();
			
			for (int i = 0; i < listData.size(); i++) {
				int value = listData.get(i);
				//第一步：找到一个基线值的点作为开始点
				if(start==-1){
					//我们找到第一个基线附近的点做为开始点
					if(Math.abs(value-baseValue)<BaseOffset){
						start = 0;
					}else{
						lastP.value = value;
						lastP.pos = i;
						/**
						 * 假设listData中有两个数，当前删除的是第一个值即i=0位置的数，
						 * 所以要用i+1传入deleteThePosBefore(),
						 * 如果listData中有1个数，要删除的是第一个值即i=0位置的数
						 * 就无法用deleteThePosBefore()方法了
						 */
						
						if(listData.size()>1){
							deleteThePosBefore(listData,i+1);
						}else{
							listData.clear();
						}
						break;
					}
				}
				//如果为这个方法进来后的第一个点，因为心率点的值不可能为0
				if(lastP.value == 0){
					lastP.value = value;
					lastP.pos = i;
					//如果到了最后一个点，就将listData清空,退出while循环
					if(i==listData.size()-1){
						listData.clear();
					}
					continue;
				}
				//如果还没出现R头，说明是上升阶段
				if(rPoint.value==0){
					//连续上升的点两连续点差值大于等于3
					if(value-lastP.value<3&&listQRS.size()<3){
						lastP.value = value;
						lastP.pos = i;
						deleteThePosBefore(listData,i);
						break;
					}else if(value-lastP.value<0&&listQRS.size()>=3){//出现下降点或QRS点的个数大于3
						//假设找到了R点
						rPoint.value = lastP.value;
						rPoint.pos = lastP.pos;
					}
				}else{//下降阶段
					//连续下降的点两连续点差值大于等于3
					if(lastP.value-value<3&&listQRS.size()<3){
						lastP.value = value;
						lastP.pos = i;
						deleteThePosBefore(listData,i);
						break;
					}else if(value - lastP.value>0&&listQRS.size()>3){
						//S点的值比基线值要小，也比Q点要小
						if(lastP.value<baseValue&&lastP.value<qPoint.value){
							//找到了S点
							sPoint.value = lastP.value;
							sPoint.pos = lastP.pos;
						}
					}
				}

				//如果还没有出现Q点
				if(qPoint.value==0){
					//假设找到了S点，如果接下来的波形不符号QRS波形的特点，此点就不是Q点
					qPoint.value = lastP.value;
					qPoint.pos = lastP.pos;
					listQRS.clear();
					listQRS.add(lastP.value);
				}
				
				//如果出现S点,结束本次QRS波的查找
				if(sPoint.value!=0){
					listR.add(rPoint.value);							
					deleteThePosBefore(listData,i);
					break;
				}

				//将符合QRS的点暂存起来
				listQRS.add(value);
							
				lastP.value = value;
				lastP.pos = i;				
			}
		}

	}
	
	/**
	 * 一个点(包含它在list中的位置信息和自身的值信息)
	 * @author zealjiang
	 * @time 2016年5月24日上午10:44:25
	 */
	class Point{
		/**位置*/
		private int pos;
		/**值*/
		private int value;
		/**清空*/
		public void clear(){
			this.pos = -1;
			this.value = 0;
		}
	}
	
	private boolean mIsRun = true;
	private boolean mIsReceivingData = false;
	Thread readFileThread = new Thread(){
		public void run() {
			FileInputStream fis = null;
	        try {

	            fis = new FileInputStream(new File("C:/zealjiang/私人云健康医生/"
	            		+ "存的12导心电文件/李志江13048118612569654_20160422103234.YE"));

	            while (mIsRun) {
	            	mIsReceivingData = true;
	                int count = 0;
	                byte[] buffer = new byte[150];

	                while ((count = fis.read(buffer)) != -1) {

	                    mHotterUtil.createPacket(buffer, count);

	                }
	                mIsRun = false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            mIsReceivingData = false;
	        } finally {
	        	mIsReceivingData = false;
	            try {
	                if(fis!=null){
	                	fis.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			
		};
	};

	@Override
	public void decryptData(ArrayList<Byte> list) {
    	//用来储存ECGII解密数据的数组
        byte[] bytes8Ecgii = new byte[8];

        int start = 8;
        for (int i = start; i < 16; i++) {
        	bytes8Ecgii[i-start] = list.get(i);   
        }
        
        //用来储存由ECGII解密数据构成的数组
        //mListBox.add(bytes8Ecgii);
        
       
        //打印
        StringBuilder sb = new StringBuilder();  
		for (int i = 0; i < 64; i++) {
			if(i<10){
				sb.append("00"+i+" ");
			}else if(i<100){
				sb.append("0"+i+" ");
			}else{
				sb.append(i+" ");
			}   						
		}
		sb.append("\n");
        for (int i = 0; i < list.size(); i++) {
        	int n = (list.get(i)&0xff);
			if(n<10){
				sb.append("00"+n+" ");
			}else if(n<100){
				sb.append("0"+n+" ");
			}else{
				sb.append(n+" ");
			}            	
        }
        System.out.println(sb.toString());
        
        sb.delete(0, sb.length());
        for (int i = 0; i < 8*3+8; i++) {
        	sb.append(" ");
		}
        
		for (int i = 0; i < 8; i++) {
			int n = (bytes8Ecgii[i]&0xff);
			if(n<10){
				sb.append("00"+n+" ");
			}else if(n<100){
				sb.append("0"+n+" ");
			}else{
				sb.append(n+" ");
			}   						
		}
		System.out.println(sb.toString());
		sb.append("\n");
		
	}
}
