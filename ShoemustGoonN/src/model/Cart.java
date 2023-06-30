package model;


import java.sql.SQLException;
import java.util.logging.Logger;



import java.util.ArrayList;

import java.util.List;

public class Cart{
	
	private static final Logger LOGGER = Logger.getLogger(Cart.class.getName());

	private ProductModelDS model;
	private List<ItemCarrello> products;
	private double totale = 0;
	private int tot = 0;
	
	public Cart() {
		products = new ArrayList<>();
		model = new ProductModelDS();
		
	}
	
	public double addProduct(ItemCarrello product) throws SQLException{
		boolean aggiunto = false;
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello())) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()+1);
				aggiunto = true;
				
				ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
				totale = totale + (p.getPrezzo());
				System.out.println("Prezzo aggiornato: " + totale);
				tot = tot + 1;
				
				return totale;
			}
		}
			if(!aggiunto) {
				this.getProducts().add(product);
				ProdottoBean p = model.doRetrieveByKey(product.getID_ProdottoItemCarrello());
				totale = totale + (p.getPrezzo() * product.getQuantitaItemCarrello());
				tot = tot + product.getQuantitaItemCarrello();
			}
			
			return totale;	
	}
	

public double deleteProduct(ItemCarrello product) throws SQLException {
	    boolean eliminato = false;
	    ItemCarrello prodDaRimuovere = null;
	    
	    for (ItemCarrello prod : products) {
	        if (prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello()) && prod.getQuantitaItemCarrello() > 1) {
	            prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello() - 1);

	            ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
	            totale = totale - p.getPrezzo();
				System.out.println("Prezzo aggiornato: " + totale);
	            tot = tot - 1;

	            return totale;
	        } else if (prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello()) && prod.getQuantitaItemCarrello() == 1) {
	            prodDaRimuovere = prod;
	            break;
	        }
	    }

	    if (prodDaRimuovere != null) {
	        this.getProducts().remove(prodDaRimuovere);
	        ProdottoBean p = model.doRetrieveByKey(prodDaRimuovere.getID_ProdottoItemCarrello());
	        totale = totale - p.getPrezzo();
	        tot = tot - 1;
	    }
	    return totale;
	}
	
public List<ItemCarrello> getProducts() {
		return  products;
	}
	
	public ProdottoBean findProduct(String id) {
		
		ProdottoBean p = new ProdottoBean();
		
		try {
			p = model.doRetrieveByKey(id);
		} catch (SQLException e) {
		
			LOGGER.log(null, "contesto", e);
		}
		
		return p;
	}
	
	public double getTotale() {
		return this.totale;
	}
	
	public void setTotale(double t) {
		this.totale = t;
	}
	
	public int getQuantita(ItemCarrello product) {
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello())) {
				return prod.getQuantitaItemCarrello();
			}
		}
		return 0;
	}
	
}