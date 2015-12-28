package com.cloud.communicator.module.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="user_message_folder")
public class UserMessageFolder implements Serializable {

    @Id
    @NotNull
    @Column(name="fk_message_id")
    private Integer messageId;

    @Id
    @NotNull
    @Column(name="fk_user_id")
    private Integer userId;

    @Id
    @NotNull
    @Column(name="fk_folder_id")
    private Integer folderId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }
}
