package com.cloud.communicator.module.message;

import com.cloud.communicator.module.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private List<MessageReceiver> receivers;

    @NotNull
    @Column(name="topic")
    private String topic;

    @NotNull
    @Column(name="text")
    private String text;

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

    @Override
    public String toString() {
        return "Id: " + this.getId() + ", author: " + this.getAuthor() + ", topic: " + this.getTopic() + ", text: " + this.getText();
    }
}
