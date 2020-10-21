package com.blazdemsar.sorting;

public class SearchAlgorithms {
	
	private static int[] listOfInts = {2,7,5,3,6,8,4,1};
	private static int[] sortedList = {1,2,3,4,5,6,7,8,9,10};
	
	public static void main(String[] args) {
		
		//boolean found = linearSearch(6);
		boolean found = binarySearch(9, 0, sortedList.length-1);
		
		if (found) {
			System.out.println("List contains the searched number.");
		} else {
			System.out.println("List does NOT contain the searched number.");
		}
		
	}
	
	public static boolean linearSearch(int num) {
		
		for (int i = 0; i < listOfInts.length; i++) {
			
			if (listOfInts[i] == num)
				return true;
		}
		
		return false;
	}
	
	public static boolean binarySearch(int num, int low, int high) {
		
		int mid = (low + high)/2;
		
		if (low > high || high < low) {
			System.out.println("Number was not found.");
			return false;
		} else {
			
			if (sortedList[mid] == num) {
				System.out.println("Num="+num+" is equal to sortedList[mid]=" +sortedList[mid] + ", mid=" +mid);
				System.out.println("Found num. num=" + num + " , sortedList[mid]=" + sortedList[mid] + ", mid=" +mid);
				return true;
			}
			else if (num < sortedList[mid] ) {
				System.out.println("Num="+num+" is smaller than sortedList[mid]=" +sortedList[mid] + ", mid=" +mid);
				return binarySearch(num, low, mid-1);
			}
			else {
				System.out.println("Num="+num+" is bigger than sortedList[mid]=" +sortedList[mid] + ", mid=" +mid);
				return binarySearch(num, mid+1, high);
			}
			
		}
		
	}
}
