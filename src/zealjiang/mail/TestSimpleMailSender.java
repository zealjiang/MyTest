package zealjiang.mail;

public class TestSimpleMailSender {

	public static void main(String[] args){  
		
		/**
		 * QQ邮箱
		 * smtp.qq.com 端口465或587
		 * pop.qq.com 端口995  
		 * 
		 * 网易126免费邮箱相关服务器服务器信息：
		 *   邮件服务器名称	服务器地址 			   端口号
			 POP3服务器	pop.126.com	     110
			 SMTP服务器	smtp.126.com	 25
			 IMAP服务器	imap.126.com	 143
		 */
         //这个类主要是设置邮件  
	     MailSenderInfo mailInfo = new MailSenderInfo();   
	     mailInfo.setMailServerHost("smtp.126.com");   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName("zealjiang@126.com");   
	     mailInfo.setPassword("邮箱密码");//您的邮箱密码   
	     mailInfo.setFromAddress("zealjiang@126.com");   
	     mailInfo.setToAddress("zealjiang@126.com");   
	     mailInfo.setSubject("java程序发送测试---标题");   
	     mailInfo.setContent("java程序发送测试---邮箱内容");   
	     //这个类主要来发送邮件  
	     SimpleMailSender sms = new SimpleMailSender();  
	     sms.sendTextMail(mailInfo);//发送文体格式   
	     sms.sendHtmlMail(mailInfo);//发送html格式  
   }  
}
