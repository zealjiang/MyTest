package com.byteArray;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.impl.Log4jLogEvent;

import com.array.PacketQueue;

import sun.rmi.runtime.Log;

/**
 * Created by zealjiang on 2016/3/25 9:21.
 */
public class HotterUtil {
	
	private HeartRateCalculate mHeartRateCalculate;
	private DecryptPacketListener mDecryptPacketListener;
	
	public static void main(String[] args) {
			
		HotterUtil hu = new HotterUtil();
		hu.readTimeFromData();
		byte[] bytes = hu.readStringToByteArray();
		
		hu.decryptData();
		
	}
	
	
	
	private void decryptData(){
		
		byte[] bytes = readStringToByteArray();
		ArrayList<Byte> packet = new ArrayList<Byte>();
		
		for (int i = 0; i < bytes.length; i++) {
			packet.add(bytes[i]);
		}
		OriginalPacket op = handlePacket(packet);
		packet = op.getData();
		
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
        for (int i = 0; i < packet.size(); i++) {
        	int n = (packet.get(i)&0xff);
			if(n<10){
				sb.append("00"+n+" ");
			}else if(n<100){
				sb.append("0"+n+" ");
			}else{
				sb.append(n+" ");
			}            	
        }
        sb.append("\n");
        System.out.println(sb.toString());
	}
	
	/**
	 * 十六进制字符串转成十进制
	 */
	public void hexString2Decimal(){
		System.out.println(Integer.valueOf("7e0", 16));
	}
	
	
	public byte[] readStringToByteArray(){
		
		String s = "60 ff 80 80 80 80 80 80 80 81 80 b1 a6 b2 b3 b0 bd fc ae b8 80 80 80 80 80 ff 80 80 80 80 80 80 80 ff 80 80 80 80 80 80 80 ff 80 80 80 80 80 80 80 ff 80 80 80 80 80 80 80 ff 80 80 80 80 80 80 80 ff 80 80 80 80 80 80 80 81 80 e6";
		
		ArrayList<Byte> list = new ArrayList<Byte>();
		String[] sa = s.split(" ");
		byte[] bArray = new byte[sa.length];
		for (int i = 0; i < sa.length; i++) {
			byte b = Integer.valueOf(sa[i], 16).byteValue();
			list.add(b);
			bArray[i] = b;
		}
				
		return bArray;	
	}
	
	/**
	 * 从Holter中读取0x10命令中的时间数据
	 * 注意：yyyy年是一个整体
	 * @author zealjiang
	 * @date 2016年4月22日 上午9:48:27
	 *
	 */
	public void readTimeFromData(){
		HotterUtil hu = new HotterUtil();
		
		//String s = "10 82 87 e0 84 8d 8a 97 99 85 e2 92 87 80 80 80 c4";
		//String s = "10 82 87 e0 84 96 8a 9f 89 85 e2 92 97 80 80 bc 91";
		//String s = "10 82 87 e0 84 96 8a 9f 8b 85 e2 92 97 80 80 bc 93";
		  String s = "10 82 87 e0 85 8a 94 81 a4 85 e2 92 89 80 df 60 80";
		
		ArrayList<Byte> list = new ArrayList<Byte>();
		String[] sa = s.split(" ");
		for (int i = 0; i < sa.length; i++) {
			list.add(Integer.valueOf(sa[i], 16).byteValue());
			//System.out.println(i+": "+ Integer.toHexString(Integer.valueOf(sa[i], 16)));
		}
			
		hu.readTimeFromBytes(list);
	}
	
