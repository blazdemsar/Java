package com.blazdemsar.sorting;
import java.util.Arrays;
import java.util.Scanner;

public class ShellSort {
	
	private static int[] listOfInts = {2,7,5,3,6};
	
	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		int gap = kbd.nextInt();
		
		shellSort(gap);
		
		System.out.println(Arrays.toString(listOfInts));
		
	}

	public static void shellSort(int gap) {
		
		for (int i = gap; i > 0; i--) {
			for(int startPos = 0; startPos < i; startPos++) {
				System.out.println("insertionSort(" +startPos+", "+i+")");
				
				shellInsertSort(startPos, i);
				
			}
		}
		
	}
	
	public static void shellInsertSort(int start, int gap) {
		
		int tempi;
		int tempj;
		// 2, 7, 5, 3, 6
		for (int i = start+gap; i < listOfInts.length; i += gap ) {
			
			tempi = listOfInts[i];
			
			for (int j = i - gap; j >= start; j -= gap) {
				
				tempj = listOfInts[j];
				
				if (tempj > tempi && j > start) {
					listOfInts[j+gap] = tempj;
				} else if (tempj > tempi && j == start) {
					listOfInts[j+gap] = tempj;
					listOfInts[j] = tempi;
				} else {
					listOfInts[j+gap] = tempi;
					break;
				}
			}
		}
	}
}
