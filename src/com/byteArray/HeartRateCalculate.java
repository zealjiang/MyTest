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
 * ͨ���ڶ�����ECG II����������һ��ʱ���ڲ���ĸ�������������
 * ͨ����������(����ģ��ʵʱ״̬��Ϣ�Ͳ�����Ϣ)������
 * ǰ10�����ҳ�ECG II���εĲ���ֵ
 * ���裺 1���õ��ɰ�������
 *      2����ԭΪԭʼ������
 *      3�������ʱ�����ݱ��濪ʼʱ��
 *     	4��ȡ��ECG II�������ݣ���ECG II ��8���ֽ�����Ϊһ����λ����List��
 *      5����һ���̲߳��ϵش�List��ȡ������(��ʱ����List��ɾ��)
 *      6-1������һ�����������ԵĶ���A������1��R�������ֽ���������List�е�λ��P2;����2��R����
 *      ���ֽ������е�λ��P1;
 *      6-2������һ��ֻ�ܴ洢���������ҵĲֿ�B������ֿ�B����������ΪsizeΪ2���������ʣ�֮��
 *      ��ղ���5List�вֿ�B�е�2��������List�е�λ�ü�֮ǰ�����ж�������ղֿ��еĵ�һ������;
 *      �ҵ�һ��R�����������A����ֿ�B�У�
 *      6-2����ȡ�������ݷ������R���ķ����У�����ҵ�R������¼�����R������Ӧ����8��ECG II�ֽ�
 *      �����е�λ��P1����List�е�λ��P2��ͬ���ҵ���һ��R����λ��P3��P4
 *      ����һ��������������ֽڣ�
 *      N = (P4-P2-1)*8+(8-P1)+P3
 *      ÿ������ÿ����16��*8���ֽڵ����� 16*8=128��1���Ӿ���128*60=7680�ֽ�
 *      Rate = 7680/N
 *      
 *      �����ǲ����ĵ粨Rλ�õķ�����
 *      7���ҵ����ֵ����֮ǰ��������ֵ�Ƚϣ����ȽϺ���µ����ֵ����
 *      8�������⵽ʱ�����ݰ����жϴӿ�ʼ�������Ƿ����10�룬����������������ECG II���εĲ���ֵ
 *      9�������ֵ��4/5��Ϊ�ο�ֵ
 *      
 *      �������������ķ�����
 *      ����һ��
 *      ��6��Ϊһ��ʱ��Σ������6���ڳ��ֵ�����������10�͵ó���1���ӵ�������
 *      ���磺6���⵽��6����������ô1���Ӿ���6*10=60������
 *      ���磺6���⵽��7����������ô1���Ӿ���7*10=70������
 *      �����70-60=10
 *      ��������
 *      ������������֮���ж��ٰ���0x60���ݣ�����ÿ�����16�����ݣ������������
 *      ���ռ�೤ʱ�䣬��60/����ó�1���ӵ�������
 *      ���磺��������֮����10�����ݣ�60/(10/16)=96
 *      ���磺��������֮����10�����ݣ�60/(11/16)=87
 *      �����96-87=7
 *      ��������
 *      ÿ������ÿ����16��*8���ֽڵ����� 16*8=128��1���Ӿ���128*60=7680�ֽ�
 *      (ע�⣺ECG II��һ�����������е�λ����9~16)
 *      ���磺��һ�������ڵ�2���������ݵ�10λ���ڶ��������ڵ�15�����ݵĵ�15λ����������
 *      �ļ��Ϊ(16-10)+(15-2-1)*8+(15-9)+1=109,7680/109=70
 *      ���磺��һ�������ڵ�2���������ݵ�11λ���ڶ��������ڵ�15�����ݵĵ�15λ����������
 *      �ļ��Ϊ(16-11)+(15-2-1)*8+(15-9)+1=108,7680/108=71
 *      �����71-70=1
 *      
 *      �Ƚ�����3���㷨�����������������С�ģ�����ѡ�÷���������������
 *      
 * @author zealjiang
 * @time 2016��5��13������2:54:12
 */
public class HeartRateCalculate implements DecryptPacketListener{
	

