package model;

import java.io.Serializable;

public class RecapitoBean implements Serializable{
	
    private static final long serialVersionUID = 1L; //Serve alla JVK

    private int ID_Indirizzo;
    private static int c = 0;
    private int cap;
    private String citta;
    private String via_piazza;
    private int n_civico;
    private UtenteBean utente;
    
    public RecapitoBean() {
    	this.ID_Indirizzo = c;
    	c++;
    	this.cap = 0;
    	this.citta = "";
    	this.via_piazza = "";
    	this.n_civico = 0;
    	
    }
    
    
    public int getID_Indirizzo() {
    	return this.ID_Indirizzo;
    }
    
    public void setID_Indirizzo(int id) {
    	this.ID_Indirizzo = id;
    }
    
    public int getCap() {
    	return this.cap;
    }
    
    public void setcap(int cap) {
    	this.cap = cap;
    }
    
    public String getCitta() {
    	return this.citta;
    }
    
    public void setCitta(String citta) {
    	this.citta = citta;
    }
    
    public String getVia_Piazza() {
    	return this.via_piazza;
    }
    
    public void setVia_Piazza(String via) {
    	this.via_piazza = via;
    }
    
    public int getN_Civico() {
    	return this.n_civico;
    }
    
    public void setN_Civico(int n) {
    	this.n_civico = n;
    }
    
    public UtenteBean getUtente() {
    	return this.utente;
    }
    
    public void setUtente(UtenteBean u) {
    	this.utente = u;
    }
}
