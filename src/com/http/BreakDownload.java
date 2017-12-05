package com.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class BreakDownload {

	

	@Test
	public void download() {
		// ��ʾstep
		int downStep = 1;
		// �ļ��ܴ�С
		int totalSize;
		// �Ѿ����غõĴ�С
		int downloadCount = 0;
		// �Ѿ��ϴ����ļ���С
		int updateCount = 0;
		
		boolean isDownloadOver = false;
		
		String apkName = "jiuzhaigou.apk";
		String url = "http://tour.ishowchina.com/download/jiuzhaigou.apk";
		try {
			InputStream is = null;

			// "http://apkc.mumayi.com/2014/03/19/8/85815/shenmiaotaowangTempleRun_V1.6.2_mumayi_96804.apk")
			// url =
			// "http://down.androidgame-store.com/201406101114/21B86989285A2E81CC5DEBB2984162DE/new/game1/56/110456/opera_1399338752830.apk?f=web_1";
			// url =
			// "http://apk.r1.market.hiapk.com/data/upload/2014/06_10/17/InternetRadio.all_175244.apk";
			URL curl = new URL(url);
			
			//��ȡҪ���ص��ļ���С
			HttpURLConnection httpURLConnection = (HttpURLConnection) curl
					.openConnection();
			httpURLConnection.setDoInput(true);
			// ��ȡ�����ļ���size
			totalSize = httpURLConnection.getContentLength();
			httpURLConnection.disconnect();
			
			
			
			File fileDir = new File("d:/","leadortourapkfile");
			if (!fileDir.exists()) {
				// ����һ��Ŀ¼ʹ��mkdir(),���Ŀ¼��mkdirs()
				fileDir.mkdir();
			}
			File apkFile = new File(fileDir, apkName);
			RandomAccessFile randomApkFile = new RandomAccessFile(apkFile, "rw");
			long fileSize = 0;
			if (apkFile.exists()) {
				// ��ȡ�ļ���С
				fileSize = apkFile.length();
				if (fileSize >= totalSize) {
					// �ļ��Ѿ��������
					isDownloadOver = true;
				} else {
					isDownloadOver = false;
				}

			} else {
				isDownloadOver = false;
			}
			
			//������������
			HttpURLConnection httpURLConnection2 = (HttpURLConnection) curl
					.openConnection();
			httpURLConnection2.setDoInput(true);
			httpURLConnection2.setRequestProperty("Range", "bytes="
					+ fileSize + "-" + (totalSize - 1));
			httpURLConnection2.setDoInput(true);
			httpURLConnection2.connect();
			if (httpURLConnection.getResponseCode() == 404) {
				throw new Exception("fail!");
			}

			if (isDownloadOver == false) {


				is = httpURLConnection2.getInputStream();

				randomApkFile.seek(fileSize);

				try {


					byte[] b = new byte[1024];
					int count = 0;
					while ((count = is.read(b)) > 0) {

						randomApkFile.write(b, 0, count);
						downloadCount += count;
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("download error:" + e.getMessage());

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("download error:" + e.getMessage());
				} finally {
					if (randomApkFile != null) {
						randomApkFile.close();
					}
					if (is != null) {
						is.close();
					}
				}
			}



		} catch (Exception e) {
			// Toast.makeText(this, "�������,����ʧ��!", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}
