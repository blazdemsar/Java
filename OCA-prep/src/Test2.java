import java.util.Arrays;

public class Test2 {
	
	public static void main(String[] args){
		
		int[] array = {2, 5, 7, 9};
		int target = 12;
		int[] result = new int[2];
		int count = 0;
		
		System.out.println("Input: " + Arrays.toString(array));
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if(array[i] != array[j]) {
					if ((array[i] + array[j]) == target) {
						result[count] = i;
						result[count + 1] = j;
						break;
					}
				}
				System.out.println("Inner loop");
			}
			if (result.length>0) {
				break;
			}
			System.out.println("Outer loop");
		}

		System.out.println("Target sum is possible with indices: " + Arrays.toString(result));
		
	}
	
}

/*1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/