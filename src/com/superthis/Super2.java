package com.superthis;

public class Super2 {

	 

    /**

     * @param args

     */

    public static void main(String[] args) {

       // TODO Auto-generated method stub

        Average aver=new Average();

        aver.k=100;

        /**
         * 调用完这个就去后，父类Sum的成员变量n被赋于了100(super.n=k;)
         */
        float result_1=aver.f();

        float result_2=aver.g();

        System.out.println("result_1="+result_1);

        System.out.println("result_2="+result_2);

    }

 

}
