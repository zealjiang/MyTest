package com.byteArray;

import java.util.ArrayList;

public class Test {

    private static ArrayList<Byte> mPacket = new ArrayList<Byte>();
    
    public static void main(String[] args) {
    	Test test = new Test();
    	test.test1();
	}
    
    private void test1(){
    	byte[] buffer = new byte[10];
    	buffer[0] = 0x20;
    	buffer[1] = (byte)0xb4;    	
    	buffer[2] = (byte)0xc3;
    	buffer[3] = (byte)0xf9;
    	buffer[4] = (byte)0x83;
    	buffer[5] = (byte)0x97;
    	buffer[6] = (byte)0xaa;
    	buffer[7] = (byte)0x60;
    	buffer[8] = (byte)0x82;
    	buffer[9] = (byte)0x87;
    	
    	boolean boo = createPacket(buffer,10);
    	if(boo){
    		String string = bytesToHexString(mPacket);
    		System.out.println(string);
/*	    	for (int i = 0; i < mPacket.size(); i++) {
				System.out.println(mPacket.get(i));
				
			}*/
    		mPacket.clear();
    		System.out.println("========");
    		test2();
    	}
    }
    
    private void test2(){
    	byte[] buffer = new byte[4];
    	buffer[0] = (byte)0xb4;    	
    	buffer[1] = (byte)0x60;
    	buffer[2] = (byte)0x82;
    	buffer[3] = (byte)0x87;
    	
    	boolean boo = createPacket(buffer,4);
    	//if(boo){
    		String string = bytesToHexString(mPacket);
    		System.out.println(string);
/*	    	for (int i = 0; i < mPacket.size(); i++) {
				System.out.println(mPacket.get(i));
				
			}*/
    		mPacket.clear();
    	//}
    }
    
    
    /**
     *判断是否是包类型ID,是返回true，否返回false
     *@author zealjiang
     *created at 2016/3/23 11:40
     *
     */
    private boolean isPacketId(byte b){
        int m = b & 0xFF;
        int n = 0x7F & 0xFF;
        if(m<=n){
            //buffer[0]为包类型ID
            return true;
        }else{
            //buffer[0]为数据或校验码
            return false;
        }
    }

    /**
     * 生成一个完整的数据包
     * @return
     */
    private boolean createPacket(byte[] buffer,int count){
        if(buffer==null||count==0){
            return false;
        }

        for (int i=0;i<count;i++) {
            boolean boo = isPacketId(buffer[i]);
            if (boo){
            	if(mPacket.size()!=0){
                    //生成完成
                    return true;
            	}else{
            		mPacket.add(buffer[i]);
            	}
            }else{
            	if(mPacket.size()!=0){
            		mPacket.add(buffer[i]);
            	}else{
            		continue;
            	}
            }
        }

        return false;
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
}
