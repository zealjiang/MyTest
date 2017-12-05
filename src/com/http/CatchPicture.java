package com.http;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatchPicture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����ץȡͼƬ�� ������ʽ
		String regular="[*]<b>.*?</b><br/><img src=\"(.*?)\" border=0 alt=\'(.*?)\' style=\".*?\" class=\".*?\">";
		List<Picture> list=new CatchPicture().lookWeiboPic("http://v.hao123.com/dianying","utf-8",regular,"2,1");
		System.out.println(list.size());
	}
	//����URL�鿴��վ�ϵ�ͼƬ
	public List<Picture> lookWeiboPic(String url,String charset,String regular,String attIndex){
		List<Picture> list=new ArrayList<Picture>();
		try {
			//��ȡ��д��url
			//�ж�������վ ��ȡ ������ʽ
			//��ȡͼƬ��ŵ� list����
			if(url.equals("")==false){
					String htmls = getPageSource(url.trim(),charset);
					Pattern pattern =null;
					pattern = Pattern.compile(regular.trim());
					if(htmls.equals("")==false){
						Matcher matcher = pattern.matcher(htmls);
						
						//�õ���������˳��
						String[] sort = regular.trim().split(","); //�±꣺0 ��ʾ ����title �� 1 ��ʾ ͼƬ·�� 
						//�жϺ�׺�� �õ���վ������ͷ�� http://www.moonbasa.com/p-032111106.html-->�õ� http://www.moonbasa.com
						String[] suffix;
						suffix =url.trim().split("cn");
						String httphread = "";
						if (suffix.length > 1) {
							httphread = suffix[0] + "cn";
	
						} else {
							suffix = url.trim().split("com");
							httphread = suffix[0] + "com";
						}
						//ѭ��ƥ���ҵ���
						while(matcher.find()){
							Picture picture=new Picture();
							
							//ƥ���title
							if (-1 == Integer.parseInt(sort[0])) {
								// ҳ����ץ��������
								picture.setTitle("");
							} else {
								// ȥ�����#
								String title=matcher.group(Integer.parseInt(sort[0])).replace("#", " ");
								picture.setTitle(title);
							}
							
							//ƥ���source
							if (-1 == Integer.parseInt(sort[1])) {
								// ҳ����ץ����ͼƬ·��
								picture.setSource("");
							}else{
								String webImgUrl=matcher.group(Integer.parseInt(sort[1]));
								//�ж��Ǿ���·���������·��
								String[] pathType=webImgUrl.split(":");
								if(pathType.length>1){
									//����·��
									picture.setSource(webImgUrl);
								}else{
									//�ж����·���Ƿ���..
									pathType=webImgUrl.split("\\.\\.");
									if(pathType.length>1){
										picture.setSource(httphread+pathType[1]);
									}else{
										if(webImgUrl.startsWith("/")){
											picture.setSource(httphread+pathType[0]);
										}else{
											picture.setSource(httphread+"/"+pathType[0]);
										}
									}
								}
							}
							String upPath=upload(picture.getSource(),"d:\\image\\");
							picture.setUpPath(upPath);
							list.add(picture);
						}//--end while
					}
		
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	} 
	
	/**
	 * ������··����ȡ ҳ��Դ��
	 * 
	 * @param pageUrl
	 * @param encoding
	 * @return
	 */
	public String getPageSource(String pageUrl, String encoding) {
		StringBuffer sb = new StringBuffer();
		try {
			// ����һURL����
			URL url = new URL(pageUrl);
			// ʹ��openStream�õ�һ���������ɴ˹���һ��BufferedReader����
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), encoding));
			String line;
			// ��ȡwww��Դ
			while ((line = in.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			in.close();
		} catch (Exception ex) {
			System.err.println("ex :" + ex);
		}
		return sb.toString();
	}
	
	/**
	 * �ϴ� ͼƬ 
	 * @param urlStr
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public String upload(String urlStr,String path) throws Exception{
		Calendar calendar = Calendar.getInstance();
		String month = calendar.get(Calendar.YEAR) + "/"
				+ (calendar.get(Calendar.MONTH) + 1);
		String filename = java.util.UUID.randomUUID().toString()
				+ getExtension(urlStr);
		path =path + month + "/";
		download(urlStr,path,filename);
		return path+month + "/" + filename;
	}
	/**
	 * ����·�� ����ͼƬ Ȼ�� ���浽��Ӧ��Ŀ¼��
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	public void download(String urlString, String filename,String savePath) throws Exception {
	    // ����URL
	    URL url = new URL(urlString);
	    // ������
	    URLConnection con = url.openConnection();
	    //���������·��
	    con.setConnectTimeout(5*1000);
	    // ������
	    InputStream is = con.getInputStream();
	
	    // 1K�����ݻ���
	    byte[] bs = new byte[1024];
	    // ��ȡ�������ݳ���
	    int len;
	    // ������ļ���
	   File sf=new File(savePath);
	   if(!sf.exists()){
		   sf.mkdirs();
	   }
	   OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
	    // ��ʼ��ȡ
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // ��ϣ��ر���������
	    os.close();
	    
	    is.close();
	} 
	
/**
 * �����ļ��� ��ȡ�ļ��ĺ�׺��
 * @param fileUrl
 * @return
 */
 public String getExtension(String fileUrl){
	 return fileUrl.substring(fileUrl.lastIndexOf("."), fileUrl.length());
 }
}


