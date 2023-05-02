package model;

import java.io.Serializable;
import java.sql.Date;

public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Varabili
	private String ID_Pagamento;
    private Date Data_Pagamento;
    private OrdineBean Ordine;
    private String Num_carta;
    private String Nome_Intestatario;
    private Date Scadenza; 
    private double Totale;
    private String Tipo;
    private String Codice_Sconto;

    //Costruttore
    public PagamentoBean () {
    	this.ID_Pagamento = "";
    	this.Data_Pagamento = new Date(2023-04-12);
    	this.Ordine = new OrdineBean();
    	this.Num_carta = "";
    	this.Nome_Intestatario = "";
    	this.Scadenza = new Date (2030-01-01);
    	this.Totale = 0.00;
    	this.Tipo = "";
    	this.Codice_Sconto = "";
    }


    //MetodiGet&Set
	public String getID_Pagamento() {
		return ID_Pagamento;
	}

	public void setID_Pagamento(String iD_Pagamento) {
		ID_Pagamento = iD_Pagamento;
	}

	public Date getData_Pagamento() {
		return Data_Pagamento;
	}

	public void setData_Pagamento(Date data_Pagamento) {
		Data_Pagamento = data_Pagamento;
	}

	public OrdineBean getOrdine() {
		return Ordine;
	}

	public void setOrdine(OrdineBean ordine) {
		Ordine = ordine;
	}

	public String getNum_carta() {
		return Num_carta;
	}

	public void setNum_carta(String num_carta) {
		Num_carta = num_carta;
	}

	public String getNome_Intestatario() {
		return Nome_Intestatario;
	}

	public void setNome_Intestatario(String nome_Intestatario) {
		Nome_Intestatario = nome_Intestatario;
	}

	public Date getScadenza() {
		return Scadenza;
	}

	public void setScadenza(Date scadenza) {
		Scadenza = scadenza;
	}

	public double getTotale() {
		return Totale;
	}

	public void setTotale(double totale) {
		Totale = totale;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getCodice_Sconto() {
		return Codice_Sconto;
	}

	public void setCodice_Sconto(String codice_Sconto) {
		Codice_Sconto = codice_Sconto;
	}
}