package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

public interface OrdineDAO {
	public void doSave(OrdineBean ordine, UtenteBean utente, RecapitoBean recapito, PortafoglioBean pagamento) throws SQLException;

	public boolean doDelete(int idOrdine) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveByUtente(String utente) throws SQLException;

	public OrdineBean doRetriveByKey(int id) throws SQLException;
}