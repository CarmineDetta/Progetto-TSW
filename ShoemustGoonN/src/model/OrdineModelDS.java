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

public class OrdineModelDS implements OrdineDAO{

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

	private static final String TABLE_NAME = "ordine";
	
	public void doSave(OrdineBean ordine, UtenteBean utente, RecapitoBean recapito) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineModelDS.TABLE_NAME
				+ " (ID_Ordine, Data_Ordine, Metodo_Pagamento, Totale, Utente, Indirizzo) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, ordine.getID_Ordine());
			preparedStatement.setString(2, ordine.getDataOrdine());
			preparedStatement.setString(3, ordine.getMetodoPagamento());
			preparedStatement.setDouble(4, ordine.getTotale());
			
			UtenteModelDS udao = new UtenteModelDS();
			ordine.setUtente(udao.doRetrieveByKey(utente.getID_Utente()));
			
			RecapitoModelDS rdao = new RecapitoModelDS();
			ordine.setRecapito(rdao.doRetrieveByKey(recapito.getID_Indirizzo()));
			
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

	
	public boolean doDelete(int idOrdine) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineModelDS.TABLE_NAME + " WHERE ID_Ordine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setInt(1, idOrdine);
			
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


	public Collection<OrdineBean> doRetrieveByUtente(String utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME + " WHERE utente = ?";

		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, utente);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setID_Ordine(rs.getInt("ID_Ordine"));
				bean.setDataOrdine(rs.getString("Data_Ordine"));
				bean.setMetodoPagamento(rs.getString("Metodo_Pagamento"));
				bean.setTotale(rs.getInt("Totale"));
			
				RecapitoModelDS rdao = new RecapitoModelDS();
				bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
				
				ordini.add(bean);
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
		return ordini;
	}
	

	
	public OrdineBean doRetriveByKey(int id) throws SQLException {
			
			//fare quando dobbiamo cercare oggetti precisi sul db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			OrdineBean bean = new OrdineBean();

			String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME + " WHERE ID_Ordine = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setID_Ordine(rs.getInt("ID_Ordine"));
					bean.setDataOrdine(rs.getString("Data_Ordine"));
					bean.setMetodoPagamento(rs.getString("Metodo_Pagamento"));
					bean.setTotale(rs.getDouble("Totale"));
					
					UtenteModelDS udao = new UtenteModelDS();
					bean.setUtente(udao.doRetrieveByKey(rs.getString("utente")));
					
					RecapitoModelDS rdao = new RecapitoModelDS();
					bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
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
	
}

