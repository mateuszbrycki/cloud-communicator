package com.cloud.communicator.module.message;

import com.cloud.communicator.module.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_author_id")
    private User author;

    @OneToMany
    @NotNull
    @JoinColumn(name="message_id")
    @JsonIgnore
    private List<MessageReceiver> receivers;

    @NotNull
    @Column(name="topic")
    private String topic;

    @NotNull
    @Column(name="text")
    private String text;

    @NotNull
    @Column(name="is_read")
    private Boolean isRead;

    @NotNull
    @Column(name="audit_cd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="d.MM")
    private Date sendDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MessageReceiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<MessageReceiver> receivers) {
        this.receivers = receivers;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "Message: {Id: " + this.getId() +
                ", author: " + this.getAuthor() +
                ", topic: " + this.getTopic() +
                ", text: " + this.getText() +
                ", is read: " + this.getIsRead() +
                ", send date: " + this.getSendDate() +
                ", receivers {" + this.getReceivers() + "}";
    }
}
