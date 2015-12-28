--unique index on default user folder
CREATE UNIQUE INDEX
ON folder (fk_owner_id, is_default_user_folder)
 WHERE is_default_user_folder = TRUE;