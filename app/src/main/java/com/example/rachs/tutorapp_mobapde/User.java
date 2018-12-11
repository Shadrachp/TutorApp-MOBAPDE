package com.example.rachs.tutorapp_mobapde;

public class User {
    private String name, id, email;

    public User(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public User() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
