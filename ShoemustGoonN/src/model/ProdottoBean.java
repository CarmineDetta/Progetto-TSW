package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {

    private static final long serialVersionUID = 1L; //Serve alla JVK

    //Variabili
    private String idProdotto;
    private String marca;
    private String colore;
    private String modello;
    private Double prezzo;
    private int quantita;
    private boolean disponibilita;
    private String descrizione;
    private String categoria;
    private ImmagineBean immagine;

    //Costruttore
    public ProdottoBean (){
    	//il costruttore è vuoto perché non  inizializziamo nessun campo
    }

    //MetodiGet&Set
    public String getID_Prodotto() {
        return idProdotto;
    }

    public void setidProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public ImmagineBean getImmagine() {
		return this.immagine;
	}

	
	public void setImmagine(ImmagineBean immagineBean){
		this.immagine = immagineBean;
	}
    
}
