package com.mpheh.dao;

import static com.mpheh.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.mpheh.dao.DAOUtilitaire.initialisationRequetePreparee;
import static com.mpheh.dao.DAOUtilitaire.mapTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mpheh.beans.Table;
import com.mpheh.interfaces.TableDao;

public class TableDaoImpl implements TableDao {
	
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM table_tb WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO table_tb(nom,nbreplace,nbrepacedispo) VALUES (? ,? ,? )";
	private static final String SQL_SELECT = "SELECT * FROM table_tb ORDER BY id";
	private static final String SQL_DELETE = "DELETE  FROM table_tb WHERE id = ?";

	TableDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	
	
	
	@Override
	public void creer(Table table) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_INSERT, true, table.getNom(),table.getNbrePlace(),table.getNbrePlace() );
			int statut = preparedStatement.executeUpdate();
			
			/* Analyse du statut retourné par la requête d'insertion */
			if ( statut == 0 ) {
				throw new DAOException( "Échec de la création de la table de place assise du restauratant, aucune ligne ajoutée dans la table." );
			}
			
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
				if ( valeursAutoGenerees.next() ) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
					table.setId( valeursAutoGenerees.getLong( 1 ) );
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
	public Table trouver(long id) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Table table = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				table = mapTable( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return table;
	}

	@Override
	public void supprimer(Table table) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_DELETE, false, table.getId() );
			resultSet = preparedStatement.executeQuery();
		
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
	}

	@Override
	public List<Table> lister() throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Table> tables = new ArrayList<Table>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			tables.add( mapTable( resultSet ) );
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		return tables;
	}

}
