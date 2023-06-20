package model;

import java.sql.SQLException;
import java.util.Collection;

public interface RecensioneDAO {
	
	public void doSave(RecensioneBean recensione, UtenteBean utente, ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int ID_Recensione, String ID_Utente) throws SQLException;

	public RecensioneBean doRetrieveByKey(int ID_Recensione) throws SQLException;
	
	public Collection<RecensioneBean> doRetrieveByUtente(String utente) throws SQLException;
}
