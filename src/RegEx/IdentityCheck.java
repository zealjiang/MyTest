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
 * 身份证校验
 * @author zealjiang
 * @time 2015年12月25日下午4:10:52
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
	 * 18位身份证校验码验证
	 * @param certiCode	身份证号字符串
	 * @return	校验码正确返回为true,否则返回false
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
     * 校验身份证
     * @param certiCode
     * @return 0--校验成功;1--位数不对; 2--生日格式不对 ; 3--校验位不对 ; 4--其他异常;5--字符异常;
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
     * @note 校验身份证中的日期是否合法
     * @author zealjiang
     * @time 2015年12月25日下午2:59:09
     * @param certiCode 身份证号
     * @return 0--校验成功;1--位数不对; 2--生日格式不对 ; 3--校验位不对 ; 4--其他异常;5--字符异常;
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
	 * 判断身份证：要么是15位，要么是18位，最后一位可以为字母，并写程序提出其中的年月日。
	 * @note 
	 * @author zealjiang
	 * @time 2015年12月25日下午1:37:25
	 */
    @Test
	public void checkIdentity() {
		while(true){
			//提示用户输入身份证号
			System.out.println("请输入身份证号码：");
			//通过流处理获得用户身份证号
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
			String idNum=null;
			try {
				idNum= consoleReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
			Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
			//通过Pattern获得Matcher
			Matcher idNumMatcher = idNumPattern.matcher(idNum);
			//判断用户输入是否为身份证号
			if(idNumMatcher.matches()){
				System.out.println("您的出生年月日是：");
				//如果是，定义正则表达式提取出身份证中的出生日期
				Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日
				//通过Pattern获得Matcher
				Matcher birthDateMather= birthDatePattern.matcher(idNum);
				//通过Matcher获得用户的出生年月日
				if(birthDateMather.find()){
					String year = birthDateMather.group(1);
					String month = birthDateMather.group(2);
					String date = birthDateMather.group(3);
					//输出用户的出生年月日
					System.out.println(year+"年"+month+"月"+date+"日");				
				}	
			}else{
				//如果不是，输出信息提示用户
				System.out.println("您输入的并不是身份证号");
			}
		}
	}
    
    /**
     * 
     * @note 
     * @author zealjiang
     * @time 2015年12月25日下午2:19:58
     */
    @Test
	public void checkIdentityC() {
/*		1) 身份证正则表达式(15位) 
		^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$
		forJava:  Pattern p = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
		------ ------ ------ 说明 start ------ ------ ------
		15位身份证号码各位的含义: 
		1-2位省、自治区、直辖市代码； 
		3-4位地级市、盟、自治州代码； 
		5-6位县、县级市、区代码； 
		7-12位出生年月日,比如670401代表1967年4月1日,与18位的第一个区别； 
		13-15位为顺序号，其中15位男为单数，女为双数；
		------ ------ ------ 说明 end ------ ------ ------
		2) 身份证正则表达式(18位)^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$
		forJava:  Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		------ ------ ------ 说明 start ------ ------ ------
		18位身份证号码各位的含义: 
		1-2位省、自治区、直辖市代码； 
		3-4位地级市、盟、自治州代码； 
		5-6位县、县级市、区代码； 
		7-14位出生年月日，比如19670401代表1967年4月1日； 
		15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数； 
		18位为校验码，0-9和X。作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，计算的结果是0-10，
		如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10。
		------ ------ ------ 说明 end ------ ------ ------*/
		
    	String identity = "11010220160231070X";
		if (identity == null || identity.length() != 15          
				&& identity.length() != 18){
			System.out.println("身份证号码位数不对");
			return;
		}else{
			Pattern idNumPattern = null;
			if(identity.length()==15){
				//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母） 	
				idNumPattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
			}else if(identity.length()==18){
				//定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母） 	
				idNumPattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");		
			}

			//通过Pattern获得Matcher
			Matcher idNumMatcher = idNumPattern.matcher(identity);
			//判断用户输入是否为身份证号
			if(idNumMatcher.matches()){
				System.out.println("身份证号位数数字字母校验成功");
				int i = checkIdentityDate(identity);
				if(i==0){
					System.out.println("身份证号出生日期校验成功");
				}else{
					System.out.println("身份证号出生日期校验失败");
					return;
				}
				//如果是15位，将其转成18位
				if(identity.length()==18){
					boolean boo = checkIDParityBit(identity);
					if(boo){
						System.out.println("身份证号校验位校验成功");
					}else{
						System.out.println("身份证号校验位校验失败");
						return;
					}
				}
				System.out.println("身份证号校验完成，是正确的身份证号");
			}else{
				System.out.println("您输入的身份证号不正确");
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
     * 把15位身份证号码转换到18位身份证号码<br>
     * 15位身份证号码与18位身份证号码的区别为：<br>
     * 1、15位身份证号码中，"出生年份"字段是2位，转换时需要补入"19"，表示20世纪<br>
     * 2、15位身份证无最后一位校验码。18位身份证中，校验码根据根据前17位生成
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
