package com.mpheh.beans;

import java.util.Date;
import java.util.Map;



public class Reservation {

	private long code;
	private Date date;
	private long nbrePlaceReserver;
	private Map<String,Table> table; // attribut qui represente la relation de composition entre une reservation et une table de multiplicité 1..*
	                        // une reservation est composée d'au moins une table
	private Map<String, Menu> menu; // attribut qui represente la relation de composition entre une reservation et un menu
						   // une reservation est composée d'au moins un menu
	private PointDeRestauration pointDR;// attribut qui represente la relation d'association simple entre une reservation et un point de reservation
									   // une reservation est associée à un seul point de restauration
	private Contact client; // attribut qui represente la relation d'association simple entre une reservation et un client
	                       // une reservation est associée à un seul client
	
	public Reservation(Map<String,Table> table, Map<String,Menu> menu,long nbrePlaceReserver,Contact client,PointDeRestauration pointDR){
		this.table = table;
		this.menu = menu;
		this.client = client;
		this.pointDR = pointDR;
		this.nbrePlaceReserver = nbrePlaceReserver;
	}
	public Reservation(){
		this.table = null;
		this.menu = null;
		this.client = null;
		this.pointDR = null;
	}
	
	/** accesseur et modificateur d'attribut **/
	public long getNbrePlaceReserver() {
		return nbrePlaceReserver;
	}

	public void setNbrePlaceReserver(long nbrePlaceReserver) {
		this.nbrePlaceReserver = nbrePlaceReserver;
	}
	public long getCode(){
		return code;
	}
	public void setCode(long code){
		this.code = code;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public Map<String,Table> getTable(){
		return table;
	}
	public void setTable(Map<String,Table> table){
		this.table = table;
	}
	public Map<String,Menu> getMenu(){
		return menu;
	}
	public void setMenu(Map<String,Menu> menu){
		this.menu = menu;
	}
	public PointDeRestauration getPDR(){
		return pointDR;
	}
	public void setPDR(PointDeRestauration pointDR){
		this.pointDR = pointDR;
	}
	public Contact getClient(){
		return client;
	}
	public void setClient(Contact client){
		this.client = client;
	}
}
