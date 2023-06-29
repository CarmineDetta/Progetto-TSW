package model;

import java.sql.SQLException;
import java.util.Collection;

public interface RecensioneDAO {
	
	public void doSave(RecensioneBean recensione, UtenteBean utente, ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int idRecensione, String idUtente) throws SQLException;

	public RecensioneBean doRetrieveByKey(int idRecensione) throws SQLException;
	
	public Collection<RecensioneBean> doRetrieveByUtente(String utente) throws SQLException;
	
	public Collection<RecensioneBean> doRetrieveByProdotto(String prodotto) throws SQLException;

}
