package zealjiang.mail;

public class TestSimpleMailSender {

	public static void main(String[] args){  
		
		/**
		 * QQ����
		 * smtp.qq.com �˿�465��587
		 * pop.qq.com �˿�995  
		 * 
		 * ����126���������ط�������������Ϣ��
		 *   �ʼ�����������	��������ַ 			   �˿ں�
			 POP3������	pop.126.com	     110
			 SMTP������	smtp.126.com	 25
			 IMAP������	imap.126.com	 143
		 */
         //�������Ҫ�������ʼ�  
	     MailSenderInfo mailInfo = new MailSenderInfo();   
	     mailInfo.setMailServerHost("smtp.126.com");   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName("zealjiang@126.com");   
	     mailInfo.setPassword("��������");//������������   
	     mailInfo.setFromAddress("zealjiang@126.com");   
	     mailInfo.setToAddress("zealjiang@126.com");   
	     mailInfo.setSubject("java�����Ͳ���---����");   
	     mailInfo.setContent("java�����Ͳ���---��������");   
	     //�������Ҫ�������ʼ�  
	     SimpleMailSender sms = new SimpleMailSender();  
	     sms.sendTextMail(mailInfo);//���������ʽ   
	     sms.sendHtmlMail(mailInfo);//����html��ʽ  
   }  
}
