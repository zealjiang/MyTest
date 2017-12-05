package com.list;

import java.util.ArrayList;

import org.junit.Test;


public class ChangeArrayListData {


	@Test
	public void test(){
		ArrayList<ViewPot> viewPots = new ArrayList<ViewPot>();
		ViewPot vp = new ViewPot();
		vp.setDayNum("day1");
		
		viewPots.add(vp);
		vp.setDayNum("day2");
		viewPots.add(vp);
		
		for(int i=0;i<viewPots.size();i++){
			System.out.println(viewPots.get(i).getDayNum());
		}
	}
	
	
	public class ViewPot{
		
		private String dayNum;
		public String getDayNum() {
			return dayNum;
		}
		public void setDayNum(String dayNum) {
			this.dayNum = dayNum;
		}

			
	}
}
