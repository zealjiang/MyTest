package com.observer.demo2;

import java.util.Observable;

/**
 * 此类为被观察的对象类
 * 这个实例中，还是对data进行观察，拥有两个观察者，分别观察奇数和偶数的变化，
 * 通过notifyObservers(arg)中的参数arg来识别通知信息。
 * @author zealjiang
 *
 */
public class NumsObservable extends Observable {
    public final static Integer ODD = 1;//奇数
    public final static Integer EVEN = 2;//偶数
    private int data = 0;
 
    public int getData() {
       return data;
    }
 
    public void setData(int i) {
       data = i;
       Integer flag = EVEN;
       if ((data & 0x0001) == 1)//与运算，1&1得1，1&0或0&0得0
           flag = ODD;
       setChanged();
       notifyObservers(flag);
    }
}
