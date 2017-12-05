package com.byteArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lenovo
 * @time 2016年3月18日上午10:33:57
 */
public class ByteToHex {
	public static final byte C0x10 = 0x10; 
	
	public static void main(String[] args) {
		System.out.println("Integer.MAX_VALUE: "+Integer.MAX_VALUE);
		System.out.println("Integer.MIN_VALUE: "+Integer.MIN_VALUE);
		System.out.println("C0x10: "+C0x10);
		
		ByteToHex bth = new ByteToHex();
		bth.byteToHex();
		
		byte[] byteArray = new byte[2];
		byteArray[0] = (byte) 0xfa;
		byteArray[1] = (byte) 0x2a;
		
		String s = bytesToHexString(byteArray);
		//System.out.println(s);
		
		bth.subList();
		
		System.out.println("======testSplitData=======");
		bth.testSplitData();
		
		System.out.println("======testRestoreData=======");
		bth.testRestoreData();
		
		System.out.println("======testGetOriginalPacket=======");
		bth.testGetOriginalPacket();
	}

	public void byteToHex() {
		byte b1 = (byte) 0x03;
		byte b2 = (byte) 0x80;
		byte b3 = (byte) 0x95;

		System.out.println(b1>b2);
		System.out.println(0xFE>0x3D);
		System.out.println((b1 & 0xFF)>(b2 & 0xFF));
		System.out.println("b1: " + b1);
		System.out.println("b2: " + b2);
		System.out.println("b3: " + b3);
		System.out.println("0x95: " + 0x95);

		int v1 = b1 & 0xFF;
		int v2 = b2 & 0xFF;
		int v3 = b3 & 0xFF;

		System.out.println("v1: " + v1);
		System.out.println("v2: " + v2);
		System.out.println("v3: " + v3);

		String s1 = Integer.toHexString(v1);
		String s2 = Integer.toHexString(v2);

		System.out.println("s1: " + s1);
		System.out.println("s2: " + s2);

		
	}

