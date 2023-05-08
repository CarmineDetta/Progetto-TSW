package model;

import java.sql.SQLException;

import java.util.Collection;

public interface UtenteDAO {
	public void doSave(UtenteBean utente) throws SQLException;

	public boolean doDelete(String ID_Utente) throws SQLException;
	
	public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;

	public UtenteBean doRetriveByEmail(String email) throws SQLException;
}