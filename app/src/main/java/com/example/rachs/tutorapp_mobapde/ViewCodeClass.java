package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewCodeClass extends AppCompatActivity {

    private TextView codeTitle;
    private TextView ownView;
    private TextView typeView;
    private TextView contentView;

    private FirebaseDatabase database;
    private FirebaseInterface fbInterface;
    private DatabaseReference codeRef;

    private String id;
    private ArrayList<CodeSample> codeSamples;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_sample_view);

        codeTitle = findViewById(R.id.codeTitle);
        ownView = findViewById(R.id.ownView);
        typeView = findViewById(R.id.typeView);
        contentView = findViewById(R.id.contentView);

        database = FirebaseDatabase.getInstance();
        fbInterface = new FirebaseInterface();
        codeRef = database.getReference("codesamples");

        Intent intent = getIntent();
        id = intent.getStringExtra("CODE_SAMPLE_ID");
        Log.d("ID", "" + intent.getStringExtra("CODE_SAMPLE_ID"));

        codeSamples = new ArrayList<>();
    }

    protected void onStart() {
        super.onStart();

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbInterface.getSelectedSamplesData(dataSnapshot, id);
                codeSamples = fbInterface.getCodes();

                for (CodeSample codeSample : codeSamples){
                    codeTitle.setText(codeSample.getTitle());
                    ownView.setText("Posted by: " + codeSample.getUsername());
                    typeView.setText("Code Type: " + codeSample.getType());
                    contentView.setText(codeSample.getCodeSample());
                    break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
