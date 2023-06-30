package model;

public class ComposizioneBean {
	private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private int idProdotto;
	private int quantita;
	private double prezzo;
	
	public ComposizioneBean() {
		idOrdine=0;
		idProdotto=0;
		quantita=0;
		prezzo=0.0;
	}

	public int getID_Ordine() {
		return idOrdine;
	}

	public void setidOrdine(int identificativoOrdine) {
		this.idOrdine = identificativoOrdine;
	}

	public int getID_Prodotto() {
		return idProdotto;
	}

	public void setidProdotto(int identificativoProdotto) {
		this.idProdotto = identificativoProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzoAcquisto) {
		this.prezzo = prezzoAcquisto;
	}

	@Override
	public String toString() {
		return "CompositionBean [identificativo_prodotto=" + this.idProdotto + ", identificativo_ordine="
				+ this.idOrdine + ", quantita=" + this.quantita + ", prezzo_acquisto=" + this.prezzo + "]";
	}
}

