package com.example.rachs.tutorapp_mobapde;

public class User {
    private String name, id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
}
