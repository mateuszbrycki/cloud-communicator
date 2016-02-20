package com.cloud.communicator.module.message;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

//TODO mbrycki this entity shouldn't exists or be type of User
@Entity
@Table(name="message_receiver")
public class MessageReceiver implements Serializable{

    @Id
    @NotNull
    @Column(name="fk_message_id")
    private Integer messageId;

    @Id
    @NotNull
    @Column(name="fk_user_id")
    private Integer receiverId;

    @NotNull
    @Column(name="is_read")
    private Boolean isRead;

    @Column(name="read_date")
    private Date readDate;


    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    @Override
    public String toString() {
        return "MessageReceiver {receiver: " + this.getReceiverId() +
                ", is read: " + this.getIsRead() +
                ", read date: " + this.getReadDate() + "}";
    }
}
