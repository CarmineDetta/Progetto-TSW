package model;

import java.io.Serializable;

import java.util.*;
import model.ProdottoBean;

public class ListaProdottiBean  implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variabili
    private String ID_Lista;
    private boolean Is_Carrello;
    private List<ProdottoBean> product;

    //Costruttore
    public ListaProdottiBean (){
        this.ID_Lista = "";
        this.Is_Carrello = false;
    }

    //MetodoGet&Set

    public String getID_Lista() {
        return ID_Lista;
    }

    public void setID_Lista(String ID_Lista) {
        this.ID_Lista = ID_Lista;
    }

    public boolean isIs_Carrello() {
        return Is_Carrello;
    }

    public void setIs_Carrello(boolean is_Carrello) {
        Is_Carrello = is_Carrello;
    }
    
    public void addProduct(ProdottoBean p) {
		product.add(p);
	}
    
	
	public void deleteProduct(ProdottoBean p) {
		for(ProdottoBean prod : product) {
			if(prod.getID_Prodotto() == p.getID_Prodotto()) {
				product.remove(prod);
				break;
			}
		}
 	}
	
	public List<ProdottoBean> getProdotto() {
		return  product;
	}
}