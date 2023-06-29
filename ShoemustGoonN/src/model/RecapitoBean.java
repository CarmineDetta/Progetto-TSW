package model;

import java.io.Serializable;

public class RecapitoBean implements Serializable{
	
    private static final long serialVersionUID = 1L; //Serve alla JVK

    private int idIndirizzo;
    private static int c = 0;
    private int cap;
    private String citta;
    private String viaPiazza;
    private int ncivico;
    private UtenteBean utente;
    
    public RecapitoBean() {
    	this.idIndirizzo = c;
    	c++;
    	this.cap = 0;
    	this.citta = "";
    	this.viaPiazza = "";
    	this.ncivico = 0;
    	
    }
    
    
    public int getID_Indirizzo() {
    	return this.idIndirizzo;
    }
    
    public void setidIndirizzo(int id) {
    	this.idIndirizzo = id;
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
    	return this.viaPiazza;
    }
    
    public void setViaPiazza(String via) {
    	this.viaPiazza = via;
    }
    
    public int getN_Civico() {
    	return this.ncivico;
    }
    

    public void setNCivico(int n) {
    	this.ncivico = n;
    }
    
    public UtenteBean getUtente() {
    	return this.utente;
    }
    
    public void setUtente(UtenteBean u) {
    	this.utente = u;
    }
}
