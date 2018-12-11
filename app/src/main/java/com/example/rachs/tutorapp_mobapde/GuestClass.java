package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class GuestClass extends AppCompatActivity {

    private RecyclerView g_recyclerArea;
    private RecyclerView.LayoutManager layoutManager;
    private CodeSampleAdapter adapter;
    private EditText g_searchText;
    private ImageButton g_btnSearch;

    private FirebaseInterface fbInterface;
    private FirebaseDatabase database;
    private DatabaseReference codeRef;

    private ArrayList<CodeSample> codeSamples;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list_code);

        g_searchText = findViewById(R.id.g_searchText);
        g_btnSearch = findViewById(R.id.g_btnSearch);
        g_recyclerArea = findViewById(R.id.g_recyclerArea);

        database = FirebaseDatabase.getInstance();
        fbInterface = new FirebaseInterface();
        codeRef = database.getReference("codesamples");

        layoutManager = new LinearLayoutManager(this);
        codeSamples = new ArrayList<>();
        adapter = new CodeSampleAdapter();

        g_recyclerArea.setLayoutManager(layoutManager);
        g_recyclerArea.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

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

    public void search_guest(View v){

    }
}
