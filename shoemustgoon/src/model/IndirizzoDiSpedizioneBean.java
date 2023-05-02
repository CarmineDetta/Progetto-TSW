package model;

import java.io.Serializable;

public class IndirizzoDiSpedizioneBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variabili
    private int CAP;
    private String Citta;
    private String Via_Piazza;
    private int N_Civico;
    private OrdineBean Ordine;

    //Costruttori
   public IndirizzoDiSpedizioneBean () {
       this.CAP = 0;
       this.Citta = "";
       this.Via_Piazza = "";
       this.N_Civico = 0;
       this.Ordine = new OrdineBean();
   }

    //MetodiGet&Set

    public int getCAP() {
        return CAP;
    }

    public void setCAP(int CAP) {
        this.CAP = CAP;
    }

    public String getCitta() {
        return Citta;
    }

    public void setCitta(String citta) {
        Citta = citta;
    }

    public String getVia_Piazza() {
        return Via_Piazza;
    }

    public void setVia_Piazza(String via_Piazza) {
        Via_Piazza = via_Piazza;
    }

    public int getN_Civico() {
        return N_Civico;
    }

    public void setN_Civico(int n_Civico) {
        N_Civico = n_Civico;
    }

    public OrdineBean getOrdine() {
        return Ordine;
    }

    public void setOrdine(OrdineBean ordine) {
        this.Ordine = ordine;
    }
}
