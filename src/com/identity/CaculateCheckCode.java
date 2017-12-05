package com.identity;

public class CaculateCheckCode {

	public static void main(String[] args) {
		
		String identity = getIdentity("130481","19860804","男");
		System.out.println("你的身份证号是： "+identity);
		System.out.println("================== ");
		String identity2 = checkIDParityBit("13048119580808693");
		System.out.println("你的身份证号是： 13048119580808693"+identity2);
		System.out.println("================== ");

		System.out.println("================== ");
		String[] identitys = getIdentity("130481","19860804","男",100);
		for(int i=0;i<identitys.length ;i++){
			//System.out.println("identitys["+i+"]" +identitys[i]);
		}
	}
	
	/**
	 * 
	 * @param area 6位的地区码
	 * @param birthday 8位的出生日期
	 * @param gender 性别 "男"或"女"
	 * @return 返回一个合法的身份证号
	 */
	public static String getIdentity(String area,String birthday,String gender){
		
		String str = "";
		int sexCode = -1;
		if(gender.equals("男")){
			sexCode = generateRandomjo(1);
		}else if(gender.equals("女")){
			sexCode = generateRandomjo(0);
		}
		if(sexCode==-1){
			return "传入的性别格式不对";
		}
		//拼成一个十七位的字符串
		str = area + birthday + generateRandom() + sexCode;
		String checkCode = checkIDParityBit(str);
		if(checkCode.equals("false")){
			System.out.println("传入的十七位数字有问题");
		}
		
		str += checkCode;
		
		return str;
	}
	
	
	/**
	 * 
	 * @param area 6位的地区码
	 * @param birthday 8位的出生日期
	 * @param gender 性别 "男"或"女"
	 * @param num 生成多少个合法的身份证号
	 * @return 返回num个合法的身份证号
	 */
	public static String[] getIdentity(String area,String birthday,String gender,int num){
		
		String[] identity = new String[num];
		
		for(int i=0;i<num;i++){
			String str = "";
			int sexCode = -1;
			if(gender.equals("男")){
				sexCode = generateRandomjo(1);
			}else if(gender.equals("女")){
				sexCode = generateRandomjo(0);
			}
			if(sexCode==-1){
				return new String[]{ "传入的性别格式不对"};
			}
			//拼成一个十七位的字符串
			str = area + birthday + generateRandom() + sexCode;
			String checkCode = checkIDParityBit(str);
			if(checkCode.equals("false")){
				System.out.println("传入的十七位数字有问题");
			}
			
			str += checkCode;
			
			identity[i] = str;
		}

		
		return identity;
	}
	
	
	/**
	 * 随机产生1个10到99的整数（两位数）
	 */
	public static int  generateRandom(){
		
		int var = 10+(int) (Math.random()*89);
		return var;
	}
	
	/**
	 * 随机产生1个0到9的偶数或奇数
	 * @param	如果str是0，表示返回一个0到9到偶数，str为1返回一个0到9的奇数
	 * @return	返回校验码
	 */
	public static int  generateRandomjo(int str){
		int var = (int) (Math.random()*8);
		if(str==0){		
			if(var%2==0){
				return var;
			}else if(var%2!=0&&var<5){
				return var + 1;
			}else if(var%2!=0&&var>5){
				return var - 1;
			}
		}else if(str==1){			
			if(var%2!=0){
				return var;
			}else if(var%2==0&&var<5){
				return var + 1;
			}else if(var%2==0&&var>5){
				return var - 1;
			}
		}
		return -1;
	}
	
	/**
	 * 18位身份证校验码验证
	 * @param certiCode	身份证号字符串
	 * @return	返回校验码
	 */
    public static String checkIDParityBit(String certiCode) {         
    	String checkCode = "";
    	if (certiCode == null || "".equals(certiCode))           
    		return "false";         
    	int wi[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    	int ki[] = {1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    	if (certiCode.length() == 17) {           
    		int i = 0;
    		int i18 = 0;
    		for (int k = 0; k < 17; k++) {            
    			char c = certiCode.charAt(k);          
    			int j = 0;                           			
    			if (c <= '9' || c >= '0')  {
    				j = c - 48; 
    			}else 
    				return "false";
    			i += j * wi[k]-i18;
    		} 

    		int mod = i % 11; 

    		checkCode = ki[mod]+"";
 
    		if(checkCode.equals("10")){
				checkCode = "X";
			}
    		}   
    	return checkCode;    
    	}
}
