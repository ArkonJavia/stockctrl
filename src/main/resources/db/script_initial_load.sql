-- Carga banco

TRUNCATE TABLE "order", stockmovement, "user", item;
ALTER SEQUENCE order_id_seq RESTART WITH 1;
ALTER SEQUENCE stock_movement_id_seq RESTART WITH 1;
ALTER SEQUENCE user_id_seq RESTART WITH 1;
ALTER SEQUENCE item_id_seq RESTART WITH 1;

INSERT INTO item (name) VALUES ('Camisa');
INSERT INTO item (name) VALUES ('Calça');
INSERT INTO item (name) VALUES ('Meia');

INSERT INTO stockmovement (creationDate, item_id, quantity) VALUES ('2022-01-01', 1, 10);
INSERT INTO stockmovement (creationDate, item_id, quantity) VALUES ('2022-01-02', 1, 25);
INSERT INTO stockmovement (creationDate, item_id, quantity) VALUES ('2022-01-03', 2, 5);
INSERT INTO stockmovement (creationDate, item_id, quantity) VALUES ('2022-01-04', 3, 20);

INSERT INTO "user" (name, email) VALUES ('Ana', 'ana@example.com');
INSERT INTO "user" (name, email) VALUES ('Bruno', 'bruno@example.com');
INSERT INTO "user" (name, email) VALUES ('Carla', 'carla@example.com');

INSERT INTO "order" (creationDate, item_id, quantity, user_id) VALUES ('2022-01-01', 1, 2, 1);
INSERT INTO "order" (creationDate, item_id, quantity, user_id) VALUES ('2022-01-02', 2, 1, 2);
INSERT INTO "order" (creationDate, item_id, quantity, user_id) VALUES ('2022-01-03', 3, 3, 3);


