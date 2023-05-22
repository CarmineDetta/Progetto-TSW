package model;

import java.io.Serializable;

public class OrdineBean implements Serializable{
	
    private static final long serialVersionUID = 1L; //Serve alla JVK

    private int ID_Ordine;
    private static int c = 0;
    private String data_ordine;
    private String metodo_pagamento;
    private double totale;
    private UtenteBean utente;
    
    public OrdineBean() {
    	this.ID_Ordine = c;
    	c++;
    	this.data_ordine = "";
    	this.metodo_pagamento = "";
    	this.totale = 0;
    }
    
    public int getID_Ordine() {
    	return this.ID_Ordine;
    }
    
    public void setID_Ordine(int n) {
    	this.ID_Ordine = n;
    }
    
    public String getDataOrdine() {
    	return this.data_ordine;
    }
    
    public void setDataOrdine(String data) {
    	this.data_ordine = data;
    }
    
    public String getMetodoPagamento() {
    	return this.metodo_pagamento;
    }
    
    public void setMetodoPagamento(String metodo) {
    	this.metodo_pagamento = metodo;
    }
    
    public double getTotale() {
    	return this.totale;
    }
    
    public void setTotale(double totale) {
    	this.totale = totale;
    }
    
    public UtenteBean getUtente() {
    	return this.utente;
    }
    
    public void setUtente(UtenteBean utente) {
    	this.utente = utente;
    }
    
   
    
}
