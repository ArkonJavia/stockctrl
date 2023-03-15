--script banco multivision
CREATE SEQUENCE item_id_seq;
CREATE TABLE item (
  id INTEGER DEFAULT nextval('item_id_seq') PRIMARY KEY,
  name TEXT
);

CREATE SEQUENCE stock_movement_id_seq;
CREATE TABLE stockmovement (
  id INTEGER DEFAULT nextval('stock_movement_id_seq') PRIMARY KEY,
  creationDate DATE,
  item_id INTEGER REFERENCES Item(id),
  quantity INTEGER
);

CREATE SEQUENCE user_id_seq;
CREATE TABLE "user" (
  id INTEGER DEFAULT nextval('user_id_seq') PRIMARY KEY,
  name TEXT,
  email TEXT
);


CREATE SEQUENCE order_id_seq;
CREATE TABLE "order" (
  id INTEGER DEFAULT nextval('order_id_seq') PRIMARY KEY,
  creationDate DATE,
  item_id INTEGER REFERENCES Item(id),
  quantity INTEGER,
  user_id INTEGER REFERENCES "user"(id)
);

