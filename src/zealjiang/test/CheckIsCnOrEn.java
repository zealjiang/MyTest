package zealjiang.test;

public class CheckIsCnOrEn{
	public static void main(String[] args) {
		wordLength("abc");
		System.out.println("===========");
		wordLength("�й�");
		System.out.println("===========");
		isCnorEn('c');
		System.out.println("===========");
		isCnorEn('��');
	}
	
	public static void wordLength(String str){
		System.out.println("str length is :"+str.length());
	}
	
	public static void isCnorEn(char c)
	{
	if(c >= 0x0391 && c <= 0xFFE5){
		System.out.println("������");

	}else if(c>=0x0000 && c<=0x00FF){
		System.out.println("Ӣ���ַ�");
	} 
	
	}
}
