package dades;

import java.util.Iterator;

import excepcions.ElementNotFoundException;
import excepcions.IndexException;

public class DoublyLinkedList<T> implements LlistaInterface<T>, Iterable<T>{

	public Node header;
	
	public DoublyLinkedList() {
		header = new Node();	
	}

	@Override
	public void inserir(T data) {
		Node end = getNode(longitud() - 1);
		Node nodeIns = new Node(null, end, data);
		
		//Si la lista esta vacia, insertamos en la cabecera
		if (header.data == null) {
			header = nodeIns;
		}
		else {
			//Asignamos el elemento como siguiente del ultimo si la lista no está vacia
			end.next = nodeIns;	
		}		
	}

	@Override
	public void inserirPos(int posicio, Object data) {
		if (posicio >= longitud() || posicio < 0) {
			throw new IndexException(posicio);
		}

		Node nodeIns = null;

		if (data != null) {
			Node node = header;

			if (node.next != null) {
				for (int i = 0; i < posicio; i++) {
					node = node.next;
				}
			}
			
			nodeIns = new Node(node.next, node, data);
			
			//Insertamos en el primero
			if (posicio == 0) {
				nodeIns.next = header;
				header = nodeIns;
			}
			else {
				if (node.next != null) {
					node.next.previous = nodeIns;
				}
			}
			node.next = nodeIns;
		}
		// return nodeIns;
		
	}

	@Override
	public T obtenir(int posicio) {
		
		if (posicio >= longitud() || posicio < 0) {
			throw new IndexException(posicio);
		}
		
		T element = null;
		Node node = header.next;
		
		//Desplazarme hasta el nodo que quiero obtener
		if (node.next != null) {
			for (int i = 0; i <= posicio; i++) {
				element = (T) node.getData();
				node = node.next;
			}
		}
		
		return element;
	}

	@Override
	public int longitud() {
		int count = 0;
		Node node = header.next;
		while (node != null) {
			++count;
			node = node.next;
		}
		return count;
	}

	@Override
	public void esborrar(int posicio) {
		
		if (posicio >= longitud() || posicio < 0) {
			throw new IndexException(posicio);
		}
		
		Node nodeAct = header;
		Node nodoAnt = null;
		
		//Desplazarme hasta el nodo que quiero borrar
		if (nodeAct.next != null) {
			for (int i = 0; i <= posicio; i++) {
				nodoAnt =  nodeAct;
				nodeAct = nodeAct.next;
			}
		}
		
		//Hemos llegado a la posicion
		nodoAnt.next = nodeAct.next;
	}

	@Override
	public int buscar(T data) throws Exception {
		
		Node node = header;
		boolean trobat = false;
		int cost = 0;
		
		while (node != null && !trobat) {
			
			//Comparamos objetos de tipo Cuidata que hay en el nodo y en el T data
			//usando el compareTo definido en esa clase.
			if ((node.getData()).equals(data)) {
				trobat = true;
			}
			
			node = node.next; //Importante desplazar el puntero al siguiente
			cost++;
		}
		
		//si el coste es mayor o igual que la longitud de la lista
		/*if (cost >= longitud()) {
			throw new ElementNotFoundException(cost);
		}*/
		
		return cost;
	}
	
	private Node getNode(int index) {
		if (index > longitud())
			throw new IndexException();
		Node node = header;
		for (int i = 0; i <= index; ++i) {
			node = node.next;
		}
		return node;
	}
	
	private DoublyLinkedList<T> copiar() {
	
		DoublyLinkedList<T> copia = new DoublyLinkedList<T>();
		
		try {
			Node actual = header;
			
			while (actual != null) {
				copia.inserir((T) actual.data);
				actual = actual.next;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return copia;
	}

	@Override
	public Iterator<T> iterator() {
		 
		return new Iterator<T>() {

			Node actual = copiar().header;

			@Override
			public boolean hasNext() {
				return actual != null;
			}

			@Override
			public T next() {
				
				if (hasNext()) {
	                 T data = (T) actual.data;
	                 actual = actual.next;
	                 return data;
	            }
				
	            return null;
			}
		};
	}
}
