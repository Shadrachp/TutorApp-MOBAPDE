package com.example.rachs.tutorapp_mobapde;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileClass extends AppCompatActivity {

    private RecyclerView user_codelist;
    private RecyclerView.LayoutManager layoutManager;
    private ProfileCodeAdapter adapter;

    private FirebaseInterface fbInterface;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference codeRef;

    private ArrayList<CodeSample> codeSamples;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_codes);

        user_codelist = findViewById(R.id.user_codelist);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        codeRef = database.getReference("codesamples");
        fbInterface = new FirebaseInterface();

        layoutManager = new LinearLayoutManager(this);
        adapter = new ProfileCodeAdapter();

        codeSamples = new ArrayList<>();

        user_codelist.setLayoutManager(layoutManager);
        user_codelist.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!codeSamples.isEmpty())
                    codeSamples.clear();

                String id = getIntent().getStringExtra("USER_ID");
                fbInterface.getSelectedSamplesData(dataSnapshot, id);
                codeSamples = fbInterface.getCodes();

                for (CodeSample codeSample : codeSamples)
                    adapter.addItem(codeSample);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
