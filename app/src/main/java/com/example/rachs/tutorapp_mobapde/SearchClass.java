package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchClass extends AppCompatActivity {

    private RecyclerView resultsList;
    private RecyclerView.LayoutManager layoutManager;
    private CodeSampleAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference codeRef;
    private FirebaseInterface fbInterface;

    private ArrayList<CodeSample> codeSamples;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        resultsList = findViewById(R.id.resultsList);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CodeSampleAdapter();

        database = FirebaseDatabase.getInstance();
        codeRef = database.getReference("codesamples");
        fbInterface = new FirebaseInterface();

        resultsList.setLayoutManager(layoutManager);
        resultsList.setAdapter(adapter);

        codeSamples = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        final String searched = intent.getStringExtra("SEARCH");

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbInterface.getSearchedSampleData(dataSnapshot, searched);
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
