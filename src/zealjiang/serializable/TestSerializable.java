package zealjiang.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerializable {

	/**
	 * 
	 * 将对象序列化到磁盘文件中
	 * 
	 * @paramo
	 * 
	 * @throwsException
	 */

	public static void writeObject(Object o) throws Exception {

		File f = new File("user.tmp");

		if (f.exists()) {

			f.delete();

		}

		FileOutputStream os = new FileOutputStream(f);

		// ObjectOutputStream 核心类

		ObjectOutputStream oos = new ObjectOutputStream(os);

		oos.writeObject(o);

		oos.close();

		os.close();

	}

	/**
	 * 
	 * 反序列化,将磁盘文件转化为对象
	 * 
	 * @paramf
	 * 
	 * @return
	 * 
	 * @throwsException
	 */

	public static User readObject(File f) throws Exception {

		InputStream is = new FileInputStream(f);

		// ObjectOutputStream 核心类

		ObjectInputStream ois = new ObjectInputStream(is);

		return (User) ois.readObject();

	}

	public static void main(String[] args) throws Exception {

		/***************** 将对象序列化 ***************/

		User user = new User();

		user.setUserId(1);

		user.setUserName("张艺谋");

		user.setUserSex("男");

		user.setUserAge(50);

		TestSerializable.writeObject(user);

		/***************** 将对象序反列化 ***************/

		User readUser = TestSerializable.readObject(new File("user.tmp"));

		System.out.println(readUser);
		
		String dir="sdcard";
		String name="health_waveBeans.txt";
		
		File file = new File(dir,name);
		System.out.println(file.getAbsolutePath());
	}
}
