package com.example.rachs.tutorapp_mobapde;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseInterface {
    private ArrayList<User> users;
    private ArrayList<CodeSample> codes;

    public FirebaseInterface() {
        users = new ArrayList<>();
        codes = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<CodeSample> getCodes() {
        return codes;
    }

    public void addNewUser(String name, DatabaseReference userRef){
        String id = userRef.push().getKey();
        User user = new User(name, id);
        userRef.child(id).setValue(user);

    }

    public void addNewCodeSample(String code, String owner,  String type, String title, String userId, DatabaseReference codeRef){
        String id = codeRef.push().getKey();
        CodeSample cs = new CodeSample(code, owner, type, title, userId);

        codeRef.child(cs.getId()).child(id).setValue(cs);
    }

    public void getUserdata(DataSnapshot usersnapShot){
        users.clear();
        for(DataSnapshot dataSnapshot : usersnapShot.getChildren()){
            User user = dataSnapshot.getValue(User.class);
            users.add(user);
        }
    }

    public void getSamplesData(DataSnapshot sampleSnapshot){
        codes.clear();
        for(DataSnapshot dataSnapshot : sampleSnapshot.getChildren()){
            String key = dataSnapshot.getKey();
            for(DataSnapshot dataSnapshot1 : sampleSnapshot.child(key).getChildren()){
                CodeSample code = dataSnapshot.getValue(CodeSample.class);
                codes.add(code);
            }
        }
    }

    public void getSelectedSamplesData(DataSnapshot sampleSnapshot, String id){
        codes.clear();
        for(DataSnapshot dataSnapshot : sampleSnapshot.child(id).getChildren()){
            CodeSample code = dataSnapshot.getValue(CodeSample.class);
            codes.add(code);
        }
    }









}
