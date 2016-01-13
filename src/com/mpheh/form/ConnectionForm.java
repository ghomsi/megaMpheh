package com.mpheh.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.beans.Restaurateur;
import com.mpheh.dao.DAOException;
import com.mpheh.interfaces.UtilisateurDao;

public class ConnectionForm {
	
	private static final String CHAMP_EMAIL = "useremail";
	private static final String CHAMP_PASS = "userpwd";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>();
	private UtilisateurDao utilisateurDao;
	
	
	public ConnectionForm( UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}
	
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	
	public Restaurateur connecterUtilisateur( HttpServletRequest request ) {
			
			/* Récupération des champs du formulaire */
			String email = getValeurChamp( request, CHAMP_EMAIL );
			String pwd = getValeurChamp( request, CHAMP_PASS );
			Restaurateur user = new Restaurateur();
			
			traiterPwd( pwd, user );
			traiterEmail( email, user );
			
			
			try {
				if ( erreurs.isEmpty() ) {
					user = utilisateurDao.trouver( email );
					resultat = "Succès de la connection de l'utilisateur.";
				} else {
					resultat = "Échec de la connection du l'utilisateur.";
				}
				} catch ( DAOException e ) {
					setErreur( "imprévu", "Erreur imprévue lors de la connection." );
					resultat = "Échec de la connection de l'utilisateur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
					e.printStackTrace();
			}
			
				return user;
		}
	
	public PointDeRestauration trouverTravailA( long id ) {
		
		/* Récupération des champs du formulaire */
		PointDeRestauration pointDR = new PointDeRestauration();
		
	
				pointDR = utilisateurDao.trouverTravail( id );
				
		
			return pointDR;
	}
	

	
		
		private void traiterEmail( String email, Restaurateur user ) {
			try {
				validationEmail( email );
			} catch ( FormValidationException e ) {
				setErreur( CHAMP_EMAIL, e.getMessage() );
			}
			user.getDonnees().setEmail( email );
		}
		private void traiterPwd( String pwd, Restaurateur user ) {
			try {
				validationPwd( pwd );
			} catch ( FormValidationException e ) {
				setErreur( CHAMP_EMAIL, e.getMessage() );
			}
			user.setPwd( pwd );
		}	
		/**
		* Valide l'adresse email saisie.
		* **/
		private void validationEmail( String email ) throws FormValidationException {
			if ( email != null && !email.matches(
			"([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
			throw new FormValidationException( "Merci de saisir une adresse mail valide." );
			}
		}
		/**
		* Valide le mot de passe saisi.
		*/
		private void validationPwd( String motDePasse ) throws FormValidationException {
			if ( motDePasse != null ) {
				if ( motDePasse.length() < 3 ) {
					throw new FormValidationException( "Le mot de passe doit contenir au moins 3 caractères." );
				}
			} else {
				throw new FormValidationException( "Merci de saisir votre mot de passe." );
			}
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
