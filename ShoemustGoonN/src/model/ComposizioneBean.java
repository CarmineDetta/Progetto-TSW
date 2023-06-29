package model;

public class ComposizioneBean {
	private static final long serialVersionUID = 1L;
	
	private int ID_Ordine;
	private int ID_Prodotto;
	private int Quantita;
	private double Prezzo;
	
	public ComposizioneBean() {
		ID_Ordine=0;
		ID_Prodotto=0;
		Quantita=0;
		Prezzo=0.0;
	}

	public int getID_Ordine() {
		return ID_Ordine;
	}

	public void setID_Ordine(int identificativo_ordine) {
		this.ID_Ordine = identificativo_ordine;
	}

	public int getID_Prodotto() {
		return ID_Prodotto;
	}

	public void setID_Prodotto(int identificativo_prodotto) {
		this.ID_Prodotto = identificativo_prodotto;
	}

	public int getQuantita() {
		return Quantita;
	}

	public void setQuantita(int quantita) {
		this.Quantita = quantita;
	}

	public double getPrezzo() {
		return Prezzo;
	}

	public void setPrezzo(double prezzo_acquisto) {
		this.Prezzo = prezzo_acquisto;
	}

	@Override
	public String toString() {
		return "CompositionBean [identificativo_prodotto=" + this.ID_Prodotto + ", identificativo_ordine="
				+ this.ID_Ordine + ", quantita=" + this.Quantita + ", prezzo_acquisto=" + this.Prezzo + "]";
	}
}

