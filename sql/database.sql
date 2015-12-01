CREATE TABLE user_role (
	role_id INTEGER PRIMARY KEY,
	role VARCHAR(255) NOT NULL
);

CREATE TABLE user_account (
	user_id INTEGER PRIMARY KEY,
	username VARCHAR NOT NULL,
	fk_role_id INTEGER NOT NULL REFERENCES user_role (role_id) ON UPDATE CASCADE,
	mail VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	is_active BOOLEAN DEFAULT TRUE,
	audit_cd TIMESTAMP NOT NULL,
	audit_md TIMESTAMP
);
CREATE TABLE message (
	message_id INTEGER PRIMARY KEY,
	topic TEXT NOT NULL,
	text TEXT NOT NULL,
	audit_cd TIMESTAMP NOT NULL,
	audit_md TIMESTAMP
);
CREATE TABLE message_receiver (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE,
	fk_user_id INTEGER REFERENCES user_account (user_id) ON UPDATE CASCADE,
	is_read boolean NOT NULL,
	read_date TIMESTAMP,
	audit_cd TIMESTAMP NOT NULL,
	autid_md TIMESTAMP NOT NULL,
	PRIMARY KEY(fk_message_id, fk_user_id)
);
CREATE TABLE folder (
	folder_id INTEGER PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	label_color INTEGER,
	owner_id INTEGER NOT NULL,
	audit_cd TIMESTAMP NOT NULL,
	audit_md TIMESTAMP
);
CREATE TABLE user_message_folder (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE,
	fk_user_id INTEGER REFERENCES user_account (user_id) ON UPDATE CASCADE,
	fk_folder_id INTEGER REFERENCES folder (folder_id) ON UPDATE CASCADE,
	PRIMARY KEY(fk_message_id, fk_user_id, fk_folder_id)
);

CREATE TABLE contact_book (
	contact_book_id INTEGER PRIMARY KEY,
	fk_owner_id INTEGER REFERENCES user_account (user_id) ON UPDATE CASCADE,
	description VARCHAR,
	audit_cd TIMESTAMP,
	audit_md TIMESTAMP
);

CREATE TABLE user_contacts (
	fk_user_id INTEGER REFERENCES user_account (user_id)  ON UPDATE CASCADE,
	fk_contact_book_id INTEGER REFERENCES contact_book (contact_book_id) ON UPDATE CASCADE,
	fk_person_in_book_id INTEGER REFERENCES user_account (user_id) ON UPDATE CASCADE,
	PRIMARY KEY(fk_user_id, fk_contact_book_id, fk_person_in_book_id)
);


