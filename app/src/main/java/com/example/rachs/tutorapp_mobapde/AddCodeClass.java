package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCodeClass extends AppCompatActivity {

    private EditText titleText, typeText, contentText;

    private FirebaseDatabase database;
    private FirebaseInterface fbInterface;
    private DatabaseReference codeRef;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_code_sample);

        titleText = findViewById(R.id.titleText);
        typeText = findViewById(R.id.typeText);
        contentText = findViewById(R.id.contentText);

        database = FirebaseDatabase.getInstance();
        fbInterface = new FirebaseInterface();
        codeRef = database.getReference("codesamples");
    }

    public void addCode(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (titleText.getText().toString().isEmpty()) {
                titleText.setError("Title is required!");
                return;
            }

            if (typeText.getText().toString().isEmpty()) {
                typeText.setError("Code type is required!");
                return;
            }

            if (contentText.getText().toString().isEmpty()) {
                contentText.setError("Content is required!");
                return;
            }

            String content = contentText.getText().toString();
            String type = typeText.getText().toString();
            String title = titleText.getText().toString();
            
            fbInterface.addNewCodeSample(content, type, title, getIntent().getStringExtra("USER_ID"), codeRef, user.getDisplayName());
            finish();
            startActivity(new Intent(this, UserClass.class));
        } else {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
