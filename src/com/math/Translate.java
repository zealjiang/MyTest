package com.math;

import org.junit.Test;

public class Translate {

	public static void main(String[] args) {
		Translate translate = new Translate();
		System.out.println("0: "+translate.toPositiveDegree(0));
		System.out.println("90: "+translate.toPositiveDegree(90));
		System.out.println("180: "+translate.toPositiveDegree(180));
		System.out.println("270: "+translate.toPositiveDegree(270));
		System.out.println("-90: "+translate.toPositiveDegree(-90));
		System.out.println("-180: "+translate.toPositiveDegree(-180));
		System.out.println("-270: "+translate.toPositiveDegree(-270));
		System.out.println("-360: "+translate.toPositiveDegree(-360));
		System.out.println("-450: "+translate.toPositiveDegree(-450));
	}
	
    private int toPositiveDegree(int degree){
        int newD = degree%360;
        switch (newD) {
            case -90:
                return 270;
            case -180:
                return 180;
            case -270:
                return 90;
            case 0:
                return 0;
            default:
                return newD;
        }
    }
}
