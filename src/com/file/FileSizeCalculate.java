package com.file;

/**
 * �ļ���С����
 * @author zealjiang
 * @time 2016��4��19������4:21:53
 */
public class FileSizeCalculate {

	public static void main(String[] args) {
		System.out.println("332: "+convertFileSize(332));
		System.out.println("33212: "+convertFileSize(33212));
		System.out.println("332542: "+convertFileSize(332542));
		System.out.println("3326432: "+convertFileSize(3326432));
		System.out.println("332643254: "+convertFileSize(332643254));
		System.out.println("33264322354: "+convertFileSize(33264322354L));
	}
	
	/**
	 * 
	 * @author zealjiang
	 * @date 2016��4��19�� ����4:22:59
	 * @param size ��λ���ֽ�
	 *
	 */
	private void calculate(long size){
		//if(size>1024*1024)
	}
	
	
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

}
