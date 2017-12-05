package com.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

public class HttpPostUploadUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String filepath1 = "d:\\1.png";
		String filepath2 = "d:\\2.png";
		String urlStr = "http://api.konly.cn/t.php";
		//方法一
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("name", "娃哈哈");
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("pic1", filepath1);
		fileMap.put("pic2", filepath2);
		String ret = formUpload(urlStr, textMap, fileMap);
		System.out.println("上传完成："+ret);
		
		//方法二
		//UpLoadRes(urlStr,filepath1,filepath2);
	}

	/**
	 * 上传图片
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @return
	 */
	public static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text  
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file  
			if (fileMap != null) {
				Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					MagicMatch match = Magic.getMagicMatch(file, false, true);
					String contentType = match.getMimeType();
					//String contentType = "image/jpeg";// match.getMimeType();
					System.out.println("contentType :"+contentType); 
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据  
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @note 方法二
	 * @author zealjiang
	 * @time 2015-10-27下午5:22:54
	 * @param url
	 * @param param
	 * @param bitmap
	 * @return
	 */
	public static JSONObject UpLoadRes(String url,String file1,String file2){
		String retSrc = null;
		JSONObject result = null;		  
		//设置要访问的地址
        HttpPost httpRequestHttpPost = new HttpPost(url);
		 MultipartEntity multipartEntity = new MultipartEntity(
				 HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName(HTTP.UTF_8));
	        ContentBody contentBody1 = new FileBody(new File(file1), "image/png");	   
	        ContentBody contentBody2 = new FileBody(new File(file2), "image/png");	
	         try {
	 	        //添加内容
	 	        multipartEntity.addPart("name",new StringBody("娃哈哈"));
	 	    	multipartEntity.addPart("pic1", contentBody1);
	 	    	multipartEntity.addPart("pic2", contentBody2);
	 	        //把要传输的内容放到请求里面
	 	        httpRequestHttpPost.setEntity(multipartEntity);
	 	        //创建客户端对象
	 	       org.apache.http.client.HttpClient  httpClient = new DefaultHttpClient();
	 	        //执行请求并得到返回结果
				HttpResponse httpResponse = httpClient.execute(httpRequestHttpPost);
				retSrc = EntityUtils.toString(httpResponse.getEntity());
				// 生成 JSON 对象
				//result = new JSONObject(retSrc);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} /*catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	         System.out.println("retSrc: "+retSrc);
		return null;
	}

}