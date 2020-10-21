package com.blazdemsar.sorting;
import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		
		int[] listOfInts = {2, 7, 5, 3, 6};
		
		int minVal;
		int minIndex;
		
		for (int i = 0; i < listOfInts.length; i++) {
			
			minVal = listOfInts[i];
			minIndex = i;
			
			for (int j = 1; j < listOfInts.length-i; j++) {
				
				if (minVal > listOfInts[i+j]) {
					minVal = listOfInts[i+j];
					minIndex = i+j;
				}
				
			}
			
			int temp = listOfInts[i];
			listOfInts[i] = listOfInts[minIndex];
			listOfInts[minIndex] = temp;
		}
		
		System.out.println(Arrays.toString(listOfInts));

	}

}
