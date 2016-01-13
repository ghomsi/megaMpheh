package com.mpheh.form;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.dao.DAOException;
import com.mpheh.interfaces.PointDeRestaurationDao;

public class AjouterPointDeRestaurationForm {

	private static final String CHAMP_NOM = "mphehnom";
	private static final String CHAMP_EMAIL = "mphehemail";
	private static final String CHAMP_NUMERO = "mphehtel";
	private static final String CHAMP_PAYS = "mphehpays";
	private static final String CHAMP_VILLE = "mphehville";
	private static final String CHAMP_ADRESSE = "mphehadresse";
	private static final int TAILLE_TAMPON = 10240;// 10ko
	private static final String CHAMP_IMAGE = "imageClient";
	
	
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String,String>();
	private PointDeRestaurationDao pointDRDao;
	
	public AjouterPointDeRestaurationForm( PointDeRestaurationDao pointDRDao ) {
		this.pointDRDao = pointDRDao;
	}
	
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
public PointDeRestauration ajouterPointDR( HttpServletRequest request ) {
		
		/* Récupération des champs du formulaire */
		
		String nom = getValeurChamp( request, CHAMP_NOM );
		String email = getValeurChamp( request, CHAMP_EMAIL );
		String numero = getValeurChamp( request, CHAMP_NUMERO );
		String pays = getValeurChamp( request, CHAMP_PAYS );
		String ville = getValeurChamp( request, CHAMP_VILLE );
		String adresse = getValeurChamp( request, CHAMP_ADRESSE );
		
		PointDeRestauration pointDR = new PointDeRestauration();
		
		traiterNom( nom, pointDR );
		traiterNumero( numero, pointDR );
		traiterPays( pays, pointDR );
		traiterVille( ville, pointDR );
		traiterAdresse( adresse, pointDR );
		traiterEmail( email, pointDR );
		
		
		try {
			if ( erreurs.isEmpty() ) {
				pointDRDao.creer( pointDR );
				resultat = "Succès de la création d'un contact.";
			} else {
				resultat = "Échec de la création d'un contact.";
			}
			} catch ( DAOException e ) {
				setErreur( "imprévu", "Erreur imprévue lors de la connection." );
				resultat = "Échec de la création d'un contact : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
				e.printStackTrace();
		}
		
			return pointDR;
	}

public PointDeRestauration trouver( long id ) {
	
	/* Récupération des champs du formulaire */
	
	PointDeRestauration pointDR = new PointDeRestauration();
	
	
			pointDRDao.trouver( id );
	
		return pointDR;
}
	
public List<PointDeRestauration> listerPointDR() {
	
	List<PointDeRestauration> pointDRs = new ArrayList<PointDeRestauration>();
	
	pointDRs = pointDRDao.lister();
	return pointDRs;
}

	private void traiterAdresse(String adresse, PointDeRestauration pointDR) {
	// TODO Auto-generated method stub
		pointDR.getDonnees().setAdresse(adresse);
	
}


	private void traiterVille(String ville, PointDeRestauration pointDR) {
	// TODO Auto-generated method stub
	pointDR.getDonnees().setVille(ville);
}


	private void traiterPays(String pays, PointDeRestauration pointDR) {
	// TODO Auto-generated method stub
	pointDR.getDonnees().setPays(pays);
}


	private void traiterNumero(String numero, PointDeRestauration pointDR) {
	// TODO Auto-generated method stub
		
	pointDR.getDonnees().setTel(numero);
}



	private void traiterNom(String nom, PointDeRestauration pointDR) {
	// TODO Auto-generated method stub
	pointDR.setNom(nom);
}


