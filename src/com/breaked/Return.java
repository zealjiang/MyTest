package com.breaked;

public class Return {
	public static void main(String[] args) {
		int k=5;

			while(true){
				System.out.println("..."+k);
				k--;
				if(k==0)
					break;
				return;//这可return可能执行不到，如果k==0,就跳出while循环了，return就不会被执行，所以这个
						//return之后可以有语句
			}
		return;
		
//注意：retrun之后出现语句会报错:	"Unreachable Code"	
//		如：System.out.println("....out");不可出现在return之后
	}
}
