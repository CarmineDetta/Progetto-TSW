package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UtenteModelDS implements UtenteDAO{
	
	private static DataSource ds;
	private static int c=3;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "utente";

	public synchronized void doSave(UtenteBean utente) throws SQLException {
		
		//fare quando dobbiamo inserire oggetti nel db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteModelDS.TABLE_NAME
				+ " (ID_Utente, Nome, Cognome, DataNascita, CF, email, password, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			String id = "U"+c;
			this.c++;
			
			utente.setID_Utente(id);

			preparedStatement.setString(1, utente.getID_Utente());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setString(4, utente.getDataNascita());
			preparedStatement.setString(5, utente.getCF());
			preparedStatement.setString(6, utente.getEmail());
			preparedStatement.setString(7, utente.getPassword());
			preparedStatement.setString(8, utente.getTipo());

			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	public synchronized boolean doDelete(String ID_Utente) throws SQLException {
		
		//fare quando dobbiamo cancellare oggetti precisi sul db
		System.out.println("Procediamo alla delete");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteModelDS.TABLE_NAME + " WHERE ID_Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setString(1, ID_Utente);
			System.out.println(deleteSQL + ID_Utente);
			
			result = preparedStatement.executeUpdate();
			connection.commit();

		}  finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	//Serve per recuperare un oggetto dal database in base all'ID
	public synchronized UtenteBean doRetrieveByKey(String ID_Utente) throws SQLException {
			
			//fare quando dobbiamo cercare oggetti precisi sul db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			UtenteBean u = new UtenteBean();

			String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE ID_Utente = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, ID_Utente);

				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					u.setID_Utente(rs.getString("ID_Utente"));
					u.setEmail(rs.getString("Nome"));
					u.setPassword(rs.getString("Cognome"));
					u.setTipo(rs.getString("DataNascita"));
					u.setEmail(rs.getString("CF"));
					u.setEmail(rs.getString("Email"));
					u.setPassword(rs.getString("password"));
					u.setTipo(rs.getString("Tipo"));
					
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
			return u;
		}
	
	public synchronized UtenteBean doRetrieveByEmail(String email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";
	
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				//In questo modo inseriamo i valori estratti dalla qeury all'interno dell'oggetto bean
				bean.setID_Utente(rs.getString("ID_Utente"));
				bean.setEmail(rs.getString("Nome"));
				bean.setPassword(rs.getString("Cognome"));
				bean.setTipo(rs.getString("DataNascita"));
				bean.setEmail(rs.getString("CF"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("password"));
				bean.setTipo(rs.getString("Tipo"));
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
	
	
	public synchronized Collection<UtenteBean> doRetrieveAll(String order) throws SQLException {
		
		//fare quando dobbiamo cercare tutti gli oggetti di una tabella
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UtenteBean> u = new LinkedList<UtenteBean>();

		String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean utente = new UtenteBean();

				utente.setID_Utente(rs.getString("ID_Utente"));
				utente.setEmail(rs.getString("Nome"));
				utente.setPassword(rs.getString("Cognome"));
				utente.setTipo(rs.getString("DataNascita"));
				utente.setEmail(rs.getString("CF"));
				utente.setEmail(rs.getString("Email"));
				utente.setPassword(rs.getString("password"));
				utente.setTipo(rs.getString("Tipo"));
				
				u.add(utente);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return u;
	}

	@Override
	public UtenteBean doRetriveByEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

