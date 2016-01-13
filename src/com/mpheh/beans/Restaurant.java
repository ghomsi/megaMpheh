package com.mpheh.beans;

import java.util.Map;

public class Restaurant {
	private String nom;
	Map<String,PointDeRestauration> pointMpheh; // attribut qui represente l'association de type composition entre un restaurant et un point de restauration de multiplicité 1..*
										   // un restaurant est composé d'au moins un point de restauration
	
	public Restaurant(String nom, Map<String,PointDeRestauration> pointMpheh){
		this.nom = nom;
		this.pointMpheh = pointMpheh;
	}

	/** accesseur et modificateur d'attributs **/
	public String getNom(){
		return nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public Map<String,PointDeRestauration> getPDR(){
		return pointMpheh;
	}
	public void setPDR(Map<String,PointDeRestauration> pointMpheh){
		this.pointMpheh = pointMpheh;
	}

}
