package com.math;

import java.awt.Point;

import org.junit.Test;

public class PointInArea {

	@Test
	public void test() {
		//��ת�Ƕ�Ϊ0
		RectF rectf = new RectF();
		rectf.set(0,0,100,100);
		//�жϵ��Ƿ���rectf��
		isInTriangle(rectf,0,101);
	}
	
	public static boolean isInTriangle(RectF rectF,float x,float y) {
		System.out.println("-----------start---------"+" x: "+x+" y: "+y);
        float left = rectF.left;
        float top = rectF.top;
        float right = rectF.right;
        float bottom = rectF.bottom;

        PointF P = new PointF(x,y);
        PointF A = new PointF(left,top);
        PointF B = new PointF(right,top);
        PointF C = new PointF(right,bottom);
        PointF D = new PointF(left,bottom);

        //����rectF���
        double ABC = triAngleArea(A, B, C);
        double ADC = triAngleArea(A, D, C);
        double areaRectF = ABC + ADC;
        System.out.println("areaRectF: "+areaRectF);

        double ABp = triAngleArea(A, B, P);
        double BCp = triAngleArea(B, C, P);
        double CDp = triAngleArea(C, D, P);
        double ADp = triAngleArea(A, D, P);

        double tempArea = ABp + BCp + CDp + ADp;
        System.out.println("tempArea: "+tempArea);
        
        boolean boo;
        if ( Math.abs(areaRectF - tempArea) < 10 ) {// �����֮�͵���ԭ�����������֤��������������,��������һ��Լ����С����֮��û���㣨25714.25390625��25714.255859375��
            boo = true;
        } else {
            boo = false;
        }
        
        System.out.println("-----------end----------");
        return boo;
    }

    private static double triAngleArea(PointF A, PointF B, PointF C) {// �������������������������������
        double result = Math.abs((A.x * B.y + B.x * C.y
                + C.x * A.y - B.x * A.y - C.x
                * B.y - A.x * C.y) / 2.0D);
        return result;
    }
}
