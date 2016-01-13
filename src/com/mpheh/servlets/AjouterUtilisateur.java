package com.mpheh.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mpheh.beans.Restaurateur;
import com.mpheh.dao.DAOFactory;
import com.mpheh.form.AjouterUtilisateurForm;
import com.mpheh.interfaces.UtilisateurDao;

/**
 * Servlet implementation class AjouterUtilisateur
 */
@WebServlet("/AjouterUtilisateur")
public class AjouterUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_SESSION_NEWUSER = "listeUtilisateurs";
	public static final String VUE_SUCCES = "/WEB-INF/pointmpheh.jsp";
	public static final String CHEMIN = "chemin";
	
	
	private UtilisateurDao utilisateurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( VUE_SUCCES).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/*
		* Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		* dans le web.xml
		*/
		@SuppressWarnings("unused")
		String chemin = this.getServletConfig().getInitParameter(CHEMIN );
		
		/* Préparation de l'objet formulaire */
		AjouterUtilisateurForm form = new AjouterUtilisateurForm(utilisateurDao );
		
		/* Traitement de la requête et récupération du bean en résultant */
		@SuppressWarnings("unused")
		Restaurateur user = form.ajouterUtilisateur( request );
		
		List<Restaurateur> users = form.listerUtilisateur();
		
		request.setAttribute("users", users);
		
		/* Alors récupération de la map des utilisateur dans la session */
		HttpSession session = request.getSession();
		/* Et enfin (ré)enregistrement de la map en session */
		session.setAttribute( ATT_SESSION_NEWUSER, users );
		
		/* Affichage de la fiche récapitulative */
		this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		
	}

}
