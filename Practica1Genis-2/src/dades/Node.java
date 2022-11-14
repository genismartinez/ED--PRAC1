package dades;

public class Node<T> {
	T data;
	Node next;
	Node previous;
	
	Node() {
	}

	Node(Node next, Node previous, T data) {
		this.next = next;
		this.previous = previous;
		this.data = data;
	}

	public String toString() {
		return data + "";
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}