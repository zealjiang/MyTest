package zealjiang.bksx.collactor;

import java.text.Collator;
import java.util.Locale;

public class CollactorTest {

	
	 public static void main(String[] args) {
		 CollactorTest ct = new CollactorTest();		 
		 ct.method3();
	}
	 
	 private void method1(){
		 // Compare two strings in the default locale
		 Collator myCollator = Collator.getInstance();
		 if( myCollator.compare("abc", "ABC") < 0 )
		     System.out.println("abc is less than ABC");
		 else
		     System.out.println("abc is greater than or equal to ABC");
	 }
	 
	 private void method2(){
		 // Get the Collator for US English and set its strength to PRIMARY
		 Collator usCollator = Collator.getInstance(Locale.US);
		 usCollator.setStrength(Collator.PRIMARY);
		 if (usCollator.compare("abc", "ABC") == 0) {
		     System.out.println("Strings are equivalent");
		 }
	 }
	 
	 public void method3(){
		 //Get the Collator for US English and set its strength to PRIMARY
		 Collator usCollator = Collator.getInstance(Locale.US);
		 usCollator.setStrength(Collator.PRIMARY);
		 if( usCollator.compare("abc", "ABC") == 0 ) {
		     System.out.println("Strings are equivalent");
		 }
	 }

	protected void protectedMethod(){
		
		System.out.println("I am protectedMethod");
	}
	
	void friendlyMethod(){
		
		System.out.println("I am friendlyMethod");
	}

}
