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
  LANGUAGE plpgsql STABLE
  COST 100;