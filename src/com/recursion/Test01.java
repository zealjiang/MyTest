package com.recursion;

/**
 * �ݹ���� ����5�Ľ׳ˣ�result = 5*4*3*2*1��
 * 
 * @author zealjiang
 * @time 2016��5��11������4:26:37
 */
public class Test01 {

	public static void main(String[] args) {

		System.out.println(f(5));

	}

	public static int f(int n) {

		if (1 == n)

			return 1;

		else

			return n * f(n - 1);

	}
}
