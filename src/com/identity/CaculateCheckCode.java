package com.identity;

public class CaculateCheckCode {

	public static void main(String[] args) {
		
		String identity = getIdentity("130481","19860804","��");
		System.out.println("������֤���ǣ� "+identity);
		System.out.println("================== ");
		String identity2 = checkIDParityBit("13048119580808693");
		System.out.println("������֤���ǣ� 13048119580808693"+identity2);
		System.out.println("================== ");

		System.out.println("================== ");
		String[] identitys = getIdentity("130481","19860804","��",100);
		for(int i=0;i<identitys.length ;i++){
			//System.out.println("identitys["+i+"]" +identitys[i]);
		}
	}
	
	/**
	 * 
	 * @param area 6λ�ĵ�����
	 * @param birthday 8λ�ĳ�������
	 * @param gender �Ա� "��"��"Ů"
	 * @return ����һ���Ϸ������֤��
	 */
	public static String getIdentity(String area,String birthday,String gender){
		
		String str = "";
		int sexCode = -1;
		if(gender.equals("��")){
			sexCode = generateRandomjo(1);
		}else if(gender.equals("Ů")){
			sexCode = generateRandomjo(0);
		}
		if(sexCode==-1){
			return "������Ա��ʽ����";
		}
		//ƴ��һ��ʮ��λ���ַ���
		str = area + birthday + generateRandom() + sexCode;
		String checkCode = checkIDParityBit(str);
		if(checkCode.equals("false")){
			System.out.println("�����ʮ��λ����������");
		}
		
		str += checkCode;
		
		return str;
	}
	
	
	/**
	 * 
	 * @param area 6λ�ĵ�����
	 * @param birthday 8λ�ĳ�������
	 * @param gender �Ա� "��"��"Ů"
	 * @param num ���ɶ��ٸ��Ϸ������֤��
	 * @return ����num���Ϸ������֤��
	 */
	public static String[] getIdentity(String area,String birthday,String gender,int num){
		
		String[] identity = new String[num];
		
		for(int i=0;i<num;i++){
			String str = "";
			int sexCode = -1;
			if(gender.equals("��")){
				sexCode = generateRandomjo(1);
			}else if(gender.equals("Ů")){
				sexCode = generateRandomjo(0);
			}
			if(sexCode==-1){
				return new String[]{ "������Ա��ʽ����"};
			}
			//ƴ��һ��ʮ��λ���ַ���
			str = area + birthday + generateRandom() + sexCode;
			String checkCode = checkIDParityBit(str);
			if(checkCode.equals("false")){
				System.out.println("�����ʮ��λ����������");
			}
			
			str += checkCode;
			
			identity[i] = str;
		}

		
		return identity;
	}
	
	
	/**
	 * �������1��10��99����������λ����
	 */
	public static int  generateRandom(){
		
		int var = 10+(int) (Math.random()*89);
		return var;
	}
	
	/**
	 * �������1��0��9��ż��������
	 * @param	���str��0����ʾ����һ��0��9��ż����strΪ1����һ��0��9������
	 * @return	����У����
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
	 * 18λ���֤У������֤
	 * @param certiCode	���֤���ַ���
	 * @return	����У����
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
