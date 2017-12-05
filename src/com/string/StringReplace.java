package com.string;

import org.junit.Test;

public class StringReplace {

	
	@Test
	public void fromHtml(){
				
		String string = "<p>�辰̲���ֳơ��辰��������ơ�����롱���ǽ����կ���ĵ�һ��̲�����ۣ�����Ҷկǰ�У�������һƬǳ̲��������辰̲��������������һ���޴���辰��</p><p>�辰̲���ڸƻ�����̬���ƻ�������ˮ��һ����ɢ��׵�̼���γ�������γɵ���Ȼ���ۡ�����˵�ƻ��Ǿ�կ����Ȼ���۵���Ҫ��������կ����ˮ�к���̼���γɷݣ����������꣬������ʮ�����ʱ�䣻��Щ̼���β��ϵĳ����γ������ĸƻ���ò��</p><p>�辰̲�ĸƻ�̲�¶��滺���ž顢��������������������ɽ�����͸��ֹ�ľ�Դ���ˮ���γ���ǧ�˰�̬����Ȼ�辰����Щ�辰��Ȼ��ɣ�û����Ϊ����������Σ���ȴ���������ױȵ�Э������Ȼ֮��չʾ�˸��߲�ε������⾳��</p>";
		
		String s = string.replace("<p>", "    ").replace("</p>", "\n");
		
		System.out.println(s);
	}
	
	@Test
	public void test(){
		isAgraterThanB("999,99.00","777,33,00");
	}
	
	public boolean isAgraterThanB(String aMoney,String bMoney){
		aMoney = aMoney.replace(",", "");
		System.out.println("aMoney :"+aMoney);
		return true;
	}
}
