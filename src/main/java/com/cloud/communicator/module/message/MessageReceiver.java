package com.cloud.communicator.module.message;

import com.cloud.communicator.module.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="message_receiver")
public class MessageReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_message_id")
    private Message message;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_user_id")
    private User receiver;

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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
