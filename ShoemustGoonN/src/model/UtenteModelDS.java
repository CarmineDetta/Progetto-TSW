package model;


import java.util.logging.Logger;
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
	
    private static final Logger LOGGER = Logger.getLogger(UtenteModelDS.class.getName());

	private static DataSource ds;
	private static int c=3;
	private static final String ID_UTENTE = "ID_Utente";
	private static final String NOME = "Nome";
	private static final String COGNOME = "Cognome";
	private static final String DATA_NASCITA = "DataNascita";
	private static final String CF = "CF";
	private static final String EMAIL = "Email";
	private static final String PASSWORD = "password";
	
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
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
			
			String id = utente.getID_Utente()+ c;
			this.c++;
			
			utente.setidUtente(id);

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
	
	public synchronized boolean doDelete(String idUtente) throws SQLException {
		
		//fare quando dobbiamo cancellare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UtenteModelDS.TABLE_NAME + " WHERE ID_Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setString(1, idUtente);
			
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
	public synchronized UtenteBean doRetrieveByKey(String idUtente) throws SQLException {
			
			//fare quando dobbiamo cercare oggetti precisi sul db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			UtenteBean u = new UtenteBean();

			String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE ID_Utente = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, idUtente);

				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					u.setidUtente(rs.getString(ID_UTENTE));
					u.setEmail(rs.getString(NOME));
					u.setPassword(rs.getString(COGNOME));
					u.setTipo(rs.getString(DATA_NASCITA));
					u.setEmail(rs.getString(CF));
					u.setEmail(rs.getString(EMAIL));
					u.setPassword(rs.getString(PASSWORD));
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
				bean.setidUtente(rs.getString(ID_UTENTE));
				bean.setNome(rs.getString(NOME));
				bean.setCognome(rs.getString(COGNOME));
				bean.setDataNascita(rs.getString(DATA_NASCITA));
				bean.setCF(rs.getString(CF));
				bean.setEmail(rs.getString(EMAIL));
				bean.setPassword(rs.getString(PASSWORD));
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

		Collection<UtenteBean> u = new LinkedList<>();

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

				utente.setidUtente(rs.getString(ID_UTENTE));
				utente.setNome(rs.getString(NOME));
				utente.setCognome(rs.getString(COGNOME));
				utente.setDataNascita(rs.getString(DATA_NASCITA));
				utente.setCF(rs.getString(CF));
				utente.setEmail(rs.getString(EMAIL));
				utente.setPassword(rs.getString(PASSWORD));
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
	
		Collection<UtenteBean> utenti = new LinkedList<>();
	
		String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE Tipo = 'utente'";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				UtenteBean utente = new UtenteBean();
				
				utente.setidUtente(rs.getString(ID_UTENTE));
				utente.setNome(rs.getString(NOME));
				utente.setCognome(rs.getString(COGNOME));
				utente.setDataNascita(rs.getString(DATA_NASCITA));
				utente.setCF(rs.getString(CF));
				utente.setEmail(rs.getString(EMAIL));
				utente.setPassword(rs.getString(PASSWORD));
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

	
public synchronized Collection<UtenteBean> doRetrieveSuggest(String stringaParziale) throws SQLException {	
	//fare quando dobbiamo cercare oggetti precisi sul db
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	UtenteBean bean = new UtenteBean();

	String selectSQL = "SELECT * FROM " + UtenteModelDS.TABLE_NAME + " WHERE Cognome LIKE ?";
	Collection<UtenteBean> utentes = new LinkedList<>();
		
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		String stringaRicerca = stringaParziale.concat("%");
		preparedStatement.setString(1, stringaRicerca);

		ResultSet rs = preparedStatement.executeQuery();
		
		
		
		while (rs.next()) {
			bean = new UtenteBean();
			bean.setidUtente(rs.getString(ID_UTENTE));
			bean.setNome(rs.getString(NOME));
			bean.setCognome(rs.getString(COGNOME));
			bean.setDataNascita(rs.getString(DATA_NASCITA));
			bean.setCF(rs.getString(CF));
			bean.setEmail(rs.getString(EMAIL));
			bean.setPassword(rs.getString(PASSWORD));
			
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



