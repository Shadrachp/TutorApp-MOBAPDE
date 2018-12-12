package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditCodeClass extends AppCompatActivity {

    private EditText titleText, typeText, contentText;

    private FirebaseDatabase database;
    private FirebaseInterface fbInterface;
    private DatabaseReference codeRef;

    private String id;
    private ArrayList<CodeSample> sample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_code_sample);

        TextView addView = findViewById(R.id.addView);
        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        contentText = findViewById(R.id.contentText);
        Button btnAdd = findViewById(R.id.btnAdd);

        addView.setText("Edit Code Sample");
        btnAdd.setText("Edit");

        Intent intent = getIntent();
        id = intent.getStringExtra("CODE_SAMPLE_ID");
        sample = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        fbInterface = new FirebaseInterface();
        codeRef = database.getReference("codesamples");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use fbInterface to get the CodeSample object through ID in the extras of intent then store it as attribute then use that for updating
                fbInterface.updateCodeTitle(sample.get(0), titleText.getText().toString(), codeRef);
                fbInterface.updateCodeType(sample.get(0), typeText.getText().toString(), codeRef);
                fbInterface.updateCodeSample(sample.get(0), contentText.getText().toString(), codeRef);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        codeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbInterface.getSelectedSamplesData(dataSnapshot, id);
                sample = fbInterface.getCodes();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
