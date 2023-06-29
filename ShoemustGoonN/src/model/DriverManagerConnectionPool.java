package model;

import java.sql.Connection;

import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(DriverManagerConnectionPool.class.getName());

		//Lista che contiene tutte le connessioni disponibili al DataBase.
		private static List<Connection> freeDbConnections;
		
		
		//Viene utilizzato "static" in modo tale che si caricano i driver per la connessione solo quando si crea la classe.
		static {
			freeDbConnections = new LinkedList<>();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} 	
			catch (ClassNotFoundException e) {
				LOGGER.log(null, "contesto", e);	//fatto perch� lo chiede sonarcloud dicendo che devo controllare se il questo codice � disattivato quando consegno del condice da eseguire
			} 
		}
		
		//Crea una nuova connessione al database 
		private static synchronized Connection createDBConnection() throws SQLException {
			Connection newConnection = null;
			String ip = "localhost";
			String port = "3306";
			String username = "root";
			String password = "carmineadmin";
			
			try {
			    newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/shoemustgoon" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			    return newConnection;
			} catch (SQLException e) {
				LOGGER.log(null, "contesto", e);
				// Gestisci l'eccezione o rilanciala con informazioni contestuali aggiuntive
				throw new SQLException("Errore durante l'esecuzione del codice SQL: " + e.getMessage(), e.getSQLState(), e.getErrorCode());

			}
			
		}


		public static synchronized Connection getConnection() throws SQLException {
			Connection connection;
			
			//Controlla se ci sono connessioni libere nella lista e se si la inserisce in "connection" e la rimuove dalla lista
			if (!freeDbConnections.isEmpty()) {
				connection = freeDbConnections.get(0);
				freeDbConnections.remove(0);
				
				try { //Controlla se la connessione non è stata aperta con successo 
					if (connection.isClosed())
						connection = getConnection();//Se il DB non concede la connessione allora si invoca ricorsivamente il metodo
				} catch (SQLException e) {
					connection.close();//Se si generano errori allora si chiude la connessione e si invoca ricorsivamente il metodo
					connection = getConnection();
				}
			} else {//Questo else si riferisce al "!freeDbConnections.isEmpty()"
				connection = createDBConnection(); //Se non ci sono connessioni disponibili allora la si crea una con il metodo precedente.
			}
			
			return connection;
		}
		
		
		//Ogni volta che si smette di utilizzare una connessione la si inserisce nella lista, è una buona pratica!
		public static synchronized void releaseConnection(Connection connection){
			if(connection != null) freeDbConnections.add(connection);
		}
	}