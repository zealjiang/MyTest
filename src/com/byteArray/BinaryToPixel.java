package com.byteArray;

import java.util.ArrayList;

public class BinaryToPixel {

    /**表示1mV'代表多大(单位：十进制)，如：1mV'表示110 ,这个值是外部指定的*/
    private double mMvDecimal = 200;
    /**表示1mV'代表多少mm(单位：毫米)，如：10mm/mV'表示1mv'代表10mm ,这个值是外部指定的*/
    private float mMvMm = 10;
    /**1mm代表多少像素 */
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
		System.out.println("------------测试binaryToPixel()--------------");
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
     *将二进制值转成手机屏幕上的像素(是对收到的字节定位其纵坐标)
     * 转换的思路：1、将byte转成十进制值，将此十进制值减去128(因为0X80对应的是基轴坐标，转成十进制就是128)
     *            2、将算出的十进制值转成mv'
     *            3、将转成的mv'值转成mm
     *            4、将转成的mm值转成像素
     *            5、以基轴为起点
     *@author zealjiang
     *created at 2016/3/31 15:35
     * @param b 表示心电波形的一个字节(0~255)
     * @param pixelBase 当前字节所在的横坐标中心轴所对应的屏幕像素坐标
     * @return 返回此二进制值对应的屏幕像素纵坐标
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
        //大于0要画在基轴的上方，小于0要画在基轴的下方
        if(iV>0){
            return pixelBase - mP;
        }else{
            return pixelBase + mP;
        }
    }
}
