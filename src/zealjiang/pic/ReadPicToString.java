package zealjiang.pic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ReadPicToString {

	
	public static void main(String[] args) {

		//源文件
		File file = new File("e:/dx.png");
		String sfString = RFTString(file);
		//将sfString写到文件里
		File fpng = new File("e:/pic.png");
		WSTFile(sfString,fpng);		
	}
	
	/**
	 * 将一个file读成一个String类型
	 * @param file 源文件
	 * @return 返回file内容的String类型
	 */
	public static String  RFTString(File file){
		
		StringBuilder sb = new StringBuilder();
		try {
			//目标文件
			File ofile = new File("e:/new_dx.txt");
			FileInputStream fis;
			fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(ofile);		
			byte[] b = new byte[1024];
			int rlength = 0;
			while((rlength = fis.read(b, 0, b.length))!=-1){
				for(int i=0;i<rlength;i++){
					//字节的占位长度与char的占位长度相同，且String中可存放char
					sb.append((char)b[i]);
				}
				//将图片内容输出到“e:/new_dx.txt”中
				fos.write(b,0,rlength);
			}
			fis.close();
			fos.flush();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * 将String写到文件里
	 * @throws Exception 
	 */
	public static void WSTFile(String str,File saveF){
		try{
			FileOutputStream sfos = new FileOutputStream(saveF);	
			byte[] pbyte = new byte[str.length()];
			for(int i = 0;i<str.length();i++){
				pbyte[i] = (byte)str.charAt(i);	
			}
			sfos.write(pbyte);
			sfos.flush();
			sfos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
