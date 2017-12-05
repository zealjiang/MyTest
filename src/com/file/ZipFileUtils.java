package com.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;



public class ZipFileUtils{
	public static void main(String[] args) {
		zipFile("d:/zip","d:/zip.zip");
	}
	
	
	public static void zipFile(String inPath,String outPath){
		ArrayList<File> filelist= new ArrayList<File>();
		ArrayList<File> resFileList = 
				DirTraversal.zipRefreshFileList(inPath);
		try {
			ZipUtils.zipFiles(resFileList, new File(outPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/**
 *  �ļ��б���
 * @author once
 *
 */
class DirTraversal {
    
    //no recursion
    public static LinkedList<File> listLinkedFiles(String strPath) {
        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(strPath);
        File[] file = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else
                System.out.println(file[i].getAbsolutePath());
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = (File) list.removeFirst();
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else
                        System.out.println(file[i].getAbsolutePath());
                }
            } else {
                System.out.println(tmp.getAbsolutePath());
            }
        }
        return list;
    }

    
    //recursion
    public static ArrayList<File> listFiles(String strPath) {
        return refreshFileList(strPath);
    }
    
    public static ArrayList<File> zipListFiles(String strPath) {
        return zipRefreshFileList(strPath);
    }

    public static ArrayList<File> refreshFileList(String strPath) {
        ArrayList<File> filelist = new ArrayList<File>();
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null)
            return null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath());
            } else {
                if(files[i].getName().toLowerCase().endsWith("zip"))
                    filelist.add(files[i]);
            }
        }
        return filelist;
    }
    
    /**
     * 
     * @param filelist ����һ������Ϊ�յ�Ҫװ���ļ���Arraylist
     * @param strPath Ҫ�������ļ�
     * @return ���ذ��������ļ����Arraylist
     */
    public static ArrayList<File> zipRefreshFileList(String strPath) {
       		
    	ArrayList<File> filelist = new ArrayList<File>();
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null)
            return null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
            	zipRefreshFileList(files[i].getAbsolutePath());
            } else {         
                    filelist.add(files[i]);
            }
        }
        return filelist;
    }
}


/**
 * Java utils ʵ�ֵ�Zip����
 *
 * @author once
 */
class ZipUtils {
    private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

