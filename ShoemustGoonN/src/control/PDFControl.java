package control;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import model.*;



@WebServlet("/PDFControl")
public class PDFControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static OrdineDAO ordineModel = new OrdineModelDS();
	static ComposizioneModelDS composizioneModel = new ComposizioneModelDS();
	static ProductModelDS productModel = new ProductModelDS();
	public PDFControl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/pdf");
        response.setHeader("Content-disposition","inline; filename = invoice.pdf");
  
        UtenteBean utente = (UtenteBean) request.getSession().getAttribute("UtenteLoggato");
        
        Integer id = null;
        id = Integer.valueOf(request.getParameter("idOrdine"));

        
        
        try {
        	
        	OrdineBean ordine = new OrdineBean();
        	PortafoglioBean payements = new PortafoglioBean();
        	
        	try {
    			ordine = ordineModel.doRetriveByKey(id);
        	} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
        	
        	Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
	
			document.open();
			
			document.newPage();
			
			Paragraph emptyLine = new Paragraph(" "); // per mettere una linea bianca di separazione
			

//CREAZIONE LINEA DI SEPRAZIONE NERA
			LineSeparator separator = new LineSeparator();
			separator.setLineColor(BaseColor.BLACK); // Colore della linea (puoi specificare altri colori)
			separator.setLineWidth(1f); // Spessore della linea in punti (puoi specificare altri spessori)
			separator.setAlignment(Element.ALIGN_CENTER); // Allineamento della linea (puoi utilizzare Element.ALIGN_LEFT o Element.ALIGN_RIGHT)

//GESTIONE IMMAGINE
			String relativeWebPath = "/image/logobianco.jpg";
            String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
            
            Image logo = Image.getInstance(absoluteDiskPath);
            logo.scaleToFit(80, 80);
            
			
//GESIONE DEI PARAGRAFI DEL TITOLO			
			/*con paragraph creiamo il pragrafo di testo*/
			Paragraph title = new Paragraph();			/*titolo*/
			Paragraph subtitle = new Paragraph();	/*sottotilo*/
			
			/*definiamo il font dei paragrafi*/
			title.setFont(new Font(Font.FontFamily.COURIER, 25, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);

			title.add("SHOEMUSTGOON\n");
			
			subtitle.setFont(new Font(Font.FontFamily.HELVETICA, 10));
			subtitle.setAlignment(Element.ALIGN_CENTER);
			
			subtitle.add("\nVia Roma 1\n");
			subtitle.add("84084, Fisciano SA");

//ALLINEAMENTO DEGLI ELEMENTI DEL TITOLO
				PdfPTable tableTitle = new PdfPTable(2);
				tableTitle.setWidthPercentage(100);

				// Aggiunta dell'immagine alla prima colonna
				PdfPCell imageCell = new PdfPCell(logo);
				imageCell.setBorderColor(BaseColor.WHITE);
				tableTitle.addCell(imageCell);

				// Aggiunta del testo alla seconda colonna
				PdfPCell textCell = new PdfPCell();
				textCell.addElement(title);
				textCell.addElement(subtitle);
				
				textCell.setBorderColor(BaseColor.WHITE);
				tableTitle.addCell(textCell);	/*aggiungiamo l'elemento alla colonna della tabella*/

//GESTIAMO IL CLIENTE CHE HA EFFETTUATO L'ACQUISTO
				Paragraph titoloUtente = new Paragraph();
				titoloUtente.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.GRAY));
				titoloUtente.setAlignment(Element.ALIGN_LEFT);
				
				titoloUtente.add("CLIENTE:\n");
				
				Paragraph datiUtente = new Paragraph();
				datiUtente.setFont(new Font(Font.FontFamily.HELVETICA, 10));
				datiUtente.setAlignment(Element.ALIGN_LEFT);
				
				
				datiUtente.add(utente.getNome() + " "+ utente.getNome() + "\n");
				datiUtente.add(utente.getDataNascita()+ "\n");
				datiUtente.add(utente.getCF() + "\n");
				
				
//GESTIONE METODI DI PAGAMENTO
				Paragraph titoloPagamenti = new Paragraph();
				titoloPagamenti.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.GRAY));
				titoloPagamenti.setAlignment(Element.ALIGN_RIGHT);
				
				titoloPagamenti.add("PAGAMENTI UTENTE:\n");
		
				Paragraph datiPagamenti = new Paragraph();
				datiPagamenti.setFont(new Font(Font.FontFamily.HELVETICA, 10));
				datiPagamenti.setAlignment(Element.ALIGN_RIGHT);
			
	        	payements = ordine.getPagamento();
				
	        	datiPagamenti.add("Numero Carta:\n" + payements.getNcarta());

//ALLINEAMENTO UTENTI E PAGAMENTI
	        	PdfPTable tableDati = new PdfPTable(2);
				tableDati.setWidthPercentage(100);

				// Aggiunta dell'immagine alla prima colonna
				PdfPCell datiUtenteCell = new PdfPCell();
				datiUtenteCell.addElement(titoloUtente);
				datiUtenteCell.addElement(datiUtente);
				datiUtenteCell.setBorderColor(BaseColor.WHITE);
				tableDati.addCell(datiUtenteCell);

				// Aggiunta del testo alla seconda colonna
				PdfPCell datiPagamentiCell = new PdfPCell();
				datiPagamentiCell.addElement(titoloPagamenti);
				datiPagamentiCell.addElement(datiPagamenti);
				datiPagamentiCell.setBorderColor(BaseColor.WHITE);
				tableDati.addCell(datiPagamentiCell);	/*aggiungiamo l'elemento alla colonna della tabella*/

