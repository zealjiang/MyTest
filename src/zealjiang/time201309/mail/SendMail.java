package zealjiang.time201309.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.file.ReadFileToString;

public class SendMail{


	public static void main(String[] args) {
		
	    // �����ʼ�
		
	    SimpleMailSender sms = MailSenderFactory
	
	        .getSender();
	
	    List<String> recipients = new ArrayList<String>();
	
	    recipients.add("411069947@qq.com");
	
	    recipients.add("zealjiang@126.com");
	    
    	String content = ReadFileToString.readFile(new File("b.html"),"gbk");
    	//System.out.println("content:  "+content);
	
    	
	    try {
	

	        for (String recipient : recipients) {
	
	        	        	
	        sms.send(recipient, "��������java�����͵��ʼ�", "����java����"
	
	            +"ʵ�ַ��ʼ����ͣ�����zealjiang"+content);
	        }
	
	    } catch (AddressException e) {
	
	        e.printStackTrace();
	
	    } catch (MessagingException e) {
	
	        e.printStackTrace();
	
	    }

    }


}
