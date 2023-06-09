package model;

import java.sql.SQLException;

import java.util.Collection;

public interface UtenteDAO {
	public void doSave(UtenteBean utente) throws SQLException;

	public boolean doDelete(String idUtente) throws SQLException;
	
	public Collection<UtenteBean> doRetrieveAll(String order) throws SQLException;

	public UtenteBean doRetriveByEmail(String email) throws SQLException;
	
	public void doUpdatePassword(String value, String id) throws SQLException;
	
	public void doUpdateEmail(String value, String id) throws SQLException;
	
	public Collection<UtenteBean> doRetrieveAllUtente() throws SQLException;
	
}