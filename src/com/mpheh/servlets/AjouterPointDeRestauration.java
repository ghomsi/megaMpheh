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

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.dao.DAOFactory;
import com.mpheh.form.AjouterPointDeRestaurationForm;
import com.mpheh.interfaces.PointDeRestaurationDao;

/**
 * Servlet implementation class AjouterPointDeRestauration
 */
@WebServlet("/AjouterPointDeRestauration")
public class AjouterPointDeRestauration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_POINTDR = "pointDR";
	public static final String ATT_SESSION_POINTDRS = "listePointDR";
	public static final String VUE_SUCCES = "/WEB-INF/pointmpheh.jsp";
	public static final String CHEMIN = "chemin";
	
	private PointDeRestaurationDao pointDRDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterPointDeRestauration() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.pointDRDao = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getPointDRDao();
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
		AjouterPointDeRestaurationForm form = new AjouterPointDeRestaurationForm(pointDRDao );
		PointDeRestauration pointDR = form.ajouterPointDR( request );
		
		
		
		/* Si aucune erreur */
		if ( form.getErreurs().isEmpty() ) {
			/* Alors récupération de la map des clients dans la
			session */
			HttpSession session = request.getSession();
			List<PointDeRestauration> pointdrs = new ArrayList<PointDeRestauration>();
			session.getAttribute( ATT_SESSION_POINTDRS );
			
			/* Si aucune map n'existe, alors initialisation d'une
			nouvelle map */
			if ( pointdrs == null ) {
				pointdrs= new ArrayList<PointDeRestauration>();
			}
			/* Puis ajout du client courant dans la map */
			pointdrs = form.listerPointDR();;
			/* Et enfin (ré)enregistrement de la map en session*/
			session.setAttribute( ATT_SESSION_POINTDRS, pointdrs );
			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		} else {
			/* Sinon, ré-affichage du formulaire de création avec
			les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		}
	}

}
