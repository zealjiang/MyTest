package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class ReflectName {

	public static void main(String[] args) {
		ReflectName reflectName = new ReflectName();
		System.out.println(reflectName.getClass().getName());
	}

	@Test
	public void newClass() {
		Class<?> demo1 = null;

		Class<?> demo2 = null;

		Class<?> demo3 = null;

		try {

			// 一般尽量采用这种形式

			demo1 = Class.forName("com.reflect.ReflectName");

		} catch (Exception e) {

			e.printStackTrace();

		}

		demo2 = new ReflectName().getClass();

		demo3 = ReflectName.class;

		System.out.println("类名称   " + demo1.getName());

		System.out.println("类名称   " + demo2.getName());

		System.out.println("类名称   " + demo3.getName());
	}

	@Test
	/**
	 * 通过无参构造实例化对象
	 */
	public void classNewInstance() {
		Class<?> demo = null;

		try {
			demo = Class.forName("com.reflect.Person");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Person per = null;

		try {
			per = (Person) demo.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		per.setName("Rollen");
		per.setAge(20);

		System.out.println(per);
	}

	@Test
	/**
	 * 通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
	 */
	public void classConstructor() {
		Class<?> demo = null;

		try {
			demo = Class.forName("com.reflect.Person");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Person per1 = null;
		Person per2 = null;
		Person per3 = null;
		Person per4 = null;
		
		// 取得全部的构造函数
		Constructor<?> cons[] = demo.getConstructors();
		try {
			per1 = (Person) cons[3].newInstance();
			per2 = (Person) cons[2].newInstance("Rollen");
			per3 = (Person) cons[1].newInstance(20);
			per4 = (Person) cons[0].newInstance("Rollen", 20);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(per1);
		System.out.println(per2);
		System.out.println(per3);
		System.out.println(per4);
	}
	
	
	/**
	 * 取得其他类中的父类
	 */
	@Test
	public void getSuperClass(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }

        //取得父类
        Class<?> temp=demo.getSuperclass();
        System.out.println("继承的父类为：   "+temp.getName());
	}
	
	@Test
	/**
	 * 获得其他类中的全部构造函数
	 */
	public void getAllSuperClass(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.reflect.Person");
        }catch (Exception e) {
        	e.printStackTrace();
        }

        Constructor<?>cons[]=demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：  "+cons[i]);
        }
	}
	
	/**
	 * 返回一个类实现的接口
	 */
	@Test
	public void getInterface(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }

        //保存所有的接口
        Class<?> intes[]=demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口   "+intes[i].getName());
        }
	}
	
	/**
	 * 获取修饰符
	 */
	@Test
	public void getModifier(){
	       Class<?> demo=null;
	        try{
	            demo=Class.forName("com.reflect.Person");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }

	        Constructor<?>cons[]=demo.getConstructors();
	        for (int i = 0; i < cons.length; i++) {
	            Class<?> p[]=cons[i].getParameterTypes();
	            System.out.print("构造方法：  ");
	            int mo=cons[i].getModifiers();
	            System.out.print(Modifier.toString(mo)+" ");
	            System.out.print(cons[i].getName());
	            System.out.print("(");
	            for(int j=0;j<p.length;++j){
	                System.out.print(p[j].getName()+" arg"+i);
	                if(j<p.length-1){
	                    System.out.print(",");
	                }
	            }
	            System.out.println("){}");

	        }
	}
	
	/**
	 * 取得其他类的全部属性
	 */
	@Test
	public void getAllAttributes(){
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============本类属性========================");
        // 取得本类的全部属性
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // 权限修饰符
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }

        System.out.println("===============实现的接口或者父类的属性========================");
        // 取得实现的接口或者父类的属性
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // 权限修饰符
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }
	}
}
