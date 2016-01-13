package com.mpheh.interfaces;

import java.util.List;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.beans.Restaurateur;
import com.mpheh.dao.DAOException;

public interface UtilisateurDao {
	
		void creer( Restaurateur utilisateur ) throws DAOException;
		Restaurateur trouver( String email ) throws DAOException;
		void supprimer( Restaurateur user ) throws DAOException;
		List<Restaurateur> lister() throws DAOException;
		PointDeRestauration trouverTravail(long id);
	
}
