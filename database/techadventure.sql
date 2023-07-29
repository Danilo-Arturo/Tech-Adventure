BEGIN TRANSACTION;

--We must drop account first because it contains a foreign key
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS techadventure_user;



CREATE TABLE techadventure_user (
	user_id serial PRIMARY KEY,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE account (
	account_id serial PRIMARY KEY,
	user_id int NOT NULL,
	points decimal(13, 2) NOT NULL,
	CONSTRAINT FK_account_tech_adventure_user FOREIGN KEY (user_id) REFERENCES techadventure_user (user_id)

);

INSERT INTO techadventure_user (username, password_hash) VALUES ('player1', '$2a$10$0W0ftjYMRwei3Lm3LDe3X.HlYsoll9oQ2yo1sypvGZQMOXWWQ/WZ6');
INSERT INTO account (user_id, points) VALUES (1, 1000.00);

COMMIT;
