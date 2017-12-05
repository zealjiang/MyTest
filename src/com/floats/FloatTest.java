package com.floats;

import java.math.BigDecimal;

public class FloatTest {

	public static void main(String[] args) {
		
		float r = 111f;

		BigDecimal b = new BigDecimal(r/12);
		System.out.println(b.setScale(1,BigDecimal.ROUND_UP).floatValue());
		
	}
}
