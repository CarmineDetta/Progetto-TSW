INSERT INTO Categoria (ID_Categoria, Nome) VALUES
('UOMOALFA', 'Uomo'),
('DONNAALFA', 'Donna'),
('UNISEX', 'Unisex');

INSERT INTO Prodotto (ID_Prodotto, Marca, Colore, Modello, Prezzo, Nome, Quantita, Disponibilita, Descrizione, Categoria) VALUES
('PRO001', 'Nike', 'Bianco', 'Air Force 1', 99.99, 'Air Force 1 Bianche', 100, true, 'Stato ottimale' , 'UNISEX'),
('PRO002', 'Adidas', 'Nero', 'Superstar', 89.99, 'Superstar Nere', 70, true, 'prodotto limited','UOMOALFA'),
('PRO003', 'Vans', 'Rosso', 'Old Skool', 69.99, 'Old Skool Rosse', 0, false, 'non ottimali per estate','UNISEX'),
('PRO004', 'Converse', 'Blu', 'Chuck Taylor All Star', 79.99, 'Chuck Taylor All Star Blu', 115, true, 'poco virili', 'UNISEX'),
('PRO005', 'Puma', 'Verde', 'Suede', 59.99, 'Suede Verdi', 2, true, 'grr','DONNAALFA');

INSERT INTO Utente (ID_Utente, Email, Password, Tipo) VALUES
('USR001', 'mario.rossi@gmail.com', 'password123', 'Guest'),
('USR002', 'giulia.bianchi@gmail.com', 'qwerty456', 'Registrato'),
('USR003', 'luca.verdi@gmail.com', '123456', 'Guest'),
('USR004', 'anna.rossi@gmail.com', 'password789', 'Amministratore'),
('USR005', 'giuseppe.neri@gmail.com', 'asdfgh123', 'Guest');

INSERT INTO Pagamento (ID_Pagamento, Data_Pagamento, Num_Carta, Nome_Intestatario, Scadenza, CVV, Totale, Tipo, Codice_Sconto) VALUES 
('PG0001', '2022-04-01', '1234567890123456', 'Mario Rossi', '04/24', '123', 199.98, 'Carta di credito', 'Sconto10'),
('PG0002', '2022-04-02', '2345678901234567', 'Luigi Verdi', '06/23', '456', 269.97, 'PayPal', NULL),
('PG0003', '2022-04-03', '3456789012345678', 'Giuseppe Bianchi', '09/22', '789', 69.99, 'Carta di credito', 'Sconto5'),
('PG0004', '2022-04-04', '4567890123456789', 'Paolo Neri', '11/23', '246', 319.96, 'PayPal', NULL);

INSERT INTO Ordine (ID_Ordine, Data_Ordine, ID_Utente, ID_Pagamento) VALUES
('ORD001', '2022-04-01', 'USR001', 'PG0001'),
('ORD002', '2022-04-02', 'USR002', 'PG0002'),
('ORD003', '2022-04-03','USR003', 'PG0003'),
('ORD004', '2022-04-04', 'USR004', 'PG0004');

INSERT INTO Composizione(ID_Ordine, ID_Prodotto) VALUES
('ORD001','PRO001'),
('ORD002','PRO002'),
('ORD003','PRO003');

INSERT INTO Indirizzo_di_Spedizione (CAP, Città, Via_Piazza, N_Civico, Ordine) VALUES
(20121, 'Milano', 'Via Verdi', 5, 'ORD001'),
(00185, 'Roma', 'Via Roma', 10, 'ORD002'),
(40121, 'Bologna', 'Piazza Maggiore', 1, 'ORD003'),
(50123, 'Firenze', 'Via dei Calzaiuoli', 20, 'ORD004'),
(20122, 'Milano', 'Via Montenapoleone', 15, 'ORD001');


