package com.observer.demo;

import java.util.Observable;

/**
 * 被观察都类
 * @author zealjiang
 *
 */
public class NumObservable extends Observable {

    private int data = 0;

 

    public int getData() {

       return data;

    }

 

    public void setData(int i) {

       data = i;

       setChanged();

       notifyObservers();

    }

}
