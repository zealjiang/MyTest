package zealjiang.bksx.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {  
    public static void main(String[] args) {  
        try {  
            // �������ͷ����׽��֣�IPĬ��Ϊ���أ��˿ں����   
            //DatagramSocket sendSocket = new DatagramSocket();  
  
            // ȷ��Ҫ���͵���Ϣ��   
            String mes = "��ã����շ���";  
  
            // �������ݱ������������ַ����鴫����ʽ�洢�ģ����Դ�ת����   
            byte[] buf = mes.getBytes();  
  
            // ȷ�����ͷ���IP��ַ���˿ںţ���ַΪ���ػ�����ַ   
            int port = 16000;  
            //InetAddress ip = InetAddress.getLocalHost();  
            InetAddress ip = InetAddress.getByName("192.168.1.100");
  
            DatagramSocket sendSocket = new DatagramSocket(1234,ip);  
            // �����������͵����ݱ���   
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip,  
                    1235);  
  
            // ͨ���׽��ַ������ݣ�   
            sendSocket.send(sendPacket);  
  
            // ȷ�����ܷ������ݵĻ���洢�������洢���ݵ��ֽ�����   
            byte[] getBuf = new byte[1024];  
  
/*            // �����������͵����ݱ�   
            DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);  
  
            // ͨ���׽��ֽ�������   
            sendSocket.receive(getPacket);  
  
            // ������������Ϣ������ӡ   
            String backMes = new String(getBuf, 0, getPacket.getLength());  
            System.out.println("���ܷ����ص���Ϣ��" + backMes); */ 
  
            // �ر��׽���   
            sendSocket.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
}  
  

