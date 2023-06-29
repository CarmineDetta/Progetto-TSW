package model;

public class RecensioneBean {
	
	private int idRecensione;
	private static int c = 0;
	private float votazione;
	private String descrizione;
	private UtenteBean utente;
	private ProdottoBean prodotto;
	
	public RecensioneBean(){
		idRecensione = c;
		c++;
		votazione = 0;
		descrizione = "";
	}
	
	public int getID_Recensione() {
		return this.idRecensione;
	}
	
	public void setidRecensione(int a){
		idRecensione = a;
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
