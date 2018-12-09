package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogo;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference userRef, codeRef;
    private FirebaseInterface fbInterface;

    private ArrayList<User> users, userdb;
    private ArrayList<CodeSample> codeSamples, samplesDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        codeRef = database.getReference("codesample");

        fbInterface = new FirebaseInterface();
        codeSamples = new ArrayList<>();
        users = new ArrayList<>();
        userdb = new ArrayList<>();
        samplesDb = new ArrayList<>();

        tvLogo = findViewById(R.id.tvLogo);
        tvLogo.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Billabong.ttf"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                samplesDb.clear();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void triggerRegister(View v){
         Intent intent = new Intent(getApplicationContext(), RegisterClass.class);

         MainActivity.this.startActivity(intent);
    }

    public void signInAsUser(View v){
        // check if credentials match,
        // if yes, proceed to next screen and put extras sa intent
        // else, make a toast that shows incorrect credentials
    }
}
