package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class CalculateDistance {

	public static void main(String[] args) {
		float a = 346184.8f;
		float result = (a>1000)? a/1000.0f : a; 

		DecimalFormat df = new DecimalFormat("#.#");
		String results = df.format(result);
		
		System.out.println(results);
	}
	
	@Test
	public void handleUniteMeter(){
		double meter = 17052.33;
		double results = (meter>1000)? (meter/1000.0f) : meter; 
		
		DecimalFormat df = new DecimalFormat("#.#");
		String r = df.format(results);
		String mm = (meter>1000)? r+"km" : r+"m"; 
		
		System.out.println(mm);
	}
}
