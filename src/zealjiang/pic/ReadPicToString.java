package zealjiang.pic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ReadPicToString {

	
	public static void main(String[] args) {

		//Դ�ļ�
		File file = new File("e:/dx.png");
		String sfString = RFTString(file);
		//��sfStringд���ļ���
		File fpng = new File("e:/pic.png");
		WSTFile(sfString,fpng);		
	}
	
	/**
	 * ��һ��file����һ��String����
	 * @param file Դ�ļ�
	 * @return ����file���ݵ�String����
	 */
	public static String  RFTString(File file){
		
		StringBuilder sb = new StringBuilder();
		try {
			//Ŀ���ļ�
			File ofile = new File("e:/new_dx.txt");
			FileInputStream fis;
			fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(ofile);		
			byte[] b = new byte[1024];
			int rlength = 0;
			while((rlength = fis.read(b, 0, b.length))!=-1){
				for(int i=0;i<rlength;i++){
					//�ֽڵ�ռλ������char��ռλ������ͬ����String�пɴ��char
					sb.append((char)b[i]);
				}
				//��ͼƬ�����������e:/new_dx.txt����
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
	 * ��Stringд���ļ���
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
