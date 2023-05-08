package model;

import java.io.Serializable;

public class UtenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variabili
    private String ID_Utente;
    private String Nome;
    private String Cognome;
    private String DataNascita;
    private String CF;
    private String Email;
    private String Password;
    private String Tipo;

    //Costruttore
    public UtenteBean () {
        this.ID_Utente = "";
        this.Nome="";
        this.Cognome="";
        this.DataNascita="";
        this.CF = "";
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
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String Cognome) {
        this.Cognome = Cognome;
    }
    
    public String getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(String DataNascita) {
        this.DataNascita = DataNascita;
    }
    
    public String getCF() {
        return CF;
    }

    public void setCF(String cf) {
        this.CF = cf;
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
	
}
