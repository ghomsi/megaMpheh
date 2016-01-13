package com.mpheh.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mpheh.beans.Reservation;
import com.mpheh.dao.DAOFactory;
import com.mpheh.form.ReservationForm;
import com.mpheh.interfaces.ReservationDao;

public class Agenda extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_RESERVCLIENT = "reservationClient";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_RESERVATION = "reservation";
	public static final String VUE_SUCCES = "/WEB-INF/agenda.jsp";
	public static final String VUE_FORM = "/index.jsp";
	public static final String CHEMIN = "chemin";
	
	private ReservationDao reservationDao;
	
	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.reservationDao = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getReservationDao();
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/WEB-INF/agenda.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		/*
		* Lecture du paramètre 'chemin' passé à la servlet via la déclaration
		* dans le web.xml
		*/
		@SuppressWarnings("unused")
		String chemin = this.getServletConfig().getInitParameter(CHEMIN );
		
		/* Préparation de l'objet formulaire */
		ReservationForm form = new ReservationForm(reservationDao );
		
		/* Traitement de la requête et récupération du bean en résultant */
		Reservation reservation = form.connecterReservation( request );
		
		
		/* Ajout du bean et de l'objet métier à l'objet requête*/
		
		request.setAttribute( ATT_RESERVCLIENT, reservation );
		request.setAttribute( ATT_FORM, form ); 
		
		/* Si aucune erreur */
		if ( form.getErreurs().isEmpty() && reservation.getDate() != null ) { 
			
			/* Alors récupération de la map des reservations dans la session */
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<Long, Reservation> reservations = (HashMap<Long, Reservation>) session.getAttribute(ATT_SESSION_RESERVATION );
			
			/* Si aucune map n'existe, alors initialisation d'une nouvelle map */
			if ( reservations == null ) {
				reservations = new HashMap<Long, Reservation>();
			}
			
			/* Puis ajout de la reservation courant dans la map */
			reservations.put( reservation.getCode(), reservation );
			
			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute( ATT_SESSION_RESERVATION, reservation );
			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES ).forward( request, response );
		
		} else {
			
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM ).forward( request, response );
		}
		
		
	}

}
