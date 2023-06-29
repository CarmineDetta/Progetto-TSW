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


public class PortafoglioModelDS implements PortafoglioDAO{
	
	private static DataSource ds;
    private static final Logger LOGGER = Logger.getLogger(PortafoglioModelDS.class.getName());

	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
	}

	private static final String TABLE_NAME = "portafoglio";
	
public synchronized void doSave(PortafoglioBean pagamento, UtenteBean utente) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PortafoglioModelDS.TABLE_NAME
				+ "(ID_Pagamento, Num_Carta, Nome_Intestatario, Scadenza, CVV, Utente) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
						
			preparedStatement.setInt(1, pagamento.getID_Pagamento());
			preparedStatement.setString(2, pagamento.getNcarta());
			preparedStatement.setString(3, pagamento.getNome_Intestatario());
			preparedStatement.setString(4, pagamento.getScadenza());
			preparedStatement.setInt(5, pagamento.getCvv());
			
			UtenteModelDS udao = new UtenteModelDS();
			pagamento.setUtente(udao.doRetrieveByKey(utente.getID_Utente()));
			
			preparedStatement.setString(6,pagamento.getUtente().getID_Utente());
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

public synchronized boolean doDelete(int idPagamento) throws SQLException {
	
	//fare quando dobbiamo cancellare oggetti precisi sul db
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result = 0;

	String deleteSQL = "DELETE FROM " + PortafoglioModelDS.TABLE_NAME + " WHERE ID_Pagamento = ?";

	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		
		
		preparedStatement.setInt(1, idPagamento);
		
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
	public synchronized PortafoglioBean doRetrieveByKey(int idPagamento) throws SQLException {
			
			//fare quando dobbiamo cercare oggetti precisi sul db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			PortafoglioBean bean = new PortafoglioBean();

			String selectSQL = "SELECT * FROM " + PortafoglioModelDS.TABLE_NAME + " WHERE ID_Pagamento = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, idPagamento);

				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setidPagamento(rs.getInt("ID_Pagamento"));
					bean.setNcarta(rs.getString("Num_Carta"));
					bean.setNomeIntestatario(rs.getString("Nome_Intestatario"));
					bean.setScadenza(rs.getString("Scadenza"));
					bean.setCvv(rs.getInt("CVV"));
					
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
	
public synchronized Collection<PortafoglioBean> doRetrieveByUtente(String user) throws SQLException {
		
		//fare quando dobbiamo cercare tutti gli oggetti di una tabella
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PortafoglioBean> payments = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + PortafoglioModelDS.TABLE_NAME + " WHERE utente = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PortafoglioBean bean = new PortafoglioBean();

				bean.setidPagamento(rs.getInt("ID_Pagamento"));
				bean.setNcarta(rs.getString("Num_Carta"));
				bean.setNomeIntestatario(rs.getString("Nome_Intestatario"));
				bean.setScadenza(rs.getString("Scadenza"));
				bean.setCvv(rs.getInt("CVV"));

				payments.add(bean);
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
		return payments;
	}

}

