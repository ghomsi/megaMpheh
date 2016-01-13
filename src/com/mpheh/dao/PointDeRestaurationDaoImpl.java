package com.mpheh.dao;

import static com.mpheh.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.mpheh.dao.DAOUtilitaire.initialisationRequetePreparee;
import static com.mpheh.dao.DAOUtilitaire.mapPointDR;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpheh.beans.PointDeRestauration;
import com.mpheh.interfaces.PointDeRestaurationDao;

public class PointDeRestaurationDaoImpl implements PointDeRestaurationDao {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM pointdereservation_tb WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO pointdereservation_tb(nom,tel,email,pays,ville,adresse) VALUES (? ,? , ? ,? ,? ,?)";
	private static final String SQL_SELECT = "SELECT * FROM pointdereservation_tb ORDER BY id";
	private static final String SQL_DELETE = "DELETE  FROM pointdereservation_tb WHERE id = ?";

	
	PointDeRestaurationDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	
	
	@Override
	public void creer(PointDeRestauration pointDR) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_INSERT, true, pointDR.getNom(),pointDR.getDonnees().getTel(),pointDR.getDonnees().getEmail(),pointDR.getDonnees().getPays(), pointDR.getDonnees().getVille(),pointDR.getDonnees().getAdresse() );
			int statut = preparedStatement.executeUpdate();
			
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création d'un pointDR, aucune ligne ajoutée dans la table." );
			}
			
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
				if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
					pointDR.setId( valeursAutoGenerees.getLong( 1 ) );
				} else {
				throw new DAOException( "Échec de la création d'un pointDR en base, aucun ID auto-généré retourné." );
				}
			} catch ( SQLException e ) {
				throw new DAOException( e );
			} finally {
				fermeturesSilencieuses( valeursAutoGenerees, preparedStatement,connexion );
			}
		
	}

	@Override
	public PointDeRestauration trouver(long id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PointDeRestauration pointDR = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				pointDR = mapPointDR( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return pointDR;
	}

	@Override
	public void supprimer(PointDeRestauration pointDR) throws DAOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_DELETE, false, pointDR.getId() );
			resultSet = preparedStatement.executeQuery();
		
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
	}

	@Override
	public List<PointDeRestauration> lister() throws DAOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PointDeRestauration> pointDRs = new ArrayList<PointDeRestauration>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			pointDRs.add( mapPointDR( resultSet ) );
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		return pointDRs;
	}

}
