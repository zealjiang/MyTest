package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriteObjToFile {
	
    private final String NEWSINFO_NAME = "newsinfo_name";
    
    public static void main(String[] args) {
    	Format format1 = new SimpleDateFormat("yyyy-MM-dd");
    	Format format2 = new SimpleDateFormat("HH:mm:ss");
    	ArrayList<NewsInfo> array = new ArrayList<NewsInfo>();
    	for (int i = 0; i < 6; i++) {
    		NewsInfo ni = new NewsInfo();
    		ni.setContent("content："+i);
    		ni.setDate(format1.format(new Date()));
    		ni.setTime(format2.format(new Date()));
    		ni.setTitle("title: "+i);
    		array.add(ni);
		}
    	
    	WriteObjToFile wf = new WriteObjToFile();    	
    	try {
			wf.newsInfoWriteToLocal(array);
			ArrayList<NewsInfo> list = (ArrayList<NewsInfo>) wf.getNewsInfoFromLocal();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    //保存缓存消息
    public void newsInfoWriteToLocal (List<NewsInfo> list ) throws Exception {
        String dataSystemDirectory = "f:/";
        File file = new File(dataSystemDirectory , NEWSINFO_NAME);
        try {
            if(!file.exists()){
                file.createNewFile();
            }else{
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            oos.close();

        }catch(IOException e){
            file.delete();
            throw new Exception(e);
        }finally {

        }
    }

    //获取缓存消息
    public List<NewsInfo> getNewsInfoFromLocal() throws Exception{
        String dataSystemDirectory = "f:/";
        File file = new File(dataSystemDirectory , NEWSINFO_NAME);
        try {
            if (file.exists()){
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                List<NewsInfo> list =(List<NewsInfo>) ois.readObject();
                ois.close();
                return list;
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Exception(e);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            throw new Exception(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    
}

class NewsInfo implements Serializable {
    private String time;
    private String title;
    private String date;
    private String content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
