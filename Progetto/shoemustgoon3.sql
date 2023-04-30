DROP SCHEMA shoemustgoon;
CREATE SCHEMA shoemustgoon;
use shoemustgoon;

CREATE TABLE Ordine(
	ID_Ordine VARCHAR(6) NOT NULL PRIMARY KEY,
	N_Prodotti INT NOT NULL
);


CREATE TABLE Categoria (
    ID_Categoria VARCHAR(10) NOT NULL PRIMARY KEY,
    Nome VARCHAR(10) NOT NULL
);

CREATE TABLE Prodotto(
    ID_Prodotto VARCHAR(6) NOT NULL PRIMARY KEY,
    Marca VARCHAR(30) NOT NULL,
    Colore VARCHAR(20) NOT NULL,
    Modello VARCHAR(30) NOT NULL,
    Prezzo DECIMAL(10, 2) NOT NULL,
    Nome VARCHAR(30) NOT NULL,
    Quantita INT NOT NULL,
    Disponibilita BOOLEAN NOT NULL,
    Descrizione VARCHAR(300) NULL,
    Categoria VARCHAR(10) NOT NULL,
		FOREIGN KEY(Categoria) REFERENCES Categoria(ID_Categoria)
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

CREATE TABLE Indirizzo_di_Spedizione(
    CAP NUMERIC NOT NULL,
    Città VARCHAR(20) NOT NULL,
    Via_Piazza VARCHAR(20) NOT NULL,
    N_Civico INT NOT NULL,
    Ordine VARCHAR(6) NOT NULL,
    PRIMARY KEY (CAP, Città),
    FOREIGN KEY(Ordine) REFERENCES Ordine(ID_Ordine) ON UPDATE CASCADE
);

CREATE TABLE Lista_Prodotti(
	ID_Lista VARCHAR(6) NOT NULL PRIMARY KEY,
    Is_Carrello BOOLEAN NOT NULL
);

CREATE TABLE Contiene(
	Lista VARCHAR(6) NOT NULL,
    Prodotto VARCHAR(6) NOT NULL,
	PRIMARY KEY(Lista, Prodotto),
    FOREIGN KEY (Lista) REFERENCES Lista_Prodotti(ID_Lista),
	FOREIGN KEY (Prodotto) REFERENCES Prodotto(ID_Prodotto)
);

CREATE TABLE Utente(
    ID_Utente VARCHAR(6) NOT NULL PRIMARY KEY,
    Ordine VARCHAR(10) NULL,
        FOREIGN KEY(Ordine) REFERENCES Ordine(ID_Ordine)
        ON UPDATE CASCADE,
    Lista_Prodotti VARCHAR(10),
        FOREIGN KEY(Lista_Prodotti) REFERENCES Lista_Prodotti(ID_Lista)
        ON UPDATE CASCADE,
    Email VARCHAR(50) unique,
    Password VARCHAR(20), 
    Tipo VARCHAR(15) NOT NULL
);

CREATE TABLE Pagamento(
    ID_Pagamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Data_Pagamento DATE NOT NULL,    
    Ordine VARCHAR(6) NOT NULL,
        FOREIGN KEY(Ordine) REFERENCES Ordine(ID_Ordine)
        ON UPDATE CASCADE,
    Num_Carta VARCHAR(30) NOT NULL,
    Nome_Intestatario VARCHAR(50) NOT NULL,
    Scadenza DATE NOT NULL,
    CVV VARCHAR(4) NOT NULL,
    Totale DECIMAL(10, 2) NOT NULL,
    Tipo VARCHAR(20) NOT NULL,
    Codice_Sconto VARCHAR(20) NULL
);