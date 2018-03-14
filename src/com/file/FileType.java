package com.file;

import java.util.Arrays;
import java.util.List;

import org.apache.http.util.TextUtils;

public class FileType {

	
	public static void main(String[] args) {
		String a = "http://ww.jpg";
		String b = "http://ww.mp4";
		System.out.println(isPicOrVideo(a));
		System.out.println(isPicOrVideo(b));
	}
	
    /**
     * 判断是图片文件还是视频文件
     * @param path
     * @return
     */
    public static String isPicOrVideo(String path){
        String suffix = null;
        if(TextUtils.isEmpty(path)){
            return "";
        }

        if (!path.contains(".")) return "";
        int pos = path.lastIndexOf(".");
        if (pos > 0 && path.length()>pos+1) {
            suffix = path.substring(pos+1);
        }
        suffix = suffix.toUpperCase();

        //判断是否是图片
        //JPEG、TIFF、RAW、BMP、GIF、PNG
        String[] picSuffixArray = {"JPEG","JPG","TIFF","RAW","BMP","GIF","PNG"};
        List<String> picList = Arrays.asList(picSuffixArray);
        if(picList.contains(suffix)){
            return "pic";
        }
        //判断是否是视频
        //wmv、asf、asx、rm、 rmvb、mp4、3gp、mov、m4v、avi、dat、mkv、flv、vob
        String[] videoSuffixArray = {"WMV","ASF","ASX","RM","RMVB","MP4","3GP","MOV","M4V","AVI","DAT","MKV","FLV","VOB"};
        List<String> videoList = Arrays.asList(videoSuffixArray);
        if(videoList.contains(suffix)){
            return "video";
        }
        return "other";
    }
}
