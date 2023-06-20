package model;

public class PortafoglioBean {
	
	private int ID_Pagamento;
	private static int c = 0;
	private String nome_Intestatario;
	private String n_carta;
	private String Scadenza;
	private int cvv;
	private UtenteBean utente;
	
	public PortafoglioBean(){
		this.ID_Pagamento = c;
		this.c++;
		this.nome_Intestatario = "";
		this.n_carta = "";
		this.Scadenza = "";
		this.cvv = 0;
	}
	
	public int getID_Pagamento() {
		return this.ID_Pagamento;
	}
	
	public void setID_Pagamento(int pagamento) {
		this.ID_Pagamento = pagamento;
	}
	
	public String getNome_Intestatario(){
		return nome_Intestatario;
	}
	
	public void setNome_Intestatario(String nome) {
		this.nome_Intestatario = nome;
	}
	
	public String getN_carta(){
		return this.n_carta;
	}
	
	public void setN_carta(String num_carta) {
		this.n_carta = num_carta;
	}
	
	public String getScadenza() {
		return this.Scadenza;
	}
	
	public void setScadenza(String n) {
		this.Scadenza = n;
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
