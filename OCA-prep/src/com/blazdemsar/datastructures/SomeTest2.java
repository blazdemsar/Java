package com.blazdemsar.datastructures;

public class SomeTest2 {

	public static void main(String[] args) {
		
		int[] arr = {2,4,5,3,6,8,7,1,9};
		LinkedList llist = new LinkedList();
		
		for (int i = 0; i < arr.length; i++) {
			llist.insertSorted(arr[i]);
			llist.print();
		}
		
		System.out.println(llist.findValueByIndex(5));
		
		llist.print();
		llist.reverse();
		llist.print();
		System.out.println("Start:" + llist.start.data);
		System.out.println("Last:" + llist.last.data);
		System.out.println(llist.length());
		
		//llist.deleteByValue(9);
		//llist.print();
		//System.out.println("Start:" + llist.start.data);
		//System.out.println("Last:" + llist.last.data);
		
	}

}
