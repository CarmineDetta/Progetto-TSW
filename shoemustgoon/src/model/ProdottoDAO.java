package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;

import utils.GenerateIDProd;




public class ProdottoDAO {

	private static final String TABLE_NAME = "Prodotto";

	
	public synchronized void doSave(ProdottoBean prodotto) throws SQLException {
		
		//fare quando dobbiamo inserire oggetti nel db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoDAO.TABLE_NAME
				+ " (ID_Prodotto, Marca, Colore, Modello, Prezzo, Nome, Quantita, Disponibilita, Descrizione, Categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			GenerateIDProd generate = new GenerateIDProd();
			prodotto.setID_Prodotto(generate.generateUniqueID());

			
			preparedStatement.setString(1, prodotto.getID_Prodotto());
			preparedStatement.setString(2, prodotto.getMarca());
			preparedStatement.setString(3, prodotto.getColore());
			preparedStatement.setString(4, prodotto.getModello());
			preparedStatement.setDouble(5, prodotto.getPrezzo());
			preparedStatement.setString(6, prodotto.getNome());
			preparedStatement.setInt(7, prodotto.getQuantita());
			preparedStatement.setBoolean(8, prodotto.isDisponibilita());
			preparedStatement.setString(9, prodotto.getDescrizione());
			preparedStatement.setString(10, prodotto.getCategoria());

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
	
	//Serve per recuperare un oggetto dal database in base all'ID
	public synchronized ProdottoBean doRetrieveByKey(String id) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE ID_Prodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setID_Prodotto(rs.getString("ID_Prodotto"));
				bean.setMarca(rs.getString("Marca"));
				bean.setColore(rs.getString("Colore"));
				bean.setModello(rs.getString("Modello"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setNome(rs.getString("Nome"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setDisponibilita(rs.getBoolean("Disponibilita"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setCategoria(rs.getString("Categoria"));
				
				
				//Immagine da aggiungere
				/*if( rs.getBlob("immagine_prodotto")!= null ) {
					Blob blob = rs.getBlob("immagine_prodotto");

					InputStream inputStream = blob.getBinaryStream();
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[4096];
					int bytesRead = -1;

					while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					}

					byte[] imageBytes = outputStream.toByteArray();
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					inputStream.close();
					outputStream.close();
					
					bean.setImmagineProdotto(base64Image);
					
				}*/
				
				
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
	
	//Ricerca tramite modello, le salvi tutte nella LinkedList
public synchronized Collection<ProdottoBean> doRetrieveSuggest(String StringaParziale) throws SQLException, IOException {
		
		//fare quando dobbiamo cercare oggetti precisi sul db
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean;

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE Modello LIKE ?";
		Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			String stringaRicerca = StringaParziale.concat("%");//concatena il carattere jolly "%" alla stringa passata come parametro StringaParziale
			preparedStatement.setString(1, stringaRicerca);
			
			System.out.println(stringaRicerca+ " nella stringa: " + selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			while (rs.next()) {
				bean = new ProdottoBean();
				bean.setID_Prodotto(rs.getString("ID_Prodotto"));
				bean.setMarca(rs.getString("Marca"));
				bean.setModello(rs.getString("Modello"));
				
				System.out.println("Gli oggetti trovati sono: "+bean.getID_Prodotto()+ " " + bean.getMarca() + " " + bean.getModello() );
				products.add(bean);
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


public synchronized boolean doDelete(String ID_Prodotto) throws SQLException {
	
	//fare quando dobbiamo cancellare oggetti precisi sul db
	System.out.println("Procediamo alla delete");
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result = 0;

	String deleteSQL = "DELETE FROM " + ProdottoDAO.TABLE_NAME + " WHERE ID_Prodotto = ?";

	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		
		
		preparedStatement.setString(1, ID_Prodotto);
		System.out.println(deleteSQL + ID_Prodotto);
		
		result = preparedStatement.executeUpdate();
		connection.commit();

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return (result != 0);
}

//Prendere tutto
public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException, IOException {
	
	//fare quando dobbiamo cercare tutti gli oggetti di una tabella
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();

	String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME;

	if (order != null && !order.equals("")) {
		selectSQL += " ORDER BY " + order;
	}

	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ProdottoBean bean = new ProdottoBean();

			bean.setID_Prodotto(rs.getString("ID_Prodotto"));
			bean.setMarca(rs.getString("Marca"));
			bean.setColore(rs.getString("Colore"));
			bean.setModello(rs.getString("Modello"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setNome(rs.getString("Nome"));
			bean.setQuantita(rs.getInt("Quantita"));
			bean.setDisponibilita(rs.getBoolean("Disponibilita"));
			bean.setDescrizione(rs.getString("Descrizione"));
			bean.setCategoria(rs.getString("Categoria"));
			
			//Immagine prodotto poi da caricare
			/*if( rs.getBlob("immagine_prodotto")!= null ) {
				Blob blob = rs.getBlob("immagine_prodotto");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				
				bean.setImmagineProdotto(base64Image);
				
			}*/
			
			
			products.add(bean);
		}

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return products;
}
	
public void doUpdate(ProdottoBean prodotto) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String updateSQL =" UPDATE "+ProdottoDAO.TABLE_NAME+" SET Marca = ?, Colore = ?, Modello = ?, Prezzo = ?, Nome = ?, Quantita = ?, "
			+ "Disponibilita = ?, Descrizione = ?, Categoria = ?, WHERE (ID_Prodotto = ?)" ;
	
	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(updateSQL);
		
		preparedStatement.setString(1, prodotto.getMarca());
		preparedStatement.setString(2, prodotto.getColore());
		preparedStatement.setString(3, prodotto.getModello());
		preparedStatement.setDouble(4, prodotto.getPrezzo());
		preparedStatement.setString(5, prodotto.getNome());
		preparedStatement.setInt(6, prodotto.getQuantita());
		preparedStatement.setBoolean(7, prodotto.isDisponibilita());
		preparedStatement.setString(8, prodotto.getDescrizione());
		preparedStatement.setString(9, prodotto.getCategoria());
		
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


public synchronized Collection<ProdottoBean> doRetrieveByCategory(String category) throws SQLException, IOException {
	
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();

	String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE Categoria = ? ";

	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(selectSQL);
		
		preparedStatement.setString(1, category );
		System.out.println("La stringa per la ricerca dei prodotti della categoria e': "+ preparedStatement);
		
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			ProdottoBean bean = new ProdottoBean();

			bean.setID_Prodotto(rs.getString("ID_Prodotto"));
			bean.setMarca(rs.getString("Marca"));
			bean.setColore(rs.getString("Colore"));
			bean.setModello(rs.getString("Modello"));
			bean.setPrezzo(rs.getDouble("Prezzo"));
			bean.setNome(rs.getString("Nome"));
			bean.setQuantita(rs.getInt("Quantita"));
			bean.setDisponibilita(rs.getBoolean("Disponibilita"));
			bean.setDescrizione(rs.getString("Descrizione"));
			bean.setCategoria(rs.getString("Categoria"));
			
			
			/*if( rs.getBlob("immagine_prodotto")!= null ) {
				Blob blob = rs.getBlob("immagine_prodotto");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				
				bean.setImmagineProdotto(base64Image);
				
			}*/
			
			
			products.add(bean);
		}

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return products;
 }

}


