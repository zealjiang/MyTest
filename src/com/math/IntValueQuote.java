package com.math;

/**
 * int 是值引用
 * @author zealjiang
 * @time 2016年5月9日下午6:06:02
 */
public class IntValueQuote {

	/**
	 * 证明int是值引用
	 * @author zealjiang
	 * @date 2016年5月9日 下午6:08:53
	 *
	 */
	private void proveValueQuote(){
		int i = -1,n = -1;
		n = 30;
		i = n;
		n = 40;
		System.out.println("i: "+i+" n: "+n);
	}
}