    /**���ڵ��ڲο�ֵ�ĵ�������뵱ǰ���ҵĵ��֮ǰ�ĵ�*/
	private PosVal mLast = null;
	/**��һ���ҵ��Ĵ��ڲο�ֵ�ĵ�*/
	private PosVal mFirst = null;
	/**R����*/
	private PosVal mMax = null;
	private PosVal mSecond = null;
	private PosVal mTempMax = null;
	private PosVal referencePv1 = new PosVal();
	private PosVal referencePv2 = new PosVal();
	private PosVal maxPv = new PosVal();
	/**����һ��ֻ�ܴ洢���������ҵĲֿ�*/
	private ArrayList<PosVal> mPosValList = new ArrayList<PosVal>();
	/**�ο�����ֵ,�������ֵ���ڵ��ڲο�ֵ������Ϊ������*/
	private int mReferenceValue = 106;
	/**��ǰ���ڲ������ʵİ���λ��
	 * ˼·��mPosValListΪ��ʱ����ʾһ�����廹û�ҵ���mThePacketIdΪ-1�����ҵ��˵�һ��
	 * ����ʱmThePacketId=0��mPosValList[0]�е�һ������İ�IDҲΪ0��
	 * �˺�ʼ��ÿ��һ�°����ݱ�������Ҳ���mThePacketId++��ֱ��
	 * �ҵ��ڶ�������ʱ����mPosValList[1]�İ�ID����ΪmThePacketId�����������ʺ�
	 * ��mPosValList[0]ɾ������mPosValList[1]�İ�ID����Ϊ0ͬʱ��mThePacketId����Ϊ0��
	 */
	private int mThePacketId = -1;
	
	/**����ģ�ⷢ������*/
	private Timer timer;
	
    /**��������һ����������ŷ����Ĳ������� by zealjiang @2016/5/12 17:51*/
    private ArrayList<byte[]> mListBox = new ArrayList<byte[]>();
    private HotterUtil mHotterUtil = new HotterUtil();
    
    /**
     * �����洢Rֵ��list,���ȳ���20���ҳ���ȷ��R��ֵ��������ο�����ֵ
     *  by zealjiang @2016/5/19 15:38
     */
    private ArrayList<byte[]> mListRBox = new ArrayList<byte[]>();
    
    
	//�����洢һ��������Ҫ�����Ĳ�ֵ
	ArrayList<Integer> listInt = new ArrayList<Integer>();
	//�����洢һ��������Ҫ�����Ĳ�ֵ�ĳ���
	private int mAnalysis = 0;
	//�����洢R��
	ArrayList<Integer> listR = new ArrayList<Integer>();
	//�����洢���е�QRS�㣬ÿ��QRS��֮ǰ��0�ָ�
	ArrayList<Integer> listQRSs = new ArrayList<Integer>();
	
	StringBuffer sb = new StringBuffer();
		
