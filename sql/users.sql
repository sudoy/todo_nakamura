CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL
);

INSERT INTO users 
VALUES (1,'田中', 'tanaka@gmail.com', '123456');
INSERT INTO users (name, email, password)
VALUES ('佐藤', 'sato@gmail.com', 'abcde');
INSERT INTO users (name, email, password)
VALUES ('吉田', 'yosi-da@gmail.com', 'qawsedrft');
INSERT INTO users (name, email, password)
VALUES ('小林', 'shinrin@gmail.com', 'w3e455rt6y');
INSERT INTO users (name, email, password)
VALUES ('水戸', '004mito@gmail.com', ':;lkoppo');


