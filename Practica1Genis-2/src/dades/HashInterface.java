package dades;

public interface HashInterface<K,T> {
	
	void crear();
	 
	void inserir(K key, T data) throws Exception;
	
	T obtenir (K key) throws Exception;
	
	int buscar (K key) throws Exception;
	
	int mida();
	
	void esborrar (K key) throws Exception;
	
	DoublyLinkedList<T> obtenirValors();
	
	DoublyLinkedList<K> obtenirClaus();
	
	Float obtenirFactorDeCarrega();
	
	void redimensionarTaula();
}
