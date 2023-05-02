package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class OrdineDAO {
	
	
	private static final String TABLE_NAME = "ordine";
	private static int lastID = 0;
	
	public synchronized void doSave(OrdineBean ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String newID = "ORD" + (++lastID);
		ordine.setID_Ordine(newID); // Aggiorna l'ID dell'ordine nel bean

		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (ID_Ordine, N_Prodotti) VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, ordine.getID_Ordine());
			preparedStatement.setInt(2, ordine.getN_prodotti());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	
	public synchronized void doUpdateNProdotti(String ordine,int NProdotti) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET N_Prodotti = ? WHERE ID_Ordine = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setInt(1, NProdotti);
			preparedStatement.setString(2, ordine);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	 }

	public synchronized void doUpdateIDOrdine(String ID_Ordine) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean per l'aggiornamento");

		String updateSQL = "UPDATE " + TABLE_NAME + " SET ID_Ordine = ? WHERE ID_Ordine = ?";
		
		System.out.println("Creo la stringa SQL per l'aggiornamento");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, ID_Ordine);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			preparedStatement.executeUpdate();


			connection.commit();
			
			System.out.println("Ho aggiornato l'utente con successo");
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	 }

	
	
public synchronized OrdineBean doRetrieveByID(String ordine) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Ordine = ?";
		
		System.out.println("Creo la stringa SQL");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, ordine);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println("ID_Utente: " + rs.getString("ID_Utente"));
				System.out.println("ID_Ordine: " + rs.getString("Ordine"));
				System.out.println("Lista_Prodotti: " + rs.getString("Lista_Prodotti"));
				System.out.println("Email: " + rs.getString("Email"));
				System.out.println("Password: " + rs.getString("Password"));
				System.out.println("Tipo: " + rs.getString("Tipo"));
				
				
				//In questo modo inseriamo i valori estratti dalla qeury all'interno dell'oggetto bean
				/*bean.setID_Utente(rs.getString("ID_Utente"));
				bean.setOrdine(rs.getString("Ordine"));
				bean.setListaProdotti(rs.getString("Lista_Prodotti"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setTipo(rs.getString("tipo"));*/
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
	
	
	}


