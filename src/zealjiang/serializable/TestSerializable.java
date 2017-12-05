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
	 * ���������л��������ļ���
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

		// ObjectOutputStream ������

		ObjectOutputStream oos = new ObjectOutputStream(os);

		oos.writeObject(o);

		oos.close();

		os.close();

	}

	/**
	 * 
	 * �����л�,�������ļ�ת��Ϊ����
	 * 
	 * @paramf
	 * 
	 * @return
	 * 
	 * @throwsException
	 */

	public static User readObject(File f) throws Exception {

		InputStream is = new FileInputStream(f);

		// ObjectOutputStream ������

		ObjectInputStream ois = new ObjectInputStream(is);

		return (User) ois.readObject();

	}

	public static void main(String[] args) throws Exception {

		/***************** ���������л� ***************/

		User user = new User();

		user.setUserId(1);

		user.setUserName("����ı");

		user.setUserSex("��");

		user.setUserAge(50);

		TestSerializable.writeObject(user);

		/***************** ���������л� ***************/

		User readUser = TestSerializable.readObject(new File("user.tmp"));

		System.out.println(readUser);
		
		String dir="sdcard";
		String name="health_waveBeans.txt";
		
		File file = new File(dir,name);
		System.out.println(file.getAbsolutePath());
	}
}
