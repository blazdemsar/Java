package com.blazdemsar.sorting;
import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		
		int[] listOfInts = {4, 5, 6, 7, 3};
		int tempi;
		int tempj;
		
		for (int i = 1; i < listOfInts.length; i++) {
			
			tempi = listOfInts[i];
			
			for (int j = i - 1; j >= 0; j--) {
				
				tempj = listOfInts[j];
				
				if (tempj > tempi && j > 0) {
					listOfInts[j+1] = tempj;
				} else if (tempj > tempi && j == 0) {
					listOfInts[j+1] = tempj;
					listOfInts[j] = tempi;
				} else {
					listOfInts[j+1] = tempi;
					break;
				}
			}
		}
		
		System.out.println(Arrays.toString(listOfInts));

	}

}
