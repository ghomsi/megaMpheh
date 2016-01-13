package com.mpheh.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mpheh.dao.DAOFactory;

public class InitialisationDaoFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daofactory";
	private DAOFactory daoFactory;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		/* Rien à réaliser lors de la fermeture de l'application... */
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
		/* Récupération du ServletContext lors du chargement de l'application */
		ServletContext servletContext = event.getServletContext();
		
		/* Instanciation de notre DAOFactory */
		this.daoFactory = DAOFactory.getInstance();
		
		/* Enregistrement dans un attribut ayant pour portée toute l'application */
		servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
		
	}

}
