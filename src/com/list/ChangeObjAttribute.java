package com.list;

import java.util.ArrayList;

public class ChangeObjAttribute {

	class PlanEntity{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static void main(String[] args) {
		ChangeObjAttribute o = new ChangeObjAttribute();
		ArrayList<PlanEntity> list = new ArrayList<PlanEntity>();
		PlanEntity pe = o.new PlanEntity();
		pe.setName("ne");
		list.add(pe);
		pe = o.new PlanEntity();
		pe.setName("ww");
		list.add(pe);
		
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i).getName();
			System.out.println("name :"+name);
		}
	}
}
