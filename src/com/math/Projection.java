package com.math;

import java.awt.Point;

import math.main;

public class Projection {

	public static void main(String[] args) {
		Projection projection = new Projection();
		projection.calculateProjection(new Point(5,0),new Point(0,0), new Point(3,10));
	}
	
	
    private double calculateProjection(Point primary,Point origin,Point projection){
        //������ָ�ƶ��ĵ����������ĵ�߳ɵ����ϵ�ͶӰ
        //�̶���
        float primaryX = primary.x;
        float primaryY = primary.y;
        //��ǰ�϶��ĵ�����С������
        float originX = origin.x;
        float originY = origin.y;

        //��ָ�ƶ���λ��
        float x = projection.x;
        float y = projection.y;

        // ����Vector a��(x, y)����
        double vectorAx = primaryX - originX;
        double vectorAy = primaryY - originY;

        // ����b��(x, y)����
        double vectorBx = x - originX;
        double vectorBy = y - originY;

        double productValue = (vectorAx * vectorBx) + (vectorAy * vectorBy);  // �����ĳ˻�
        double vectorAval = Math.sqrt(vectorAx * vectorAx + vectorAy * vectorAy);  // ����a��ģ
        //double vectorBval = Math.sqrt(vectorBx * vectorBx + vectorBy * vectorBy);  // ����b��ģ
/*        double cosValue = productValue / (vectorAval * vectorBval);      // ���ҹ�ʽ
        double aProduction = vectorBval * cosValue;*/
        double aProduction = productValue/vectorAval;

        System.out.println(" aProduction: "+aProduction);
        return aProduction;
    }
}
