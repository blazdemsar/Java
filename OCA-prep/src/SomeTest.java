
public class SomeTest {

	public static void main(String[] args) {
		
		int[] arr = new int[5];
		
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5;
		
		int[] arr1 = arr;
		
		arr1[0] = 10;
		
		System.out.println(arr[0]);
		System.out.println(arr1[0]);
		

	}

}
