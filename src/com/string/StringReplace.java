package com.string;

import org.junit.Test;

public class StringReplace {

	
	@Test
	public void fromHtml(){
				
		String string = "<p>盆景滩：又称“盆景海”藏语称“甲珠措”，是进入九寨沟的第一个滩流景观，过荷叶寨前行，便来到一片浅滩，这便是盆景滩，整个景点犹如一座巨大的盆景。</p><p>盆景滩属于钙化流型态，钙化是由流水中一种松散多孔的碳酸盐长年沉积形成的自然景观。可以说钙化是九寨沟自然景观的重要基础。九寨沟的水中含有碳酸盐成份，经过数万年，乃至数十万年的时间；这些碳酸盐不断的沉积形成了如今的钙化地貌。</p><p>盆景滩的钙化滩坡度舒缓，杜鹃、杨柳、松树、柏树、高山柳，和各种灌木丛矗立水中形成了千姿百态的自然盆景，这些盆景浑然天成，没有人为的造作与雕饰，但却以其无与伦比的协和与自然之美展示了更高层次的神美意境。</p>";
		
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
