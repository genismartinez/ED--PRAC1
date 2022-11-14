package dades;

import java.util.Objects;

public class Ciutada implements Comparable<Ciutada>{
	
	String nom;
	String cognom;
	String dni;
	Node header;

	public Ciutada(String nom, String cognom, String dni) {
		this.nom = nom;
		this.cognom = cognom;
		this.dni = dni;
		this.header = new Node(null, null, null);
	}

	@Override
	public String toString() {
		return this.nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciutada other = (Ciutada) obj;
		return Objects.equals(dni, other.dni);
	}

	public int compareTo (Ciutada c) {
		if(c.getDni().equalsIgnoreCase(this.getDni())) {
			return 0;
		}
		return -1;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
