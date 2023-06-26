package model;


import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import java.util.Iterator;

import model.ItemCarrello;

public class Cart{
		
	private ProductModelDS model;
	private List<ItemCarrello> products;
	private double totale = 0;
	
	public Cart() {
		products = new ArrayList<>();
		model = new ProductModelDS();
		
	}
	
	public void addProduct(ItemCarrello product) throws SQLException{
		boolean aggiunto = false;
		for(ItemCarrello prod : products) {
			if(prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello())) {
				prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello()+1);
				aggiunto = true;
				
				ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
				totale = totale + (p.getPrezzo());
				break;
			}
		}
			if(!aggiunto) {
				this.getProducts().add(product);
				ProdottoBean p = model.doRetrieveByKey(product.getID_ProdottoItemCarrello());
				totale = totale + (p.getPrezzo() * product.getQuantitaItemCarrello());
			}
			
	}
	
	public void deleteProduct(ItemCarrello product) throws SQLException {
	   
		boolean found = false;

	    for (Iterator<ItemCarrello> iterator = products.iterator(); iterator.hasNext();) {
	        ItemCarrello prod = iterator.next();

	        if (prod.getID_ProdottoItemCarrello().equals(product.getID_ProdottoItemCarrello()) && prod.getQuantitaItemCarrello() > 1) {
	            prod.setQuantitaItemCarrello(prod.getQuantitaItemCarrello() - 1);

	            ProdottoBean p = model.doRetrieveByKey(prod.getID_ProdottoItemCarrello());
	            totale = totale - p.getPrezzo();

	            found = true;
	            break;
	        } else {
	            ProdottoBean p = model.doRetrieveByKey(product.getID_ProdottoItemCarrello());
	            totale = totale - p.getPrezzo();

	            iterator.remove();
	            found = true;
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
	
	public double getTotale() {
		return this.totale;
	}
	
	public void setTotale(double t) {
		this.totale = t;
	}
}