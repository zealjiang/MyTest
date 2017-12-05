package com.enumm;

/**
 * 用来表示8条波形
 * Created by zealjiang on 2016/4/8 13:46.
 */
public enum WAVE {
    ECGI("ECG I"), ECGII("ECG II"), ECGC1("ECGC1"), ECGC2("ECGC2"),
    ECGC3("ECGC3"),ECGC4("ECGC4"),ECGC5("ECGC5"),ECGC6("ECGC6");

    private String name;

    private WAVE(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static void main(String[] args) {
		for (int i = 0; i < WAVE.values().length; i++) {
			System.out.println(WAVE.values()[i].getName());			
		}
	}
}
