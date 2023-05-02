package model;

import java.io.Serializable;

public class UtenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variabili

    private String ID_Utente;
    private String Ordine;
	private String Lista_Prodotti;
    private String Email;
    private String Password;
    private String Tipo;

    //Costruttore
    public UtenteBean () {
        this.ID_Utente = "";
        this.Ordine = "";
        this.Lista_Prodotti = "";
        this.Email = "";
        this.Password = "";
        this.Tipo = "";
    }

    //MetodiGet&Set
    public String getID_Utente() {
        return ID_Utente;
    }

    public void setID_Utente(String ID_Utente) {
        this.ID_Utente = ID_Utente;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getOrdine() {
		return Ordine;
	}

	public void setOrdine(String ordine) {
		Ordine = ordine;
	}

	public String getListaProdotti() {
		return Lista_Prodotti;
	}

	public void setListaProdotti(String listaProdotti) {
		Lista_Prodotti = listaProdotti;
	}

	
}
