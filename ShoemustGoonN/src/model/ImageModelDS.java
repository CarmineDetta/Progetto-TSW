package model;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ImageModelDS {
    private static final Logger LOGGER = Logger.getLogger(ImageModelDS.class.getName());

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
	
	private static final String TABLE_NAME = "Immagine";
	
	public synchronized void doSave(ImmagineBean immagine) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ImageModelDS.TABLE_NAME
				+ " (NomeImmagine, Path) VALUES (?, ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, immagine.getnomeImmagine());
			preparedStatement.setString(2, immagine.getPath());
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
	
	public synchronized ImmagineBean doRetriveByKey(String nome) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ImmagineBean bean = new ImmagineBean();
		
		String insertSQL = "SELECT * FROM " + ImageModelDS.TABLE_NAME
				+ " WHERE NomeImmagine = ?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, nome);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				bean.setnomeImmagine(rs.getString("NomeImmagine"));
				bean.setPath(rs.getString("Path"));
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
