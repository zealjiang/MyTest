/**
 * 2013-12-4
 * ����9:28:33
 */
package com.string;

import org.junit.Test;

/**
 * @author Administrator
 * @time 2013-12-4����9:28:33
 */
public class RegularExpression {

	/**
	 * "\." ƥ��С���㣨.������;"." С�������ƥ����˻��з���\n�����������һ���ַ�
	 *	"\\" ���� "\" ����
	 * 
	 * @author: zj
	 * @time: 2013-12-4����9:43:39
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
