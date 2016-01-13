package com.mpheh.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mpheh.beans.Contact;
import com.mpheh.dao.DAOFactory;
import com.mpheh.form.AjouterContactForm;
import com.mpheh.interfaces.ContactDao;

/**
 * Servlet implementation class AjouterContact
 */
@WebServlet("/AjouterContact")
public class AjouterContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_CONTACT = "contact";
	public static final String ATT_SESSION_CONTACTS = "listeContact";
	public static final String VUE_SUCCES = "/WEB-INF/pointmpheh.jsp";
	public static final String CHEMIN = "chemin";
	
	private ContactDao contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterContact() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.contactDao = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getContactDao();
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		* Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		* dans le web.xml
		*/
		String chemin = this.getServletConfig().getInitParameter(CHEMIN );
		
		/* Préparation de l'objet formulaire */
		AjouterContactForm form = new AjouterContactForm(contactDao );
		
		/* Traitement de la requête et récupération du bean en résultant */
		Contact contact = form.ajouterContact( request );
		
		
		/* Si aucune erreur */
		if ( form.getErreurs().isEmpty() ) {
			/* Alors récupération de la map des clients dans la session */
			HttpSession session = request.getSession();
			List<Contact> contacts = new ArrayList<Contact>();
			session.getAttribute( ATT_SESSION_CONTACTS );
			
			/* Si aucune map n'existe, alors initialisation d'une
			nouvelle map */
			if ( contacts == null ) {
				contacts= new ArrayList<Contact>();
			}
			/* Puis ajout du client courant dans la map */
			contacts = form.listerContact();;
			/* Et enfin (ré)enregistrement de la map en session*/
			session.setAttribute( ATT_SESSION_CONTACTS, contacts );
			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		} else {
			/* Sinon, ré-affichage du formulaire de création avec
			les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		}
	}

}
