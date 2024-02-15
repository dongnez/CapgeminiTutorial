INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Instameme');
INSERT INTO client(name) VALUES ('Manuel');
INSERT INTO client(name) VALUES ('Pedro');
INSERT INTO client(name) VALUES ('Juan');
INSERT INTO client(name) VALUES ('Pepe');


INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (4, 3, '2024-02-13', '2024-02-20');
INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (3, 4, '2024-02-14', '2024-02-21');
INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (5, 2, '2024-02-16', '2024-02-22');
INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (1, 2, '2024-02-16', '2024-02-23');
INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (2, 3, '2024-02-20', '2024-02-24');
INSERT INTO prestamo(game_id, client_id, fecha_inicio, fecha_fin) VALUES (3, 4, '2024-02-18', '2024-02-25');
