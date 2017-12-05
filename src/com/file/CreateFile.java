/**
 * 2013-12-4
 * ионГ11:00:24
 */
package com.file;

import java.io.File;
import java.io.IOException;

import org.junit.Test;


/**
 * @author Administrator
 * @time 2013-12-4ионГ11:00:24
 */
public class CreateFile {

	@Test
	public void createFileMethod1(){
		File updateDir = new File("d:", "leadorapkfile");
		//updateDir.mkdir();
		try {
			updateDir.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
