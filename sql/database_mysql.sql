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
	audit_md TIMESTAMP,
	FOREIGN KEY (fk_role_id)
		REFERENCES user_role (role_id)
		ON UPDATE CASCADE
);
CREATE TABLE message (
	message_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	topic TEXT NOT NULL,
	text TEXT NOT NULL,
	audit_cd TIMESTAMP NOT NULL,
	audit_md TIMESTAMP
);
CREATE TABLE message_receiver (
	fk_message_id INTEGER,
	fk_user_id INTEGER,
	is_read boolean NOT NULL,
	read_date TIMESTAMP,
	audit_cd TIMESTAMP NOT NULL,
	autid_md TIMESTAMP NOT NULL,
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
	audit_md TIMESTAMP
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
	audit_md TIMESTAMP,
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
INSERT INTO `user_role` (`id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');



