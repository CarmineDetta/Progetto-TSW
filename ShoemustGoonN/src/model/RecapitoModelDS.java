package model;

import java.sql.Connection;

import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecapitoModelDS implements RecapitoDAO{

    private static final Logger LOGGER = Logger.getLogger(RecapitoModelDS.class.getName());

	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
	}

	private static final String TABLE_NAME = "recapito";

	public void doSave(RecapitoBean spedizione, UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecapitoModelDS.TABLE_NAME
				+ "(ID_Spedizione, Cap, Citta, Via_Piazza, N_Civico, Utente) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
 			
			preparedStatement.setInt(1, spedizione.getID_Indirizzo());
			preparedStatement.setInt(2, spedizione.getCap());
			preparedStatement.setString(3, spedizione.getCitta());
			preparedStatement.setString(4, spedizione.getVia_Piazza());
			preparedStatement.setInt(5, spedizione.getN_Civico());
		
			UtenteModelDS udao = new UtenteModelDS();
			spedizione.setUtente(udao.doRetrieveByKey(utente.getID_Utente()));
			
			preparedStatement.setString(6,spedizione.getUtente().getID_Utente());

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

	public boolean doDelete(int ID_Indirizzo) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecapitoModelDS.TABLE_NAME + " WHERE ID_Spedizione = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setInt(1, ID_Indirizzo);
			//System.out.println(deleteSQL + ID_Indirizzo);
			
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

	public RecapitoBean doRetrieveByKey(int ID_Indirizzo) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecapitoBean bean = new RecapitoBean();

		String selectSQL = "SELECT * FROM " + RecapitoModelDS.TABLE_NAME + " WHERE ID_Spedizione = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, ID_Indirizzo);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setID_Indirizzo(rs.getInt("ID_Spedizione"));
				bean.setcap(rs.getInt("Cap"));
				bean.setCitta(rs.getString("Citta"));
				bean.setVia_Piazza(rs.getString("Via_Piazza"));
				bean.setN_Civico(rs.getInt("N_Civico"));
				
				UtenteModelDS udao = new UtenteModelDS();
				bean.setUtente(udao.doRetrieveByKey(rs.getString("Utente")));
				
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
		return bean;
	}
	
public synchronized Collection<RecapitoBean> doRetrieveByUtente(String user) throws SQLException {
		
		//fare quando dobbiamo cercare tutti gli oggetti di una tabella
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RecapitoBean> recapiti = new LinkedList<RecapitoBean>();

		String selectSQL = "SELECT * FROM " + RecapitoModelDS.TABLE_NAME + " WHERE Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecapitoBean bean = new RecapitoBean();

				bean.setID_Indirizzo(rs.getInt("ID_Spedizione"));
				bean.setCitta(rs.getString("Citta"));
				bean.setVia_Piazza(rs.getString("Via_Piazza"));
				bean.setcap(rs.getInt("Cap"));
				bean.setN_Civico(rs.getInt("N_Civico"));

				recapiti.add(bean);
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
		return recapiti;
	}
	
}
