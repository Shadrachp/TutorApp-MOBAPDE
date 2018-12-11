package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserClass extends AppCompatActivity {

    private RecyclerView u_recyclerArea;
    private RecyclerView.LayoutManager layoutManager;
    private CodeSampleAdapter adapter;
    private EditText u_searchText;
    private ImageButton u_btnSearch;
    private FloatingActionButton fab_Add;

    private FirebaseInterface fbInterface;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference codeRef;

    private ArrayList<CodeSample> codeSamples;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_code);

        codeSamples = new ArrayList<>();
        u_searchText = findViewById(R.id.u_searchText);
        u_btnSearch = findViewById(R.id.u_btnSearch);
        u_recyclerArea = findViewById(R.id.u_recyclerArea);
        fab_Add = findViewById(R.id.fabAdd);
        fab_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddCodeClass.class));
            }
        });

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        fbInterface = new FirebaseInterface();
        codeRef = database.getReference("codesamples");

        // connect to FirebaseDatabase and load Code Samples
        layoutManager = new LinearLayoutManager(this);
        adapter = new CodeSampleAdapter();
        codeSamples = new ArrayList<>();

        u_recyclerArea.setLayoutManager(layoutManager);
        u_recyclerArea.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // current user is not logged in, return to MainActivity
        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        // load code samples
        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!codeSamples.isEmpty())
                    codeSamples.clear();

                fbInterface.getSamplesData(dataSnapshot);
                codeSamples = fbInterface.getCodes();
                for (CodeSample codeSample : codeSamples)
                    adapter.addItem(codeSample);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void search_user(View v) {
        String searchText = u_searchText.getText().toString().trim();
        for (CodeSample codeSample : codeSamples){
            if (codeSample.getTitle().contains(searchText)){

            }
        }
    }

}