    /**
     * ����ѹ���ļ����У�
     *
     * @param resFileList Ҫѹ�����ļ����У��б�
     * @param zipFile ���ɵ�ѹ���ļ�
     * @throws IOException ��ѹ�����̳���ʱ�׳�
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile) throws IOException {
        ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(
                zipFile), BUFF_SIZE));
        for (File resFile : resFileList) {
            zipFile(resFile, zipout, "");
        }
        zipout.close();
    }

    /**
     * ����ѹ���ļ����У�
     *
     * @param resFileList Ҫѹ�����ļ����У��б�
     * @param zipFile ���ɵ�ѹ���ļ�
     * @param comment ѹ���ļ���ע��
     * @throws IOException ��ѹ�����̳���ʱ�׳�
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile, String comment)
            throws IOException {
        ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(
                zipFile), BUFF_SIZE));
        for (File resFile : resFileList) {
            zipFile(resFile, zipout, "");
        }
        zipout.setComment(comment);
        zipout.close();
    }

    /**
     * ��ѹ��һ���ļ�
     *
     * @param zipFile ѹ���ļ�
     * @param folderPath ��ѹ����Ŀ��Ŀ¼
     * @throws IOException ����ѹ�����̳���ʱ�׳�
     */
    public static void upZipFile(File zipFile, String folderPath) throws ZipException, IOException {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
            ZipEntry entry = ((ZipEntry)entries.nextElement());
            InputStream in = zf.getInputStream(entry);
            String str = folderPath + File.separator + entry.getName();
            str = new String(str.getBytes("8859_1"), "GB2312");
            File desFile = new File(str);
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    fileParentDir.mkdirs();
                }
                desFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[BUFF_SIZE];
            int realLength;
            while ((realLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, realLength);
            }
            in.close();
            out.close();
        }
    }

    /**
     * ��ѹ�ļ��������������ֵ��ļ�
     *
     * @param zipFile ѹ���ļ�
     * @param folderPath Ŀ���ļ���
     * @param nameContains ������ļ�ƥ����
     * @throws ZipException ѹ����ʽ����ʱ�׳�
     * @throws IOException IO����ʱ�׳�
     */
    public static ArrayList<File> upZipSelectedFile(File zipFile, String folderPath,
            String nameContains) throws ZipException, IOException {
        ArrayList<File> fileList = new ArrayList<File>();

        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdir();
        }

        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
            ZipEntry entry = ((ZipEntry)entries.nextElement());
            if (entry.getName().contains(nameContains)) {
                InputStream in = zf.getInputStream(entry);
                String str = folderPath + File.separator + entry.getName();
                str = new String(str.getBytes("8859_1"), "GB2312");
                // str.getBytes("GB2312"),"8859_1" ���
                // str.getBytes("8859_1"),"GB2312" ����
                File desFile = new File(str);
                if (!desFile.exists()) {
                    File fileParentDir = desFile.getParentFile();
                    if (!fileParentDir.exists()) {
                        fileParentDir.mkdirs();
                    }
                    desFile.createNewFile();
                }
                OutputStream out = new FileOutputStream(desFile);
                byte buffer[] = new byte[BUFF_SIZE];
                int realLength;
                while ((realLength = in.read(buffer)) > 0) {
                    out.write(buffer, 0, realLength);
                }
                in.close();
                out.close();
                fileList.add(desFile);
            }
        }
        return fileList;
    }

    /**
     * ���ѹ���ļ����ļ��б�
     *
     * @param zipFile ѹ���ļ�
     * @return ѹ���ļ����ļ�����
     * @throws ZipException ѹ���ļ���ʽ����ʱ�׳�
     * @throws IOException ����ѹ�����̳���ʱ�׳�
     */
    public static ArrayList<String> getEntriesNames(File zipFile) throws ZipException, IOException {
        ArrayList<String> entryNames = new ArrayList<String>();
        Enumeration<?> entries = getEntriesEnumeration(zipFile);
        while (entries.hasMoreElements()) {
            ZipEntry entry = ((ZipEntry)entries.nextElement());
            entryNames.add(new String(getEntryName(entry).getBytes("GB2312"), "8859_1"));
        }
        return entryNames;
    }

    /**
     * ���ѹ���ļ���ѹ���ļ�������ȡ��������
     *
     * @param zipFile ѹ���ļ�
     * @return ����һ��ѹ���ļ��б�
     * @throws ZipException ѹ���ļ���ʽ����ʱ�׳�
     * @throws IOException IO��������ʱ�׳�
     */
    public static Enumeration<?> getEntriesEnumeration(File zipFile) throws ZipException,
            IOException {
        ZipFile zf = new ZipFile(zipFile);
        return zf.entries();

    }

    /**
     * ȡ��ѹ���ļ������ע��
     *
     * @param entry ѹ���ļ�����
     * @return ѹ���ļ������ע��
     * @throws UnsupportedEncodingException
     */
    public static String getEntryComment(ZipEntry entry) throws UnsupportedEncodingException {
        return new String(entry.getComment().getBytes("GB2312"), "8859_1");
    }

    /**
     * ȡ��ѹ���ļ����������
     *
     * @param entry ѹ���ļ�����
     * @return ѹ���ļ����������
     * @throws UnsupportedEncodingException
     */
    public static String getEntryName(ZipEntry entry) throws UnsupportedEncodingException {
        return new String(entry.getName().getBytes("GB2312"), "8859_1");
    }

    /**
     * ѹ���ļ�
     *
     * @param resFile ��Ҫѹ�����ļ����У�
     * @param zipout ѹ����Ŀ���ļ�
     * @param rootpath ѹ�����ļ�·��
     * @throws FileNotFoundException �Ҳ����ļ�ʱ�׳�
     * @throws IOException ��ѹ�����̳���ʱ�׳�
     */
    private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath)
            throws FileNotFoundException, IOException {
        rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator)
                + resFile.getName();
        rootpath = new String(rootpath.getBytes("8859_1"), "GB2312");
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            for (File file : fileList) {
                zipFile(file, zipout, rootpath);
            }
        } else {
            byte buffer[] = new byte[BUFF_SIZE];
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile),
                    BUFF_SIZE);
            zipout.putNextEntry(new ZipEntry(rootpath));
            int realLength;
            while ((realLength = in.read(buffer)) != -1) {
                zipout.write(buffer, 0, realLength);
            }
            in.close();
            zipout.flush();
            zipout.closeEntry();
        }
    }
}

