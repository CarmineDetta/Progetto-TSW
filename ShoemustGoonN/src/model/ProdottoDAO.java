package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProdottoDAO {
	public void doSave(ProdottoBean product) throws SQLException;

	public boolean doDelete(String ID_Prodotto) throws SQLException;

	public ProdottoBean doRetrieveByKey(String ID_Prodotto) throws SQLException;
	
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException;
}
