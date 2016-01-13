package com.mpheh.dao;

import static com.mpheh.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.mpheh.dao.DAOUtilitaire.initialisationRequetePreparee;
import static com.mpheh.dao.DAOUtilitaire.mapReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mpheh.beans.Reservation;
import com.mpheh.interfaces.ReservationDao;

public class ReservationDaoImpl implements ReservationDao {

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_CODE = "SELECT * FROM reservation_tb,contact_tb,menu_tb,pointdr_tb,table_tb WHERE reservation_tb.idclient = contact_tb.id AND reservation_tb.idpointdr = pointdr_tb.id AND reservation_tb.idtable = table_tb.id AND reservation_tb.idmenu = menu_tb.id AND reservation_tb.id = ?";
	private static final String SQL_INSERT = "INSERT INTO reservation_tb(idtable,nbreplacereserver,idmenu,idpointdr,idclient,date) VALUES (? ,? , ?, ?, ?,NOW())";
	private static final String SQL_SELECT = "SELECT * FROM reservation_tb ORDER BY code";
	private static final String SQL_DELETE = "DELETE FROM reservation_tb where id = ?";
	
	ReservationDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void creer(Reservation reservation) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		List<PreparedStatement> preparedStatements = new ArrayList<PreparedStatement>();
		ResultSet valeursAutoGenerees = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			Set<String> cles = reservation.getTable().keySet();
			Iterator<String> it = cles.iterator();
			while(it.hasNext()){
				String clestable = (String) it.next();
				preparedStatements.add(initialisationRequetePreparee( connexion,SQL_INSERT, true, reservation.getTable().get(clestable).getId(), reservation.getNbrePlaceReserver(),reservation.getMenu().get(clestable).getId(),reservation.getPDR().getId(),reservation.getClient().getId() ));	
			}
			Iterator<PreparedStatement> itP = preparedStatements.iterator();
			while(itP.hasNext()){
				int statut = itP.next().executeUpdate();
				/* Analyse du statut retourné par la requête d'insertion */
				if ( statut == 0 ) {
					throw new DAOException( "Échec de la création d'une reservation, aucune ligne ajoutée dans la table." );
				}
			
				/* Récupération de l'id auto-généré par la requête d'insertion */
				valeursAutoGenerees = itP.next().getGeneratedKeys();
					if ( valeursAutoGenerees.next() ) {
					/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
						reservation.setCode( valeursAutoGenerees.getLong( 1 ) );
					} else {
						throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
					}
			}	
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			Iterator<PreparedStatement> itP = preparedStatements.iterator();
			while(itP.hasNext()){
			fermeturesSilencieuses( valeursAutoGenerees, itP.next(),connexion );
			}
		}
		
	}

	@Override
	public Reservation trouver(long code) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Reservation reservation = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_SELECT_PAR_CODE, false, code );
			resultSet = preparedStatement.executeQuery();
			
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				reservation = mapReservation( resultSet,daoFactory  );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return reservation;
	}

	@Override
	public void supprimer(Reservation reservation) throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion,SQL_DELETE, false, reservation.getCode() );
			resultSet = preparedStatement.executeQuery();
			
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		
	}

	@Override
	public List<Reservation> lister() throws DAOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			reservations.add( mapReservation( resultSet,daoFactory  ) );
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		return reservations;
	}

}
