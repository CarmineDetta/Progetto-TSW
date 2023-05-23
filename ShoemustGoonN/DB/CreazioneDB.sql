DROP SCHEMA shoemustgoon;
CREATE SCHEMA shoemustgoon;
use shoemustgoon;


CREATE TABLE Categoria (
    ID_Categoria VARCHAR(10) NOT NULL PRIMARY KEY,
    Nome VARCHAR(10) NOT NULL
);

CREATE TABLE Immagine(
	NomeImmagine VARCHAR(45) NOT NULL PRIMARY KEY,
	Path VARCHAR(150) NOT NULL
);

CREATE TABLE Prodotto(
    ID_Prodotto VARCHAR(17) NOT NULL PRIMARY KEY,
    Marca VARCHAR(30) NOT NULL,
    Colore VARCHAR(20) NOT NULL,
    Modello VARCHAR(30) NOT NULL,
    Prezzo DECIMAL(10, 2) NOT NULL,
    Quantita INT NOT NULL,
    Disponibilita BOOLEAN NOT NULL,
    Descrizione VARCHAR(150) NOT NULL,
    Categoria VARCHAR(10) NOT NULL,
		FOREIGN KEY(Categoria) REFERENCES Categoria(ID_Categoria),
	Immagine VARCHAR(45),
		FOREIGN KEY (Immagine) REFERENCES Immagine(NomeImmagine)
			ON DELETE CASCADE
);

DELIMITER //

CREATE TRIGGER update_disponibilita
AFTER UPDATE ON Prodotto
FOR EACH ROW
BEGIN
    IF NEW.Quantita = 0 THEN
        UPDATE Prodotto SET Disponibilita = false WHERE ID_Prodotto = NEW.ID_Prodotto;
    ELSE
        UPDATE Prodotto SET Disponibilita = true WHERE ID_Prodotto = NEW.ID_Prodotto;
    END IF;
END //

DELIMITER ;

CREATE TABLE Utente(
    ID_Utente VARCHAR(6) NOT NULL PRIMARY KEY,
    Nome VARCHAR(30) NOT NULL,
    Cognome VARCHAR(30) NOT NULL,
    DataNascita DATE NOT NULL,
    CF VARCHAR(16) NOT NULL,
	Email VARCHAR(50),
    Password VARCHAR(20), 
    Tipo VARCHAR(15) NOT NULL
);

CREATE TABLE Portafoglio(
    ID_Pagamento INT NOT NULL PRIMARY KEY,
    Num_Carta VARCHAR(16) NOT NULL,
    Nome_Intestatario VARCHAR(50) NOT NULL,
    Scadenza VARCHAR(7) NOT NULL,
    CVV INT NOT NULL,
    Utente VARCHAR(6) NOT NULL,
	FOREIGN KEY(Utente) REFERENCES Utente(ID_Utente)
);

CREATE TABLE Recapito(
    ID_Spedizione INT NOT NULL PRIMARY KEY,
	CAP NUMERIC NOT NULL,
    Citta VARCHAR(50) NOT NULL,
    Via_Piazza VARCHAR(50) NOT NULL,
    N_Civico INT NOT NULL,
     Utente VARCHAR(6) NOT NULL,
    FOREIGN KEY (Utente) REFERENCES Utente(ID_Utente)
);

CREATE TABLE Ordine(
	ID_Ordine int NOT NULL PRIMARY KEY,
    Data_ordine DATE NOT NULL,
    Metodo_Pagamento VARCHAR(25) NOT NULL,
    Totale NUMERIC NOT NULL,
    Utente VARCHAR(6) NOT NULL,
    FOREIGN KEY (Utente) REFERENCES Utente(ID_Utente)
    Indirizzo int not null,
    FOREIGN KEY(Indirizzo) REFERENCES Recapito(ID_Spedizione)
);

CREATE TABLE Composizione(
	ID_Ordine int NOT NULL,
    ID_Prodotto VARCHAR(17) NOT NULL,
    Quantita int not null,
    primary key(ID_Ordine, ID_Prodotto),
	FOREIGN KEY (ID_Prodotto) REFERENCES Prodotto(ID_Prodotto),
	FOREIGN KEY (ID_Ordine) REFERENCES Ordine(ID_Ordine)	
);



