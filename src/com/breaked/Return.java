package com.breaked;

public class Return {
	public static void main(String[] args) {
		int k=5;

			while(true){
				System.out.println("..."+k);
				k--;
				if(k==0)
					break;
				return;//���return����ִ�в��������k==0,������whileѭ���ˣ�return�Ͳ��ᱻִ�У��������
						//return֮����������
			}
		return;
		
//ע�⣺retrun֮��������ᱨ��:	"Unreachable Code"	
//		�磺System.out.println("....out");���ɳ�����return֮��
	}
}
