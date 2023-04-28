INSERT INTO Ordine (ID_Ordine, N_Prodotti) VALUES
('ORD001', 2),
('ORD002', 3),
('ORD003', 1),
('ORD004', 4);

INSERT INTO Categoria (ID_Categoria, Nome) VALUES
('UOMOALFA', 'Uomo'),
('DONNAALFA', 'Donna'),
('UNISEX', 'Unisex');

INSERT INTO Prodotto (ID_Prodotto, Marca, Colore, Modello, Prezzo, Nome, Quantita, Disponibilita, Categoria) VALUES
('PRO001', 'Nike', 'Bianco', 'Air Force 1', 99.99, 'Air Force 1 Bianche', 100, true, 'UNISEX'),
('PRO002', 'Adidas', 'Nero', 'Superstar', 89.99, 'Superstar Nere', 70, true, 'UOMOALFA'),
('PRO003', 'Vans', 'Rosso', 'Old Skool', 69.99, 'Old Skool Rosse', 0, false, 'UNISEX'),
('PRO004', 'Converse', 'Blu', 'Chuck Taylor All Star', 79.99, 'Chuck Taylor All Star Blu', 115, true, 'UNISEX'),
('PRO005', 'Puma', 'Verde', 'Suede', 59.99, 'Suede Verdi', 2, true, 'DONNAALFA');

INSERT INTO Indirizzo_di_Spedizione (CAP, Città, Via_Piazza, N_Civico, Ordine) VALUES
(20121, 'Milano', 'Via Verdi', 5, 'ORD001'),
(00185, 'Roma', 'Via Roma', 10, 'ORD002'),
(40121, 'Bologna', 'Piazza Maggiore', 1, 'ORD003'),
(50123, 'Firenze', 'Via dei Calzaiuoli', 20, 'ORD004'),
(20122, 'Milano', 'Via Montenapoleone', 15, 'ORD001');

INSERT INTO Lista_Prodotti (ID_Lista, Is_Carrello) VALUES
('LST001', true),
('LST002', false),
('LST003', false),
('LST004', false);

INSERT INTO Contiene(Lista, Prodotto) VALUES
('LST001', 'PRO001'),
('LST001', 'PRO002'),
('LST002', 'PRO002');

INSERT INTO Utente (ID_Utente, Ordine, Lista_Prodotti, Email, Password, Tipo) VALUES
('USR001', 'ORD001', 'LST001', 'mario.rossi@gmail.com', 'password123', 'Guest'),
('USR002', 'ORD002', 'LST002', 'giulia.bianchi@gmail.com', 'qwerty456', 'Registrato'),
('USR003', 'ORD003', 'LST003', 'luca.verdi@gmail.com', '123456', 'Guest'),
('USR004', 'ORD004', NULL, 'anna.rossi@gmail.com', 'password789', 'Amministratore'),
('USR005', NULL, 'LST004', 'giuseppe.neri@gmail.com', 'asdfgh123', 'Guest');

INSERT INTO Pagamento (Data_Pagamento, Ordine, Num_Carta, Nome_Intestatario, Scadenza, CVV, Totale, Tipo, Codice_Sconto) VALUES 
('2022-04-01', 'ORD001', '1234567890123456', 'Mario Rossi', '04/24', '123', 199.98, 'Carta di credito', 'Sconto10'),
('2022-04-02', 'ORD002', '2345678901234567', 'Luigi Verdi', '06/23', '456', 269.97, 'PayPal', NULL),
('2022-04-03', 'ORD003', '3456789012345678', 'Giuseppe Bianchi', '09/22', '789', 69.99, 'Carta di credito', 'Sconto5'),
('2022-04-04', 'ORD004', '4567890123456789', 'Paolo Neri', '11/23', '246', 319.96, 'PayPal', NULL);
