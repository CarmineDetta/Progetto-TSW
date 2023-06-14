package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class OrdineBean implements Serializable{
	
    private static final long serialVersionUID = 1L; //Serve alla JVK

    private int ID_Ordine;
    private static int c = 0;
    private String data_ordine;
    private double totale;
    private UtenteBean utente;
    private RecapitoBean recapito;
    private PortafoglioBean pagamento;
    
    public OrdineBean() {
    	this.ID_Ordine = c;
    	c++;
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
    
    public RecapitoBean getRecapito() {
    	return this.recapito;
    }
    
    public void setRecapito(RecapitoBean recapito) {
    	this.recapito = recapito;
    }
    
    public PortafoglioBean getPagamento() {
    	return this.pagamento;
    }
    
    public void setPagamento(PortafoglioBean pagamento) {
    	this.pagamento = pagamento;
    }
    
}
