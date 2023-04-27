CREATE SCHEMA shoemustgoon;
use shoemustgoon;

CREATE TABLE Ordine(
	ID_Ordine VARCHAR(6) NOT NULL PRIMARY KEY,
	N_Prodotti INT NOT NULL
);

CREATE TABLE Prodotto(
	ID_Prodotto VARCHAR(6) NOT NULL PRIMARY KEY,
	Marca VARCHAR(30) NOT NULL,
    Colore VARCHAR(20) NOT NULL,
	Modello VARCHAR(20) NOT NULL,
    Prezzo NUMERIC(20) NOT NULL,
	Nome VARCHAR(20) NOT NULL,
    Disponibilità boolean NOT NULL
);

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
    Prodotto VARCHAR(6) NOT NULL,
		FOREIGN KEY(Prodotto) REFERENCES Prodotto(ID_Prodotto)
        ON UPDATE CASCADE,
	N_Prodotti INT NOT NULL,
    Tipo VARCHAR(20) NOT NULL
);

CREATE TABLE Utente(
	ID_Utente VARCHAR(6) NOT NULL PRIMARY KEY,
    Ordine VARCHAR(5) NOT NULL,
    	FOREIGN KEY(Ordine) REFERENCES Ordine(ID_Ordine)
        ON UPDATE CASCADE,
	Lista_Prodotti VARCHAR(6) NOT NULL,
		FOREIGN KEY(Lista_Prodotti) REFERENCES Lista_Prodotti(ID_Lista)
        ON UPDATE CASCADE,
	Email VARCHAR(30),
    Password VARCHAR(20), 
	Tipo VARCHAR(20) NOT NULL
);

CREATE TABLE Pagamento(
	Data_Pagamento DATE NOT NULL PRIMARY KEY,    
    Ordine VARCHAR(5) NOT NULL,
    	FOREIGN KEY(Ordine) REFERENCES Ordine(ID_Ordine)
        ON UPDATE CASCADE,
	Num_Carta VARCHAR(30) NOT NULL,
    Totale NUMERIC NOT NULL,
	Tipo VARCHAR(20) NOT NULL,
    Codice_Sconto VARCHAR(20) NOT NULL
);





