package com.mpheh.beans;

public class Donnees {

	private String tel;
	private String email;
	private String pays;
	private String ville;
	private String adresse;
	
	public Donnees(String tel, String email, String pays, String ville, String adresse){
		this.tel = tel;
		this.email = email;
		this.pays = pays;
		this.ville = ville;
		this.adresse = adresse;
	}
	public Donnees(){
		this.tel = null;
		this.email = null;
		this.pays = null;
		this.ville = null;
		this.adresse = null;
	}
	
	/** accesseur et modificateur d'attributs **/
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPays(){
		return pays;
	}
	public void setPays(String pays){
		this.pays = pays;
	}
	public String getVille(){
		return ville;
	}
	public void setVille(String ville){
		this.ville = ville;
	}
	public String getAdresse(){
		return adresse;
	}
	public void setAdresse(String adresse){
		this.adresse = adresse;
	}
}
