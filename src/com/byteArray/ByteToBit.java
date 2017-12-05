package com.byteArray;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ByteToBit {

	public static void main(String[] args) {
		ByteToBit byteToBit = new ByteToBit();
		byteToBit.byte2Bit();
		byteToBit.byteToBit((byte)0x35);
		byteToBit.getBooleanArray((byte)0x95);
		
		System.out.println((byte)0x93-(byte)0x80);
		System.out.println((byte)0x33-(byte)0x80);
		System.out.println("(byte)0x33:"+(byte)0x33+" (byte)0x80:"+(byte)0x80);
		System.out.println("(byte)0x7F:"+(byte)0x7F+" (byte)0x80:"+(byte)0x80);
		
		System.out.println(0x93 & 0x7F);
		
		byteToBit.compare();
		
	}
	
	private void compare(){
		ArrayList<Byte> list = new ArrayList<Byte>();
		list.add(((byte)0x85));
		list.add(((byte)0x80));
		list.add(((byte)0x92));
		list.add(((byte)0xFF));
		list.add(((byte)0x7F));
		list.add(((byte)0x00));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i+" :"+list.get(i));
		}
		
		String info = verifyPacket(list);
		System.out.println("info: "+info);
	}
	
	
    /**
     *У��������Ƿ��д�����д��ش�����Ϣ���޴���""�ַ���
     *@author zealjiang
     *created at 2016/3/24 11:14
     *
     */
    private String verifyPacket(ArrayList<Byte> packet){
        if(packet==null||packet.size()<=4){
            //��̵İ����ݰ�����ͷID(1���ֽ�)������ͷ(1���ֽ�)������(1���ֽ�)��У���(1���ֽ�)��4���ֽ�
            return "������̫��,packet�д�";
        }
        
        if(packet.get(0)<0){
            //�ǰ�����ID
        	return "��ͷ����ID,����"+1+"���ֽ����λ��0";
        }

        for (int i = 1; i < packet.size(); i++) {
            if(packet.get(i)>0){//byte��ȡֵ��Χ��-128(0x80)~127(0x7F)
                return "��"+(i+1)+"���ֽ����λ��1";
            }
        }

        return "";
    }
	
	private void byte2Bit(){
		byte b = 0x35;
		String s = new BigInteger(1, new byte[]{b}).toString(2);// �����1��������  
		System.out.println(s);
	}
	
    /** 
     * ��byteתΪ�ַ�����bit 
     */  
    public void byteToBit(byte b) {  
       String s = ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
       
       System.out.println(s);
    } 
    
    /** 
     * ��byteת��Ϊһ������Ϊ8��byte���飬����ÿ��ֵ����bit 
     */  
    public void getBooleanArray(byte b) {  
    	//System.out.println("getBooleanArray------0x"+Integer.toHexString((b&0xff)));
        byte[] array = new byte[8];  
        for (int i = 7; i >= 0; i--) {  
            array[i] = (byte)(b & 1);  
            b = (byte) (b >> 1);  
        }  
        
        
/*        for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}*/
        //System.out.println("\n"+"end---getBooleanArray------");
    }  


}
