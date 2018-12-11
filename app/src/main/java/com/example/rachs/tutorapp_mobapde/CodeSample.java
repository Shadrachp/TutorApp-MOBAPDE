package com.example.rachs.tutorapp_mobapde;

public class CodeSample {
    private String codeSample, owner, type, title, id;

    public CodeSample() {

    }

    public CodeSample(String codeSample, String owner, String type, String title, String id) {
        this.codeSample = codeSample;
        this.owner = owner;
        this.type = type;
        this.title = title;
        this.id = id;
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

    public void setOwner(String owner){
        this.owner = owner;
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

    public String getOwner(){
        return owner;
    }
}
