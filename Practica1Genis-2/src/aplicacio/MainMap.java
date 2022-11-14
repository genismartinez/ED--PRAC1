package aplicacio;

import dades.Ciutada;
import dades.DoublyLinkedList;
import dades.HashMap;

public class MainMap {

	public static void main(String[] args) throws Exception {
		
		HashMap<Integer, Ciutada> map = new HashMap<Integer, Ciutada>();
		map.inserir(1, new Ciutada("Pere", "García", "12345678A"));
		map.inserir(2, new Ciutada("Daniel", "García", "987654321V"));
		map.inserir(3, new Ciutada("Carlos", "Martínez", "87654321B"));
		map.inserir(4, new Ciutada("Miguel", "Navarro", "12345678A"));
		
		DoublyLinkedList<Ciutada> valors = map.obtenirValors();	
		//DoublyLinkedList<String> claus = map.obtenirClaus();	
		
		//map.esborrar("987654321V");
		map.redimensionarTaula();
		
		valors = map.obtenirValors();	
		
		//Prova iterator DoublyLinkedList
		for (Ciutada c: valors) {
			System.out.println(c);
		}
	}
}
