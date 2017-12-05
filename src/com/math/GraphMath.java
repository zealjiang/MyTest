package com.math;

import org.junit.Test;

public class GraphMath {

	/**
	 * 
	 * @note 计算多边形的中心点坐标
	 * @author zealjiang
	 * @time 2015-8-13下午5:18:09
	 */
	@Test
	public void getCenterXY(){
		//构造一个五边形的五个顶点数组
		Pointer[] Pointers = new Pointer[]{
				new Pointer(100,100),
				new Pointer(300,100),
				new Pointer(320,200),
				new Pointer(130,300),
				new Pointer(80,200)};
		
		//define the min X coordinate, min Y coordinate,max X coordinate,max Y coordinate
		int minX,minY,maxX,maxY,centerX,centerY;
		
		//赋初值
		maxX = minX = Pointers[0].x;
		minY = maxY = Pointers[0].y;
		for (int i = 0; i < Pointers.length; i++) {
			
			if(minX>Pointers[i].x){
				minX = Pointers[i].x;
			}else if(maxX < Pointers[i].x){
				maxX = Pointers[i].x;
			}
			
			
			if(minY>Pointers[i].y){
				minY = Pointers[i].y;
			}else if(maxY < Pointers[i].y){
				maxY = Pointers[i].y;
			}
			
		}
		
		centerX = (maxX - minX)/2+minX;
		centerY = (maxY - minY)/2+minY;
		System.out.println("center X :"+centerX+"  center Y :"+centerY);
	}
	
	
	/**
	 * 顶点对象
	 * @author zealjiang
	 * @time 2015-8-13下午5:17:34
	 */
	class Pointer{
		int x;
		int y;
		
		
		
		public Pointer(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}
}
