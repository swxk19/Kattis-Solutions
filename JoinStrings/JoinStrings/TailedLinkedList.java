package JoinStrings;

public class TailedLinkedList<T> {
	Node<T> head;
	Node<T> tail;
	Node<T> next;
	int size = 0;
	T value; 

	public Node getTail() {
		return this.tail;
	}

	
	public void add(T item) {
		if (this.size == 0) {
			this.head = new Node<T>(item);
			this.tail = this.head;
			this.value = item;
		} 
		else if (this.size == 1) {
			this.next = new Node<T>(item);
			this.tail = this.next;
		} else {
			this.tail.next = new Node<T>(item);
			this.tail = this.tail.next;
		}
		this.size += 1;
	}

	public void append(TailedLinkedList<T> anotherList) {
		if (this.size == 1) {
			this.next = anotherList.head;
		}
		this.tail.next = anotherList.head;
		anotherList.head = this.head;
		this.tail = anotherList.tail;
		this.size += anotherList.size;
	}

	public void clear() {
		this.head.value = null;
		this.tail = this.head;
	}
}
