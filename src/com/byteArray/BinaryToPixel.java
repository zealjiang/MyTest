package com.byteArray;

import java.util.ArrayList;

public class BinaryToPixel {

    /**��ʾ1mV'������(��λ��ʮ����)���磺1mV'��ʾ110 ,���ֵ���ⲿָ����*/
    private double mMvDecimal = 200;
    /**��ʾ1mV'�������mm(��λ������)���磺10mm/mV'��ʾ1mv'����10mm ,���ֵ���ⲿָ����*/
    private float mMvMm = 10;
    /**1mm����������� */
    private double mMmPixel = 720/63;
    
	public static void main(String[] args) {
		BinaryToPixel btp = new BinaryToPixel();
		btp.testBinaryToPixel();
		
		int p = 5;
		btp.modifyNum(p);
		System.out.println("p: "+p);
		
		btp.stringToHex();
		btp.simulateSend();
	}
	
	private void modifyNum(int i){
		i = 8;
	}
	
	private void stringToHex(){
		String s = "60";
		int i = Integer.valueOf(s, 16);
		System.out.println("i: "+i);
		byte b = (byte) i;
		System.out.println("b: "+b);
		
		
	}
	
    private void simulateSend(){
        String sData = "60 80 e0 e0 e0 e0 e0 e0 e0 80 e0 df df df df df df 80 df df e1 e1 e1 e1 e1 80 e1 e1 e1 e0 e0 e0 e0 80 e0 e0 e0 e0 df df df 80 df df df df df dc de 80 de de de de de de dd 80 de de de de de de de 80 dd df df df df df df 80 df ab";
        ArrayList<Byte> list = new ArrayList<Byte>();
        String[] sArray = sData.split(" ");
        for (int i = 0; i < sArray.length; i++) {
            int hex = Integer.valueOf(sArray[i], 16);
            list.add((byte)hex);
        }
        
        System.out.println("----simulateSend--------");
        for (byte b : list) {
        	System.out.print(Integer.toHexString((b&0XFF)) +" ");
		}
        System.out.println("---------------");
        
    }
	
	private void testBinaryToPixel(){
		System.out.println("------------����binaryToPixel()--------------");
		System.out.println("0x80: "+binaryToPixel((byte)0x80, 45));
		System.out.println("0xA3: "+binaryToPixel((byte)0xA3, 135));
		System.out.println("0x30: "+binaryToPixel((byte)0x30, 225));
		System.out.println("0xFF: "+binaryToPixel((byte)0xFF, 315));
		System.out.println("0x34: "+binaryToPixel((byte)0x34, 405));
		System.out.println("0xa0: "+binaryToPixel((byte)0xa0, 495));
		System.out.println("0x12: "+binaryToPixel((byte)0x12, 585));
		System.out.println("0x99: "+binaryToPixel((byte)0x99, 675));
		
		System.out.println("---------------------------------");
		
	};
	
    /**
     *��������ֵת���ֻ���Ļ�ϵ�����(�Ƕ��յ����ֽڶ�λ��������)
     * ת����˼·��1����byteת��ʮ����ֵ������ʮ����ֵ��ȥ128(��Ϊ0X80��Ӧ���ǻ������꣬ת��ʮ���ƾ���128)
     *            2���������ʮ����ֵת��mv'
     *            3����ת�ɵ�mv'ֵת��mm
     *            4����ת�ɵ�mmֵת������
     *            5���Ի���Ϊ���
     *@author zealjiang
     *created at 2016/3/31 15:35
     * @param b ��ʾ�ĵ粨�ε�һ���ֽ�(0~255)
     * @param pixelBase ��ǰ�ֽ����ڵĺ���������������Ӧ����Ļ��������
     * @return ���ش˶�����ֵ��Ӧ����Ļ����������
     */
    private int binaryToPixel(byte b,int pixelBase){
    	//System.out.println("---");
        int iV = (b&0XFF) - 128;
        //System.out.println("iV: "+iV);
        int absIv = Math.abs(iV); 
        //System.out.println("absIv: "+absIv);
        double mV = absIv/mMvDecimal;
        //System.out.println("mV: "+mV);
        double mM = mV*mMvMm;
        //System.out.println("mM: "+mM);
        int mP = (int)(mM*mMmPixel);
        //System.out.println("mP: "+mP);
        //System.out.println("---");
        //����0Ҫ���ڻ�����Ϸ���С��0Ҫ���ڻ�����·�
        if(iV>0){
            return pixelBase - mP;
        }else{
            return pixelBase + mP;
        }
    }
}
