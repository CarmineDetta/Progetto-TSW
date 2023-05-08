package model;

import java.io.Serializable;

public class ItemCarrello implements Serializable{
	
	  private static final long serialVersionUID = 1L;
	  
	  String ID_Prodotto;
	  int quantita;
	  
	  
	  public ItemCarrello(String id, int qty){
		  ID_Prodotto = id;
		  quantita = qty;
	  }
	  
	  public ItemCarrello(){
		  ID_Prodotto = "";
		  quantita = 0;
	  }
	  
	  public String getID_ProdottoItemCarrello() {
		  return this.ID_Prodotto;
	  }
	  
	  public int getQuantitaItemCarrello() {
		  return this.quantita;
	  }
	  
	  public void setID_ProdottoItemCarrello(String id) {
		  this.ID_Prodotto = id;
	  }
	  
	  public void setQuantitaItemCarrello(int q) {
		  this.quantita = q;
	  }
}