	/**
	 * 将字节数组转成时间
	 * @author zealjiang
	 * @date 2016年4月22日 下午4:46:25
	 * @param list
	 * @return 返回时间 时间格式是 yyyy-MM-dd HH:mm:ss
	 *
	 */
	public String readTimeFromBytes(ArrayList<Byte> list){

		OriginalPacket op = handlePacket(list);
		if(op.data==null){
			System.out.println(op.getError());
			System.out.println("--进行一次纠错--");
			
			//进行一次纠错
			int i = 1;
			for (; i < list.size(); i++) {
				if(list.get(i)>0){
					//除包头类型外，出现了小于0x80的错误字节
					break;
				}
			}
			
			if(i<10){
				return "";
			}
			System.out.println("i: "+i);
			list = new ArrayList<Byte>(list.subList(0, i));
			op = handlePacket(list);
		}
		
		StringBuilder sb  = new StringBuilder();
		//打印十进制值
		String sHexYear = Integer.toHexString(op.data.get(0)&0xFF)+
		Integer.toHexString(op.data.get(1)&0xFF);
		String sHexMonth = Integer.toHexString(op.data.get(2)&0xFF);
		String sHexDay = Integer.toHexString(op.data.get(3)&0xFF);
		String sHexHour = Integer.toHexString(op.data.get(4)&0xFF);
		String sHexMinute = Integer.toHexString(op.data.get(5)&0xFF);
		String sHexSecond = Integer.toHexString(op.data.get(6)&0xFF);
		sb.append(Integer.valueOf(sHexYear, 16)+"-");
		String sM = Integer.valueOf(sHexMonth, 16)+"";
		sM = sM.length()==2 ? sM:"0"+sM;
		sb.append(sM+"-");
		String sD = Integer.valueOf(sHexDay, 16)+"";
		sD = sD.length()==2 ? sD:"0"+sD;
		sb.append(sD+" ");
		String sH = Integer.valueOf(sHexHour, 16)+"";
		sH = sH.length()==2 ? sH:"0"+sH;
		sb.append(sH+":");
		String sm = Integer.valueOf(sHexMinute, 16)+"";
		sm = sm.length()==2 ? sm:"0"+sm;
		sb.append(sm+":");
		String sS = Integer.valueOf(sHexSecond, 16)+"";
		sS = sS.length()==2 ? sS:"0"+sS;
		sb.append(sS+"");
		System.out.println("time: "+sb.toString());
		
		return sb.toString();
	}

    ArrayList<Byte> mPacket = new ArrayList<Byte>();
    
