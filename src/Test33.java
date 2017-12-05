import com.byteArray.intToByteArray;


public class Test33 {
	public static void main(String[] args) {
		int i,a[]={6,12,7,11,5};
		s(a, 5);
		for(i=0;i<a.length;i++)System.out.print(a[i]+" ");
		System.out.println();
	}
	
	static void s(int b[],int k){
		int i,j,t;
		for(i=1;i<k;i++){
			for(t=b[i],j=i-1;j>=0&&t<b[j];j--){
				b[j+1]=b[j];
				b[j] =t;
			}
		}
	}


}
