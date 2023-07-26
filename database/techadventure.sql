BEGIN TRANSACTION;

DROP TABLE IF EXISTS techadventure_user;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE techadventure_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_techadventure_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	points decimal(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tech-adventure_user FOREIGN KEY (user_id) REFERENCES techadventure_user (user_id)

);

COMMIT;
