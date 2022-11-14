package dades;

import java.util.ArrayList;
import java.util.Objects;

import excepcions.ElementNotFoundException;
import excepcions.NewNodeException;

public class HashMap<K, T> implements HashInterface<K, T>{
	
    private HashNode<K, T>[] inicio;
    private Integer numElements;
    private final Integer DIMENSIO = 10;
    private final Float factorCarregaSup = 0.75f;
    private final Float factorCarregaInf = 0.10f;
    
    public HashMap() {
    	crear();
    }

    public void crear() {
    	inicio = new HashNode[DIMENSIO];
    	numElements = 0;
    }
    

	private Integer hash(K key) {

		String clau = key.toString();
		Integer resultat = 1;
		
		for (int i = 0; i < clau.length(); i++) {
			int potencia = (int) Math.pow(2, i);
			resultat = (((Integer) resultat * potencia) + clau.charAt(i)) % inicio.length;
		}
		
		return resultat;
	}	

	@Override
	public void inserir(K key, T data) throws Exception {
		int posicion;
        int posBusqueda = -1;
        boolean encontrado = false;
        
        HashNode<K, T> nuevo = new HashNode(key, data, null);

        if (nuevo != null) {
	        //Buscar elemento para ver si existe
        	//Si la funcion buscar me devuelve un valor mayor o igual que el tamaño es porque no lo ha encontrado
        	//Bucle hasta que encuentre la clave
        	
        	posicion = hash(key);
        	
        	if (inicio[posicion] == null) {
        		//posicion = hashCode(key) % inicio.length;
        		inicio[posicion] = nuevo;
		        numElements++;
        	}
        	else {
        		HashNode<K, T> nodeAct = inicio[posicion];
        		
        		while (nodeAct.next != null) {
        			nodeAct = nodeAct.next;
        		}
        		
        		nodeAct.next= nuevo;
        		numElements++;
        	}
        }
        else {
        	throw new NewNodeException();
        }

        redimensionarTaula();
	}

	@Override
	public T obtenir(K key) throws Exception {
		
		boolean encontrado = false;
		T objeto = null;
		
    	//Bucle hasta que encuentre la clave
    	for(int i = 0; i < numElements && encontrado == false; i++) {
    		
    		//No buscar en posiciones vacías
    		//Si la key del nodo actual es igual a la key del parametro
    		if (inicio[i].key.equals(key)) {
    			objeto = inicio[i].value; //Lo he encontrado
    			encontrado = true;
    		}
    		else {
    			///Buscar dentro de la lista enlazada del nodo
    			HashNode<K, T> nodeAct = inicio[i];
    			
    			while (nodeAct != null && encontrado == false) {
    				if (nodeAct.value.equals(key)) {
    					encontrado = true;
    					objeto = inicio[i].value;
    				}
    				
    				nodeAct = nodeAct.next;
    			}
    		}
    	}
    	
    	//si el elemento no se ha encontrado lanzamos excepción
    	if (encontrado == false) {
    		throw new ElementNotFoundException();
    	}
    	
    	return objeto;
	}

	@Override
	public int buscar(K key) throws Exception {
		
		int elements = 0;
		boolean encontrado = false;

		int posicion = hash(key);
		
		HashNode<K, T> nodeAct = inicio[posicion];
		
		if (nodeAct != null) {
			
			while (nodeAct != null && encontrado == false) {
				if (inicio[posicion].value.equals(key)) {
					encontrado = true;
				}
				elements++;
				nodeAct = nodeAct.next;
			}
		}
		
    	return elements;
	}

	@Override
	public int mida() {
		return numElements;
	}

	public boolean isEmpty() { 
		return mida() == 0; 
	}

	private final int hashCode (K key) {
		return Objects.hashCode(key);
	}
	
	@Override
	public void esborrar(K key) throws Exception {
		
		int posicion = -1;
		boolean encontrado = false;

		posicion = hash(key);
    	
    	if (inicio[posicion] != null) {
    		
    		if (inicio[posicion].next == null) {
    			inicio[posicion] = null;
    			numElements--;
    		}
    		else {
    			
    			HashNode<K, T> nodeAct = inicio[posicion];
    			HashNode<K, T> nodePrev = null;
    			
    			while (nodeAct != null && encontrado == false) {
    				if (nodeAct.value.equals(key)) {
    					encontrado = true;
    				}
    				
    				nodePrev = nodeAct;
    				nodeAct = nodeAct.next;
    			}
    			
    			//Acaba el bucle, estoy en la posicion para borrar
    			nodePrev.next = nodeAct.next;
    		}
    	}
    	//Sino se ha encontrado lanzo la excepcion
    	else {
    		throw new ElementNotFoundException();
    	}
    	
    	redimensionarTaula();
	}

	@Override
	public DoublyLinkedList<T> obtenirValors() {
		
		DoublyLinkedList<T> llista = new DoublyLinkedList<T>();

		//Bucle hasta que encuentre la clave
    	for(int i = 0; i < numElements; i++) {
    		
    		if (inicio[i] != null) {
    			
    			HashNode<K, T> nodeAct = inicio[i];
    			
    			while (nodeAct != null) {
    				//Para cada nodo insertamos el value en la lista
    				llista.inserir(nodeAct.value);
    				nodeAct = nodeAct.next;
    			}
    		}
		}
		
		return llista;		
	}

	@Override
	public DoublyLinkedList<K> obtenirClaus() {
		
		DoublyLinkedList<K> llista = new DoublyLinkedList<K>();

		//Bucle hasta que encuentre la clave
    	for(int i = 0; i < numElements; i++) {
    		
    		if (inicio[i] != null) {
    			
    			HashNode<K, T> nodeAct = inicio[i];
    			
    			while (nodeAct != null) {
    				//Para cada nodo insertamos el value en la lista
    				llista.inserir(inicio[i].key);
    				nodeAct = nodeAct.next;
    			}
    		}
		}
		
		return llista;		
	}

	@Override
	public Float obtenirFactorDeCarrega() {
		return (numElements.floatValue() / inicio.length);
	}

	@Override
	public void redimensionarTaula() {
		
		 if (obtenirFactorDeCarrega() > factorCarregaSup) {
			
			//Creamos tabla auxiliar de 10 elementos más
			HashNode<K, T>[] aux = new HashNode[inicio.length + DIMENSIO];
			
			//Copiamos los datos de la tabla inicio
			for (int i = 0; i < numElements; i++) {
				aux[i] = inicio[i];
			}
			
			//Creo de nuevo la tabla más grande
			inicio = new HashNode[inicio.length + DIMENSIO];
			
			//Copiamos los datos de la tabla aux
			 for (int i = 0; i < numElements; i++) {
				 inicio[i] = aux[i];
			 }

		}
		else if (obtenirFactorDeCarrega() < factorCarregaInf) {
			 
			//Creamos tabla auxiliar de 10 elementos más
			HashNode<K, T>[] aux = new HashNode[inicio.length - DIMENSIO];
			
			//Copiamos los datos de la tabla inicio
			for (int i = 0; i < numElements; i++) {
				aux[i] = inicio[i];
			}
			
			//Creo de nuevo la tabla más grande
			inicio = new HashNode[inicio.length - DIMENSIO];
			
			//Copiamos los datos de la tabla aux
			 for (int i = 0; i < numElements; i++) {
				 inicio[i] = aux[i];
			 }
		}
	}
}
