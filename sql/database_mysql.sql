--TABLES

CREATE TABLE user_role (
	role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(255) NOT NULL
);

CREATE TABLE user_account (
	user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	fk_role_id INTEGER NOT NULL,
	mail VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	is_active BOOLEAN DEFAULT TRUE,
	audit_cd TIMESTAMP NOT NULL,
	audit_md DATE DEFAULT NULL,
	FOREIGN KEY (fk_role_id)
		REFERENCES user_role (role_id)
		ON UPDATE CASCADE
);
CREATE TABLE message (
	message_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	fk_author_id INTEGER NOT NULL,
	topic TEXT NOT NULL,
	text TEXT NOT NULL,
	audit_cd TIMESTAMP NOT NULL,
	audit_md DATE DEFAULT NULL,
	FOREIGN KEY (fk_author_id)
		REFERENCES user_account(user_id)
		ON UPDATE CASCADE
);
CREATE TABLE message_receiver (
	fk_message_id INTEGER,
	fk_user_id INTEGER,
	is_read boolean NOT NULL DEFAULT false,
	read_date DATE DEFAULT NULL,
	audit_cd TIMESTAMP NOT NULL,
	autid_md DATE DEFAULT NULL,
	PRIMARY KEY(fk_message_id, fk_user_id),
	FOREIGN KEY (fk_message_id)
		REFERENCES message (message_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (fk_user_id)
		REFERENCES user_account (user_id)
		ON UPDATE CASCADE
);
CREATE TABLE folder (
	folder_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	label_color INTEGER,
	owner_id INTEGER NOT NULL,
	audit_cd TIMESTAMP NOT NULL,
	audit_md DATE DEFAULT NULL
);
CREATE TABLE user_message_folder (
	fk_message_id INTEGER,
	fk_user_id INTEGER,
	fk_folder_id INTEGER,
	PRIMARY KEY(fk_message_id, fk_user_id, fk_folder_id),
	FOREIGN KEY (fk_message_id)
		REFERENCES message (message_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (fk_user_id)
		REFERENCES user_account (user_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (fk_folder_id)
		REFERENCES folder (folder_id)
		ON UPDATE CASCADE
);

CREATE TABLE contact_book (
	contact_book_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	fk_owner_id INTEGER,
	description VARCHAR(255),
	audit_cd TIMESTAMP,
	audit_md DATE DEFAULT NULL,
	FOREIGN KEY (fk_owner_id)
		REFERENCES user_account (user_id)
		ON UPDATE CASCADE
);

CREATE TABLE user_contacts (
	fk_user_id INTEGER,
	fk_contact_book_id INTEGER,
	fk_person_in_book_id INTEGER,
	PRIMARY KEY(fk_user_id, fk_contact_book_id, fk_person_in_book_id),
	FOREIGN KEY (fk_user_id)
		REFERENCES user_account (user_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (fk_contact_book_id)
		REFERENCES contact_book (contact_book_id)
		ON UPDATE CASCADE,
	FOREIGN KEY (fk_person_in_book_id)
		REFERENCES user_account (user_id)
		ON UPDATE CASCADE

);

--DATA
INSERT INTO `user_role` (`id`, `role_id`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');

--DROP TABLE user_contacts;
--DROP TABLE contact_book;
--DROP TABLE user_message_folder;
--DROP TABLE folder;
--DROP TABLE message_receiver;
--DROP TABLE message;
--DROP TABLE user_account;
--DROP TABLE user_role;


