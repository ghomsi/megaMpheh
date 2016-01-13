package com.mpheh.beans;

import java.sql.Timestamp;
import java.util.Date;


public class Restaurateur {

	private long id;
	private String nom;
	private String prenom;
	private String pwd;
	private String nivoAcces;
	private Date date;
	private Donnees donnees;
	private long travailA; // attribut representant l'association simple entre restaurateur et point de restauration de multiplicité 1
						 // ->un restaurateur travail dans un seul point de restauration
	

	
	public Restaurateur(String nom,String prenom, String pwd, String nivoAcces,Timestamp date,long travailA){
		this.nom = nom;
		this.prenom = prenom;
		this.pwd = pwd;
		this.nivoAcces = nivoAcces;
		this.date = date;
		this.travailA = travailA;
		this.donnees = new Donnees();
	}
	public Restaurateur(){
		this.nom = null;
		this.prenom = null;
		this.pwd = null;
		this.nivoAcces = null;
		this.date = null;
		this.travailA = 0;
		this.donnees = new Donnees();
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
	public String getPrenom(){
		return prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public String getPwd(){
		return pwd;
	}
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	public String getType(){
		return nivoAcces;
	}
	public void setType(String type){
		this.nivoAcces = type;
	}
	public Donnees getDonnees(){
		return donnees;
	}
	public void setDonnees(Donnees donnees){
		this.donnees = donnees;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public long getTravailA(){
		return travailA;
	}
	public void setTravailA(long idPDR){
		this.travailA = idPDR;
	}

}
