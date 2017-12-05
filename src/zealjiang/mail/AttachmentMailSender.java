package zealjiang.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import sun.misc.BASE64Encoder;

/**  
* ���ʼ��������������ʼ���������  
*/   
public class AttachmentMailSender  {   
 
	private String host = "";  //smtp������
	 private String from = "";  //�����˵�ַ
	 private String to = "";    //�ռ��˵�ַ
	 private String affix = ""; //������ַ
	 private String affixName = ""; //��������
	 private String user = "";  //�û���
	 private String pwd = "";   //����
	 private String subject = ""; //�ʼ�����
	 
	 public void setAddress(String from,String to,String subject){
	  this.from = from;
	  this.to   = to;
	  this.subject = subject;
	 }
	 
	 public void setAffix(String affix,String affixName){
	  this.affix = affix;
	  this.affixName = affixName;
	 }
	 
	 public void send(String host,String user,String pwd) {
	  this.host = host;
	  this.user = user;
	  this.pwd  = pwd;
	      
	        Properties props = new Properties();
	      
	        //���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
	        props.put("mail.smtp.host", host);
	        //��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤
	        props.put("mail.smtp.auth", "true");
	        //�øո����úõ�props���󹹽�һ��session
	        Session session = Session.getDefaultInstance(props);
	      
	        //������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ
	        //�ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
	        session.setDebug(true);
	      
	        //��sessionΪ����������Ϣ����
	        MimeMessage message = new MimeMessage(session);
	        try{
	         //���ط����˵�ַ
	            message.setFrom(new InternetAddress(from));
	           //�����ռ��˵�ַ
	            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	           //���ر���
	            message.setSubject(subject);
	          
	            // ��multipart�����������ʼ��ĸ����������ݣ������ı����ݺ͸���
	            Multipart multipart = new MimeMultipart();         
	          
	          
	            //�����ʼ����ı�����
	            BodyPart contentPart = new MimeBodyPart();
	            contentPart.setText("�ڶ��ַ���������");
	            multipart.addBodyPart(contentPart);
	            //���Ӹ���
	            BodyPart messageBodyPart= new MimeBodyPart();
	            DataSource source = new FileDataSource(affix);
	            //���Ӹ���������
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            //���Ӹ����ı���
	            //�������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
	            BASE64Encoder enc = new BASE64Encoder();
	            messageBodyPart.setFileName("=?GBK?B?"+enc.encode(affixName.getBytes())+"?=");
	            multipart.addBodyPart(messageBodyPart);
	          
	          
	            //��multipart����ŵ�message��
	            message.setContent(multipart);
	            //�����ʼ�
	            message.saveChanges();
	            //   �����ʼ�
	            Transport transport = session.getTransport("smtp");
	            //���ӷ�����������
	            transport.connect(host, user, pwd);
	            //���ʼ����ͳ�ȥ
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }  
	  
	    public static void main(String[] args){
	     AttachmentMailSender cn = new AttachmentMailSender();
	     //���÷����˵�ַ���ռ��˵�ַ���ʼ�����
	     cn.setAddress("zealjiang@126.com","zealjiang@126.com","һ����������JavaMail�ʼ�(����)");
	     //����Ҫ���͸�����λ�úͱ���
	     cn.setAffix("D:/jiuzhaigou2.1.4.txt","��կ����");
	     //����smtp�������Լ�������ʺź�����
	     cn.send("smtp.126.com","zealjiang@126.com","��������");
	    }
}