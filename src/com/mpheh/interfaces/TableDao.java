package com.mpheh.interfaces;

import java.util.List;

import com.mpheh.beans.Table;
import com.mpheh.dao.DAOException;

public interface TableDao {

	void creer( Table table ) throws DAOException;
	Table trouver( long id ) throws DAOException;
	void supprimer( Table table ) throws DAOException;
	List<Table> lister() throws DAOException;
}
