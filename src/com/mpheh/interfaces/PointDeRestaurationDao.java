package com.mpheh.interfaces;

import java.util.List;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.dao.DAOException;

public interface PointDeRestaurationDao {

	void creer( PointDeRestauration pointDR ) throws DAOException;
	PointDeRestauration trouver( long id ) throws DAOException;
	void supprimer( PointDeRestauration pointDR ) throws DAOException;
	List<PointDeRestauration> lister() throws DAOException;
}
