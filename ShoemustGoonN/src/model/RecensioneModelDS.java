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

public class RecensioneModelDS implements RecensioneDAO{

    private static final Logger LOGGER = Logger.getLogger(RecensioneModelDS.class.getName());

	private static DataSource ds;
	
	private static final String VOTAZIONE = "Votazione";	/*per sonarcloud creo le costanti di stringhe che si ripetono*/
	private static final String DESCRIZIONE = "Descrizione";
	private static final String ID_RECENSIONE = "ID_Recensione";
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perch� lo chiede sonarcloud dicendo che devo controllare se il questo codice � disattivato quando consegno del condice da eseguire
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
			
			preparedStatement.setString(4,recensione.getUtente().getID_Utente());

			ProductModelDS pdao = new ProductModelDS();
			recensione.setProdotto(pdao.doRetrieveByKey(prodotto.getID_Prodotto()));
			preparedStatement.setString(5,recensione.getProdotto().getID_Prodotto());
			
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

	public boolean doDelete(int idRecensione, String idUtente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneModelDS.TABLE_NAME + " WHERE ID_Recensione = ? AND ID_Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setInt(1, idRecensione);
			preparedStatement.setString(2, idUtente);
			
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

	public RecensioneBean doRetrieveByKey(int idRecensione) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean bean = new RecensioneBean();

		String selectSQL = "SELECT * FROM " + RecensioneModelDS.TABLE_NAME + " WHERE ID_Recensione = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idRecensione);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setidRecensione(rs.getInt(ID_RECENSIONE));
				bean.setVotazione(rs.getFloat(VOTAZIONE));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				
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

		Collection<RecensioneBean> recensioni = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDS.TABLE_NAME + " WHERE Utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();

				bean.setidRecensione(rs.getInt(ID_RECENSIONE));
				bean.setVotazione(rs.getFloat(VOTAZIONE));
				bean.setDescrizione(rs.getString(DESCRIZIONE));

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

	public Collection<RecensioneBean> doRetrieveByProdotto(String prodotto) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<RecensioneBean> recensioni = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + RecensioneModelDS.TABLE_NAME + " WHERE Prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, prodotto);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();

				bean.setidRecensione(rs.getInt(ID_RECENSIONE));
				bean.setVotazione(rs.getFloat(VOTAZIONE));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				
				UtenteModelDS udao = new UtenteModelDS();
				bean.setUtente(udao.doRetrieveByKey(rs.getString("Utente")));
		
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
