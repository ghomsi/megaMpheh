package com.mpheh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.mpheh.beans.Contact;
import com.mpheh.beans.Menu;
import com.mpheh.beans.PointDeRestauration;
import com.mpheh.beans.Reservation;
import com.mpheh.beans.Restaurateur;
import com.mpheh.beans.Table;

public class DAOUtilitaire {

	
	/*
	* Initialise la requête préparée basée sur la connexion passée en argument,
	* avec la requête SQL et les objets donnés.
	*/
	public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys,Object... objets ) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ?Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i] );
		}
		return preparedStatement;
	}
	
	/*
	* Simple méthode utilitaire permettant de faire la correspondance(le
	* mapping) entre une ligne issue de la table des utilisateurs (un
	* ResultSet) et un bean Utilisateur.
	*/
	public static Restaurateur map( ResultSet resultSet ) throws SQLException {
		Restaurateur utilisateur = new Restaurateur();
		
		utilisateur.setId( resultSet.getLong( "id" ) );
		utilisateur.setPwd(resultSet.getString( "pwd" ));
		utilisateur.setNom( resultSet.getString( "nom" ) );
		utilisateur.setPrenom( resultSet.getString( "prenom" ) );
		utilisateur.setDate( resultSet.getTimestamp("date" ) );
		utilisateur.setType( resultSet.getString("type" ) );
		utilisateur.getDonnees().setEmail( resultSet.getString( "email" ) );
		utilisateur.getDonnees().setTel( resultSet.getString( "tel" ) );
		utilisateur.getDonnees().setPays( resultSet.getString( "pays" ) );
		utilisateur.getDonnees().setVille( resultSet.getString( "ville" ) );
		utilisateur.getDonnees().setAdresse( resultSet.getString( "adresse" ) );
		utilisateur.setTravailA( resultSet.getLong( "travaila" ) );
		
		return utilisateur;
	}
	
	/*
	* Simple méthode utilitaire permettant de faire la correspondance(le
	* mapping) entre une ligne issue de la table des utilisateurs (un
	* ResultSet) et un bean Utilisateur.
	*/
	public static PointDeRestauration mapPointDR( ResultSet resultSet ) throws SQLException {
		PointDeRestauration pointDR = new PointDeRestauration();
		pointDR.setId( resultSet.getLong( "id" ) );
		pointDR.setNom( resultSet.getString( "nom" ) );
		pointDR.getDonnees().setEmail( resultSet.getString( "email" ) );
		pointDR.getDonnees().setTel( resultSet.getString( "tel" ) );
		pointDR.getDonnees().setPays( resultSet.getString( "pays" ) );
		pointDR.getDonnees().setVille( resultSet.getString( "ville" ) );
		pointDR.getDonnees().setAdresse( resultSet.getString( "adresse" ) );
		
		return pointDR;
	}
	
	/*
	* Simple méthode utilitaire permettant de faire la correspondance(le
	* mapping) entre une ligne issue de la table des places assises du restaurant (un
	* ResultSet) et un bean Table.
	*/
	public static Table mapTable( ResultSet resultSet ) throws SQLException {
		Table table = new Table();
		
		table.setId( resultSet.getLong( "id" ) );
		table.setNom(resultSet.getString( "nom" ));
		table.setNbrePlace( resultSet.getLong( "nbreplace" ) );
		table.setNbrePlaceDipso( resultSet.getLong( "nbrepacedispo" ) );
		
		return table;
	}
	
	/*
	* Simple méthode utilitaire permettant de faire la correspondance(le
	* mapping) entre une ligne issue de la table des places assises du restaurant (un
	* ResultSet) et un bean Table.
	*/
	public static Contact mapContact( ResultSet resultSet ) throws SQLException {
		Contact contact = new Contact();
		
		contact.setId( resultSet.getLong( "id" ) );
		contact.setNom( resultSet.getString( "nom" ) );
		contact.setPrenom( resultSet.getString( "prenom" ) );
		contact.getDonnees().setEmail( resultSet.getString( "email" ) );
		contact.getDonnees().setTel( resultSet.getString( "tel" ) );
		contact.getDonnees().setPays( resultSet.getString( "pays" ) );
		contact.getDonnees().setVille( resultSet.getString( "ville" ) );
		contact.getDonnees().setAdresse( resultSet.getString( "adresse" ) );
		
		return contact;
	}
	
	
	/*
	* Simple méthode utilitaire permettant de faire la correspondance(le
	* mapping) entre une ligne issue de la table des reservations (un
	* ResultSet) et un bean reservation.
	*/
	public static Reservation mapReservation( ResultSet resultSet, DAOFactory daoFactory ) throws SQLException {
		Reservation reservation = new Reservation();
		long code = resultSet.getLong( "id" );
		reservation.setCode( resultSet.getLong( "id" ) );
		reservation.setTable(mapTableReserver(daoFactory ,code));
		reservation.setNbrePlaceReserver(resultSet.getLong("nbreplacereserver"));
		reservation.setMenu(mapMenuReserver(daoFactory ,code));
		reservation.setPDR(trouverPointDR(daoFactory ,resultSet.getLong("idpointdr")));
		reservation.setClient(trouverClient(daoFactory ,resultSet.getLong("idclient")));
		reservation.setDate(resultSet.getDate("date"));
		
		return reservation;
	}
	
	public static Map<String,Table> mapTableReserver( DAOFactory daoFactory ,long id ) throws SQLException {
		 String SQL_SELECT_PAR_ID = "SELECT * FROM reservation_tb,table_tb WHERE reservation_tb.idtable = table_tb.id AND reservation_tb.id = ?";
		 Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String,Table>  tables = null;
		tables = new HashMap <String,Table>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			Table table = new Table();
			
			table.setId(resultSet.getLong("idtable"));
			table.setNom(resultSet.getString("nom"));
			table.setNbrePlace(resultSet.getLong("nbreplace"));
			table.setNbrePlaceDipso(resultSet.getLong("nbreplacedispo"));
			
			tables.put(resultSet.getString("nom"), table);
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
		
		return tables;
	}
	
	public static Map<String,Menu> mapMenuReserver( DAOFactory daoFactory ,long id ) throws SQLException {
		 String SQL_SELECT_PAR_ID = "SELECT * FROM reservation_tb,menu_tb WHERE reservation_tb.idmenu=menu_tb.id AND reservation_tb.id = ?";
		 Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<String,Menu>  menus = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			Menu menu = new Menu();
			menus = new HashMap<String,Menu>();
			
			menu.setId(resultSet.getLong("idmenu"));
			menu.setNom(resultSet.getString("nom"));
			menu.setPrix(resultSet.getLong("prix"));
			menu.setImage(resultSet.getString("image"));
			
			menus.put(resultSet.getString("nom"), menu);
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
		
		return menus;
	}
	
	public static PointDeRestauration trouverPointDR( DAOFactory daoFactory ,long id ) throws SQLException {
		String SQL_SELECT_PAR_ID = "SELECT * FROM pointdr_tb WHERE id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PointDeRestauration  pointDR = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			pointDR = new PointDeRestauration();
			
			pointDR.setId(resultSet.getLong("id"));
			pointDR.setNom(resultSet.getString("nom"));
			pointDR.getDonnees().setEmail(resultSet.getString("email"));
			pointDR.getDonnees().setTel(resultSet.getString("tel"));
			pointDR.getDonnees().setPays(resultSet.getString("pays"));
			pointDR.getDonnees().setVille(resultSet.getString("ville"));
			pointDR.getDonnees().setAdresse(resultSet.getString("adresse"));
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
		
		return pointDR;
	}
	
	public static Contact trouverClient( DAOFactory daoFactory ,long id ) throws SQLException {
		String SQL_SELECT_PAR_ID = "SELECT * FROM contact_tb WHERE id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Contact  client = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connection,SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
		while ( resultSet.next() ) {
			client = new Contact();
			
			client.setId(resultSet.getLong("id"));
			client.setNom(resultSet.getString("nom"));
			client.setPrenom(resultSet.getString("prenom"));
			client.getDonnees().setEmail(resultSet.getString("email"));
			client.getDonnees().setTel(resultSet.getString("tel"));
			client.getDonnees().setPays(resultSet.getString("pays"));
			client.getDonnees().setVille(resultSet.getString("ville"));
			client.getDonnees().setAdresse(resultSet.getString("adresse"));
		}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement,connection );
		}
		
		
		return client;
	}
	
	/* Fermeture silencieuse du resultset */
	public static void fermetureSilencieuse( ResultSet resultSet ) {
		if ( resultSet != null ) {
			try {
				resultSet.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du ResultSet: " + e.getMessage() );
			}
		}	
	}
	/* Fermeture silencieuse du statement */
	public static void fermetureSilencieuse( Statement statement ) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du Statement: " + e.getMessage() );
			}	
		}
	}
	
	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) {
		if ( connexion != null ) {
			try {
				connexion.close();
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}
	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement,Connection connexion ) {
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}
	
	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet,Statement statement, Connection connexion ) {
		fermetureSilencieuse( resultSet );
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion );
	}
	
}
