/**
 * 2016年5月11日
 * 下午10:36:37
 */
package com.byteArray;

/**
 * @author zealjiang
 * @time 2016年5月11日下午10:36:37
 */
public class ByteToInt {

	public static void main(String[] args) {
		ByteToInt bti = new ByteToInt();
		bti.positiveNegative();
	}
	
	private void positiveNegative(){
		System.out.println("0x79: "+(byte)0x79);
		System.out.println("0x80: "+(byte)0x80);
		System.out.println("0x81: "+(byte)0x81);
	}
}
