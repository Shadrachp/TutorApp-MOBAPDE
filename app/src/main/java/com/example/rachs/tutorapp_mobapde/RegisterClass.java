package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterClass extends AppCompatActivity{

    private EditText nameText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        nameText = findViewById(R.id.nameText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View v){
        // trigger database after all checks
        if (nameText.getText().toString().isEmpty()) {
            nameText.setError("Full name is required!");
            return;
        }

        if (usernameText.getText().toString().isEmpty()) {
            usernameText.setError("Username is required!");
            return;
        }

        if (passwordText.getText().toString().isEmpty()) {
            passwordText.setError("Password is required!");
            return;
        }

        if (passwordText.getText().length() < 6) {
            passwordText.setError("Minimum length should 6 characters");
            return;
        }

        if (emailText.getText().toString().isEmpty()) {
            emailText.setError("Email address is required!");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText.getText().toString()).matches()) {
            emailText.setError("Please enter a valid email address.");
            return;
        }
    }
}
