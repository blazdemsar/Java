package com.blazdemsar.datastructures;

public class LinkedList {
		
	Node start;
	Node last;
	
	public void addToEnd(int data) {
		
		Node next = new Node(data);
		
		if (start == null) {
			
			start = next;
		}
		else {
			
			last.next = next;
		}
		
		last = next;
		
	}
	
	public void addToFront(int data) {
		
		Node newNode = new Node(data);
		
		if (start == null) {
			
			
			last = newNode;
		}
		else {
			
			newNode.next = start;
			
		}
		
		start = newNode;
	}
	
	public void insertSorted(int data) {
		
		Node newNode = new Node(data);
		
		if (start == null) {
			
			start = newNode;
			last = newNode;
		}
		else {
			
			Node previousNode = null;
			Node currentNode = start;
			
			while (currentNode != null) {
				
				if (previousNode == null) {
					
					if (newNode.data < currentNode.data) {
						
						newNode.next = currentNode;
						start = newNode;
						
						if (currentNode.next == null) {
							last = currentNode;
						}
						return;
					}
					
					previousNode = start;
					currentNode = currentNode.next;
					
				}
				else {
					
					if (newNode.data >= previousNode.data && newNode.data <= currentNode.data) {
						
						newNode.next = currentNode;
						previousNode.next = newNode;
						return;
						
					}
					
					previousNode = previousNode.next;
					currentNode = currentNode.next;
				}
					
			}
			
			if (previousNode != null) {  // 4  5  6  7  9   - insert 10
				
				if (newNode.data > previousNode.data) {
					previousNode.next = newNode;
					last = newNode;
				}
			}
		}
	}
	
	public void deleteFirst() {
		
		Node newStart;
		
		if (start == null) {
			return;
		}
		else if (start.next == null){
			start = null;
			last = null;
			return;
		}
		else {
			
			newStart = start.next;
			start = null;
			start = newStart;
			return;
		}
	}
	
	public void deleteLast() {
		
		Node previousNode = null;
		Node currentNode = start;
		
		if (last == null) {
			return;
		}
		else {
			
			while (currentNode != null) {
				
				if (currentNode.next == null) {
					
					if (previousNode == null) {
						
						start = null;
						last = null;
						return;
					}
					else {
						
						last = previousNode;
						currentNode = null;
						previousNode.next = null;
						return;
					}
				}
				
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
		}
	}
	
	public void deleteByIndex(int index) {
		
		int i = 0;
		int length = this.length();
		Node prevNode = null;
		Node currNode = start;
		
		if (length == 0) {
			System.out.println("LinkedList is empty!");
			return;
		}
		else if (index >= length || index < 0) {
			System.out.println("Index out of bounds!");
			return;
		}
		else if (index == 0) {
			this.deleteFirst();
			return;
		}
		else if (index == length-1) {
			this.deleteLast();
			return;
		}
		else {
			
			while (currNode != null && i < index) {
				
				prevNode = currNode;
				currNode = currNode.next;
				i++;
			}
			
			prevNode.next = currNode.next;
			currNode = null;
			return;
		}
	}
	
	public void deleteByValue(int val) {
		
		int length = this.length();
		Node prevNode = null;
		Node currNode = start;
		
		if (length == 0) {
			System.out.println("LinkedList is empty!");
			return;
		}
		else {
			
			while (currNode != null) {
				
				if (val == currNode.data) {
					
					if (prevNode == null) {
						
						if (currNode.next == null) {
							
							start = null;
							last = null;
							currNode = null;
							return;
						}
						else {
							
							start = currNode.next;
							currNode = null;
							return;
						}
					}
					else {
						
						prevNode.next = currNode.next;
						currNode = null;
						
						if (prevNode.next == null) {
							last = prevNode;
						}
						
						return;
					}
					
				}
				
				prevNode = currNode;
				currNode = currNode.next;
			}
		}
		System.out.println("The value is not in this LinkedList!");
	}
	
	public int findValueByIndex(int index) {
		
		int i = 0;
		int length = this.length();
		Node currNode = start;
		
		if (length == 0) {
			System.out.println("LinkedList is empty!");
			return -1;
		}
		else if (index >= length || index < 0) {
			System.out.println("Index out of bounds!");
			return -1;
		}
		else if (index == 0) {
			return this.start.data;
			
		}
		else if (index == length-1) {
			return this.last.data;
		}
		else {
			
			while (currNode != null && i < index) {
				
				currNode = currNode.next;
				i++;
			}
			
			return currNode.data;
		}
	}
	
	public void print() {
		
		Node currentNode = start;
		
		if (currentNode == null) {
			System.out.println("LinkedList is empty!");
			return;
		}
		else {
			
			while (currentNode.next != null) {
				
				System.out.print("" + currentNode.data + "->");
				
				currentNode = currentNode.next;
			}
			
			System.out.println("" + currentNode.data);
		}
		
	}
	
	public void reverse() {
		
		Node firstNode = start;
		Node secNode = start.next;
		Node thirdNode;
		
		if (firstNode == null) {
			return;
		}
		else if (secNode == null) {
			return;
		}
		else {
			
			thirdNode = secNode.next;
			firstNode.next = null;
			last = firstNode;
			
			while (thirdNode != null) {
				
				System.out.println("First: " + firstNode.data + ", Second: " + secNode.data + ", Third: " + thirdNode.data);
				if (thirdNode.next == null) {
					thirdNode.next = secNode;
					start = thirdNode;
					secNode.next = firstNode;
					break;
					
				}

				secNode.next = firstNode;
				
				firstNode = secNode;
				secNode = thirdNode;
				thirdNode = thirdNode.next;
				
			}
		}
	}
	
	public int length() {
		
		Node currentNode = start;
		int length = 0;
		
		if (currentNode == null) {
			return length;
		}
		else {
			
			length += 1;
			
			while (currentNode.next != null) {
				
				currentNode = currentNode.next;
				length += 1;
			}
		}
		
		return length;
	}

}
