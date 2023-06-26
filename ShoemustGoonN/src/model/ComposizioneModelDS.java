package model;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.OrdineBean;

public class ComposizioneModelDS {
	
    private static final Logger LOGGER = Logger.getLogger(ComposizioneModelDS.class.getName());

	private static DataSource ds;
	static ProdottoDAO modelProdotto = new ProductModelDS();
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) (initCtx).lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/shoemustgoon");

		} catch (NamingException e) {
			LOGGER.log(null, "contesto", e);	//fatto perchè lo chiede sonarcloud dicendo che devo controllare se il questo codice è disattivato quando consegno del condice da eseguire
		}
	}

	private static final String TABLE_NAME = "composizione";

	public synchronized  void doSaveAll(OrdineBean ordine, Cart carrello) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + ComposizioneModelDS.TABLE_NAME + " (ID_Ordine, ID_Prodotto, Quantita, Prezzo)" + " VALUES (?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			for(ItemCarrello a : carrello.getProducts()) {
				
				preparedStatement.setInt(1, ordine.getID_Ordine());
				preparedStatement.setString(2, a.getID_ProdottoItemCarrello());
				preparedStatement.setInt(3, a.getQuantitaItemCarrello());
				preparedStatement.setDouble(4, a.getQuantitaItemCarrello() * modelProdotto.doRetrieveByKey(a.getID_ProdottoItemCarrello()).getPrezzo());
				
				preparedStatement.executeUpdate();
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
	
	}
	
	public synchronized List<ItemCarrello> doRetrieveByOrdine(int idOrdine) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ItemCarrello> lista = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ID_Ordine = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idOrdine);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				ItemCarrello i = new ItemCarrello();
				i.setidProdottoItemCarrello(rs.getString("ID_Prodotto"));
				i.setQuantitaItemCarrello(rs.getInt("Quantita"));
				lista.add(i);
			}
		
		}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				connection.close();
		}
	}
		return lista;
	}
	
}
