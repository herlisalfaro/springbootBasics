/* Populate tables*/
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Frank', 'Hustler', 'hustler@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Brian', 'Symphony', 'symphony@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Roger', 'Music', 'hustler@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Brittany', 'Symphony', 'symphony@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Cole', 'Sea', 'dfgdfgddf@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Esther', 'House', 'rttertertg@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Howard', 'Stone', 'dfggggdfrt@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Nate', 'Falling', 'srtyrtrt@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Nick', 'Brie', 'rtyrt@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Murphy', 'Splance', 'srtyrty@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Nathen', 'Solance', 'fghfghfghf@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Caroline', 'Going', 'sfhfghfgh@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Liz', 'Blast', 'hfgjhfgh@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Amy', 'Cose', 'sympfghfg@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Sugar', 'Blur', 'fghgfhfg@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Pete', 'Pace', 'sdfgdg@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Lance', 'Escape', 'dfgdfgd@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Kevin', 'Rice', 'sfdfgfd@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Chip', 'Love', 'hussdddsd@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Holly', 'Lock', 'sdddd@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Faust', 'Kind', 'wwwwww@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Stephanie', 'Soft', 'sssss@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Jim', 'Blow', 'sssss@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Tom', 'Sky', 'saaaaa@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Matt', 'Try', 'sssss@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Moore', 'Mind', 'aaaa@email.com', '2019-08-21','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Taylor', 'Keen', 'aaaa@email.com', '2019-08-19','');
INSERT INTO clients (NAME, SURNAME, EMAIL, CREATED_AT, picture) VALUES('Lip', 'Low', 'aaaa@email.com', '2019-08-21','');


/*Populate table 'products'*/
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Magic Writing Pen',40,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Blowing Stories Builder',100,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Inspiring Papersheets 100',30,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Inspiring Papersheets 200',50,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Bohemian Bag',75,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Dont like it eraser',5,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Magic Writing Pencil',10,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Magic Writing Pencil Pack 10',90,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Imagining Worlds',35,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('How to be Human',20,NOW());
INSERT INTO products (NAME, PRICE, CREATED_AT) VALUES('Destroying Characters',40,NOW());

/*Creating some bills*/
INSERT INTO bills (DESCRIPTION, OBSERVATIONS, CLIENT_BEARER_ID,CREATED_AT) VALUES('Writers Starter Kit Bill', null, 1, NOW());
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(1,1,1);
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(2,5,1);
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(1,7,1);
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(1,4,1);
INSERT INTO bills (DESCRIPTION, OBSERVATIONS, CLIENT_BEARER_ID,CREATED_AT) VALUES('Vintage Writers Bill', 'Black colour, please!', 1, NOW());
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(1,5,2);
INSERT INTO bill_items (AMOUNT, PRODUCT_ID, BILL_ID) VALUES(2,1,2);
