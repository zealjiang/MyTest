package com.byteArray;

import java.util.LinkedList;

/**
 * Created by zealjiang on 2016/3/28 16:42.
 */
public class Command {

	public static void main(String[] args) {
		Command command = new Command();
		
		//LinkedList<Byte> list = command.createEncryptPacket("02", "34,43");
		LinkedList<Byte> list = command.createEncryptPacket("10", "07,e0,03,1d,0b,19,1c,e2,12,93,ff,c0,00");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(Integer.toHexString(list.get(i)&0xFF)+" ");
		}
	} 

    enum COMMAND{
        CONNECT,DISCONNECT,WAVE_TRANS,WAVE_STOP;
    }

    //连接

    //断开连接

    //波形传输

    //波形停止

    public byte[] getCommandPacket(COMMAND command){

        return null;
    }

    /**
     *连接命令
     *@author zealjiang
     *created at 2016/3/28 16:53
     */
    private void connectCreate(){
        String id = "0x02";
        String data = "0x34,0x43";
    }


    /**
     *生成加密后的包数据
     *@author zealjiang
     *created at 2016/3/29 11:05
     * @param id 包类型ID (十六进制：如"02")
     * @param data 包原数据 (十六进制,用逗号隔开多个原数据：如"02,45,a3")
     * @return LinkedList<Byte>
     */
    public LinkedList<Byte> createEncryptPacket(String id,String data){
        LinkedList<Byte> list = new LinkedList<Byte>();
        //存id
        int iId = Integer.valueOf(id,16);
        list.add((byte) iId);
        //存data
        LinkedList<Byte> dataList = createEncryptData(data);
        for (int i = 0; i < dataList.size(); i++) {
            list.add(dataList.get(i));
        }
        //存校验码
        list.add(createVerify(id,dataList));
        return list;
    }

    /**
     *生成校验和字节
     *@author zealjiang
     *created at 2016/3/28 17:21
     * @param id 包类型ID (十六进制：如"02")
     * @param list 包加密数据
     *
     */
    private byte createVerify(String id,LinkedList<Byte> list){

        if(id==null||list==null){
            return 0;
        }
        int sum = 0;
        int iId = Integer.valueOf(id, 16);
        sum += iId;

        for(int i=0;i<list.size();i++){
            sum += list.get(i);
        }

        int low8bit = (byte)sum & 0xFF;
        int verify = (byte)low8bit | 0x80;
        return (byte)verify;
    }

    /**
     *原数据数据，多个数据中间用逗号隔开
     *@author zealjiang
     *created at 2016/3/29 11:02
     */
    private LinkedList<Byte> createEncryptData(String data){
        if(data==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] datas = data.split(",");
        //原数据最高位
        int[] highBit = new int[datas.length];
        //原数据对应的加密数据
        byte[] encryptData = new byte[datas.length];

        for(int i=0;i<datas.length;i++){
            int decimal = Integer.valueOf(datas[i],16);
            highBit[i] = decimal>>7;
            encryptData[i] = (byte)(decimal | 0x80);
        }

        //加上数组头，一组1个数据头+7个有效数据
        int groupNum = datas.length/7;
        int remainder = datas.length%7;
        if(remainder!=0){
            groupNum +=1;
        }

        LinkedList<Byte> list = new LinkedList<Byte>();
        StringBuilder sHb = new StringBuilder();
        for (int i = 0; i < groupNum; i++) {
            //计算当前组的数据头和数据尾
            int end = 0;
            end = datas.length>7*(i+1) ? 7*(i+1):datas.length;
            int start = 7*i;

            //计算出每组数据的数组头
            for (int j = end - 1; j >= start; j--) {
                sHb.append(highBit[j]);
            }

            int decimal = Integer.valueOf(sHb.toString(),2);
            //System.out.println("decimal: "+decimal);
            byte head = (byte)(decimal | 0x80);
            sHb.setLength(0);//清空		
            //保存一组数据
            list.add(head);
            for (int j = start; j < end; j++) {
                list.add(encryptData[j]);
            }

        }

        return list;
    }

}
