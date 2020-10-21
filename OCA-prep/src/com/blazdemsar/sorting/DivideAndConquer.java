package com.blazdemsar.sorting;
import java.util.Arrays;

public class DivideAndConquer {
	
	private static int[] listOfInts = {2,7,5,3,6,8,4,1};
	private static int[] tempArray = new int[8];
	
	public static void main(String[] args) {
		
		//quickSort(0,7);
		mergeSort(0,7);
		
		System.out.println(Arrays.toString(listOfInts));
		
	}
	
	public static void quickSort(int low, int high) {
		
		if (low >= high)
			return;
		
		int pivot = listOfInts[low];
		int i = low + 1;
		int j = high;
		
		while (i <= j) {
			
			while (i <= high && listOfInts[i] < pivot)
				i++;
			
			while (j >= low && listOfInts[j] > pivot)
				j--;
			
			if (i < j) {
				int tempi = listOfInts[i];
				listOfInts[i] = listOfInts[j];
				listOfInts[j] = tempi;
			}
			
		}
		
		if (low < j) {
			int temp = listOfInts[j];			
			listOfInts[j] = listOfInts[low];
			listOfInts[low] = temp;
		}
		
		quickSort(low, j-1);
		quickSort(j+1, high);
		
	}
	
	public static void mergeSort(int low, int high) {
		
		if (low >= high) {
			return;
		}
		
		int mid = (low+high)/2;
		
		mergeSort(low, mid);
		mergeSort(mid+1, high);
		
		int i = low;
		int j = mid+1;
		int k = low;
		
		while (i <= mid && j <= high) {
			
			if (listOfInts[i] <= listOfInts[j]) {
				tempArray[k] = listOfInts[i];
				i++;
				k++;
			} else {
				tempArray[k] = listOfInts[j];
				j++;
				k++;
			}			
		}
		
		while (j <= high) {
			tempArray[k] = listOfInts[j];
			
			j++;
			k++;
		}
		
		while (i <= mid) {
			tempArray[k] = listOfInts[i];
			i++;
			k++;
		}
		
		for (int l = 0; l < listOfInts.length; l++) { // This was necessary because the while loop is comparing the values in original list
			if (tempArray[l] != 0) {				  // so if it is not updated at the end of each recursion, it does not compare the correct
				listOfInts[l] = tempArray[l];		  // updated values.
			}
		}
		
	}

}
