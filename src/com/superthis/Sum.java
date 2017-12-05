package com.superthis;

class Sum{

    int n;

    float f(){

       float sum=0;
       System.out.println("......"+n);
       for(int i=1;i<=n;i++){

           sum=sum+i;

       }

       return sum;

    }

}

class Average extends Sum{

    int k;

    float f(){

       float c;

       super.n=k;//调用父类中的成员变量

       c=super.f();//调用父类中的成员方法并赋给局部变量c

       return c/k;

    }

    float g(){

       float c;

       c=super.f();//调用父类中的成员方法并赋给局部变量c

       return c/2;

    }

}


