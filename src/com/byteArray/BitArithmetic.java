package com.byteArray;

import java.util.LinkedList;

import com.array.Test;

/**
 * λ����
 * @author zealjiang
 * @time 2016��3��29������9:51:18
 */
public class BitArithmetic {

	public static void main(String[] args) {
		BitArithmetic test = new BitArithmetic();
		test.testHexAdd();
		test.getHighBit();
		test.HigtBitTo1();
		test.binaryStringToHex();
		test.binaryStringToDecimal();
		test.createEncryptData();
	}
	
	public void getHighBit(){
		System.out.println("----------��ȡʮ�����ƶ�Ӧ���������λ--------");
		System.out.println(0x41>>7);//0x41 01000001
		System.out.println(0x80>>7);//0x80 10000000
		System.out.println(0xa4>>7);//0xa4 10100100
		System.out.println(0xff>>7);//0xff 11111111
		System.out.println("-------------------");
	}
	
	public void HigtBitTo1(){
		System.out.println("----------�����������λ���1--------");
		System.out.println((byte)(0x41 | 0x80) & 0xFF);//0x41 01000001
		System.out.println((byte)(0x80 | 0x80) & 0xFF);//0x80 10000000
		System.out.println((byte)(0xa4 | 0x80) & 0xFF);//0xa4 10100100
		System.out.println((byte)(0xff | 0x80) & 0xFF);//0xff 11111111
		System.out.println("-------------------");
	}
	
	public void binaryStringToHex(){
		System.out.println("----------��java string ������ת16����-------");
		System.out.println(Integer.toString (Integer.parseInt ("01000001", 2), 16));//0x41 01000001
		System.out.println(Integer.toString (Integer.parseInt ("10000000", 2), 16));//0x80 10000000
		System.out.println(Integer.toString (Integer.parseInt ("10100100", 2), 16));//0xa4 10100100
		System.out.println(Integer.toString (Integer.parseInt ("1000001", 2), 16));//0x41 01000001
		System.out.println("-------------------");
	}
	
	public void binaryStringToDecimal(){
		System.out.println("----------��java string ������ת10����-------");
		System.out.println(Integer.toString (Integer.valueOf ("01000001", 2), 10));//0x41 01000001
		System.out.println(Integer.toString (Integer.valueOf ("10000000", 2), 10));//0x80 10000000
		System.out.println(Integer.toString (Integer.valueOf ("10100100", 2), 10));//0xa4 10100100
		System.out.println(Integer.toString (Integer.valueOf ("1000001", 2), 10));//0x41 01000001
		System.out.println("-------------------");
	}
	

	public void testHexAdd(){
		int sum = 0;
		String id = "02";
		String data = "34,43";
		int iId = Integer.valueOf(id,16);
		byte bId = (byte)iId;
		sum += iId;
		String[] datas = data.split(",");
		for(int i=0;i<datas.length;i++){
			sum += Integer.valueOf(datas[i],16);
		}
		System.out.println("testHexAdd--------ʮ���������----------");
		System.out.println((byte)sum);
		System.out.println(Integer.toHexString(sum));	
		System.out.println((byte)sum | 0x80);
		System.out.println("--------------------");
		
	}
	
    private byte createEncryptData(){
    	String data = "34,43,56,65,78,87,9a,01";
        if(data==null){
            return 0;
        }
        StringBuilder sb = new StringBuilder();
		String[] datas = data.split(",");
		//ԭ�������λ
		int[] highBit = new int[datas.length];
		//ԭ���ݶ�Ӧ�ļ�������
		byte[] encryptData = new byte[datas.length];

		for(int i=0;i<datas.length;i++){
			int decimal = Integer.valueOf(datas[i],16);
			highBit[i] = decimal>>7;
			encryptData[i] = (byte)(decimal | 0x80);			
		}
		
		//��������ͷ��һ��1������ͷ+7����Ч����
		int groupNum = datas.length/7;
		int remainder = datas.length%7;
		if(remainder!=0){
			groupNum +=1;
		}
		
		LinkedList<Byte> list = new LinkedList<Byte>();
		StringBuilder sHb = new StringBuilder();		
		for (int i = 0; i < groupNum; i++) {
			//���㵱ǰ�������ͷ������β
			int end = 0;
			end = datas.length>7*(i+1) ? 7*(i+1):datas.length;		
			int start = 7*i;

			//�����ÿ�����ݵ�����ͷ
			for (int j = end - 1; j >= start; j--) {						
				sHb.append(highBit[j]);
			}

			int decimal = Integer.valueOf(sHb.toString(),2);
			System.out.println("decimal: "+decimal);
			byte head = (byte)(decimal | 0x80);
			sHb.setLength(0);//���		
			//����һ������
			list.add(head);
			for (int j = start; j < end; j++) {						
				list.add(encryptData[j]);
			}
			
		}
		
		//�������
		System.out.println("---------���ܺ������---------");
		for (int i = 0; i < list.size(); i++) {		
			System.out.print(Integer.toHexString(list.get(i))+" ");		
			System.out.println();
		}
		System.out.println("------------------");
        
        
        return 0;
    }
}
