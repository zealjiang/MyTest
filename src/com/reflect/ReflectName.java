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

			// һ�㾡������������ʽ

			demo1 = Class.forName("com.reflect.ReflectName");

		} catch (Exception e) {

			e.printStackTrace();

		}

		demo2 = new ReflectName().getClass();

		demo3 = ReflectName.class;

		System.out.println("������   " + demo1.getName());

		System.out.println("������   " + demo2.getName());

		System.out.println("������   " + demo3.getName());
	}

	@Test
	/**
	 * ͨ���޲ι���ʵ��������
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
	 * ͨ��Class�����������еĹ��캯�� ��Ҳ����ͨ�����ַ�ʽͨ��Class����������Ķ���
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
		
		// ȡ��ȫ���Ĺ��캯��
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
	 * ȡ���������еĸ���
	 */
	@Test
	public void getSuperClass(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }

        //ȡ�ø���
        Class<?> temp=demo.getSuperclass();
        System.out.println("�̳еĸ���Ϊ��   "+temp.getName());
	}
	
	@Test
	/**
	 * ����������е�ȫ�����캯��
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
            System.out.println("���췽����  "+cons[i]);
        }
	}
	
	/**
	 * ����һ����ʵ�ֵĽӿ�
	 */
	@Test
	public void getInterface(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }

        //�������еĽӿ�
        Class<?> intes[]=demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("ʵ�ֵĽӿ�   "+intes[i].getName());
        }
	}
	
	/**
	 * ��ȡ���η�
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
	            System.out.print("���췽����  ");
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
	 * ȡ���������ȫ������
	 */
	@Test
	public void getAllAttributes(){
        Class<?> demo = null;
        try {
            demo = Class.forName("com.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===============��������========================");
        // ȡ�ñ����ȫ������
        Field[] field = demo.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            // Ȩ�����η�
            int mo = field[i].getModifiers();
            String priv = Modifier.toString(mo);
            // ��������
            Class<?> type = field[i].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + field[i].getName() + ";");
        }

        System.out.println("===============ʵ�ֵĽӿڻ��߸��������========================");
        // ȡ��ʵ�ֵĽӿڻ��߸��������
        Field[] filed1 = demo.getFields();
        for (int j = 0; j < filed1.length; j++) {
            // Ȩ�����η�
            int mo = filed1[j].getModifiers();
            String priv = Modifier.toString(mo);
            // ��������
            Class<?> type = filed1[j].getType();
            System.out.println(priv + " " + type.getName() + " "
                    + filed1[j].getName() + ";");
        }
	}
}
