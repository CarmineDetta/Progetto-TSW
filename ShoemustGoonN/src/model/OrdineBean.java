package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class OrdineBean implements Serializable{
	
    private static final long serialVersionUID = 1L; //Serve alla JVK

    private int idOrdine;
    private String dataOrdine;
    private double totale;
    private UtenteBean utente;
    private RecapitoBean recapito;
    private transient PortafoglioBean pagamento;
    
    public OrdineBean() {
    	this.totale = 0;
    }
    
    public int getID_Ordine() {
    	return this.idOrdine;
    }
    
    public void setidOrdine(int n) {
    	this.idOrdine = n;
    }
    
    public String getDataOrdine() {
    	return this.dataOrdine;
    }
    
    public void setDataOrdine(String data) {
    	this.dataOrdine = data;
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
