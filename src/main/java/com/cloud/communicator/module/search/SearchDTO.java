package com.cloud.communicator.module.search;


public class SearchDTO {

    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return "SearchDTO: {phrase: " + this.getPhrase() + "}";
    }

}
