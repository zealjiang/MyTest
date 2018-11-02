package com.math;

import java.awt.Point;

public class PointF {

	public float x;
	public float y;
	
	public PointF() {}
	
	public PointF(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public PointF(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	
}
