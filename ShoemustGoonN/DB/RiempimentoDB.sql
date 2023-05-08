use shoemustgoon;

INSERT INTO Categoria (ID_Categoria, Nome) VALUES
('UOMO', 'Uomo'),
('DONNA', 'Donna'),
('UNISEX', 'Unisex');

INSERT INTO Prodotto (ID_Prodotto, Marca, Colore, Modello, Prezzo, Quantita, Disponibilita, Descrizione, Categoria) VALUES
('PRO001', 'Nike', 'Bianco', 'Air Force 1', 99.99, 100, true, 'ciao', 'UNISEX'),
('PRO002', 'Adidas', 'Nero', 'Superstar', 89.99,  70, true, 'ciao', 'UOMO'),
('PRO003', 'Vans', 'Rosso', 'Old Skool', 69.99,  0, false, 'ciao', 'UNISEX'),
('PRO004', 'Converse', 'Blu', 'Chuck Taylor All Star', 79.99,  115, true, 'ciao', 'UNISEX'),
('PRO005', 'Puma', 'Verde', 'Suede', 59.99,  2, true, 'ciao', 'DONNA');

INSERT INTO Utente(ID_Utente, Nome, Cognome, DataNascita, CF, Email, Password, Tipo) VALUES 
('U00', 'Salvatore', 'Alberti', '2003-02-04', 'ALB754DTF853DTV7', 's.alberti1@studenti.unisa.it', 'Gianmarco99.', 'admin'),
('U01', 'Mario', 'Rossi', '2000-10-24', 'MRR537YTD365DG6', 'm.rossi@studenti.unisa.it', '12345678', 'utente');
 
