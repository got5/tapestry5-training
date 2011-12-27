package net.atos.mm.formation.tapestry.data;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private long id;
	
	private String nom;
	
	private String prenom;
	
	private List<Phone> phones = new ArrayList<Phone>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	
}
