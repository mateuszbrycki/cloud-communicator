CREATE UNIQUE INDEX user_contacts_user_person_idx
 ON user_contacts (fk_user_id, fk_person_in_book_id);

 CREATE INDEX user_contacts_user_idx
  ON user_contacts (fk_user_id);

CREATE UNIQUE INDEX
 ON folder (fk_owner_id, is_default_user_folder)
  WHERE is_default_user_folder = TRUE;

CREATE INDEX folder_id_owner_default_idx
  ON folder (folder_id, fk_owner_id, is_default_user_folder);

CREATE INDEX folder_owner_idx
  ON folder (fk_owner_id);

CREATE INDEX folder_owner_default_idx
  ON folder (fk_owner_id, is_default_user_folder);

CREATE INDEX folder_id_owner_idx
  ON folder (folder_id, fk_owner_id);

CREATE INDEX message_receiver_message_user_idx
  ON message_receiver (fk_message_id, fk_user_id);

CREATE INDEX message_receiver_message_idx
  ON message_receiver (fk_message_id);

CREATE INDEX message_receiver_user_idx
  ON message_receiver (fk_user_id);

CREATE INDEX user_message_folder_message_user_idx
  ON user_message_folder (fk_message_id, fk_user_id);

CREATE INDEX user_message_user_folder_idx
  ON user_message_folder (fk_user_id, fk_folder_id);

CREATE INDEX user_message_folder_message_idx
  ON user_message_folder (fk_message_id);

CREATE INDEX user_message_folder_folder_idx
  ON user_message_folder (fk_folder_id);

