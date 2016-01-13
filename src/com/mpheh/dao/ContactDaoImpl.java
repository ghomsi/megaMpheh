package com.mpheh.dao;

import static com.mpheh.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.mpheh.dao.DAOUtilitaire.initialisationRequetePreparee;
import static com.mpheh.dao.DAOUtilitaire.mapContact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpheh.beans.Contact;
import com.mpheh.interfaces.ContactDao;

public class ContactDaoImpl implements ContactDao {
	
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM contact_tb WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO contact_tb(nom,prenom,tel,email,pays,ville,adresse) VALUES (? ,? ,? , ? ,? ,? ,?)";
	private static final String SQL_SELECT = "SELECT * FROM contact_tb ORDER BY id";
	private static final String SQL_DELETE = "DELETE  FROM contact_tb WHERE id = ?";
	
	ContactDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void creer(Contact contact) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_INSERT, true, contact.getNom(),contact.getPrenom(),contact.getDonnees().getTel(),contact.getDonnees().getEmail(),contact.getDonnees().getPays(), contact.getDonnees().getVille(),contact.getDonnees().getAdresse() );
			int statut = preparedStatement.executeUpdate();
			
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création d'un contact, aucune ligne ajoutée dans la table." );
			}
			
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
				if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
					contact.setId( valeursAutoGenerees.getLong( 1 ) );
				} else {
				throw new DAOException( "Échec de la création d'un contact en base, aucun ID auto-généré retourné." );
				}
			} catch ( SQLException e ) {
				throw new DAOException( e );
			} finally {
				fermeturesSilencieuses( valeursAutoGenerees, preparedStatement,connexion );
			}
		
	}

	@Override
	public Contact trouver(long id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Contact contact = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				contact = mapContact( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return contact;
	}

	@Override
	public void supprimer(Contact contact) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_DELETE, false, contact.getId() );
			resultSet = preparedStatement.executeQuery();
		
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
	}

	@Override
	public List<Contact> lister() throws DAOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			contacts.add( mapContact( resultSet ) );
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		return contacts;
	}

}
