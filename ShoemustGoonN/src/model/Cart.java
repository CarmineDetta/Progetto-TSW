package model;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import model.*;

public class Cart{

	private ProductModelDS model;
	private List<ItemCarrello> products;
	
	public Cart() {
		products = new ArrayList<ItemCarrello>();
		model = new ProductModelDS();
		
	}
	
	public void addProduct(ItemCarrello product){
		boolean aggiunto = false;
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello())) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()+1);
				aggiunto = true; 
				break;
			}
		}
			if(!aggiunto)
			this.getProducts().add(product);
	}
	
	public void deleteProduct(ItemCarrello product) {
		
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello()) && prod.getQuantitaItemCarrello() > 1) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()-1);
				break;
			}else {		
				products.remove(prod);
				break;
			}
		}
 	}
	
	public List<ItemCarrello> getProducts() {
		return  products;
	}
	
	public ProdottoBean findProduct(String id) {
		
		ProdottoBean p = new ProdottoBean();
		
		try {
			p = model.doRetrieveByKey(id);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return p;
	}
}