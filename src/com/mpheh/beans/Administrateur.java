package com.mpheh.beans;

import java.sql.Timestamp;

public class Administrateur extends Restaurateur {

	public Administrateur(String nom,String prenom, String pwd, Timestamp date,int travailA){
		super(nom,prenom,pwd,"admin",date,travailA);
	}
	
	public void creerUtilisateur(Restaurateur restaurateur){
		
	}
	
	public void supprimerUtilisateur(Restaurateur restaurateur){
		
	}
	public void modifierUtilisateur(Restaurateur restaurateur){
		
	}
}
