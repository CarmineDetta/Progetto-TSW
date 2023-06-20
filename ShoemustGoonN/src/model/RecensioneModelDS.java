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

public class RecensioneModelDS implements RecensioneDAO{

private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "recensione";
	
	public void doSave(RecensioneBean recensione, UtenteBean utente, ProdottoBean prodotto) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneModelDS.TABLE_NAME
				+ "(ID_Recensione, Votazione, Descrizione, Utente, Prodotto) VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
						
			preparedStatement.setInt(1, recensione.getID_Recensione());
			preparedStatement.setFloat(2, recensione.getVotazione());
			preparedStatement.setString(3, recensione.getDescrizione());
			
			UtenteModelDS udao = new UtenteModelDS();
			recensione.setUtente(udao.doRetrieveByKey(utente.getID_Utente()));
			
			ProductModelDS pdao = new ProductModelDS();
			recensione.setProdotto(pdao.doRetrieveByKey(prodotto.getID_Prodotto()));
			
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

	public boolean doDelete(int ID_Recensione, String ID_Utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneModelDS.TABLE_NAME + " WHERE ID_Recensione = ? AND ID_Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setInt(1, ID_Recensione);
			preparedStatement.setString(2, ID_Utente);
			
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

	public RecensioneBean doRetrieveByKey(int ID_Recensione) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean bean = new RecensioneBean();

		String selectSQL = "SELECT * FROM " + RecensioneModelDS.TABLE_NAME + " WHERE ID_Recensione = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, ID_Recensione);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setID_Recensione(rs.getInt("ID_Recensione"));
				bean.setVotazione(rs.getFloat("Votazione"));
				bean.setDescrizione(rs.getString("Descrizione"));
				
				UtenteModelDS udao = new UtenteModelDS();
				bean.setUtente(udao.doRetrieveByKey(rs.getString("Utente")));
				
				ProductModelDS pdao = new ProductModelDS();
				bean.setProdotto(pdao.doRetrieveByKey(rs.getString("Prodotto")));
				
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

	public Collection<RecensioneBean> doRetrieveByUtente(String utente) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDS.TABLE_NAME + " WHERE Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();

				bean.setID_Recensione(rs.getInt("ID_Recensione"));
				bean.setVotazione(rs.getFloat("Votazione"));
				bean.setDescrizione(rs.getString("Descrizione"));

				ProductModelDS pdao = new ProductModelDS();
				bean.setProdotto(pdao.doRetrieveByKey(rs.getString("Prodotto")));
		
				recensioni.add(bean);
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
		return recensioni;
	}


}
