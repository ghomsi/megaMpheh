package com.mpheh.beans;

import java.util.List;

public class PointDeRestauration {

	private long id;
	private String nom;
	private Donnees donnees;
	private List<Restaurateur> employer; // attribut representant l'association de type composition entre un point de restauration et un restaurateur de multiplicité 1..*
							   // un point de restauration est composé d'au moins un restaurateur
	private List<Reservation>  reservations; // attribut representant l'association simple entre un point de restauration et un reservation de multiplicité 1..*
	                              // un point de restauration est associé a au moin une reservation
	
	public PointDeRestauration(String nom, Donnees donnees, List<Restaurateur>  employer){
		this.nom = nom;
		this.donnees = donnees;
		this.employer = employer;
	}
	public PointDeRestauration(){
		this.nom = null;
		this.donnees = new Donnees();
		this.employer = null;
	}

	/** accesseur et modificateur d'attributs **/
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
	public Donnees getDonnees(){
		return donnees;
	}
	public void setDonnees(Donnees donnees){
		this.donnees = donnees;
	}
	public List<Restaurateur>  getEmployer(){
		return employer;
	}
	public void setEmployer(List<Restaurateur> employer){
		this.employer = employer;
	}
	public List<Reservation>  getReservation(){
		return reservations;
	}
	public void setReservation(List<Reservation>  reservations){
		this.reservations = reservations;
	}

}
