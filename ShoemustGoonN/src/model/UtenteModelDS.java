package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.UtenteBean;
import model.UtenteDAO;

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
	
	public UtenteBean doRetriveByEmail(String email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE email = ?";
	
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				//In questo modo inseriamo i valori estratti dalla qeury all'interno dell'oggetto bean
				bean.setID_Utente(rs.getString("ID_Utente"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setDataNascita(rs.getString("DataNascita"));
				bean.setCF(rs.getString("CF"));
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
				utente.setNome(rs.getString("Nome"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setDataNascita(rs.getString("DataNascita"));
				utente.setCF(rs.getString("CF"));
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
		
	public synchronized void doUpdatePassword(String value, String id) throws SQLException {
											//in value c'è il nuovo valore
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	
			String updateSQL = "UPDATE " + UtenteModelDS.TABLE_NAME + " SET Password = ? WHERE ID_Utente = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);
				

				preparedStatement.setString(1, value);
				preparedStatement.setString(2, id);
				
				//System.out.println("Ho preparato la stringa SQL: "+preparedStatement);
				preparedStatement.executeUpdate();
					
				connection.commit();
				//System.out.println("Ho aggiornato l'utente con successo");
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
	
	public synchronized void doUpdateEmail(String value, String id) throws SQLException {
							//in value c'è il nuovo valore
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE " + UtenteModelDS.TABLE_NAME + " SET Email = ? WHERE ID_Utente = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
		
			
			preparedStatement.setString(1, value);
			preparedStatement.setString(2, id);
		
			preparedStatement.executeUpdate();
		
			connection.commit();	//conferma le modifiche nel database
		} finally {
			try {
					if (preparedStatement != null)
						preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public Collection<UtenteBean> doRetrieveAllUtente() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
	
		String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE Tipo = 'utente'";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				UtenteBean utente = new UtenteBean();
				
				utente.setID_Utente(rs.getString("ID_Utente"));
				utente.setNome(rs.getString("Nome"));
				utente.setCognome(rs.getString("Cognome"));
				utente.setDataNascita(rs.getString("DataNascita"));
				utente.setCF(rs.getString("CF"));
				utente.setEmail(rs.getString("Email"));
				utente.setPassword(rs.getString("password"));
				utente.setTipo(rs.getString("Tipo"));
				
				utenti.add(utente);
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
		return utenti;
	}

	
public synchronized Collection<UtenteBean> doRetrieveSuggest(String StringaParziale) throws SQLException, IOException {	
	//fare quando dobbiamo cercare oggetti precisi sul db
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	UtenteBean bean = new UtenteBean();

	String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE Cognome LIKE ?";
	Collection<UtenteBean> utentes = new LinkedList<UtenteBean>();
		
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		String stringaRicerca = StringaParziale.concat("%");
		preparedStatement.setString(1, stringaRicerca);
		System.out.println(stringaRicerca+ " nella stringa: " + selectSQL);

		ResultSet rs = preparedStatement.executeQuery();
		
		
		
		while (rs.next()) {
			bean = new UtenteBean();
			bean.setID_Utente(rs.getString("ID_Utente"));
			bean.setNome(rs.getString("Nome"));
			bean.setCognome(rs.getString("Cognome"));
			bean.setDataNascita(rs.getString("DataNascita"));
			bean.setCF(rs.getString("CF"));
			bean.setEmail(rs.getString("Email"));
			bean.setPassword(rs.getString("password"));
			System.out.println("Gli oggetti trovati sono: "+bean.getID_Utente()+ " " + bean.getNome() + " " + bean.getCognome() );
			utentes.add(bean);
		}

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	utentes.toString();
	return utentes;
 }
}



