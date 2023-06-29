package model;

import java.io.IOException;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.*;


import javax.naming.Context;


import utils.GenerateIDProd;


public class ProductModelDS implements ProdottoDAO{
	
    private static final Logger LOGGER = Logger.getLogger(ProductModelDS.class.getName());

	private static DataSource ds;	
	private static final String	ID_PRODOTTO = "ID_Prodotto";	/*sempre per sonarcloud*/
	private static final String MARCA = "Marca";
	private static final String DISPONIBILITA = "Disponibilita";

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
	}

	private static final String TABLE_NAME = "prodotto";

	public synchronized void doSave(ProdottoBean prodotto) throws SQLException {
		
		//fare quando dobbiamo inserire oggetti nel db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelDS.TABLE_NAME
				+ "(ID_Prodotto, Marca, Colore, Modello, Prezzo, Quantita, Disponibilita, Descrizione, Categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
 
			GenerateIDProd generate = new GenerateIDProd();
			prodotto.setidProdotto(generate.generateUniqueID());

			
			preparedStatement.setString(1, prodotto.getID_Prodotto());
			preparedStatement.setString(2, prodotto.getMarca());
			preparedStatement.setString(3, prodotto.getColore());
			preparedStatement.setString(4, prodotto.getModello());
			preparedStatement.setDouble(5, prodotto.getPrezzo());
			preparedStatement.setInt(6, prodotto.getQuantita());
			preparedStatement.setBoolean(7, prodotto.isDisponibilita());
			preparedStatement.setString(8, prodotto.getDescrizione());
			preparedStatement.setString(9, prodotto.getCategoria());
			
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
	
	public synchronized boolean doDelete(String idProdotto) throws SQLException {
		
		//fare quando dobbiamo cancellare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelDS.TABLE_NAME + " WHERE ID_Prodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setString(1, idProdotto);
			
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
	public synchronized ProdottoBean doRetrieveByKey(String idProdotto) throws SQLException {
			
			//fare quando dobbiamo cercare oggetti precisi sul db
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ProdottoBean bean = new ProdottoBean();

			String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE ID_Prodotto = ?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, idProdotto);

				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					bean.setidProdotto(rs.getString(ID_PRODOTTO));
					bean.setMarca(rs.getString(MARCA));
					bean.setColore(rs.getString("Colore"));
					bean.setModello(rs.getString("Modello"));
					bean.setPrezzo(rs.getDouble("Prezzo"));
					bean.setQuantita(rs.getInt("Quantita"));
					bean.setDisponibilita(rs.getBoolean(DISPONIBILITA));
					bean.setDescrizione(rs.getString("Descrizione"));
					bean.setCategoria(rs.getString("Categoria"));
									
					ImageModelDS idao = new ImageModelDS();
					bean.setImmagine(idao.doRetriveByKey(rs.getString("Immagine")));

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
	
	public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
		
		//fare quando dobbiamo cercare tutti gli oggetti di una tabella
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();

				bean.setidProdotto(rs.getString(ID_PRODOTTO));
				bean.setMarca(rs.getString(MARCA));
				bean.setColore(rs.getString("Colore"));
				bean.setModello(rs.getString("Modello"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setDisponibilita(rs.getBoolean(DISPONIBILITA));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setCategoria(rs.getString("Categoria"));
				
				ImageModelDS idao = new ImageModelDS();
				bean.setImmagine(idao.doRetriveByKey(rs.getString("Immagine")));
				
				products.add(bean);
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
		return products;
	}


	
public synchronized Collection<ProdottoBean> doRetrieveSuggest(String stringaParziale) throws SQLException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME + " WHERE Marca LIKE ?";
		Collection<ProdottoBean> products = new LinkedList<>();
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			String stringaRicerca = stringaParziale.concat("%");
			preparedStatement.setString(1, stringaRicerca);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
				bean = new ProdottoBean();
				bean.setidProdotto(rs.getString(ID_PRODOTTO));
				bean.setMarca(rs.getString(MARCA));
				bean.setDisponibilita(rs.getBoolean(DISPONIBILITA));
				if( bean.isDisponibilita() ) {
					products.add(bean);
				}

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		products.toString();
		return products;
	}

	
}

