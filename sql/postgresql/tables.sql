CREATE TABLE message (
	message_id SERIAL PRIMARY KEY,
	fk_author_id INTEGER NOT NULL,
	topic TEXT NOT NULL,
	text TEXT NOT NULL,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
);
CREATE TABLE message_receiver (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE ON DELETE CASCADE,
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
	label_color TEXT,
	fk_owner_id INTEGER NOT NULL,
	is_default_user_folder boolean DEFAULT FALSE,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
);
CREATE TABLE user_message_folder (
	fk_message_id INTEGER REFERENCES message (message_id) ON UPDATE CASCADE ON DELETE CASCADE,
	fk_user_id INTEGER,
	fk_folder_id INTEGER REFERENCES folder (folder_id) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(fk_message_id, fk_user_id, fk_folder_id)
);

CREATE TABLE user_contacts (
	fk_user_id INTEGER,
	fk_person_in_book_id INTEGER,
	audit_cd TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	audit_md TIMESTAMP
	PRIMARY KEY(fk_user_id, fk_person_in_book_id),

);

--archive tables
CREATE TABLE folder_archive AS SELECT *, NOW() as action_time FROM folder;
CREATE TABLE message_receiver_archive AS SELECT *, NOW() as action_time  FROM message_receiver;
CREATE TABLE user_contacts_archive AS SELECT *, NOW() as action_time  FROM user_contacts;
CREATE TABLE user_message_folder_archive AS SELECT *, NOW() as action_time  FROM user_message_folder;
CREATE TABLE message_archive AS SELECT *, NOW() as action_time  FROM message;

--DROP TABLE user_contacts;
--DROP TABLE contact_book;
--DROP TABLE user_message_folder;
--DROP TABLE folder;
--DROP TABLE message_receiver;
--DROP TABLE message;
--DROP TABLE folder_archive;
--DROP TABLE message_receiver_archive;
--DROP TABLE user_contacts_archive;
--DROP TABLE user_message_folder_archive;
--DROP TABLE message_archive;