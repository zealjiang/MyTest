package zealjiang.scanner;

import java.util.Scanner;

public class MyScanner {
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个姓名：");
		String str = in.nextLine();
		System.out.println("请输入年龄：");
		int iAge = in.nextInt();
		System.out.println("请输入职业：");
		//in.nextLine();
		String strJob = in.nextLine();
		//String gg = in.nextLine();
		System.out.println("请输入工作地点：");
		String strAddress = in.nextLine();
		System.out.println(str+iAge+strJob+strAddress);
	}
}