	public static void main(String[] args) {

		HeartRateCalculate hrc = new HeartRateCalculate();
		//һ�������������
		//hrc.test();
		
/*		//����2
        //��ʮ����Holter�������ݲ���
		//����ģ������
		hrc.simulateSend();
		//����ģ������
		hrc.startSend();
		//�����������ʹ���
		hrc.start();*/
		
/*		//����3
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
		
/*		//����4
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
		
		//���Դ��ļ��ж�����������
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
		//���Ҳ�
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
		//����
		bytes = new byte[]{(byte)160,(byte)197,(byte)205,(byte)205,(byte)196};
		calHearRateNums(bytes,200);
	}
	
	private void startSend(){
		timer = new Timer();
		//ÿ��62.5���뷢��һ�����ݣ�һ��������16�����ݣ����Ǻ���Ҫ����������ֵ
		timer.schedule(ttFz, 1000, 62);
	}
	
	//������������б�
    ArrayList<ArrayList<Byte>> mSmLists;
    /**
     *���ɷ������ݲ���ӽ�mList��
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
    /**ģ�ⷢ�ͷ�������*/
    TimerTask ttFz = new TimerTask() {
        @Override
        public void run() {
        	//System.out.println("------------order: "+order);
        	//��������ECGII�������ݵ�����
            byte[] bytes8Ecgii = new byte[8];
            //˳���ظ���ȡģ������
            ArrayList list = mSmLists.get(order % mSmLists.size());

            OriginalPacket op = mHotterUtil.handlePacket(list);
            ArrayList<Byte> dlist = op.getData();
            int start = 8;
            for (int i = start; i < 16; i++) {
            	bytes8Ecgii[i-start] = dlist.get(i);   
            	//System.out.println(i+" "+(dlist.get(i)&0xff));
            }
            
            //����������ECGII�������ݹ��ɵ�����
            mListBox.add(bytes8Ecgii);
            
           
            //��ӡ
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
            
            //����
            if(order==mSmLists.size()){
            	order = 0;
            }
            
            //System.out.println("-------"+new Date());
        }
    };

	
	
	/**
	 * ����R��
	 * ͨ���ڶ�����ECG II����������һ��ʱ���ڲ���ĸ�������������
	 * ͨ����������(����ģ��ʵʱ״̬��Ϣ�Ͳ�����Ϣ)������
	 * ǰ10�����ҳ�ECG II���εĲ���ֵ
	 * ���裺 1���õ��ɰ�������
	 *      2����ԭΪԭʼ������
	 *      3�������ʱ�����ݱ��濪ʼʱ��
	 *     	4��ȡ��ECG II�������ݣ���ECG II ��8���ֽ�����Ϊһ����λ����List��
	 *      5����һ���̲߳��ϵش�List��ȡ������(��ʱ����List��ɾ��)
	 *      6-1������һ�����������ԵĶ���A������1��R�������ֽ���������List�е�λ��P2;����2��R����
	 *      ���ֽ������е�λ��P1;
	 *      6-2������һ��ֻ�ܴ洢���������ҵĲֿ�B������ֿ�B����������ΪsizeΪ2���������ʣ�֮��
	 *      ��ղ���5List�вֿ�B�е�2��������List�е�λ�ü�֮ǰ�����ж�������ղֿ��еĵ�һ������;
	 *      �ҵ�һ��R�����������A����ֿ�B�У�
	 *      6-2����ȡ�������ݷ������R���ķ����У�����ҵ�R������¼�����R������Ӧ����8��ECG II�ֽ�
	 *      �����е�λ��P1����List�е�λ��P2��ͬ���ҵ���һ��R����λ��P3��P4
	 *      ����һ��������������ֽڣ�
	 *      N = (P4-P2-1)*8+(8-P1)+P3
	 *      ÿ������ÿ����16��*8���ֽڵ����� 16*8=128��1���Ӿ���128*60=7680�ֽ�
	 *      Rate = 7680/N
	 *      
	 *      �����ǲ����ĵ粨Rλ�õķ�����
	 *      7���ҵ����ֵ����֮ǰ��������ֵ�Ƚϣ����ȽϺ���µ����ֵ����
	 *      8�������⵽ʱ�����ݰ����жϴӿ�ʼ�������Ƿ����10�룬����������������ECG II���εĲ���ֵ
	 *      9�������ֵ��4/5��Ϊ�ο�ֵ
	 *      
	 *      �������������ķ�����
	 *      ����һ��
	 *      ��6��Ϊһ��ʱ��Σ������6���ڳ��ֵ�����������10�͵ó���1���ӵ�������
	 *      ���磺6���⵽��6����������ô1���Ӿ���6*10=60������
	 *      ���磺6���⵽��7����������ô1���Ӿ���7*10=70������
	 *      �����70-60=10
	 *      ��������
	 *      ������������֮���ж��ٰ���0x60���ݣ�����ÿ�����16�����ݣ������������
	 *      ���ռ�೤ʱ�䣬��60/����ó�1���ӵ�������
	 *      ���磺��������֮����10�����ݣ�60/(10/16)=96
	 *      ���磺��������֮����10�����ݣ�60/(11/16)=87
	 *      �����96-87=7
	 *      ��������
	 *      ÿ������ÿ����16��*8���ֽڵ����� 16*8=128��1���Ӿ���128*60=7680�ֽ�
	 *      (ע�⣺ECG II��һ�����������е�λ����9~16)
	 *      ���磺��һ�������ڵ�2���������ݵ�10λ���ڶ��������ڵ�15�����ݵĵ�15λ����������
	 *      �ļ��Ϊ(16-10)+(15-2-1)*8+(15-9)+1=109,7680/109=70
	 *      ���磺��һ�������ڵ�2���������ݵ�11λ���ڶ��������ڵ�15�����ݵĵ�15λ����������
	 *      �ļ��Ϊ(16-11)+(15-2-1)*8+(15-9)+1=108,7680/108=71
	 *      �����71-70=1
	 *      
	 *      �Ƚ�����3���㷨�����������������С�ģ�����ѡ�÷���������������
	 *      
	 *      ʵ�֣�
	 * @author zealjiang
	 * @date 2016��5��13�� ����2:41:45
	 * @param bytes
	 * @param referenceValue
	 *
	 */
	private void calHearRateNums(byte[] bytes,int referenceValue){

		System.out.println("calHearRateNums ------��ʼ-----");
/*		mListBox.add(bytes);
		int packetPoi = mListBox.size()-1;*/
		
		if(mThePacketId==-1){
			
		}else{
			mThePacketId++;
		}

		/**
		 * �ٶ�8���ֽڵ��������ֻ��һ��R��
		 * �˼���һ��Ҫ��
		 */
		for (int i = 0; i < bytes.length; i++) {
			int b1 = bytes[i] & 0xFF;
			System.out.println("b1: "+b1);
/*			System.out.println("mTempMax: "+mTempMax);
			System.out.println("mLast: "+mLast);
			System.out.println("mMax: "+mMax);*/
			
			//���һ��ֻ��max����ڲο�ֵ
			if(b1>=referenceValue){
				if(mFirst==null){
					//�Ѿ��ҳ��˵�һ������������ֵ
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
					//��������ӵ�PosValList��
					addMaxToPosValList();					
					//���¿�ʼ
					mLast = null;
					mFirst = null;
					mMax = null;
					mSecond = null;
					mTempMax = null;
					continue;
				}
			}
			
			
			//�Ѿ��ҳ��˵�һ������������ֵ
			if(mLast!=null){
				if(mMax==null){
					//˵����û���ҵ����ֵ��������ɽ
					if(b1>mLast.value){
						//������
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
							 * �ҵ������㣬
							 * �˴�����ӵ��ֿ�mPosValList�е�ԭ��
							 * ����Ϊ�����һ���Ѿ���������������������Ѿ�����
							 * ��ӵ���mPosValList��
							 * ���������һ����˳����������
							 */
							mMax = mTempMax;
							mLast.setBytePoi(i);
							mLast.setValue(b1);
							
							//���һ��ֻ��max����ڲο�ֵ
							if(b1>=referenceValue){

							}else{
								//���¿�ʼ
								mLast = null;
								mFirst = null;
								mMax = null;
								mSecond = null;
								mTempMax = null;
								continue;						
							}
							continue;
						}else{
							System.out.println("δ�ҵ����ֵǰ����"+i+"����"+b1+"������С��"
								+ "��ǰ�������");
							break;
						}				
					}
				}else{
					//˵���Ѿ��ҵ����ֵ��������ɽ
					if(b1<mLast.value){
						if(b1>referenceValue){
							//������ɽ
							mLast.setBytePoi(i);
							mLast.setValue(b1);
						}else{
							//���¿�ʼ
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
			System.out.println("λ�ã� "+mPosValList.get(j).bytePoi+
					" ֵ��"+(mPosValList.get(j).getValue() & 0xFF));
		}*/
		System.out.println("calHearRateNums------------����------");
	}
	
	/**
	 * ���洢R���Ĳֿ���ʱҪ���Ĵ���
	 * @author zealjiang
	 * @date 2016��5��13�� ����3:19:04
	 *
	 */
	private void addMaxToPosValList(){
		/**
		 * ����ֿ�mPosValList����������ΪsizeΪ2���������ʣ�֮��
		 * ���mListBox�вֿ�mPosValList�е�2��������mListBox�е�
		 * λ�ü���λ��֮ǰ�����ж���
		 * ����ղֿ�mPosValList�ĵ�һ������;
		 */
		if(mPosValList.size()==0){
			System.out.println("�ҵ���һ�����ֵ�ĵ�: "+mMax.getValue()+"  λ��: "+mThePacketId);
			if(mThePacketId==-1){
				mThePacketId = 0;
			}			
			mMax.setPacketPoi(mThePacketId);
			//��ӵ��ֿ�mPosValList��
			mPosValList.add(mMax);	
		}else if(mPosValList.size()==1){
			System.out.println("�ҵ��ڶ������ֵ�ĵ�: "+mMax.getValue()+"  λ��: "+mThePacketId);			
			mMax.setPacketPoi(mThePacketId);
			//��ӵ��ֿ�mPosValList��
			mPosValList.add(mMax);
			
			//�ж�mPosValList�ֿ��Ƿ���������������,�����������ʲôҲ��ִ��
			handlePosValListFull();
		}
	}
	
	/**
	 * ���洢R���Ĳֿ���ʱҪ���Ĵ���
	 * @author zealjiang
	 * @date 2016��5��13�� ����3:19:04
	 *
	 */
	private void handlePosValListFull(){
		/**
		 * ����ֿ�mPosValList����������ΪsizeΪ2���������ʣ�֮��
		 * ���mListBox�вֿ�mPosValList�е�2��������mListBox�е�
		 * λ�ü���λ��֮ǰ�����ж���
		 * ����ղֿ�mPosValList�ĵ�һ������;
		 */
		if(mPosValList.size()==2){
			System.out.println("mPosValList.get(0): "+mPosValList.get(0).getValue());
			System.out.println("mPosValList.get(1): "+mPosValList.get(1).getValue());
			//��������
			int rate = calHearRate(mPosValList.get(0),mPosValList.get(1));
			//�����ʴ��ݳ�ȥ
			setHearRate(rate);
			//���mListBox�вֿ�mPosValList�е�2��������mListBox�е�λ�ü���λ��֮ǰ�����ж���
			//int ppos = mPosValList.get(1).getPacketPoi();
					
			//deleteThePosBefore(mListBox,ppos);		
			//��ղֿ�mPosValList
			mPosValList.remove(0);
			//���½��ֿ��еĲ�����id����Ϊ0
			mThePacketId = 0;
			mPosValList.get(0).setPacketPoi(mThePacketId);
		}
	}
	
	
	/**
	 * ��������
	 * @author zealjiang
	 * @date 2016��5��13�� ����2:19:43
	 * @param pvFirst �������ڵ�R������ǰһ��R������
	 * @param pvSecond �������ڵ�R�����к�һ��R������
	 * @return ���ؼ����������
	 *
	 */
	private int calHearRate(PosVal pvFirst,PosVal pvSecond){
		if(null==pvFirst){
			System.out.println("calHearRate()  pvFirstΪnull");
			return -1;
		}
		if(null==pvSecond){
			System.out.println("calHearRate()  pvSecondΪnull");
			return -1;
		}
		/**
		 * 	ȡ��pvFirst��bytePoiֵ�������R������Ӧ����8��ECG II�ֽ�
		 *  �����е�λ��P1�Լ�packetPoiֵ������List�е�λ��P2��
		 *  ͬ���ҵ�pvSecond�е�bytePoi P3�ͺ�packetPoi P4
		 *  ����һ��������������ֽڣ�
		 *  N = (P4-P2-1)*8+(8-P1)+P3
		 *  ÿ������ÿ����16��*8���ֽڵ����� 16*8=128��1���Ӿ���128*60=7680�ֽ�
		 *  Rate = 7680/N
		 */
		
		int N = (pvSecond.packetPoi-pvFirst.packetPoi)*8+(8-pvFirst.bytePoi)+pvSecond.bytePoi;
		int rate = 8*16*60/N;
		return rate;
	}
	
	/**
     * ִ�м������ʵ��߳�
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
             * ��ʵ�ʲ��Է��֣�ʹ��mHolterDraw.draw(mOp,surfaceHolder);������һ����ʱ�Ĳ����������20ms����
             * ���mOp�����������������ģ�����mOp���͹�����ʱ�����20ms���ͻ���ֶ���������mHolterDraw.draw���ڻ���
             * һ�η��������ݣ��ַ������µ����ݣ������������µ����ݣ���ʱ�ͳ��������ݻ�û���ļ����ƣ��ͱ����ǵ��ˣ�
             *
             * ���������׼��һ�����ӣ��ѷ����������ȷŵ������Ȼ��Ӻ�����ȡ��������,����һ�����ݣ��ٴӺ�����ȡ��һ��
             * ����
             * ע�⣺ÿ��һҳ�µ����ݶ�Ҫ����պ���
             */
            while (isRunning) {
                try {
 
                    if(mListBox.size()!=0){
                        /**
                         * ������Ӳ�Ϊ�գ�����Ա�Ӻ�����ȡ����������������Աÿȡ��һ�����ݶ�Ҫ���д����������������Ӳ�Ϊ��
                         * �ٴӺ�����ȡ��һ������������ֱ������������Ϊ��ʱֹͣ
                         */
                        byte[] bytes = mListBox.get(0);
                        mListBox.remove(0);
                        calHearRateNums(bytes,mReferenceValue);
                        
                        //���ڲ���4������һ�������Ĳ���ֵ
                        if(listInt.size()<16*8*20){
                        	for (int j = 0; j < bytes.length; j++) {
                        		int b1 = bytes[j] & 0xFF;
                        		listInt.add(b1);
							}            	
                        }else{
                        	//���в���4
                        	System.out.println("------���в���4-----");
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
     * R��������Ϣ
     * 
	 * ����һ�����������ԵĶ���A������1��R�������ֽ���������List�е�λ��P2;����2��R����
	 * ���ֽ������е�λ��P1;����3��R����ֵ    
     * @author zealjiang
     * @time 2016��5��13������10:51:22
     */
	class PosVal{
		/**��ǰ����ֵ���ֽ������е�λ��*/
		private int bytePoi;
		/**�ֽ�������List�е�λ��*/
		private int packetPoi;
		/**R����Ӧ��ֵ*/
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
	 * ɾ��list��ָ��λ��֮ǰ����������(λ�ô�0��ʼ)
	 * @author zealjiang
	 * @date 2016��5��13�� ����11:02:05
	 * @param list
	 * @param pos
	 * @return
	 *
	 */
	public boolean deleteThePosBefore(ArrayList list,int pos){
		if(list==null){
			System.out.println("deleteThePosBefore(): �����listΪnull");
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
		
		//ɾ����pos��֮ǰ����������(λ�ô�0��ʼ)
		for (int i = 0; i < pos; i++) {
			list.remove(0);
		}	
		//System.out.println("deleteThePosBefore() �ɹ�");
		return true;
	}
	
	/**
	 * �����µ����ʲ���ʱ���˷����ᱻ����
	 * @author zealjiang
	 * @date 2016��5��13�� ����2:38:50
	 * @param rate ����
	 *
	 */
	private void setHearRate(int rate){
		System.out.println("����Ϊ��"+rate);
	}
	
	/**
	 * ����ο�ֵ
	 * �����Ǵ��������ĵ粨���л�ȡ��ECG II���ݣ����н��ܺ������
	 * ���     000 001 002 003 004 005 006 007 008 009 010 011 012 013 014 015 016 017 018 019 020 021 022 023 024 025 026 027 028 029 030 031 032 033 034 035 036 037 038 039 040 041 042 043 044 045 046 047 048 049 050 051 052 053 054 055 056 057 058 059 060 061 062 063 
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


		����һ��������ĵ粨�н�����������ȡ������ECGII�����ݣ�
		�ӽ�����������ȡ��ECGII������
		���  008 009 010 011 012 013 014 015
			
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
	 * ͨ���۲��Լ���ϵ���εĻ�׼��0x60,��֪���ھ�Ϣ״̬�£��ĵ粨�ڻ��߸�������10���²�����(96+10��96-10)֮�䣬
	 * P���ڻ����Ϸ�(0~10֮����һ���շ��ŵ�ͼ����ʽ����)��T���ڻ����Ϸ�(0~30֮����һ���շ��ŵ�ͼ����ʽ����)��
	 * QRS�Σ�QRΪһֱ�������Σ�RS��Ϊһֱ�½����Σ�����Q���ڻ��ߴ�������S���ڻ����·���Լ-30��,
	 * Q����ǰ3����Ӧ�ô��ڻ���λ�ø�����S�����4����ҲӦ�ô��ڻ���λ�ø���,R��
	 * 
	 * @param bytes Ҫ�������ֽ�����
	 * @param base ���ߵ�ֵ
	 * @author zealjiang
	 * @date 2016��5��16�� ����7:28:53
	 *QRS ��
	 */
	
	/**�洢һ��byte���ݣ������Ƿ����QRS*/
	private ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
	/**�洢Rֵ������*/
	private ArrayList<Integer> mRPointsList = new ArrayList<Integer>();
	/**��һ�ε��������*/
	int lastVal = 0;
	int mBase = 0x60&0xff;
	/**����Ϊ����QRS�ε���С�߶�*/
	int mBaseQRSHeight = mBase+40;
	private void calReferencePv(byte[] bytes){
		for (int i = 0; i < bytes.length; i++) {
			int b1 = bytes[i] & 0xFF;
			
			
			if(lastVal==0){	
				//��û�г��ֵ�һ�θ��ڻ��߲���������Χ��ֵ
				if(b1-mBase>10){
					tempArrayList.add(b1);
					lastVal = b1;
				}
			}else{
				//�Ѿ����ֵ�һ�θ��ڻ��߲���������Χ��ֵ
				if(b1>lastVal){
					tempArrayList.add(b1);
					lastVal = b1;
				}else{
					//�ж��Ƿ���QRS��
					if(lastVal>mBaseQRSHeight){
						
					}
				}
			}	

			lastVal = b1;
		}
	}
	
	/**
	 * ͨ���ҳ����ִ������������ҳ���ȷ��Rֵ
	 * Ŀ�ģ���һϵ��Rֵ���ҳ�������10�ķ�Χ�ڳ��ִ��������Ǹ�������Ҫ�ҵ�Rֵ
	 * д���������ԭ���ǣ������Ӳ��͸��ŵ�Ӱ�죬��R���л����һЩ��������Rֵ��
	 * ֻ���޳�����Щ��������Rֵ�������ҳ�������Rֵ����������Rֵ�������һ��׼ȷ��
	 * �ο�ֵ
	 * ʵ��˼·��
	 * ����һ��
	 * ������20��Rֵ��list����20��Rֵ����ڷ���ȷ��Rֵ(���и��ŵ�R��ֵ)
	 * ����20��Rֵ��С������������ȡ��������м�λ�õ��Ǹ�Rֵ����Ϊ������Rֵ
	 * 
	 * ��������
	 * ������20��Rֵ��list1����20��Rֵ����ڷ���ȷ��Rֵ(���и��ŵ�R��ֵ)��
	 * ����һ����Rֵlist1������ͬ��list2,��������ÿ��Rֵ���ֵĴ�����������Num��ʾ
	 * ���Num=0
	 * ����1��ȡ����һ��Rֵ
	 * ����2�����α������������е�Rֵ����� R-10<R'<R+10���ͽ�Num+1,
	 * ������ɺ�Num����list2�У���Num������Ϊ0��
	 * ��ȡ���ڶ���ֵ���ظ�����2
	 * �ҳ�list2�������Ǹ�����λ�ã���list1��ȡ����λ�õ�Rֵ����Ϊ������10�ķ�Χ��
	 * ���ִ��������Ǹ���
	 * @author zealjiang
	 * @date 2016��5��19�� ����2:10:52
	 * @param list Rֵlist
	 * @return ������10�ķ�Χ�ڳ��ִ��������Ǹ���
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
		//�ҳ�freqList�������Ǹ�����λ��
		int max = 0;int maxPos = 0;
		for (int i = 0; i < freqList.size(); i++) {
			int value = freqList.get(i);
			if(value>max){
				max = value;
				maxPos = i;
			}
			//System.out.println(list.get(i)+" ���ִ�����"+value);
		}
		
		return list.get(maxPos);
	}
	
	/**
	 * ͨ���������ҳ���ȷ��Rֵ
	 * Ŀ�ģ���һϵ��Rֵ���ҳ�������10�ķ�Χ�ڳ��ִ��������Ǹ�������Ҫ�ҵ�Rֵ
	 * д���������ԭ���ǣ������Ӳ��͸��ŵ�Ӱ�죬��R���л����һЩ��������Rֵ��
	 * ֻ���޳�����Щ��������Rֵ�������ҳ�������Rֵ����������Rֵ�������һ��׼ȷ��
	 * �ο�ֵ
	 * ʵ��˼·��
	 * ����һ��
	 * ������20��Rֵ��list����20��Rֵ����ڷ���ȷ��Rֵ(���и��ŵ�R��ֵ)
	 * ����20��Rֵ��С������������ȡ��������м�λ�õ��Ǹ�Rֵ����Ϊ������Rֵ
	 * @author zealjiang
	 * @date 2016��5��19�� ����2:49:34
	 * @param list Rֵlist
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
	 * �������еĲ���R���ֵ�������Ǹ��ŵĻ���������
	 * ˼·������һ��List,��¼�����е��ҵ���R�㣻�ٴ���һ��List
	 * ��¼��һ��Ҫ����������
	 * ������һ���������ĵ�ͼ�Ĵ������
	 *          *
	 * 		   * *
	 * 		  *   *	  
	 * *******     * ****
	 *              *
	 * �������������������ĵ�ͼ��ȡ����ECG II��QRS�����ݣ�(��λ��ʮ����)
	 * ��һ�飺97,102,116,128,136,121,96,79,76,82,88,93,96;
	 * �ڶ��飺93,118,169,199,146,083,072,084,92,96;
	 * �ҵ��Ĺ���:
	 * 1���������ǲ��õĻ���ֵΪ96,����Q�������ֵ��96����(�ٶ�Ϊ��5);
	 * 2�����������ĵ�������½��ĵ㣬���������ֵ�ľ���ֵ���ڵ���3
	 * 3��S���ֵ�Ȼ���ֵҪС��Ҳ��Q��ҪС
	 * 4������������ĸ�������Ϊ3�������½���ĸ���Ҳ����Ϊ3
	 * ������������Ϊ60��100��/���ӣ���ô1����1��1.6�£����Ǽٶ�20
	 * ���������������������������ô�ͷ���20������ݡ�1���������16*8=128��
	 * �ֽڣ�20�����128*20=2560�ֽڵ����ݡ�������20������ݣ�����ҳ���R��
	 * ����20����������N����20-N>10,����ȡ2560�ֽ����ݣ����20-N<10,����ȡ
	 * 2560/2�ֽڵ����ݷ���(����ʵ����Ҫȡ��Ӧ��������)
	 * @author zealjiang
	 * @date 2016��5��23�� ����5:23:23
	 *
	 */
	private void getRValue(ArrayList<Integer> listData,int baseValue){
		//�����洢һ��QRS�������ڹ۲�
		ArrayList<Integer> listQRS = new ArrayList<Integer>();
		Point lastP = new Point();
		Point qPoint = new Point();
		Point rPoint = new Point();
		Point sPoint = new Point();
		final int BaseOffset = 5;
		
		while(listData.size()>0){
			//����һ��QRS������ʼ�㣬���û���ҵ�startΪ-1���ҵ�Ϊ����ֵ
			int start = -1;
			//���¿�ʼ����QRS
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
				//��һ�����ҵ�һ������ֵ�ĵ���Ϊ��ʼ��
				if(start==-1){
					//�����ҵ���һ�����߸����ĵ���Ϊ��ʼ��
					if(Math.abs(value-baseValue)<BaseOffset){
						start = 0;
					}else{
						lastP.value = value;
						lastP.pos = i;
						/**
						 * ����listData��������������ǰɾ�����ǵ�һ��ֵ��i=0λ�õ�����
						 * ����Ҫ��i+1����deleteThePosBefore(),
						 * ���listData����1������Ҫɾ�����ǵ�һ��ֵ��i=0λ�õ���
						 * ���޷���deleteThePosBefore()������
						 */
						
						if(listData.size()>1){
							deleteThePosBefore(listData,i+1);
						}else{
							listData.clear();
						}
						break;
					}
				}
				//���Ϊ�������������ĵ�һ���㣬��Ϊ���ʵ��ֵ������Ϊ0
				if(lastP.value == 0){
					lastP.value = value;
					lastP.pos = i;
					//����������һ���㣬�ͽ�listData���,�˳�whileѭ��
					if(i==listData.size()-1){
						listData.clear();
					}
					continue;
				}
				//�����û����Rͷ��˵���������׶�
				if(rPoint.value==0){
					//���������ĵ����������ֵ���ڵ���3
					if(value-lastP.value<3&&listQRS.size()<3){
						lastP.value = value;
						lastP.pos = i;
						deleteThePosBefore(listData,i);
						break;
					}else if(value-lastP.value<0&&listQRS.size()>=3){//�����½����QRS��ĸ�������3
						//�����ҵ���R��
						rPoint.value = lastP.value;
						rPoint.pos = lastP.pos;
					}
				}else{//�½��׶�
					//�����½��ĵ����������ֵ���ڵ���3
					if(lastP.value-value<3&&listQRS.size()<3){
						lastP.value = value;
						lastP.pos = i;
						deleteThePosBefore(listData,i);
						break;
					}else if(value - lastP.value>0&&listQRS.size()>3){
						//S���ֵ�Ȼ���ֵҪС��Ҳ��Q��ҪС
						if(lastP.value<baseValue&&lastP.value<qPoint.value){
							//�ҵ���S��
							sPoint.value = lastP.value;
							sPoint.pos = lastP.pos;
						}
					}
				}

				//�����û�г���Q��
				if(qPoint.value==0){
					//�����ҵ���S�㣬����������Ĳ��β�����QRS���ε��ص㣬�˵�Ͳ���Q��
					qPoint.value = lastP.value;
					qPoint.pos = lastP.pos;
					listQRS.clear();
					listQRS.add(lastP.value);
				}
				
				//�������S��,��������QRS���Ĳ���
				if(sPoint.value!=0){
					listR.add(rPoint.value);							
					deleteThePosBefore(listData,i);
					break;
				}

				//������QRS�ĵ��ݴ�����
				listQRS.add(value);
							
				lastP.value = value;
				lastP.pos = i;				
			}
		}

	}
	
	/**
	 * һ����(��������list�е�λ����Ϣ�������ֵ��Ϣ)
	 * @author zealjiang
	 * @time 2016��5��24������10:44:25
	 */
	class Point{
		/**λ��*/
		private int pos;
		/**ֵ*/
		private int value;
		/**���*/
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

	            fis = new FileInputStream(new File("C:/zealjiang/˽���ƽ���ҽ��/"
	            		+ "���12���ĵ��ļ�/��־��13048118612569654_20160422103234.YE"));

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
    	//��������ECGII�������ݵ�����
        byte[] bytes8Ecgii = new byte[8];

        int start = 8;
        for (int i = start; i < 16; i++) {
        	bytes8Ecgii[i-start] = list.get(i);   
        }
        
        //����������ECGII�������ݹ��ɵ�����
        //mListBox.add(bytes8Ecgii);
        
       
        //��ӡ
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