	/*
	 * * * Convert byte[] to hex string.这里我们可以将byte转换成int，
	 * 然后利用Integer.toHexString(int) 来转换成16进制字符串。
	 * 
	 * @param src byte[] data
	 * 
	 * @return hex string
	 * 
	 */
	public static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);

			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
			stringBuilder.append(" ");
		}
		return stringBuilder.toString();
	}

	private void subList(){
		ArrayList<Byte> list = new ArrayList<Byte>();
		list.add(((byte)0x85));
		list.add(((byte)0x80));
		list.add(((byte)0x92));
		list.add(((byte)0xFF));
		list.add(((byte)0x7F));
		list.add(((byte)0x00));
		
		List<Byte> list1 = list.subList(0, 3);
		List<Byte> list2 = list.subList(2, list.size());
		
		for (int i = 0; i < list1.size(); i++) {
			System.out.println("list1<"+i+">: "+Integer.toHexString(list1.get(i) & 0xFF));
		}
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println("list2<"+i+">: "+Integer.toHexString(list2.get(i) & 0xFF));
		}
	}
	
	
	private void testSplitData(){
		ArrayList<Byte> list = new ArrayList<Byte>();
		list.add(((byte)0x10));
		list.add(((byte)0x82));
		list.add(((byte)0x87));
		list.add(((byte)0xe0));
		list.add(((byte)0x83));
		list.add(((byte)0x97));
		
		list.add(((byte)0x8f));
		list.add(((byte)0x9f));
		list.add(((byte)0x9b));
		list.add(((byte)0x9d));
		list.add(((byte)0xe2));
		list.add(((byte)0x92));
		
		list.add(((byte)0x85));
		list.add(((byte)0xff));
		list.add(((byte)0xc0));
		list.add(((byte)0x80));
		list.add(((byte)0xb1));

		ArrayList<List<Byte>> mlist= splitData(list);
		for (int i = 0; i < mlist.size(); i++) {
			for (int j = 0; j < mlist.get(i).size(); j++) {
				System.out.print(Integer.toHexString(mlist.get(i).get(j) & 0xFF)+" ");
			}
			//10 82 87 e0 83 97 8f 9f 9b 9d e2 92 85 ff c0 80 b1
			System.out.println();
		}
	
		System.out.println("---------------");
		for (int i = 0; i < mlist.size(); i++) {
			ArrayList<Byte> array = restoreData(mlist.get(i));
			
			for (int j = 0; j < array.size(); j++) {
				System.out.print(Integer.toHexString(array.get(j) & 0xFF)+" ");
			}
			System.out.println();
		}

	}
	
    /**
     *将一包数据按组分开
     *@author zealjiang
     *created at 2016/3/24 14:10
     *
     */
    private ArrayList<List<Byte>> splitData(ArrayList<Byte> packet){
        if(null==packet||packet.size()==0){
            return null;
        }
        //存储每组数据
        ArrayList<List<Byte>> list = new ArrayList<List<Byte>>();
        //存储数据头对应的位置
        ArrayList<Integer> headList = new ArrayList<Integer>();

        for (int i = 1; i < packet.size()-1; i++) {
            if((i-1)%8==0){
                //是数据头
                headList.add(i);
            }
        }

        for (int i = 0; i < headList.size(); i++) {
        	
            if(i+1<headList.size()) {
                list.add(packet.subList(headList.get(i), headList.get(i + 1)));
            }else{
                list.add(packet.subList(headList.get(i), packet.size()-1));
            }
        }


        return list;
    }
    
	
    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public byte[] getByteArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }

        return array;
    }
    
    
    private void testRestoreData(){
		ArrayList<Byte> list = new ArrayList<Byte>();
		list.add(((byte)0x82));
		list.add(((byte)0x87));
		list.add(((byte)0xe0));
		list.add(((byte)0x83));
		list.add(((byte)0x97));	
		list.add(((byte)0x8f));
		list.add(((byte)0x9f));
		list.add(((byte)0x9b));
		
		ArrayList<Byte> array = restoreData(list);
		
		for (int j = 0; j < array.size(); j++) {
			System.out.print(Integer.toHexString(array.get(j) & 0xFF)+" ");
		}
    }
    
    /**
     *将一组数据还原为原始数据
     *@author zealjiang
     *created at 2016/3/24 14:10
     *
     */
    private ArrayList<Byte> restoreData(List<Byte> list){
    	if(null==list||list.size()==0){
    		return null;
    	}
    	ArrayList<Byte> rDataList = new ArrayList<Byte>();
    	
    	byte[] bArray = getByteArray(list.get(0));

    	for (int j = 1; j < list.size(); j++) {
    		
    		if(bArray[bArray.length-1-(j-1)]==0){
    			byte b = (byte) (list.get(j) & 0x7F);
    			rDataList.add(b);
    		}else{
    			rDataList.add(list.get(j));
    		}
    	}
		return rDataList;
    }
    
    /**
     *获取一包加密的数据中的数据部分
     *@author zealjiang
     *created at 2016/3/24 15:43
     * @param packet
     * @return ArrayList<Byte>
     */
    private ArrayList<Byte> getOriginalPacket(ArrayList<Byte> packet){
        if(null==packet||packet.size()==0){
            return null;
        }
        ArrayList<Byte> oriArray = new ArrayList<Byte>();

        ArrayList<List<Byte>> lists = splitData(packet);
        for (int i = 0; i < lists.size(); i++) {

            ArrayList<Byte> list = restoreData(lists.get(i));
            for (int j = 0; j < list.size(); j++) {
                oriArray.add(list.get(j));
            }

        }

        return oriArray;
    }
    
    /**
     * 测试获取原始数据包中的数据部分
     */
    private void testGetOriginalPacket(){
    	ArrayList<Byte> list = getFakeData();
    	ArrayList<Byte> oriList = getOriginalPacket(list);
    	for (int i = 0; i < oriList.size(); i++) {
    		System.out.print(Integer.toHexString(oriList.get(i) & 0xFF)+" ");
		}
    }
    
    /**
     * 获取假数据
     * @return
     */
    private ArrayList<Byte> getFakeData(){
		ArrayList<Byte> list = new ArrayList<Byte>();
		list.add(((byte)0x10));
		list.add(((byte)0x82));
		list.add(((byte)0x87));
		list.add(((byte)0xe0));
		list.add(((byte)0x83));
		list.add(((byte)0x97));
		
		list.add(((byte)0x8f));
		list.add(((byte)0x9f));
		list.add(((byte)0x9b));
		list.add(((byte)0x9d));
		list.add(((byte)0xe2));
		list.add(((byte)0x92));
		
		list.add(((byte)0x85));
		list.add(((byte)0xff));
		list.add(((byte)0xc0));
		list.add(((byte)0x80));
		list.add(((byte)0xb1));
		
		return list;
    }
}
