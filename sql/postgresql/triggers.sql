--FOLDER
--DROP TRIGGER deleted_trigger_folder ON folder;
CREATE TRIGGER deleted_trigger_folder
AFTER DELETE ON folder
    FOR EACH ROW EXECUTE PROCEDURE move_deleted_folder();

--DROP TRIGGER audit_cd_trigger_folder ON folder;
CREATE TRIGGER audit_cd_trigger_folder
BEFORE INSERT ON folder
    FOR EACH ROW EXECUTE PROCEDURE audit_cd_function();

--DROP TRIGGER audit_md_trigger_folder ON folder;
CREATE TRIGGER audit_md_trigger_folder
BEFORE UPDATE ON folder
    FOR EACH ROW EXECUTE PROCEDURE audit_md_function();

--MESSAGE

--DROP TRIGGER deleted_trigger_message ON message;
CREATE TRIGGER deleted_trigger_message
AFTER DELETE ON message
    FOR EACH ROW EXECUTE PROCEDURE move_deleted_message();

--DROP TRIGGER audit_cd_trigger_message ON message;
CREATE TRIGGER audit_cd_trigger_message
BEFORE INSERT ON message
    FOR EACH ROW EXECUTE PROCEDURE audit_cd_function();

--DROP TRIGGER audit_md_trigger_message ON message;
CREATE TRIGGER audit_md_trigger_message
BEFORE UPDATE ON message
    FOR EACH ROW EXECUTE PROCEDURE audit_md_function();

--MESSAGE_RECEIVER
--DROP TRIGGER deleted_trigger_message_receiver ON message_receiver;
CREATE TRIGGER deleted_trigger_message_receiver
AFTER DELETE ON message_receiver
    FOR EACH ROW EXECUTE PROCEDURE move_deleted_message_receiver();

--DROP TRIGGER audit_cd_trigger_message_receiver ON message_receiver;
CREATE TRIGGER audit_cd_trigger_message_receiver
BEFORE INSERT ON message_receiver
    FOR EACH ROW EXECUTE PROCEDURE audit_cd_function();

--DROP TRIGGER audit_md_trigger_message_receiver ON message_receiver;
CREATE TRIGGER audit_md_trigger_message_receiver
BEFORE UPDATE ON message_receiver
    FOR EACH ROW EXECUTE PROCEDURE audit_md_function();

--USER_CONTACTS
--DROP TRIGGER deleted_trigger_user_contacts ON user_contacts;
CREATE TRIGGER deleted_trigger_user_contacts
AFTER DELETE ON user_contacts
    FOR EACH ROW EXECUTE PROCEDURE move_deleted_user_contacts();

--DROP TRIGGER audit_cd_trigger_user_contacts ON user_contacts;
CREATE TRIGGER audit_cd_trigger_user_contacts
BEFORE INSERT ON user_contacts
    FOR EACH ROW EXECUTE PROCEDURE audit_cd_function();

--DROP TRIGGER audit_md_trigger_user_contacts ON user_contacts;
CREATE TRIGGER audit_md_trigger_user_contacts
BEFORE UPDATE ON user_contacts
    FOR EACH ROW EXECUTE PROCEDURE audit_md_function();

--USER_MESSAGE_FOLDER
--DROP TRIGGER deleted_trigger_user_message_folder ON user_message_folder;
CREATE TRIGGER deleted_trigger_user_message_folder
AFTER DELETE ON user_message_folder
    FOR EACH ROW EXECUTE PROCEDURE move_deleted_user_message_folder();


-- DROP TRIGGER deleted_trigger_folder ON folder;
-- DROP TRIGGER audit_cd_trigger_folder ON folder;
-- DROP TRIGGER audit_md_trigger_folder ON folder;
-- DROP TRIGGER audit_cd_trigger_message ON message;
-- DROP TRIGGER audit_md_trigger_message ON message;
-- DROP TRIGGER deleted_trigger_message_receiver ON message_receiver;
-- DROP TRIGGER audit_cd_trigger_message_receiver ON message_receiver;
-- DROP TRIGGER audit_md_trigger_message_receiver ON message_receiver;
-- DROP TRIGGER deleted_trigger_user_contacts ON user_contacts;
-- DROP TRIGGER audit_cd_trigger_user_contacts ON user_contacts;
-- DROP TRIGGER audit_md_trigger_user_contacts ON user_contacts;
-- DROP TRIGGER deleted_trigger_user_message_folder ON user_message_folder;
-- DROP TRIGGER audit_cd_trigger_user_message_folder ON user_message_folder;
-- DROP TRIGGER audit_md_trigger_user_message_folder ON user_message_folder;