INSERT INTO LEILAO (descricao, valor_minimo, status) VALUES ('Jarro de Porcelana', 250, 0);
INSERT INTO LEILAO (descricao, valor_minimo, status) VALUES ('Kit Talheres', 350, 0);
INSERT INTO LEILAO (descricao, valor_minimo, status) VALUES ('Monalisa', 2500000000, 0);

INSERT INTO CONCORRENTE (cpf, nome) VALUES ('123.456.789-12', 'Tunico Tinoco');
INSERT INTO CONCORRENTE (cpf, nome) VALUES ('219.876.543-21', 'Tinoco Tunico');
INSERT INTO CONCORRENTE (cpf, nome) VALUES ('111.111.111-11', 'Rebimboca da Parafuseta');

INSERT INTO LANCE (valor, Concorrente_Id, Leilao_Id) VALUES (350, 1, 1);
INSERT INTO LANCE (valor, Concorrente_Id, Leilao_Id) VALUES (450, 2, 2);
INSERT INTO LANCE (valor, Concorrente_Id, Leilao_Id) VALUES (550, 3, 3);