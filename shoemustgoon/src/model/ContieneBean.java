package model;

import java.io.Serializable;

public class ContieneBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Variabili
	private String Lista;
	private String Prodotto;
	
	//Costuttore
	public ContieneBean() {
		this.Lista = "";
		this.Prodotto = "";
	}
	
	//Metodi Get&Set
	public String getLista() {
		return Lista;
	}

	public void setLista(String lista) {
		Lista = lista;
	}

	public String getProdotto() {
		return Prodotto;
	}

	public void setProdotto(String prodotto) {
		Prodotto = prodotto;
	}
	
}
