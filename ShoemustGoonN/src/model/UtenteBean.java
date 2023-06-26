package model;

import java.io.Serializable;

public class UtenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variabili
    private String idUtente;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String cf;
    private String email;
    private String password;
    private String tipo;

    //Costruttore
    public UtenteBean () {
        this.idUtente = "";
        this.nome="";
        this.cognome="";
        this.dataNascita="";
        this.cf = "";
        this.email = "";
        this.password = "";
        this.tipo = "";
    }

    //MetodiGet&Set
    public String getID_Utente() {
        return idUtente;
    }

    public void setidUtente(String idUtente) {
        this.idUtente = idUtente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }
    
    public String getCF() {
        return cf;
    }

    public void setCF(String cf) {
        this.cf = cf;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
