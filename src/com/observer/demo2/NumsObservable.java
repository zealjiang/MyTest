package com.observer.demo2;

import java.util.Observable;

/**
 * ����Ϊ���۲�Ķ�����
 * ���ʵ���У����Ƕ�data���й۲죬ӵ�������۲��ߣ��ֱ�۲�������ż���ı仯��
 * ͨ��notifyObservers(arg)�еĲ���arg��ʶ��֪ͨ��Ϣ��
 * @author zealjiang
 *
 */
public class NumsObservable extends Observable {
    public final static Integer ODD = 1;//����
    public final static Integer EVEN = 2;//ż��
    private int data = 0;
 
    public int getData() {
       return data;
    }
 
    public void setData(int i) {
       data = i;
       Integer flag = EVEN;
       if ((data & 0x0001) == 1)//�����㣬1&1��1��1&0��0&0��0
           flag = ODD;
       setChanged();
       notifyObservers(flag);
    }
}
