package zealjiang.scanner;

import java.util.Scanner;

public class MyScanner {
	public static void main(String[] args)
	{

		Scanner in = new Scanner(System.in);
		System.out.println("������һ��������");
		String str = in.nextLine();
		System.out.println("���������䣺");
		int iAge = in.nextInt();
		System.out.println("������ְҵ��");
		//in.nextLine();
		String strJob = in.nextLine();
		//String gg = in.nextLine();
		System.out.println("�����빤���ص㣺");
		String strAddress = in.nextLine();
		System.out.println(str+iAge+strJob+strAddress);
	}
}
