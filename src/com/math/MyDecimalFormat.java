package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class MyDecimalFormat {

	@Test
	public void decimalFormat() {
		DecimalFormat df = new DecimalFormat("0.0");
		System.out.println(df.format(12.12345));
		double a = Double.parseDouble(df.format(12.12345));
		System.out.println(a);
	}
	
	@Test
	public void decimalFormat2() {
		String out = new DecimalFormat("###,###,##0.00").format(2088888888.111d);
		System.out.println(out);
	}
}
