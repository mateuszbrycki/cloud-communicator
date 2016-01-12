--counting unread messages in folder
CREATE OR REPLACE FUNCTION get_user_folder_unread_messages(
    userId integer, folderId integer)
  RETURNS integer AS
$BODY$
DECLARE
  counter integer;
BEGIN

	SELECT COUNT(*) FROM folder f
		JOIN user_message_folder umf ON umf.fk_folder_id = f.folder_id
		JOIN message_receiver mr ON mr.fk_message_id = umf.fk_message_id
		WHERE
			mr.is_read != true
		AND
			mr.fk_user_id = userId
		AND
			f.folder_id = folderId
	INTO counter;

	RETURN counter;

END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

--audit_cd trigger
CREATE OR REPLACE FUNCTION audit_cd_function() RETURNS TRIGGER AS $BODY$
BEGIN
   NEW.audit_cd := NOW();

   RETURN NEW;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

--audit_md trigger
CREATE OR REPLACE FUNCTION audit_md_function() RETURNS TRIGGER AS $BODY$
BEGIN
   NEW.audit_md := NOW();

   RETURN NEW;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

--move_deleted_folder trigger
CREATE OR REPLACE FUNCTION move_deleted_folder() RETURNS TRIGGER AS $BODY$
BEGIN
   INSERT INTO folder_archive VALUES(
      OLD.folder_id,
      OLD.name,
      OLD.description,
      OLD.label_color,
      OLD.fk_owner_id,
      OLD.is_default_user_folder,
      OLD.audit_cd,
      OLD.audit_md,
      NOW()
   );

   RETURN OLD;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  --move_deleted_message trigger
CREATE OR REPLACE FUNCTION move_deleted_message() RETURNS TRIGGER AS $BODY$
BEGIN
   INSERT INTO message_archive VALUES(
      OLD.message_id,
      OLD.fk_author_id,
      OLD.topic,
      OLD.text,
      OLD.audit_cd,
      OLD.audit_md,
      NOW()
   );

   RETURN OLD;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;


--move_deleted_message_receiver trigger
CREATE OR REPLACE FUNCTION move_deleted_message_receiver() RETURNS TRIGGER AS $BODY$
BEGIN
   INSERT INTO message_receiver_archive VALUES(
      OLD.fk_message_id,
      OLD.fk_user_id,
      OLD.is_read,
      OLD.read_date,
      OLD.audit_cd,
      OLD.autid_md,
      NOW()
   );

   RETURN OLD;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

--move_deleted_user_contacts trigger
CREATE OR REPLACE FUNCTION move_deleted_user_contacts() RETURNS TRIGGER AS $BODY$
BEGIN
   INSERT INTO user_contacts_archive VALUES(
      OLD.fk_user_id,
      OLD.fk_person_in_book_id,
      NOW()
   );

   RETURN OLD;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

--move_deleted_user_message_folder
CREATE OR REPLACE FUNCTION move_deleted_user_message_folder() RETURNS TRIGGER AS $BODY$
BEGIN
   INSERT INTO user_message_folder_archive VALUES(
      OLD.fk_message_id,
      OLD.fk_user_id,
      OLD.fk_folder_id,
      NOW()
   );

   RETURN OLD;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;