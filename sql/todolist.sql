CREATE TABLE todolist(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	detail TEXT NOT NULL,
	priority INT NOT NULL,
	limit_day DATE
);

INSERT INTO todolist 
VALUES (1,'買い物', '新宿に14時', 3, '2018/05/01');
INSERT INTO todolist 
VALUES (2,'勉強', '課題１～５をやる', 2, '2018/05/02');
INSERT INTO todolist(title, detail, priority, limit_day)
VALUES ('食事', '南青山のおいしいフレンチ', 1, '2018/05/03');
INSERT INTO todolist(title, detail, priority)
VALUES ('勉強（この前の復習をする）', '再復習しないとやばいかもしれない', 2);
