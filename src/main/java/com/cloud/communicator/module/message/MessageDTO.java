package com.cloud.communicator.module.message;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class MessageDTO {

    @NotNull
    @Length(min = 3)
    private String receivers;

    @NotNull
    @Length(min = 3)
    private String topic;

    @NotNull
    @Length(min = 3)
    private String text;

    public MessageDTO() {}

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
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
        return "MessageDTO: {Mail: " + this.receivers + ", topic: " + this.topic + ", text: " + this.text + "}";
    }
}