    /**
     *处理数据流程
     *@author zealjiang
     *created at 2016/3/25 11:42
     */
    public void process(){
        /**
         * 方法一：如下：会出现线程阻塞，数据丢失的问题
         * 
         */
        //第一步：创建一个存储buffer数据的容器ArrayList<Byte>
        //第二步：从buffer中取一个整包数据，处理这一整包数据（线程阻塞），处理完成后清空这一整包数据
        //第三步：如果当前的buffer中还有数据，再次使用这个容器存储一整包数据(如果buffer已读完就读下一个buffer),重复第二步和第三步操作
        //第四步：当流中无数据后，关闭流和通道

        /**
         * 方法二：如下，用异步线程+队列处理数据
         */
        //第一步：创建一个存储buffer数据的容器ArrayList<Byte>和一个队列
        //第二步：从buffer中取一个整包数据，将这一包数据复制一份交给一个队列，然后清空这一整包数据  绘图线程从队列里取包数据处理
        //第三步：如果当前的buffer中还有数据，再次使用这个容器存储一整包数据(如果buffer已读完就读下一个buffer),重复第二步和第三步操作
        //第四步：当流中无数据后，关闭流和通道
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
     * 注意：1、当连接关闭后，最后一包的数据不会存到队列里;
     *      2、初次生成包时，如果第一个字节非类型ID头，这一非整包数据不会存入队列
     *@author zealjiang
     *created at 2016/3/25 16:17
     */
    public void createPacket(byte[] buffer,int count){
        if(buffer==null||count==0){
            return;
        }
        
        for (int i=0;i<count;i++) {
            boolean boo = isPacketId(buffer[i]);
            if (boo){
                if(mPacket.size()!=0){
                    //生成完成，添加到队列里
                    //PacketQueue.getInstance().put(mPacket.clone());
                    testDecryptData((ArrayList<Byte>)mPacket.clone());
                    
                    //清空
                    mPacket.clear();
                    //存入新的包头类型ID
                    mPacket.add(buffer[i]);

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

    }
    
    /**
     * 解密一包数据
     * @author zealjiang
     * @date 2016年5月26日 下午4:55:55
     * @param list
     *
     */
    private void testDecryptData(ArrayList<Byte> list){
        if((int)(list.get(0))==Integer.valueOf("60",16)){//如果波形信息
            //还原为原始数据
            OriginalPacket op = handlePacket(list);
            list = op.getData();
            //将数据返回调用此类的对象
            if(mDecryptPacketListener!=null){
            	mDecryptPacketListener.decryptData(list);
            }

        }
    }
    
    /**
     * 调用这个类的类接收成包的解密数据，需要实现这个接口
     * @author zealjiang
     * @time 2016年5月26日下午5:01:39
     */
    public interface DecryptPacketListener{
    	public abstract void decryptData(ArrayList<Byte> list);
    }
    public void setDecryptPacketListener(DecryptPacketListener decryptPacketListener){
    	mDecryptPacketListener = decryptPacketListener;
    }
    

    /**
     * 生成一个完整的数据包
     * @return
     */
    public boolean createPacket(byte[] buffer,int count,ArrayList<Byte> packet){
        if(buffer==null||count==0){
            return false;
        }

        for (int i=0;i<count;i++) {
            boolean boo = isPacketId(buffer[i]);
            if (boo){
                if(packet.size()!=0){
                    //生成完成
                    return true;
                }else{
                    packet.add(buffer[i]);
                }
            }else{
                if(packet.size()!=0){
                    packet.add(buffer[i]);
                }else{
                    continue;
                }
            }
        }

        return false;
    }


    /**
     *将下位机发送过来的一包数据还原为原始数据
     *@author zealjiang
     *created at 2016/3/24 16:42
     *@param packet 加密的一包数据
     */
    public OriginalPacket handlePacket(ArrayList<Byte> packet){
        OriginalPacket originalPacket = new OriginalPacket();

        //校验
        String info = verifyPacket(packet);
        if(info.length()>0){//包有错
            originalPacket.setError(info);
            return originalPacket;
        }

        originalPacket.setID(packet.get(0));
        ArrayList<Byte> orList = getOriginalPacket(packet);
        //set 原始数据的数据部分
        originalPacket.setData(orList);

        //set 校验码
        originalPacket.setVerify(packet.get(packet.size()-1));

        return originalPacket;
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
     *将一组数据还原为原始数据
     *所有包的数据头依次包含数据字节的最高位，如：数据头的bit0为数据1的bit7,数据头的bit1为数据2的bit7
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
     *校验包数据是否有错，如果有错返回错误信息，无错返回""字符串
     *@author zealjiang
     *created at 2016/3/24 11:14
     *
     */
    private String verifyPacket(ArrayList<Byte> packet){
        if(packet==null||packet.size()<=4){
            //最短的包数据包含包头ID(1个字节)、数据头(1个字节)、数据(1个字节)、校验和(1个字节)共4个字节
            return "包长度太段,packet有错";
        }

        if(packet.get(0)<0){
            //是包类型ID
            return "包头类型ID,即第"+1+"个字节最高位非0";
        }

        for (int i = 1; i < packet.size(); i++) {
            if(packet.get(i)>0){//byte的取值范围是-128(0x80)~127(0x7F)
                return "第"+(i+1)+"个字节最高位非1";
            }
        }

        return "";
    }


    /**
     * 把byte转为字符串的bit
     */
    private String byteToBit(byte b) {
        return  ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);


    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    private byte[] getByteArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }

        return array;
    }
    
    /**
     * Created by zealjiang on 2016/3/24.
     */
    public class OriginalPacket extends Message {
        private byte ID;
        private ArrayList<Byte> data;
        private byte verify;

        public byte getID() {
            return ID;
        }

        public void setID(byte ID) {
            this.ID = ID;
        }

        public ArrayList<Byte> getData() {
            return data;
        }

        public void setData(ArrayList<Byte> data) {
            this.data = data;
        }

        public byte getVerify() {
            return verify;
        }

        public void setVerify(byte verify) {
            this.verify = verify;
        }
    }
    
    /**
     * Created by zealjiang on 2016/3/24.
     */
    public class Message {

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
