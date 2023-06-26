package model;
import java.sql.SQLException;
import java.util.Collection;

import model.RecapitoBean;


public interface RecapitoDAO {

	public void doSave(RecapitoBean spedizione, UtenteBean utente) throws SQLException;

		public boolean doDelete(int idIndirizzo) throws SQLException;

		public RecapitoBean doRetrieveByKey(int idIndirizzo) throws SQLException;
		
		public Collection<RecapitoBean> doRetrieveByUtente(String utente) throws SQLException;

		
	}

