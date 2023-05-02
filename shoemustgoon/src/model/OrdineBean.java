package model;

import java.io.Serializable;

public class OrdineBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Variabili
    private String ID_Ordine;
    private int N_prodotti;

    //Costruttore
    public OrdineBean() {
    	this.ID_Ordine = "";
    	this.N_prodotti = 0;
    }

    //MetodiGet&Set

    public String getID_Ordine() {
        return ID_Ordine;
    }

    public void setID_Ordine(String ID_Ordine) {
        this.ID_Ordine = ID_Ordine;
    }

    public int getN_prodotti() {
        return N_prodotti;
    }

    public void setN_prodotti(int n_prodotti) {
        N_prodotti = n_prodotti;
    }
}
