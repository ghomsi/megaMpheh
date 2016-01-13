package com.mpheh.beans;

public class Menu {

	private long id;
	private String nom;
	private long prix;
	private String image;
	
	
	public Menu(String nom, long prix){
		this.nom = nom;
		this.prix = prix;
	}
	public Menu(){
		this.nom = null;
		this.prix = 0;
	}
	
	/** accesseur et modificateur d'attribut **/
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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
	public long getPrix(){
		return prix;
	}
	public void setPrix(long prix){
		this.prix = prix;
	}
}
