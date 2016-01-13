package com.mpheh.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mpheh.beans.Table;
import com.mpheh.dao.DAOException;
import com.mpheh.interfaces.TableDao;

public class AjouterTableForm {

	private static final String CHAMP_NOM = "tablenom";
	private static final String CHAMP_NBREPLACE = "tablenbreplace";
	@SuppressWarnings("unused")
	private static final int TAILLE_TAMPON = 10240;// 10ko
	@SuppressWarnings("unused")
	private static final String CHAMP_IMAGE = "imageClient";
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>();
	private TableDao tableDao;
	
	public AjouterTableForm( TableDao tableDao ) {
		this.tableDao = tableDao;
	}
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	
	public Table ajouterTable( HttpServletRequest request ) {
		
		/* Récupération des champs du formulaire */
		
		String nom = getValeurChamp( request, CHAMP_NOM );
		long nbreplace = Long.parseLong(getValeurChamp( request, CHAMP_NBREPLACE ));
		
		Table table = new Table();
		
		traiterNom( nom, table );
		traiterPrenom( nbreplace, table );
		
		
		try {
			if ( erreurs.isEmpty() ) {
				tableDao.creer( table );
				resultat = "Succès de la connection de l'utilisateur.";
			} else {
				resultat = "Échec de la connection du l'utilisateur.";
			}
			} catch ( DAOException e ) {
				setErreur( "imprévu", "Erreur imprévue lors de la connection." );
				resultat = "Échec de la connection de l'utilisateur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
				e.printStackTrace();
		}
		
			return table;
	}
	
	
	public List<Table> listerTable() {
		
		List<Table> tables = new ArrayList<Table>();
		
		tables = tableDao.lister();
		return tables;
	}



	private void traiterPrenom(long nbreplace, Table table) {
		// TODO Auto-generated method stub
		table.setNbrePlace(nbreplace);
	}

	private void traiterNom(String nom, Table table) {
		// TODO Auto-generated method stub
		table.setNom(nom);
	}
	
	/*
	* Ajoute un message correspondant au champ spécifié à la map des erreurs.
	*/
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}
	
	/*
	* Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	* sinon.
	*/
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
	
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}

	
}
