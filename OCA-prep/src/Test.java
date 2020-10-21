import java.util.Stack;

public class Test {

	   public static void main(String[] args){
		      String hello = "Hello", lo = "lo";
		     // System.out.print((testPackage.Other.hello == hello) + " ");    //line 1
		     // System.out.print((other.Other.hello == hello) + " ");   //line 2
		     // System.out.print((hello == ("Hel"+"lo")) + " ");           //line 3
		      System.out.print((hello == ("Hel"+lo)) + " ");              //line 4
		      System.out.println("Hel"+lo);
		      System.out.println(hello == ("Hel"+lo).intern());          //line 5
		   }


}