//TABELLA PRODOTTI
				Paragraph titoloProdotti = new Paragraph();
				titoloProdotti.setFont(new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.GRAY));
				titoloProdotti.setAlignment(Element.ALIGN_CENTER);
				
				titoloProdotti.add("DATI ORDINE\n");
				
				//dichiarazioni
				PdfPTable tableProdotti = new PdfPTable(3);
				tableProdotti.setWidthPercentage(100);
				tableProdotti.getDefaultCell().setBackgroundColor(BaseColor.GRAY);

				//titoli per la tabella dei prodotti
				PdfPCell titoloSpecificheprodotto = new PdfPCell(new Phrase("PRODOTTO"));
				titoloSpecificheprodotto.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProdotti.addCell(titoloSpecificheprodotto);
				
				PdfPCell titoloQuantita = new PdfPCell(new Phrase("QUANTITA'"));				titoloQuantita.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProdotti.addCell(titoloQuantita);
				
				PdfPCell titoloPrezzo = new PdfPCell(new Phrase("PREZZO"));
				titoloPrezzo.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProdotti.addCell(titoloPrezzo);
				
				
				//scorriamo tutti i prodotti dell'ordine
				try {
					for(ItemCarrello a : composizioneModel.doRetrieveByOrdine(ordine.getID_Ordine())){
					
						ProdottoBean product = productModel.doRetrieveByKey(a.getID_ProdottoItemCarrello());
						int quantita = a.getQuantitaItemCarrello();
						
						
						Paragraph codice = new Paragraph();
						codice.setFont(new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.GRAY));
						
						codice.add("(" + product.getID_Prodotto() + ")  " + product.getMarca());

						double prezzo = product.getPrezzo();
						String stringaPrezzo = String.format("%.2f", prezzo);
						
						int qty = quantita;
						String stringaQuantita = String.valueOf(qty);
				
						PdfPCell prodottoCell = new PdfPCell(codice);
						prodottoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						prodottoCell.setBorderColor(BaseColor.WHITE);
						tableProdotti.addCell(prodottoCell);
						
						PdfPCell quantityCell = new PdfPCell(new Phrase(stringaQuantita));
						quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						quantityCell.setBorderColor(BaseColor.WHITE);
						tableProdotti.addCell(quantityCell);
						
						PdfPCell prezzoCell = new PdfPCell(new Phrase(stringaPrezzo));
						prezzoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						prezzoCell.setBorderColor(BaseColor.WHITE);
						tableProdotti.addCell(prezzoCell);
					
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
//CREO SPAZIO PER L'INDIRIZZO DI SPEDIZIONE
				RecapitoBean recapito = new RecapitoBean();
				
				Paragraph titoloSpedizione = new Paragraph();
				titoloSpedizione.setFont(new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.GRAY));	
				titoloSpedizione.setAlignment(Element.ALIGN_LEFT);
				
				titoloSpedizione.add("INDIRIZZO DI SPEDIZIONE:");
				
				Paragraph spedizione = new Paragraph();
				spedizione.setFont(new Font(Font.FontFamily.HELVETICA, 10));	
				spedizione.setAlignment(Element.ALIGN_LEFT);
				
				recapito = ordine.getRecapito();
				
				spedizione.add(recapito.getCitta() + ", ");
				
				int cap = recapito.getCap();
				String stringaCap = String.valueOf(cap);

				spedizione.add(stringaCap + "\n");
				
				spedizione.add(recapito.getVia_Piazza() + " - N° ");
				
				int nCivico = recapito.getN_Civico();
				String stringaCivico = String.valueOf(nCivico);
				
				spedizione.add(stringaCivico + "\n");
				
				
//CREAZIONE TABELLA DEL TOTALE
				Paragraph titoloTotale = new Paragraph();
				titoloTotale.setFont(new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED));	
				titoloTotale.setAlignment(Element.ALIGN_RIGHT);

				titoloTotale.add("TOTALE SPESO:");
				
				Paragraph totale = new Paragraph();
				totale.setFont(new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));	
				totale.setAlignment(Element.ALIGN_RIGHT);

				double spesaTotale = ordine.getTotale();
				String stringaTotale = String.format("%.2f", spesaTotale);
				
				totale.add(stringaTotale + " €");
						

				
			document.add(tableTitle);	//gestisce il titolo della fattura
			
			document.add(emptyLine);
			document.add(separator);
			
			document.add(emptyLine);
			document.add(tableDati);	//tabella dati dell'utente
			document.add(emptyLine);
			document.add(separator);
			
			document.add(emptyLine);
			document.add(titoloProdotti);	//
			document.add(emptyLine);
			
			document.add(tableProdotti);
			document.add(emptyLine);
			document.add(separator);
			
			document.add(emptyLine);
			document.add(titoloSpedizione);
			document.add(emptyLine);
			document.add(spedizione);
			
			document.add(emptyLine);
			document.add(titoloTotale);
			document.add(emptyLine);
			document.add(totale);
			
			document.close();
        
        } catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
  	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
