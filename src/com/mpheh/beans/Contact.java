package com.mpheh.beans;

public class Contact {

	private long id;
	private String nom;
	private String prenom;
	private Donnees donnees; // attribut qui marque l'association de type composition entre contact et données "d'ordre de muliplicité 1"
							// Un contact est composé de donnée
	public Contact(String nom, String prenom,Donnees donnees){
		this.nom = nom;
		this.prenom = prenom;
		this.donnees = donnees;
	}
	public Contact(){
		this.nom = null;
		this.prenom = null;
		this.donnees = new Donnees();
	}
	
	/** accesseur et modificateur d'attribut **/
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	public String getNom(){
		return nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getPrenom(){
		return prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public Donnees getDonnees(){
		return donnees;
	}
	public void setDonnees(Donnees donnees){
		this.donnees = donnees;
	}
	

}
