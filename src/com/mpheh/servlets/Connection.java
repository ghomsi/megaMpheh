package com.mpheh.servlets;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.beans.Restaurateur;
import com.mpheh.dao.DAOFactory;
import com.mpheh.form.ConnectionForm;
import com.mpheh.interfaces.UtilisateurDao;

public class Connection extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_TRAVAILA = "travaila";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE_SUCCES = "/WEB-INF/pointmpheh.jsp";
	public static final String VUE_FORM = "/index.jsp";
	public static final String CHEMIN = "chemin";
	
	
	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getUtilisateurDao();
	}
	
	public void doGet( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException{
			/* Affichage de la page d'inscription */
			this.getServletContext().getRequestDispatcher( VUE_FORM).forward( request, response );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
	
		
			/*
			* Lecture du paramètre 'chemin' passé à la servlet via la déclaration
			* dans le web.xml
			*/
			@SuppressWarnings("unused")
			String chemin = this.getServletConfig().getInitParameter(CHEMIN );
			
			/* Préparation de l'objet formulaire */
			ConnectionForm form = new ConnectionForm(utilisateurDao );
			
			
			/* Traitement de la requête et récupération du bean en résultant */
			Restaurateur user = new Restaurateur();
			user = form.connecterUtilisateur( request );
			
			PointDeRestauration travailA = new PointDeRestauration();
			travailA = form.trouverTravailA(user.getTravailA());
			
			/* Ajout du bean et de l'objet métier à l'objet requête*/
			
			request.setAttribute( ATT_USER, user );
			request.setAttribute( ATT_FORM, form ); 
			request.setAttribute( ATT_TRAVAILA, travailA );
			
			/* Si aucune erreur */			
			if ( form.getErreurs().isEmpty() && user!=null ) { 
				
				/* Alors récupération de la map des utilisateur dans la session */
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				Map<Long, Restaurateur> users = (HashMap<Long, Restaurateur>) session.getAttribute(ATT_SESSION_USER );
				
				/* Si aucune map n'existe, alors initialisation d'une nouvelle map */
				if ( users == null ) {
					users = new HashMap<Long, Restaurateur>();
				}
				
				/* Puis ajout du client courant dans la map */
				users.put( user.getId(), user );
				
				/* Et enfin (ré)enregistrement de la map en session */
				session.setAttribute( ATT_SESSION_USER, users );
				session.setAttribute( ATT_USER, user );
				session.setAttribute( ATT_TRAVAILA , travailA );
				
				/* Affichage de la fiche récapitulative */
				this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
			
			} else {
				
				/* Sinon, ré-affichage du formulaire de création avec les erreurs */
				this.getServletContext().getRequestDispatcher(VUE_FORM ).forward( request, response );
			}
			
		}
		
	
}
