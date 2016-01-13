package com.mpheh.interfaces;

import java.util.List;

import com.mpheh.beans.Reservation;
import com.mpheh.dao.DAOException;

public interface ReservationDao {

	void creer( Reservation reservation ) throws DAOException;
	Reservation trouver( long code ) throws DAOException;
	void supprimer( Reservation reservation ) throws DAOException;
	List<Reservation> lister() throws DAOException;
}
