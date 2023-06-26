package model;

import java.sql.SQLException;
import java.util.Collection;

public interface PortafoglioDAO {
	public void doSave(PortafoglioBean pagamento, UtenteBean utente) throws SQLException;

	public boolean doDelete(int idPagamento) throws SQLException;

	public PortafoglioBean doRetrieveByKey(int idPagamento) throws SQLException;
	
	public Collection<PortafoglioBean> doRetrieveByUtente(String utente) throws SQLException;

}
