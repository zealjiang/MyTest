package com.recursion;

/**
 * 递归调用 计算5的阶乘（result = 5*4*3*2*1）
 * 
 * @author zealjiang
 * @time 2016年5月11日下午4:26:37
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
