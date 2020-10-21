package com.blazdemsar.datastr2;

public class DoublyLinkedList {
		
	Node start;
	Node last;
	
	public void addToEnd(int data) {
		
		Node next = new Node(data);
		
		if (start == null) {
			
			start = next;
		}
		else {
			
			last.next = next;
			next.prev = last;
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
			start.prev = newNode;
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
						currentNode.prev = newNode;
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
						currentNode.prev = newNode;
						previousNode.next = newNode;
						newNode.prev = previousNode;
						return;
						
					}
					
					previousNode = previousNode.next;
					currentNode = currentNode.next;
				}
					
			}
			
			if (previousNode != null) {  // 4  5  6  7  9   - insert 10
				
				if (newNode.data > previousNode.data) {
					previousNode.next = newNode;
					newNode.prev = previousNode;
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
			newStart.prev = null;
			return;
		}
	}
	
	public void deleteLast() {
		
		Node newLast;
		
		if (last == null) {
			return;
		}
		else if (last.prev == null) {
			start = null;
			last = null;
			return;
		}
		else {
			
			newLast = last.prev;
			last = null;
			last = newLast;
			newLast.next = null;
			return;
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
			currNode.next.prev = prevNode;
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
							start.prev = null;
							currNode = null;
							return;
						}
					}
					else {
						
						prevNode.next = currNode.next;
						
						if (prevNode.next == null) {
							last = prevNode;
							currNode = null;
							return;
						}
						
						currNode.next.prev = prevNode;
						currNode = null;

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
	
	public void printForward() {
		
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
	
	public void printBackwards() {
		
		Node currentNode = last;
		
		if (currentNode == null) {
			System.out.println("LinkedList is empty!");
			return;
		}
		else {
			
			while (currentNode.prev != null) {
				
				System.out.print("" + currentNode.data + "->");
				
				currentNode = currentNode.prev;
			}
			
			System.out.println("" + currentNode.data);
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
