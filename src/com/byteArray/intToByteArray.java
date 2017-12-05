package com.byteArray;

public class intToByteArray {


	public static void main(String[] args) {
		
		/**
		 * byte是一个字节，其中有8位，取值范围为  -128~+127,闭区间
			因为有符号，所以符号位占一位
			-128的二进制位   1000 0000  也就是0X80 
			+127就是   0111 1111  也就是0X7F。
			
			
			java里面int是32位的，byte是8位的。
			int类型的data进行data & 0xff运算后还是int类型的,强制转换成byte后，就保留其低8位。
			500=0x01f4,(byte)500&0xff=0xf4=-12，结果是以补码表示的，最高位是符号位。
			0x01f4
			0x00ff 与
			----------
		  =	0x00f4
		 	因为0x00f4>0x80,所以结果是以补码表示的
		 */
		
		//intToByteArray(1234567899);
		
		byte[] buffer = new byte[1];
		buffer[0]= (byte)(123456789&0xff);//十进制123456789转成16进制是：75BCD15,与0xff与运算，保留其低8位即：0x15,其十进制表示为21
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
