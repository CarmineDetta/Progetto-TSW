package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineModelDS implements OrdineDAO{

	private static DataSource ds;
	private static int c = 5;
	
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
	
	public void doSave(OrdineBean ordine, UtenteBean utente, RecapitoBean recapito, PortafoglioBean pagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineModelDS.TABLE_NAME
				+ " (ID_Ordine, Data_Ordine, Totale, Utente, Indirizzo, Pagamento) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			
			int id =  ordine.getID_Ordine() + c;
			this.c++;
			
			ordine.setID_Ordine(id);
			
			preparedStatement.setInt(1, ordine.getID_Ordine());
			
			
			
			// Ottenere la data corrente come oggetto LocalDate
	        LocalDate dataAcquisto = LocalDate.now();

	        // Creare un oggetto DateTimeFormatter per il formato desiderato
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Formattare la data nel formato desiderato
	        String dataFormattata = dataAcquisto.format(formatter);
		
	        ordine.setDataOrdine(dataFormattata);
			preparedStatement.setString(2, dataFormattata);
			
			preparedStatement.setDouble(3, ordine.getTotale());

			UtenteModelDS udao = new UtenteModelDS();
			ordine.setUtente(udao.doRetrieveByKey(utente.getID_Utente()));
			preparedStatement.setString(4, ordine.getUtente().getID_Utente());
			
			RecapitoModelDS rdao = new RecapitoModelDS();
			ordine.setRecapito(rdao.doRetrieveByKey(recapito.getID_Indirizzo()));
			preparedStatement.setInt(5, ordine.getRecapito().getID_Indirizzo());
			
			PortafoglioModelDS pdao = new PortafoglioModelDS();
			ordine.setPagamento(pdao.doRetrieveByKey(pagamento.getID_Pagamento()));
			preparedStatement.setInt(6, ordine.getPagamento().getID_Pagamento());
			
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
				
				String dataOrdine = rs.getString("Data_Ordine");
			    bean.setDataOrdine(dataOrdine);
			    
				bean.setTotale(rs.getInt("Totale"));
			
				RecapitoModelDS rdao = new RecapitoModelDS();
				bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
				
				PortafoglioModelDS pdao = new PortafoglioModelDS();
				bean.setPagamento(pdao.doRetrieveByKey(rs.getInt("Pagamento")));
				
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
					
					Date dataOrdineSql = rs.getDate("Data_Ordine");
					
					String dataOrdine = rs.getString("Data_Ordine");
				    bean.setDataOrdine(dataOrdine);
					
					bean.setTotale(rs.getDouble("Totale"));
					
					UtenteModelDS udao = new UtenteModelDS();
					bean.setUtente(udao.doRetrieveByKey(rs.getString("utente")));
					
					RecapitoModelDS rdao = new RecapitoModelDS();
					bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
					
					PortafoglioModelDS pdao = new PortafoglioModelDS();
					bean.setPagamento(pdao.doRetrieveByKey(rs.getInt("Pagamento")));
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
	


	public  Collection<OrdineBean> doRetrieveAll() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();
	
		String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME;
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
	
				bean.setID_Ordine(rs.getInt("ID_Ordine"));
				
				Date dataOrdineSql = rs.getDate("Data_Ordine");
				
				String dataOrdine = rs.getString("Data_Ordine");
			    bean.setDataOrdine(dataOrdine);
				
				bean.setTotale(rs.getDouble("Totale"));
				
				UtenteModelDS udao = new UtenteModelDS();
				bean.setUtente(udao.doRetrieveByKey(rs.getString("utente")));
				
				RecapitoModelDS rdao = new RecapitoModelDS();
				bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
				
				PortafoglioModelDS pdao = new PortafoglioModelDS();
				bean.setPagamento(pdao.doRetrieveByKey(rs.getInt("Pagamento")));			
				
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
	
public Collection<OrdineBean> doRetrieveByDate(String DataInizio, String DataFine) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDS.TABLE_NAME + " WHERE Data_Ordine >=  ? AND Data_Ordine <= ?";

		try {
			
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, DataInizio);
			preparedStatement.setString(2, DataFine);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();

				bean.setID_Ordine(rs.getInt("ID_Ordine"));
				
				String dataOrdine = rs.getString("Data_Ordine");
			    bean.setDataOrdine(dataOrdine);
			    
				bean.setTotale(rs.getInt("Totale"));
			
				RecapitoModelDS rdao = new RecapitoModelDS();
				bean.setRecapito(rdao.doRetrieveByKey(rs.getInt("Indirizzo")));
				
				PortafoglioModelDS pdao = new PortafoglioModelDS();
				bean.setPagamento(pdao.doRetrieveByKey(rs.getInt("Pagamento")));
				
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
}