CREATE TABLE message (
	message_id SERIAL PRIMARY KEY,
	fk_author_id INTEGER NOT NULL,
	topic TEXT NOT NULL,
	text TEXT NOT NULL,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
);
CREATE TABLE message_receiver (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE,
	fk_user_id INTEGER,
	is_read boolean NOT NULL,
	read_date TIMESTAMP,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	autid_md TIMESTAMP,
	PRIMARY KEY(fk_message_id, fk_user_id)
);
CREATE TABLE folder (
	folder_id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	label_color INTEGER,
	owner_id INTEGER NOT NULL,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
);
CREATE TABLE user_message_folder (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE,
	fk_user_id INTEGER,
	fk_folder_id INTEGER REFERENCES folder (folder_id) ON UPDATE CASCADE,
	PRIMARY KEY(fk_message_id, fk_user_id, fk_folder_id)
);

CREATE TABLE contact_book (
	contact_book_id SERIAL PRIMARY KEY,
	fk_owner_id INTEGER,
	description VARCHAR,
	audit_cd TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
);

CREATE TABLE user_contacts (
	fk_user_id INTEGER,
	fk_contact_book_id INTEGER REFERENCES contact_book (contact_book_id) ON UPDATE CASCADE,
	fk_person_in_book_id INTEGER,
	PRIMARY KEY(fk_user_id, fk_contact_book_id, fk_person_in_book_id)
);

INSERT INTO message (fk_author_id, topic, text, audit_cd, audit_md) VALUES
	(2, 'New message.', 'First new message.', '2015-12-04 20:25:25', NULL),
	(2, 'Next one new message.', 'This is second message. Lets check if dump will be saved.', '2015-12-08 03:29:45', NULL);

INSERT INTO message_receiver (fk_message_id, fk_user_id, is_read, read_date, audit_cd, autid_md) VALUES
	(1, 1, true, NULL, '2015-12-10 04:56:35', NULL),
	(2, 1, true, NULL, '2015-12-08 05:31:35', NULL),
	(2, 3, true, NULL, '2015-12-04 16:54:46', NULL);

--DROP TABLE user_contacts;
--DROP TABLE contact_book;
--DROP TABLE user_message_folder;
--DROP TABLE folder;
--DROP TABLE message_receiver;
--DROP TABLE message;