package com.math;

import java.text.DecimalFormat;

import org.junit.Test;

public class Format {

	@Test
	public void saveDecimals() {
	    DecimalFormat df = new DecimalFormat("#.0");
	    String text = df.format(1314 * 1.0d/1000);	
	    
	    System.out.println("text: "+text);
	}

}
