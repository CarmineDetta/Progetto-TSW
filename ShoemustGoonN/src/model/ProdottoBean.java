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

    public void setidProdotto(String id) {
        this.idProdotto = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marc) {
        this.marca = marc;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String color) {
        this.colore = color;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String model) {
        this.modello = model;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double price) {
        this.prezzo = price;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disp) {
        this.disponibilita = disp;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantity) {
        this.quantita = quantity;
    }

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String category) {
		this.categoria = category;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descr) {
		this.descrizione = descr;
	}
	
	public ImmagineBean getImmagine() {
		return this.immagine;
	}

	
	public void setImmagine(ImmagineBean immagineBean){
		this.immagine = immagineBean;
	}
    
}
