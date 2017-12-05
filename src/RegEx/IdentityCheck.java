package RegEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import RegEx.CheckNumric;
/**
 * ���֤У��
 * @author zealjiang
 * @time 2015��12��25������4:10:52
 */
public class IdentityCheck {

	public static void main(String[] args) {
		boolean boo;
		String identity1 = "130481198708046932";
		String identity2 = "610111198911254568";
		String identity3 = "342625196810050032";
		String identity4 = "11010219800307070X";
		boo = checkIDParityBit(identity1);
		System.out.println("boo>>"+boo);
		int i = checkCertiCode(identity4);
		System.out.println("i :"+i);
		
		IdentityCheck idecheck = new IdentityCheck();
		idecheck.checkIdentityC();
	}
	
	/**
	 * 18λ���֤У������֤
	 * @param certiCode	���֤���ַ���
	 * @return	У������ȷ����Ϊtrue,���򷵻�false
	 */
    private static boolean checkIDParityBit(String certiCode) {         
    	boolean flag = false;         
    	if (certiCode == null || "".equals(certiCode))           
    		return false;         
    	int wi[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    	int ki[] = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    	if (certiCode.length() == 18) {           
    		int i = 0;
    		int i18 = 0;
    		for (int k = 0; k < 18; k++) {            
    			char c = certiCode.charAt(k);          
    			int j;                
    			if (c == 'X')              
    				j = 10;               			
    			else if (c <= '9' || c >= '0')               
    				j = c - 48;         
    			else               
    				return flag;
    			if(k==17){
    				i18 = j;
    			}
    			System.out.print("  "+j);
    			i += j * wi[k]-i18;
    			System.out.println("  "+i);
    		} 
    		System.out.println("i>>"+i);
    		int mod = i % 11; 
    		System.out.println("mod>>"+mod+"ki>>"+ki[mod]);
    		if (ki[mod] == i18)           
    			flag = true;
    	}    
    	return flag;    
    } 
    
      
     

    /**
     * У�����֤
     * @param certiCode
     * @return 0--У��ɹ�;1--λ������; 2--���ո�ʽ���� ; 3--У��λ���� ; 4--�����쳣;5--�ַ��쳣;
     * 
     */
    private static int checkCertiCode(String certiCode) {     
    	try {          
    		if (certiCode == null || certiCode.length() != 15          
    				&& certiCode.length() != 18)       
    			return 1;           
    		String s1;         
    		String s2;      
    		String s3;         
    		if (certiCode.length() == 15) {  
    			if (!CheckNumric.checkFigure(certiCode)) {   
    				return 5;      
    				}         
    			s1 = "19" + certiCode.substring(6, 10);
    			s2 = certiCode.substring(8, 10);       
    			s3 = certiCode.substring(10, 12);   
    			System.out.println("s1:"+s1+" s2:"+s2+" s3:"+s3);
    			if (!CheckTimeFormat.checkDate(s1, s2, s3))        
    				return 2;          
    			}             
    		if (certiCode.length() == 18) {   
    			if (!CheckNumric.checkFigure(certiCode.substring(0, 17))) {  
    				return 5;         
    				}             
    			s1 = certiCode.substring(6, 10);      
    			s2 = certiCode.substring(10, 12);     
    			s3 = certiCode.substring(12, 14); 
    			System.out.println("s1:"+s1+" s2:"+s2+" s3:"+s3);
    			if (!CheckTimeFormat.checkDate(s1, s2, s3))     
    				return 2;           
    			if (!checkIDParityBit(certiCode))      
    				return 3;        
    			}     
    		}      
    	catch (Exception exception) {     
    		return 4;       
    		}       
    	return 0;    
    	} 
    
    
    /**
     * 
     * @note У�����֤�е������Ƿ�Ϸ�
     * @author zealjiang
     * @time 2015��12��25������2:59:09
     * @param certiCode ���֤��
     * @return 0--У��ɹ�;1--λ������; 2--���ո�ʽ���� ; 3--У��λ���� ; 4--�����쳣;5--�ַ��쳣;
     */
    public int checkIdentityDate(String certiCode){
    	try {          
    		if (certiCode == null || certiCode.length() != 15          
    				&& certiCode.length() != 18){       
    			return 1;      
    		}
    		String s1;         
    		String s2;      
    		String s3;         
    		if (certiCode.length() == 15) {  
    			if (!CheckNumric.checkFigure(certiCode)) {   
    				return 5;      
    			}         
    			s1 = "19" + certiCode.substring(6, 10);
    			s2 = certiCode.substring(8, 10);       
    			s3 = certiCode.substring(10, 12);   
    			if (!CheckTimeFormat.checkDate(s1, s2, s3))        
    				return 2;          
    		}             
    		if (certiCode.length() == 18) {   
    			if (!CheckNumric.checkFigure(certiCode.substring(0, 17))) {  
    				return 5;         
    			}             
    			s1 = certiCode.substring(6, 10);      
    			s2 = certiCode.substring(10, 12);     
    			s3 = certiCode.substring(12, 14); 
    			if (!CheckTimeFormat.checkDate(s1, s2, s3))     
    				return 2;   			
    		}     
		}catch (Exception exception) {     
			return 4;       
		}
    	return 0;
    }
    
	/**
	 * �ж����֤��Ҫô��15λ��Ҫô��18λ�����һλ����Ϊ��ĸ����д����������е������ա�
	 * @note 
	 * @author zealjiang
	 * @time 2015��12��25������1:37:25
	 */
    @Test
	public void checkIdentity() {
		while(true){
			//��ʾ�û��������֤��
			System.out.println("���������֤���룺");
			//ͨ�����������û����֤��
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
			String idNum=null;
			try {
				idNum= consoleReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//�����б��û����֤�ŵ�������ʽ��Ҫô��15λ��Ҫô��18λ�����һλ����Ϊ��ĸ��
			Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
			//ͨ��Pattern���Matcher
			Matcher idNumMatcher = idNumPattern.matcher(idNum);
			//�ж��û������Ƿ�Ϊ���֤��
			if(idNumMatcher.matches()){
				System.out.println("���ĳ����������ǣ�");
				//����ǣ�����������ʽ��ȡ�����֤�еĳ�������
				Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//���֤�ϵ�ǰ6λ�Լ�����������
				//ͨ��Pattern���Matcher
				Matcher birthDateMather= birthDatePattern.matcher(idNum);
				//ͨ��Matcher����û��ĳ���������
				if(birthDateMather.find()){
					String year = birthDateMather.group(1);
					String month = birthDateMather.group(2);
					String date = birthDateMather.group(3);
					//����û��ĳ���������
					System.out.println(year+"��"+month+"��"+date+"��");				
				}	
			}else{
				//������ǣ������Ϣ��ʾ�û�
				System.out.println("������Ĳ��������֤��");
			}
		}
	}
    
    /**
     * 
     * @note 
     * @author zealjiang
     * @time 2015��12��25������2:19:58
     */
    @Test
	public void checkIdentityC() {
/*		1) ���֤������ʽ(15λ) 
		^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$
		forJava:  Pattern p = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		------ ------ ------ ˵�� start ------ ------ ------
		15λ���֤�����λ�ĺ���: 
		1-2λʡ����������ֱϽ�д��룻 
		3-4λ�ؼ��С��ˡ������ݴ��룻 
		5-6λ�ء��ؼ��С������룻 
		7-12λ����������,����670401����1967��4��1��,��18λ�ĵ�һ������ 
		13-15λΪ˳��ţ�����15λ��Ϊ������ŮΪ˫����
		------ ------ ------ ˵�� end ------ ------ ------
		2) ���֤������ʽ(18λ)^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$
		forJava:  Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		------ ------ ------ ˵�� start ------ ------ ------
		18λ���֤�����λ�ĺ���: 
		1-2λʡ����������ֱϽ�д��룻 
		3-4λ�ؼ��С��ˡ������ݴ��룻 
		5-6λ�ء��ؼ��С������룻 
		7-14λ���������գ�����19670401����1967��4��1�գ� 
		15-17λΪ˳��ţ�����17λ�������ڶ�λ����Ϊ������ŮΪ˫���� 
		18λΪУ���룬0-9��X����Ϊβ�ŵ�У���룬���ɰ�ǰʮ��λ���ִ���ͳһ�Ĺ�ʽ��������ģ�����Ľ����0-10��
		���ĳ�˵�β����0��9�����������X�������β����10����ô�͵���X�����棬��Ϊ�����10��β�ţ���ô���˵����֤�ͱ����19λ��X���������ֵ�10����X������10��
		------ ------ ------ ˵�� end ------ ------ ------*/
		
    	String identity = "11010220160231070X";
		if (identity == null || identity.length() != 15          
				&& identity.length() != 18){
			System.out.println("���֤����λ������");
			return;
		}else{
			Pattern idNumPattern = null;
			if(identity.length()==15){
				//�����б��û����֤�ŵ�������ʽ��Ҫô��15λ��Ҫô��18λ�����һλ����Ϊ��ĸ�� 	
				idNumPattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
			}else if(identity.length()==18){
				//�����б��û����֤�ŵ�������ʽ��Ҫô��15λ��Ҫô��18λ�����һλ����Ϊ��ĸ�� 	
				idNumPattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");		
			}

			//ͨ��Pattern���Matcher
			Matcher idNumMatcher = idNumPattern.matcher(identity);
			//�ж��û������Ƿ�Ϊ���֤��
			if(idNumMatcher.matches()){
				System.out.println("���֤��λ��������ĸУ��ɹ�");
				int i = checkIdentityDate(identity);
				if(i==0){
					System.out.println("���֤�ų�������У��ɹ�");
				}else{
					System.out.println("���֤�ų�������У��ʧ��");
					return;
				}
				//�����15λ������ת��18λ
				if(identity.length()==18){
					boolean boo = checkIDParityBit(identity);
					if(boo){
						System.out.println("���֤��У��λУ��ɹ�");
					}else{
						System.out.println("���֤��У��λУ��ʧ��");
						return;
					}
				}
				System.out.println("���֤��У����ɣ�����ȷ�����֤��");
			}else{
				System.out.println("����������֤�Ų���ȷ");
				return;
			}
		}
	}
    
    @Test
    public void getBirthDayPart() {
    	String identity = "110101198001015890";
    	System.out.println(identity.substring(6, 14));
    	//return identity.substring(6, 14);
    }
   
    
    /**
     * ��15λ���֤����ת����18λ���֤����<br>
     * 15λ���֤������18λ���֤���������Ϊ��<br>
     * 1��15λ���֤�����У�"�������"�ֶ���2λ��ת��ʱ��Ҫ����"19"����ʾ20����<br>
     * 2��15λ���֤�����һλУ���롣18λ���֤�У�У������ݸ���ǰ17λ����
     * 
     * @param cardNumber
     * @return
     */
    private static String contertToNewCardNumber(String oldCardNumber) {
	     StringBuilder buf = new StringBuilder();
	     buf.append(oldCardNumber.substring(0, 6));
	     buf.append("19");
	     buf.append(oldCardNumber.substring(6));
	    // buf.append(CheckIdCard.calculateVerifyCode(buf));
	     return buf.toString();
    }
}
