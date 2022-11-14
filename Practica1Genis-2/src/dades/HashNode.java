package dades;

public class HashNode<K,T> {
	K key;
	T value;

	// Reference to next node
	HashNode<K, T> next;

	// Constructor
	public HashNode(K key, T value, HashNode next)
	{
		this.key = key;
		this.value = value;
		this.next = next;
	}	
}
