package com.example.rachs.tutorapp_mobapde;

public class CodeSample {
    private String codeSample, type, title, id, username, owner;

    public CodeSample() {

    }

    public CodeSample(String codeSample, String type, String title, String id, String username, String owner) {
        this.codeSample = codeSample;
        this.owner = owner;
        this.type = type;
        this.title = title;
        this.id = id;
        this.username = username;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCodeSample(String codeSample) {
        this.codeSample = codeSample;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCodeSample() {
        return codeSample;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

}
