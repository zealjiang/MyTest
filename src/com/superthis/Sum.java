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

       super.n=k;//���ø����еĳ�Ա����

       c=super.f();//���ø����еĳ�Ա�����������ֲ�����c

       return c/k;

    }

    float g(){

       float c;

       c=super.f();//���ø����еĳ�Ա�����������ֲ�����c

       return c/2;

    }

}


