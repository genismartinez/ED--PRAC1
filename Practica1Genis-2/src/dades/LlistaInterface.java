package dades;

public interface LlistaInterface<T> {
	
	void inserir (T data);
	
	void inserirPos (int posicio, T data);
	
	T obtenir (int posicio);
	
	int longitud();
	
	void esborrar (int posicio);
	
	int buscar (T data) throws Exception;
}
