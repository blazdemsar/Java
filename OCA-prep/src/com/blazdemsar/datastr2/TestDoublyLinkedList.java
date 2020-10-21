package com.blazdemsar.datastr2;

public class TestDoublyLinkedList {

	public static void main(String[] args) {
		
		int[] arr = {2,4,5,3,6,9,1,8,7};
		DoublyLinkedList llist = new DoublyLinkedList();
		
		for (int i = 0; i < arr.length; i++) {
			llist.insertSorted(arr[i]);
			llist.printForward();
		}
		
		//System.out.println(llist.findValueByIndex(5));
		llist.deleteByValue(5);
		llist.printForward();
		llist.printBackwards();
		
	}

}
