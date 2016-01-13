package com.mpheh.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mpheh.beans.Reservation;
import com.mpheh.dao.DAOException;
import com.mpheh.interfaces.ReservationDao;

public class ReservationForm {

	private static final String CHAMP_CODE ="code";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>();
	private ReservationDao reservationrDao;
	
	public ReservationForm( ReservationDao reservationrDao ) {
		this.reservationrDao = reservationrDao;
	}
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	
	public Reservation connecterReservation( HttpServletRequest request ) {
		
		/* Récupération des champs du formulaire */
		long code = getValeurChamp( request, CHAMP_CODE );
		Reservation reservation = new Reservation();
		
		traiterCode( code, reservation );
		
		
		try {
			if ( erreurs.isEmpty() ) {
				reservation = reservationrDao.trouver( code );
				resultat = "Succès de la connection de l'utilisateur.";
			} else {
				resultat = "Échec de la connection du l'utilisateur.";
			}
			} catch ( DAOException e ) {
				setErreur( "imprévu", "Erreur imprévue lors de la connection." );
				resultat = "Échec de la connection de l'utilisateur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
				e.printStackTrace();
		}
		
			return reservation;
	}
	
	
	private void traiterCode( Long code, Reservation reservation ) {
		try {
			validationCode( code );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_CODE, e.getMessage() );
		}
		reservation.setCode( code );
	}
	
	/**
	* Valide le code saisi.
	*/
	private void validationCode( Long code ) throws FormValidationException {
		if ( code == null ) {
			throw new FormValidationException( "Merci de saisir votre code de reservation." );
		}
	}
	
	/*
	* Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	* sinon.
	*/
	private static long getValeurChamp( HttpServletRequest request, String nomChamp ) {
		long valeur = new Integer(request.getParameter( nomChamp ));
	
			return valeur;
		
	}
	
	/*
	* Ajoute un message correspondant au champ spécifié à la map des erreurs.
	*/
	private void setErreur( String champ, String message ) {
		erreurs.put( champ, message );
	}
}
