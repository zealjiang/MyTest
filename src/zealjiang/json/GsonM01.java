package zealjiang.json;

import java.util.List;

import com.google.gson.annotations.Expose;

public class GsonM01 {

	private String name;

	private boolean Sex;

	private int age;
	
	private List<String> listName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return Sex;
	}

	public void setSex(boolean sex) {
		Sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getListName() {
		return listName;
	}

	public void setListName(List<String> listName) {
		this.listName = listName;
	}
}
