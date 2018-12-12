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

    public void addNewUser(String name, String email, DatabaseReference userRef){
        String id = userRef.push().getKey();
        User user = new User(name, id, email);
        userRef.child(id).setValue(user);

    }

    public void addNewCodeSample(String code, String type, String title, String userId, DatabaseReference codeRef, String username){
        String id = codeRef.push().getKey();
        CodeSample cs = new CodeSample(code, type, title, id, username, userId);

        codeRef.child(userId).child(id).setValue(cs);
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
                CodeSample code = dataSnapshot1.getValue(CodeSample.class);
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

    public void getSpecificSampleData(String userId, String codeId, DataSnapshot sampleSnapshot){
        codes.clear();
        DataSnapshot dataSnapshot = sampleSnapshot.child(userId).child(codeId);
        CodeSample code = dataSnapshot.getValue(CodeSample.class);
        codes.add(code);
    }

    public void updateUserName(User user, String name, DatabaseReference userRef){
        user.setName(name);
        userRef.child(user.getId()).setValue(user);
    }

    public void updateUserEmail(User user, String email, DatabaseReference userRef){
        user.setEmail(email);
        userRef.child(user.getId()).setValue(user);
    }

    public void updateCodeSample(CodeSample cs, String code, DatabaseReference codeRef){
        cs.setCodeSample(code);
        codeRef.child(cs.getOwner()).setValue(cs);
    }

    public void updateCodeType(CodeSample cs, String type, DatabaseReference codeRef){
        cs.setCodeSample(type);
        codeRef.child(cs.getOwner()).setValue(cs);
    }

    public void updateCodeTitle(CodeSample cs, String title, DatabaseReference codeRef){
        cs.setTitle(title);
        codeRef.child(cs.getOwner()).setValue(cs);
    }


    public void deleteCode(String userId, String codeId, DatabaseReference codeRef){
        codeRef.child(userId).child(codeId).removeValue();
    }


}
