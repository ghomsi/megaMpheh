package com.mpheh.interfaces;

import java.util.List;

import com.mpheh.beans.Contact;
import com.mpheh.dao.DAOException;

public interface ContactDao {

	void creer( Contact contact ) throws DAOException;
	Contact trouver( long id ) throws DAOException;
	void supprimer( Contact contact ) throws DAOException;
	List<Contact> lister() throws DAOException;
}
