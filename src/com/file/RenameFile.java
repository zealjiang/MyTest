package com.file;

import java.io.File;

public class RenameFile {

	public static void main(String[] args) {
		File file = new File("e:/ccwj.RMVB");
		boolean boo = renameFile(file,"f:/ccwjj.RMVB");
	}
	
	
	public static boolean renameFile(File sFile,String newName){
		boolean isSuc = false;
		try{
			File nFile = new File(newName);
			isSuc = sFile.renameTo(nFile);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isSuc;
	}
}
