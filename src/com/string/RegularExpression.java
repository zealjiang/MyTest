/**
 * 2013-12-4
 * 上午9:28:33
 */
package com.string;

import org.junit.Test;

/**
 * @author Administrator
 * @time 2013-12-4上午9:28:33
 */
public class RegularExpression {

	/**
	 * "\." 匹配小数点（.）本身;"." 小数点可以匹配除了换行符（\n）以外的任意一个字符
	 *	"\\" 代表 "\" 本身
	 * 
	 * @author: zj
	 * @time: 2013-12-4上午9:43:39
	 */
	@Test
	public void splitByBackSlash(){
		String oldVersion = "1.2.2";
		String[] localStrings = oldVersion.split("\\.");
		for(int i=0;i<localStrings.length;i++){
			System.out.println(localStrings[i]);
		}
		
		String localString = localStrings[0] + "." + localStrings[1];
		System.out.println("localString--->"+localString);
	}
	
	@Test
	public void getApkName() {
		String path = "filename=3";
		int index = path.indexOf("filename=");
		if (index < 0) {
			System.out.println("updateVersion.apk");
		} else {
			System.out.println(path.substring(index + "filename=".length(), path.length()));
		}
	}
}
