package com.mpheh.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mpheh.beans.Restaurateur;
import com.mpheh.dao.DAOFactory;
import com.mpheh.interfaces.UtilisateurDao;

/**
 * Servlet Filter implementation class RestrictionFilter
 */
@WebFilter("/RestrictionFilter")
public class RestrictionFilter implements Filter {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_USER = "user";
	private UtilisateurDao utilisateurDao;
	
    /**
     * Default constructor. 
     */
    public RestrictionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		/* Cast de l'objet request */
		HttpServletRequest request = (HttpServletRequest) req;
		
		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();
		
		
		/*
		* Si la map des clients n'existe pas en session, alors l'utilisateur se
		* connecte pour la première fois et nous devons précharger en session
		* les infos contenues dans la BDD.
		*/
		if ( session.getAttribute( ATT_SESSION_USER ) == null ){
			/*
			* Récupération de la liste des clients existant, et enregistrement
			* en session
			*/
			List<Restaurateur> listeClients = utilisateurDao.lister();
			Map<Long, Restaurateur> mapClients = new HashMap<Long,Restaurateur>();
			for ( Restaurateur user : listeClients ) {
				mapClients.put( user.getId(), user );
			}
				session.setAttribute( ATT_SESSION_USER, mapClients);
			}
			
		/* Pour terminer, poursuite de la requête en cours */

		// pass the request along the filter chain
		chain.doFilter(request, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
		/* Récupération d'une instance de nos DAO Client et Commande */
		this.utilisateurDao = ( (DAOFactory) fConfig.getServletContext().getAttribute( CONF_DAO_FACTORY )).getUtilisateurDao();
		
	}

}
