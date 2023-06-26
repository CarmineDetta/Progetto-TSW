package model;

public class PortafoglioBean {
	
	private static final long serialVersionUID = 1L;
	
	private int idPagamento;
	private static int c = 0;
	private String nomeIntestatario;
	private String ncarta;
	private String scadenza;
	private int cvv;
	private UtenteBean utente;
	
	public PortafoglioBean(){
		this.idPagamento = c;
		this.c++;
		this.nomeIntestatario = "";
		this.ncarta = "";
		this.scadenza = "";
		this.cvv = 0;
	}
	
	public int getID_Pagamento() {
		return this.idPagamento;
	}
	
	public void setidPagamento(int pagamento) {
		this.idPagamento = pagamento;
	}
	
	public String getNome_Intestatario(){
		return nomeIntestatario;
	}
	
	public void setNomeIntestatario(String nome) {
		this.nomeIntestatario = nome;
	}
	

	public String getNcarta(){
		return this.ncarta;
	}
	
	public void setNcarta(String num_carta) {
		this.ncarta = num_carta;
	}
	
	public String getScadenza() {
		return this.scadenza;
	}
	
	public void setScadenza(String n) {
		this.scadenza = n;
	}
	
	public int getCvv(){
		return this.cvv;
	}
	
	public void setCvv(int c) {
		this.cvv = c;
	}
	
	public UtenteBean getUtente() {
		return this.utente;
	}
	
	public void setUtente(UtenteBean u) {
		this.utente = u;
	}
	
}
