package com.threadpool;

public class ConcurrentCalculatorTest {

	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 };
		ConcurrentCalculator calc = new ConcurrentCalculator();
		long sum = calc.sum(numbers);
		System.out.println(sum);	
		calc.close();
	}

}
