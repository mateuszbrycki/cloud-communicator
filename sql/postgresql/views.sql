CREATE VIEW folder_view AS
    SELECT f.*, get_user_folder_unread_messages(f.fk_owner_id, f.folder_id) AS unread_message
    FROM folder f;