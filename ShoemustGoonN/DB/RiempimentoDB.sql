use shoemustgoon;


INSERT INTO Categoria (ID_Categoria, Nome) VALUES
('UOMO', 'Uomo'),
('DONNA', 'Donna'),
('UNISEX', 'Unisex');

INSERT INTO Immagine(NomeImmagine, Path) values
('foto.jpg', 'image/foto.jpg'),
('vans.jpeg', 'image/vans.jpeg'),
('puma.jpeg', 'image/puma.jpeg'),
('superstar.jpeg', 'image/superstar.jpeg'),
('converse.jpeg', 'image/converse.jpeg');
 
INSERT INTO Prodotto (ID_Prodotto, Marca, Colore, Modello, Prezzo, Quantita, Disponibilita, Descrizione, Categoria, Immagine) VALUES
('PRO001', 'Nike', 'Bianco', 'Air Force 1', 99.99, 100, true, 'ciao', 'UNISEX', 'foto.jpg'),
('PRO002', 'Adidas', 'Nero', 'Superstar', 89.99,  70, true, 'ciao', 'UOMO', 'superstar.jpeg'),
('PRO003', 'Vans', 'Rosso', 'Old Skool', 69.99,  0, false, 'ciao', 'UNISEX', 'vans.jpeg' ),
('PRO004', 'Converse', 'Blu', 'Chuck Taylor All Star', 79.99,  115, true, 'ciao', 'UNISEX', 'converse.jpeg' ),
('PRO005', 'Puma', 'Verde', 'Suede', 59.99,  2, true, 'ciao', 'DONNA', 'puma.jpeg');

INSERT INTO Utente(ID_Utente, Nome, Cognome, DataNascita, CF, Email, Password, Tipo) VALUES 
('U00', 'Salvatore', 'Alberti', '2003-02-04', 'ALB754DTF853DTV7', 's.alberti1@studenti.unisa.it', 'Gianmarco99.', 'admin'),
('U01', 'Mario', 'Rossi', '2000-10-24', 'MRR537YTD365DG6', 'm.rossi@studenti.unisa.it', '12345678', 'utente');
 
INSERT INTO Recapito(ID_Spedizione, CAP, Citta, Via_Piazza, N_Civico, Utente)
VALUES ('0', '84030', 'Torraca', 'Via Roma', 5, 'U01');

INSERT INTO Portafoglio(ID_Pagamento, Num_Carta, Nome_Intestatario, Scadenza, Cvv, Utente)
VALUES ('0', '5333171100891579', 'Mario Rossi', '2026-05-12', 302, 'U01');

INSERT INTO Ordine (ID_Ordine, Data_Ordine, Totale, Utente, Indirizzo, Pagamento)
VALUES ('0', '2023-05-12', 50, 'U01', '0', '0');
