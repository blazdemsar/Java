package com.blazdemsar.sorting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSort {

	public static void main(String[] args) {
		
		int[] listOfInts = {2, 7, 5, 3, 6};
		
		for (int i = 0; i < listOfInts.length; i++) {
			
			for (int j = 0; j < listOfInts.length-1-i; j++) {
				
				if (listOfInts[j] > listOfInts[j+1]) {
					int temp = listOfInts[j];
					listOfInts[j] = listOfInts[j+1];
					listOfInts[j+1] = temp;
				}
				
			}
			
		}
		
		System.out.println(Arrays.toString(listOfInts));
		
	}
}
