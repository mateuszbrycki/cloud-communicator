package com.cloud.communicator.module.message;

import com.cloud.communicator.module.user.User;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Service("messageReceiver")
@Entity
@Table(name="message_receiver")
public class MessageReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name="fk_message_id")
    private Integer messageId;

    @NotNull
    @Column(name="fk_user_id")
    private Integer receiverId;

    @NotNull
    @Column(name="is_read")
    private Boolean isRead;

    @NotNull
    @Column(name="read_date")
    private Date readDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getReceiver() {
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
}
