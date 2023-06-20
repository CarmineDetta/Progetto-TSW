package model;

public class RecensioneBean {
	
	private int ID_Recensione;
	private static int c = 0;
	private float votazione;
	private String descrizione;
	private UtenteBean utente;
	private ProdottoBean prodotto;
	
	public RecensioneBean(){
		ID_Recensione = c;
		c++;
		votazione = 0;
		descrizione = "";
	}
	
	public int getID_Recensione() {
		return this.ID_Recensione;
	}
	
	public void setID_Recensione(int a){
		ID_Recensione = a;
	}
	
	public float getVotazione() {
		return this.votazione;
	}
	
	public void setVotazione(float v) {
		this.votazione = v;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public void setDescrizione(String d) {
		this.descrizione = d;
	}
	
	public UtenteBean getUtente(){
		return this.utente;
	}
	
	public void setUtente(UtenteBean u) {
		this.utente = u;
	}
	
	public ProdottoBean getProdotto() {
		return this.prodotto;
	}
	
	public void setProdotto(ProdottoBean n) {
		this.prodotto = n;
	}
	
	
}
