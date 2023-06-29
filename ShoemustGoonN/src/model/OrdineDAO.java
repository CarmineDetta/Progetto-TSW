package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

public interface OrdineDAO {
	public void doSave(OrdineBean ordine, UtenteBean utente, RecapitoBean recapito, PortafoglioBean pagamento) throws SQLException;

	public boolean doDelete(int idOrdine) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveByUtente(String utente) throws SQLException;

	public OrdineBean doRetriveByKey(int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll() throws SQLException;
	
	public Collection<OrdineBean> doRetrieveByDate(String dataInizio, String dataFine) throws SQLException;


}
