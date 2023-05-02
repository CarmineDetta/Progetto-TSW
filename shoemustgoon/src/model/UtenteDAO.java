package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class UtenteDAO {
	
	private static final String TABLE_NAME = "utente"; //viene utilizzata per memorizzare il nome di una tabella di un database in una costante in Java.
	
	//Permette di salvare un oggetto UtenteBean nel database
	public synchronized void doSave(UtenteBean utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteDAO.TABLE_NAME+ " (ID_Utente, Ordine, Lista_Prodotti, Email, Password, Tipo) VALUES (?,?,?,?,?,?)";
		
		try {//Per ogni campo del database inserisco dati dell ogogetto "utente"
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getID_Utente());
			preparedStatement.setString(2,utente.getOrdine()); 
			preparedStatement.setString(3, utente.getListaProdotti());
			preparedStatement.setString(4,utente.getEmail());
			preparedStatement.setString(5,utente.getPassword());
			preparedStatement.setString(6,"Registrato");
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
	
	//Metodo che serve per ricercare i dati dell'utente mediante email e password
	public synchronized UtenteBean doRetrieveByKey(String utente,String password) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Email = ? AND Password = ?";
		
		System.out.println("Creo la stringa SQL");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			//Sostituiscono il segna posto "?" con il valore utente e password
			preparedStatement.setString(1, utente);
			preparedStatement.setString(2, password);
			
			System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

			//Viene eseguita la query e inserita all'interno di "rs"
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				System.out.println("ID_Utente: " + rs.getString("ID_Utente"));
				System.out.println("ID_Ordine: " + rs.getString("Ordine"));
				System.out.println("Lista_Prodotti: " + rs.getString("Lista_Prodotti"));
				System.out.println("Email: " + rs.getString("Email"));
				System.out.println("Password: " + rs.getString("Password"));
				System.out.println("Tipo: " + rs.getString("Tipo"));
				
				
				//In questo modo inseriamo i valori estratti dalla qeury all'interno dell'oggetto bean
				bean.setID_Utente(rs.getString("ID_Utente"));
				bean.setOrdine(rs.getString("Ordine"));
				bean.setListaProdotti(rs.getString("Lista_Prodotti"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setTipo(rs.getString("tipo"));
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
	
//cerca un record all'interno del database sulla base dell'ID_Utente dell'utente e restituisce un oggetto UtenteBean
//che rappresenta il record corrispondente.
	public synchronized UtenteBean doRetrieveByUser(String utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		System.out.println("Creo il bean");
		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Utente = ?";
		
		System.out.println("Creo la stringa SQL");
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente);
			
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
				bean.setID_Utente(rs.getString("ID_Utente"));
				bean.setOrdine(rs.getString("Ordine"));
				bean.setListaProdotti(rs.getString("Lista_Prodotti"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setTipo(rs.getString("tipo"));
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
	

//In generale, questo metodo viene utilizzato per recuperare tutti gli utenti da una tabella degli utenti. 
//Può anche essere utilizzato per recuperare un singolo utente se viene passato un ID utente specifico come input.
public synchronized Collection<UtenteBean> doRetrieveAll(String user) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean");
	

	String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Utente = ?";
	String selectSQLnoUser = "SELECT * FROM " + TABLE_NAME + " ";
	
	
	System.out.println("Creo la stringa SQL");
	
	Collection<UtenteBean> utenti = new LinkedList<UtenteBean>();
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		if(user.equalsIgnoreCase("*")) {
			preparedStatement = connection.prepareStatement(selectSQLnoUser);
		} else {
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);
		}
		
		System.out.println("Ho preparato la stringa SQL: "+preparedStatement);

		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			
			UtenteBean bean = new UtenteBean();
			
			//In questo modo inseriamo i valori estratti dalla qeury all'interno dell'oggetto bean
			bean.setID_Utente(rs.getString("ID_Utente"));
			bean.setOrdine(rs.getString("Ordine"));
			bean.setListaProdotti(rs.getString("Lista_Prodotti"));
			bean.setEmail(rs.getString("Email"));
			bean.setPassword(rs.getString("Password"));
			bean.setTipo(rs.getString("tipo"));
			
			utenti.add(bean);
			
			System.out.println("Ho aggiunto questo utente alla Collection: "+bean.getEmail());
		}

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	System.out.println("La collection contiene: "+ utenti.size());
	return utenti;
	}

//questo metodo serve per aggiornare il tipo di un utente all'interno del database e viene chiamato quando 
//l'amministratore del sito web desidera modificare i privilegi di un utente
public synchronized void doUpdateTipo(String utente,String ruolo) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean per l'aggiornamento");

	String updateSQL = "UPDATE " + TABLE_NAME + " SET Tipo = ? WHERE ID_Utente = ?";
	
	System.out.println("Creo la stringa SQL per l'aggiornamento");
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		
		preparedStatement.setString(1, ruolo);
		preparedStatement.setString(2, utente);
		
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

////questo metodo serve per aggiornare l'ordine di un utente all'interno del database e viene chiamato quando 
//l'amministratore del sito web desidera modificare l'ordine di un utente
public synchronized void doUpdateOrder(String utente, String ordine) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean per l'aggiornamento");

	String updateSQL = "UPDATE " + TABLE_NAME + " SET Ordine = ? WHERE ID_Utente = ?";
	
	System.out.println("Creo la stringa SQL per l'aggiornamento");
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		
		preparedStatement.setString(1, ordine);
		preparedStatement.setString(2, utente);
		
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


////questo metodo serve per aggiornare la lista di un utente all'interno del database e viene chiamato quando 
//l'amministratore del sito web desidera modificare la lista di un utente
public synchronized void doUpdateList(String utente,String lista) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean per l'aggiornamento");

	String updateSQL = "UPDATE " + TABLE_NAME + " SET Lista_Prodotti = ? WHERE ID_Utente = ?";
	
	System.out.println("Creo la stringa SQL per l'aggiornamento");
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		
		preparedStatement.setString(1, lista);
		preparedStatement.setString(2, utente);
		
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

////questo metodo serve per aggiornare l'Email di un utente all'interno del database e viene chiamato quando 
//l'amministratore del sito web desidera modificare l'Email di un utente
public synchronized void doUpdateMail(String utente,String mail) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean per l'aggiornamento");

	String updateSQL = "UPDATE " + TABLE_NAME + " SET Mail = ? WHERE ID_Utente = ?";
	
	System.out.println("Creo la stringa SQL per l'aggiornamento");
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		
		preparedStatement.setString(1, mail);
		preparedStatement.setString(2, utente);
		
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

////questo metodo serve per aggiornare la Password di un utente all'interno del database e viene chiamato quando 
//l'amministratore del sito web desidera modificare la Password di un utente
public synchronized void doUpdatePassword(String utente,String password) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	System.out.println("Creo il bean per l'aggiornamento");

	String updateSQL = "UPDATE " + TABLE_NAME + " SET Password = ? WHERE ID_Utente = ?";
	
	System.out.println("Creo la stringa SQL per l'aggiornamento");
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, utente);
		
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

}



	