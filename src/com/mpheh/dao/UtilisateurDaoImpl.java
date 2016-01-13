package com.mpheh.dao;

import static com.mpheh.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.beans.Restaurateur;
import com.mpheh.interfaces.UtilisateurDao;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM user_tb WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO user_tb(nom,prenom,pwd,type,date,tel,email,pays,ville,adresse,travailA) VALUES (? ,? ,? , ?, NOW() ,? ,? ,? ,? ,? ,?)";
	private static final String SQL_SELECT = "SELECT * FROM user_tb ORDER BY id";
	private static final String SQL_DELETE = "DELETE  FROM user_tb WHERE id = ?";
	private static final String SQL_SELECT_TRAVAIL = "SELECT *  FROM pointdr_tb WHERE id = ?";
	
	UtilisateurDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	
	
	
	@Override
	public void creer(Restaurateur utilisateur) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_INSERT, true, utilisateur.getNom(),utilisateur.getPrenom(),utilisateur.getPwd(),utilisateur.getType(),utilisateur.getDonnees().getTel(),utilisateur.getDonnees().getEmail(),utilisateur.getDonnees().getPays(), utilisateur.getDonnees().getVille(),utilisateur.getDonnees().getAdresse(),utilisateur.getTravailA() );
			int statut = preparedStatement.executeUpdate();
			
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
			}
			
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
				if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
					utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
				} else {
				throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
				}
			} catch ( SQLException e ) {
				throw new DAOException( e );
			} finally {
				fermeturesSilencieuses( valeursAutoGenerees, preparedStatement,connexion );
			}
	}

	@Override
	public Restaurateur trouver(String email) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Restaurateur utilisateur = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_PAR_EMAIL, false, email );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				utilisateur = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return utilisateur;
	}




	@Override
	public void supprimer(Restaurateur user) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_DELETE, false, user.getId() );
			resultSet = preparedStatement.executeQuery();
		
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
	
	}
		





	@Override
	public List<Restaurateur> lister() throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Restaurateur> users = new ArrayList<Restaurateur>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			users.add( map( resultSet ) );
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		return users;
	}




	@Override
	public PointDeRestauration trouverTravail(long id) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PointDeRestauration travaila = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_TRAVAIL, false, id );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				travaila = mapPointDR( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return travaila;
	}

}
