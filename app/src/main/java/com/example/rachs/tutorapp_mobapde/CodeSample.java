package com.example.rachs.tutorapp_mobapde;

public class CodeSample {
    private String codeSample, type, title, id;

    public CodeSample() {

    }

    public CodeSample(String codeSample, String type, String title, String id) {
        this.codeSample = codeSample;
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
