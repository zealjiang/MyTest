package com.math;

import java.awt.Point;

import math.main;

public class Projection {

	public static void main(String[] args) {
		Projection projection = new Projection();
		projection.calculateProjection(new Point(5,0),new Point(0,0), new Point(3,10));
	}
	
	
    private double calculateProjection(Point primary,Point origin,Point projection){
        //计算手指移动的点在上下中心点边成的线上的投影
        //固定点
        float primaryX = primary.x;
        float primaryY = primary.y;
        //当前拖动的调整大小操作点
        float originX = origin.x;
        float originY = origin.y;

        //手指移动的位置
        float x = projection.x;
        float y = projection.y;

        // 向量Vector a的(x, y)坐标
        double vectorAx = primaryX - originX;
        double vectorAy = primaryY - originY;

        // 向量b的(x, y)坐标
        double vectorBx = x - originX;
        double vectorBy = y - originY;

        double productValue = (vectorAx * vectorBx) + (vectorAy * vectorBy);  // 向量的乘积
        double vectorAval = Math.sqrt(vectorAx * vectorAx + vectorAy * vectorAy);  // 向量a的模
        //double vectorBval = Math.sqrt(vectorBx * vectorBx + vectorBy * vectorBy);  // 向量b的模
/*        double cosValue = productValue / (vectorAval * vectorBval);      // 余弦公式
        double aProduction = vectorBval * cosValue;*/
        double aProduction = productValue/vectorAval;

        System.out.println(" aProduction: "+aProduction);
        return aProduction;
    }
}
