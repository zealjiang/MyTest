package com.reflect;

public class Person implements China{

	private String name;
    private int age;
    
	public Person() {
		
	}
	
	public Person(String name) {
		super();
		this.name = name;
	}
	
	public Person(int age) {
		super();
		this.age = age;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
    @Override
    public void sayChina(){
        System.out.println("hello ,china");
    }

    @Override
    public void sayHello(String name, int age){
        System.out.println(name+"  "+age);
    }
    
   @Override
    public String toString(){
        return "["+this.name+"  "+this.age+"]";
    }
}
