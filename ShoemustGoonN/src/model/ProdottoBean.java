package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable {

    private static final long serialVersionUID = 1L; //Serve alla JVK

    //Variabili
    private String ID_Prodotto;
    private String Marca;
    private String Colore;
    private String Modello;
    private Double Prezzo;
    private int Quantita;
    private boolean Disponibilita;
    private String Descrizione;
    private String Categoria;

    //Costruttore
    public ProdottoBean () {
        this.ID_Prodotto = "";
        this.Marca = "";
        this.Colore = "";
        this.Modello = "";
        this.Prezzo = 0.00;
        this.Quantita= 0;
        this.Disponibilita = false;
        this.Descrizione= "";
        this.Categoria = "";
    }

    //MetodiGet&Set
    public String getID_Prodotto() {
        return ID_Prodotto;
    }

    public void setID_Prodotto(String ID_Prodotto) {
        this.ID_Prodotto = ID_Prodotto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getColore() {
        return Colore;
    }

    public void setColore(String colore) {
        Colore = colore;
    }

    public String getModello() {
        return Modello;
    }

    public void setModello(String modello) {
        Modello = modello;
    }

    public Double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(Double prezzo) {
        Prezzo = prezzo;
    }

    public boolean isDisponibilita() {
        return Disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        Disponibilita = disponibilita;
    }

    public int getQuantita() {
        return Quantita;
    }

    public void setQuantita(int quantita) {
        Quantita = quantita;
    }

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		this.Categoria = categoria;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
    
}