	private void traiterEmail( String email, PointDeRestauration pointDR) {
		try {
			validationEmail( email );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		pointDR.getDonnees().setEmail( email );
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
	
	@SuppressWarnings("unused")
	private String validationImage( HttpServletRequest request,String chemin ) throws FormValidationException {
		/*
		* Récupération du contenu du champ image du formulaire. Il faut ici
		* utiliser la méthode getPart().
		*/
		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart( CHAMP_IMAGE );
			nomFichier = getNomFichier( part );
			/*
			* Si la méthode getNomFichier() a renvoyé quelque chose, il s'agit
			* donc d'un champ de type fichier (input type="file").
			*/
			if ( nomFichier != null && !nomFichier.isEmpty() ) {
				/*
				* Antibug pour Internet Explorer, qui transmet pour une raison
				* mystique le chemin du fichier local à la machine du client...
				* Ex : C:/dossier/sous-dossier/fichier.ext
				* On doit donc faire en sorte de ne sélectionner que le nom et
				* l'extension du fichier, et de se débarrasser du superflu.
				*/
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' )+ 1 );
				
				/* Récupération du contenu du fichier */
				contenuFichier = part.getInputStream();
					
				/* Extraction du type MIME du fichier depuis l'InputStream */
				//MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
				//Collection<?> mimeTypes = MimeUtil.getMimeTypes(contenuFichier );
				/*
				* Si le fichier est bien une image, alors son en-tête MIME
				* commence par la chaîne "image"
				*/
				//if ( mimeTypes.toString().startsWith( "image" ) ){
					
						/* Ecriture du fichier sur le disque */
				//		ecrireFichier( contenuFichier, nomFichier,chemin );
				//} else {
				//		throw new FormValidationException( "Le fichier envoyé doit être une image." );
				//}
			}
		} catch ( IllegalStateException e ) {
				/*
				* Exception retournée si la taille des données dépasse les limites
				* définies dans la section <multipart-config> de la déclaration de
				* notre servlet d'upload dans le fichier web.xml
				*/
				e.printStackTrace();
				throw new FormValidationException( "Le fichier envoyé ne doit pas dépasser 1Mo." );
		} catch ( IOException e ) {
				/*
				* Exception retournée si une erreur au niveau des répertoires de
				* 
				* stockage survient (répertoire inexistant, droits d'accès insuffisants, etc.)
				* */
				e.printStackTrace();
				throw new FormValidationException( "Erreur de configuration du serveur." );
		} catch ( ServletException e ) {
				/*
				* Exception retournée si la requête n'est pas de type
				* multipart/form-data.
				*/
				e.printStackTrace();
				throw new FormValidationException("Ce type de requête n'est pas supporté, merci d'utiliserle formulaire prévu pour envoyer votre fichier." );
		}
		return nomFichier;
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
	
	
	
	/*
	* Méthode utilitaire qui a pour unique but d'analyser l'en-tête
	* "content-disposition", et de vérifier si le paramètre"filename" y est
	* présent. Si oui, alors le champ traité est de type File et la méthode
	* retourne son nom, sinon il s'agit d'un champ de formulaire classique et
	* la méthode retourne null.
	*/
	private static String getNomFichier( Part part ) {
	/* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
		for ( String contentDisposition : part.getHeader("content-disposition" ).split( ";" ) ) {
		
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if ( contentDisposition.trim().startsWith( "filename") ) {
			
			/*
			* Si "filename" est présent, alors renvoi de sa valeur,
			* c'est-à-dire du nom de fichier sans guillemets.
			*/
				return contentDisposition.substring(contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "");
			}
		}
		
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}

	
	
	/*
	* Méthode utilitaire qui a pour but d'écrire le fichier passé en
	paramètre
	* sur le disque, dans le répertoire donné et avec le nom donné.
	*/
	@SuppressWarnings("unused")
	private void ecrireFichier( InputStream contenuFichier, String nomFichier, String chemin )
			throws FormValidationException {
			
				/* Prépare les flux. */
				BufferedInputStream entree = null;
				BufferedOutputStream sortie = null;
				try {
					/* Ouvre les flux. */
					entree = new BufferedInputStream( contenuFichier,TAILLE_TAMPON );
					sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),TAILLE_TAMPON );
							
					/** Lit le fichier reçu et écrit son contenu dans un fichier sur le
					 * disque.
					 */
					byte[] tampon = new byte[TAILLE_TAMPON];
					int longueur = 0;
					while ( ( longueur = entree.read( tampon ) ) > 0 ) {
						sortie.write( tampon, 0, longueur );
					}
				} catch ( Exception e ) {
					throw new FormValidationException( "Erreur lors de l'écriture du fichier sur le disque." );
				} finally {
					try {
						sortie.close();
					} catch ( IOException ignore ) {
					
					}
					try {
						entree.close();
					} catch ( IOException ignore ) {
					
					}
				}
	}		

}
