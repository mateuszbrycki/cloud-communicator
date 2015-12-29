package com.cloud.communicator;

/**
 * Select2 requirement
 */
public class Select2Response {
    private String id;
    private String text;

    public Select2Response(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
