package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CategoriaDAO {


	private static final String TABLE_NAME = "Categoria";

	
	public synchronized void doSave(CategoriaBean categoria) throws SQLException {
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " +CategoriaDAO.TABLE_NAME+ " (ID_Categoria, Nome) VALUES (?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, categoria.getID_Categoria());
			preparedStatement.setString(2, categoria.getNome());
			
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

//Ricerca tramite ID_Categoria
public synchronized CategoriaBean doRetrieveByKey(String nome_categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CategoriaBean bean = new CategoriaBean();

		String selectSQL = "SELECT * FROM " + CategoriaDAO.TABLE_NAME + " WHERE ID_Categoria = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, nome_categoria);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID_Categoria(rs.getString("ID_Categoria"));
				bean.setNome(rs.getString("Nome"));
			}
			
			System.out.println(bean.getNome());
			
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


	
public synchronized boolean doDelete(String id_categoria) throws SQLException {
		
		//non puoi eliminare la categoria perché c'è una chiave esterna vincolata nella tabella prodotto 
		//che fa riferimento alla categoria che si sta cercando di eliminare. 
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CategoriaDAO.TABLE_NAME + " WHERE ID_Categoria = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			
			preparedStatement.setString(1, id_categoria);

			result = preparedStatement.executeUpdate();

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

	
	public synchronized Collection<CategoriaBean> doRetrieveAll(String order) throws SQLException {
		
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<CategoriaBean> categories = new LinkedList<CategoriaBean>();

		String selectSQL = "SELECT * FROM " + CategoriaDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				
				bean.setID_Categoria(rs.getString("ID_Categoria"));
				bean.setNome(rs.getString("Nome"));
				
				categories.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return categories;
	}
	
	

}