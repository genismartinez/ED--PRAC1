package aplicacio;

import java.util.Scanner;

import dades.Ciutada;
import dades.DoublyLinkedList;

import java.util.LinkedList;


public class Main {

	public static void main(String[] args) throws Exception {
		mostrarMenu();

	}
	public static void mostrarMenu() throws Exception {
		final Scanner teclat;

		teclat= new Scanner(System.in);
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("\t***Benvingut/da al programa.***");
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("Introdueix la operaciÃ³ que vols realitzar. ->");
		System.out.println("-----------------------------------------------");

		DoublyLinkedList<Ciutada> list = null;


		boolean leave = false;
		do {	
			System.out.println("[1] - Crear llista");
			System.out.println("[2] - Afegir element al final de la llista");
			System.out.println("[3] - Afegir element a la llista a una posició indicada");
			System.out.println("[4] - Element de la posició");
			System.out.println("[5] - Nombre d'elements a la llista");
			System.out.println("[6] - Eliminar un determinat element de la llista");
			System.out.println("[7] - Busca element a la llista");
			System.out.println("[8] - EXIT");
			System.out.println("--------------------------------------------------------");
			System.out.println("EXTRES");
			System.out.println("--------------------------------------------------------");
			System.out.println("[9] - Mostrar llista");



			switch (opcioMenu()) {
				case 1:
				{
					list = new DoublyLinkedList<Ciutada>();
					list.inserir(new Ciutada("Pere", "García", "12345678A"));
					list.inserir(new Ciutada("Daniel", "García", "987654321V"));
					list.inserir(new Ciutada("Carlos", "Martínez", "87654321B"));
					list.inserir(new Ciutada("InÃ©s", "Ortiz", "00000000C"));
					System.out.println("Llista creada amb éxit.");
					break;
				}
				case 2:
				{
					System.out.println("Introdueixi el NOM del Ciutadà  que vol afegir.");
					String nomCiut = teclat.nextLine();
					System.out.println("Introdueixi el COGNOM del Ciutadà  que vol afegir.");
					String cognomCiut = teclat.nextLine();
					System.out.println("Introdueixi el DNI del Ciutadà  que vol buscar. (En format '00000000A')");
					String dniCiut = teclat.nextLine();
	
					list.inserir(new Ciutada(nomCiut,cognomCiut,dniCiut));
					break;
				}
				case 3:
				{
					System.out.println("Introdueixi a quina posició vol introduir el Ciutadà .");
					int index = teclat.nextInt();
					teclat.nextLine();
					System.out.println("Introdueixi el NOM del Ciutadà  que vol afegir.");
					String nomCiuta = teclat.nextLine();
					System.out.println("Introdueixi el COGNOM del Ciutadà  que vol afegir.");
					String cognomCiuta = teclat.nextLine();
					System.out.println("Introdueixi el DNI del Ciutadà  que vol buscar. (En format '00000000A')");
					String dniCons = teclat.nextLine();

					Ciutada auxi= new Ciutada(nomCiuta,cognomCiuta,dniCons);
					list.inserirPos(index, auxi);
					break;
				}
				case 4:
				{
					System.out.println("Introdueixi l'índex del node que vol comprobar.");
					int ind = teclat.nextInt();				
					System.out.println(list.obtenir(ind));
					int posicio=0;
	
					break;
				}
				case 5:
				{
					System.out.println("A la llista hi han " + list.longitud() + " elements.");
	
					break;
				}
				case 6:
				{
					System.out.println("Introdueixi l'índex de la posició que vol eliminar.");
					int i = teclat.nextInt();
	
					list.esborrar(i);
					System.out.println("El ciutadà  s'ha esborrat correctament.");
					break;
				}
				case 7:
				{
					System.out.println("Introdueixi el NOM del Ciutadà  que vol afegir.");
					String nomCiuta = teclat.nextLine();
					System.out.println("Introdueixi el COGNOM del Ciutadà  que vol afegir.");
					String cognomCiuta = teclat.nextLine();
					System.out.println("Introdueixi el DNI del Ciutadà  que vol buscar. (En format '00000000A')");
					String dniCons = teclat.next();
					
					Ciutada c= new Ciutada(nomCiuta,cognomCiuta,dniCons);
					System.out.println("El elemento se encuentra en la posición: " + list.buscar(c));
					break;
				}
				case 8:
				{
					System.out.println("Fins Aviat!");
					leave = true;
					break;
				}
				case 9:
				{
					break;
				}	
			}
		} while (!leave);
		
		teclat.close();
	}
	public static int opcioMenu() {
		int opcio = 0;
		Scanner teclat = new Scanner(System.in);
		do {
			opcio = teclat.nextInt();
			if (opcio < 1 || opcio > 9) {
				System.out.println("El número introduit ha d'estar entre l'1 i el 9!");
			}
		} while (opcio < 1 || opcio > 9);
		return opcio;
	}
}

