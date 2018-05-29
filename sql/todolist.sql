CREATE TABLE todolist(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	detail TEXT,
	priority INT NOT NULL,
	limit_day DATE NOT NULL
);

INSERT INTO todolist 
VALUES (1,'買い物', 'あ', 3, '2018/05/01');
INSERT INTO todolist 
VALUES (2,'勉強', '', 2, '2018/05/02');
INSERT INTO todolist 
VALUES (3,'食事', '南青山のおいしいフレンチ', 1, '2018/05/03');
INSERT INTO todolist(title, priority, limit_day)
VALUES ('勉強', 2, '2018/05/04');
