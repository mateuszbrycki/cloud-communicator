package com.cloud.communicator.module.contact;

import javax.validation.constraints.NotNull;


public class UserContactDTO {

    @NotNull
    private String contacts;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
