package zealjiang.time201309.mail;

/**

 * �����乤��

 * 

 * @author MZULE

 * 

 */
public class MailSenderFactory {

	/**

     * ��������

     */

    private static SimpleMailSender serviceSms = null;

 

    /**

     * ��ȡ����

     * 

     * @param type ��������

     * @return �������͵�����

     */

    public static SimpleMailSender getSender() {


	        if (serviceSms == null) {
	
	        serviceSms = new SimpleMailSender("zealjiang@126.com",
	
	            "password");
	
	        }
	
	        return serviceSms;
	
    }
}
