package com.byteArray;

public class intToByteArray {


	public static void main(String[] args) {
		
		/**
		 * byte��һ���ֽڣ�������8λ��ȡֵ��ΧΪ  -128~+127,������
			��Ϊ�з��ţ����Է���λռһλ
			-128�Ķ�����λ   1000 0000  Ҳ����0X80 
			+127����   0111 1111  Ҳ����0X7F��
			
			
			java����int��32λ�ģ�byte��8λ�ġ�
			int���͵�data����data & 0xff�������int���͵�,ǿ��ת����byte�󣬾ͱ������8λ��
			500=0x01f4,(byte)500&0xff=0xf4=-12��������Բ����ʾ�ģ����λ�Ƿ���λ��
			0x01f4
			0x00ff ��
			----------
		  =	0x00f4
		 	��Ϊ0x00f4>0x80,���Խ�����Բ����ʾ��
		 */
		
		//intToByteArray(1234567899);
		
		byte[] buffer = new byte[1];
		buffer[0]= (byte)(123456789&0xff);//ʮ����123456789ת��16�����ǣ�75BCD15,��0xff�����㣬�������8λ����0x15,��ʮ���Ʊ�ʾΪ21
		System.out.println(buffer[0]);
		
		//FEH+E1H+E2H+E4H+E0H+E0H
	    byte[] b = new byte[1048];
        b[0] = (byte) 0x0f;
        System.out.println(b[0]);
        
        byte by = (byte)0x60;
        System.out.println("0x60: "+Integer.valueOf("60",16));
        System.out.println("by 0x60: "+by);
        if((int)(by)==Integer.valueOf("60",16)){

        }
	}
	
	public static byte[] intToByteArray(int number){
		
		String str = number+"";
		int length = str.length();
		int array_length =0;
		
		//System.out.println(7%8);
		if(length%8==0){
			array_length = length/8;
		}else{
			array_length = length/8+1;
		}
		String[] b = new String[array_length];
		//System.out.println(b.length);
		for(int i=0;i<b.length;i++){
			if(length>8){
				
			}
			b[i] = str.substring(8*i, 8*(i+1));
			
			
		}
		
		for(int i=0;i<b.length;i++){
			System.out.println(b[i]);
		}
		//str.substring(0, 7);
		
		return null;
	}
}
