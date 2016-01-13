package com.mpheh.beans;

public class Table {

	private long id;
	private String nom;
	private long nbrePlace;
	private long nbrePlaceDispo;
	
	public Table(String nom, int nbreP){
		this.nom = nom;
		this.nbrePlace = nbreP;
		this.nbrePlaceDispo = nbreP;
	}
	public Table(){
		this.nom = null;
		this.nbrePlace =  0;
		this.nbrePlaceDispo = 0;
	}
	
	/** modificateur et accesseur d'attribut **/
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
	public long getNbrePlace(){
		return nbrePlace;
	}
	public void setNbrePlace(long nbre){
		this.nbrePlace = nbre;
	}
	public long getNbrePlaceDispo(){
		return nbrePlaceDispo;
	}
	public void setNbrePlaceDipso(long nbre){
		this.nbrePlaceDispo = nbre;
	}

}
