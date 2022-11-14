package aplicacio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import dades.Ciutada;
import dades.DoublyLinkedList;
import dades.HashMap;

public class MainCost {

	private static HashMap<Integer, Integer> crearMap(int n) throws Exception {
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Integer aleatori;
		
		for (int i = 0; i < n; i++) {
			Random rnd = new Random();
			aleatori = rnd.nextInt(n/2) + 1;
			map.inserir(aleatori, aleatori);
		}
		
		return map;
	}
	
	private static DoublyLinkedList<Integer> crearLlista(int n) throws Exception {
		
		DoublyLinkedList<Integer> llista = new DoublyLinkedList<Integer>();
		Integer aleatori;
		
		for (int i = 0; i < n; i++) {
			Random rnd = new Random();
			aleatori = rnd.nextInt(n/2) + 1;
			llista.inserir(aleatori);
		}
		
		return llista;
	}
	
	private static void crearCSV(ArrayList<Integer> listaMap, ArrayList<Integer> llistaEnllac, File file) throws IOException {
       
		FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);

	    //Leer archivo
	    String linea = null;
	    String lineaTotal = "";
	   
	    //Mientras que tenga linea el fichero 
        while ((linea = br.readLine()) != null) {
     	//Concateno la linea del fichero y un '\n' al final
        	lineaTotal += linea;
        	lineaTotal += "\n";
        }
        
        br.close();
        fr.close();

	    //Calcular costes
        double sumaMap = 0;
        double sumaLlista = 0;
        double costMitjaMap = 0;
        double costMitjaLista = 0;
        double rango = 0;
        double varianza = 0;
        double desviacionMap = 0;
        double desviacionColision = 0;
        
        for(int i=0;i<listaMap.size();i++)
        {
            sumaMap += listaMap.get(i);
        }
        
        for(int i=0;i<llistaEnllac.size();i++)
        {
        	sumaLlista += llistaEnllac.get(i);
        }

        //Escribir resultados
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        //Media
        costMitjaMap = sumaMap / listaMap.size();
        costMitjaLista = sumaLlista / llistaEnllac.size();
        
        //Desviación
        for(int i = 0 ; i<listaMap.size(); i++){
        	rango = Math.pow(listaMap.get(i) - costMitjaMap, 2);
        	   varianza = varianza + rango;
        }
        
        varianza = varianza / listaMap.size();
        desviacionMap = Math.sqrt(varianza);
        
        rango = 0;
        varianza = 0;

        //Desviación
        for(int i = 0 ; i<llistaEnllac.size(); i++){
        	rango = Math.pow(llistaEnllac.get(i) - costMitjaLista, 2);
        	   varianza = varianza + rango;
        }
        
        varianza = varianza / llistaEnllac.size();
        desviacionColision = Math.sqrt(varianza);
        
        bw.write(lineaTotal);
        bw.write(listaMap.size()+","+costMitjaMap+","+ desviacionMap +","+costMitjaLista +","+ desviacionColision + "\n");
        bw.flush();
        bw.close();
        fw.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		//Creo dos arrayList para guardar los resultados de las busquedas en el map y en la lista de colisiones
		ArrayList<Integer> listaMap = new ArrayList();
		ArrayList<Integer> listaLista = new ArrayList();
		
		//Bucle para crear una lista de 1000 elementos hasta 50000 pero de 1000 en 1000.
		for (int i = 1000; i <= 50000; i= i + 1000) {
			
			//Cremo un map de esa cantidad (1000, 2000, 3000, ...)
			HashMap<Integer, Integer> map = crearMap(i);
			DoublyLinkedList<Integer> llista = crearLlista(i);
			
			//Borrar las listas antes de rellenarlas con las búsquedas
			listaMap.clear();
			
			for (int j = 0; j < i; j++) {
				Random rnd = new Random();
				int aleatori = rnd.nextInt(i/2) + 1;
				listaMap.add(map.buscar(aleatori));
				listaLista.add(llista.buscar(aleatori));
			}
			
			File file = new File("test.csv");
			
			//Guardamos en un csv
			crearCSV(listaMap, listaLista, file);
			
			//log para saber por donde va
			System.out.println(i);
		}

		int prueba = 0;

	}
}
