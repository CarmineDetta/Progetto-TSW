package model;

import java.sql.SQLException;
import java.util.Collection;

public class test {
	
	public static void main (String []args) {
		
		CategoriaDAO dao = new CategoriaDAO ();
		CategoriaBean bean = new CategoriaBean ();
		
		/*try {
			Collection<CategoriaBean> categories = dao.doRetrieveAll("");
			for (CategoriaBean bean : categories) {
				System.out.println(bean.getID_Categoria() + " - " + bean.getNome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		try {
			dao.doDelete("TRANS");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

