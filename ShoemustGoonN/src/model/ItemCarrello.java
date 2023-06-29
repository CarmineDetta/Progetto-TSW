package model;

import java.io.Serializable;

public class ItemCarrello implements Serializable{
	
	  private static final long serialVersionUID = 1L;
	  
	  String idProdotto;
	  int quantita;
	  
	  
	  public ItemCarrello(String id, int qty){
		  idProdotto = id;
		  quantita = qty;
	  }
	  
	  public ItemCarrello(){
		  idProdotto = "";
		  quantita = 0;
	  }
	  
	  public String getID_ProdottoItemCarrello() {
		  return this.idProdotto;
	  }
	  
	  public int getQuantitaItemCarrello() {
		  return this.quantita;
	  }
	  
	  public void setidProdottoItemCarrello(String id) {
		  this.idProdotto = id;
	  }
	  
	  public void setQuantitaItemCarrello(int q) {
		  this.quantita = q;
	  }
}
