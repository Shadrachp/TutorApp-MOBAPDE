package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterClass extends AppCompatActivity{

    private EditText nameText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private boolean passNotEmpty, passMinLength, emailNotEmpty, emailFormat;

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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Toast toast = Toast.makeText(this, "Not signed in", Toast.LENGTH_SHORT);
            toast.show();
        }
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
            passNotEmpty = false;
            return;
        }else{
            passNotEmpty = true;
        }

        if (passwordText.getText().length() < 6) {
            passwordText.setError("Minimum length should 6 characters");
            return;
        }else{
            passMinLength = true;
        }

        if (emailText.getText().toString().isEmpty()) {
            emailText.setError("Email address is required!");
            emailNotEmpty = false;
            return;
        }else{
            emailNotEmpty = true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText.getText().toString()).matches()) {
            emailText.setError("Please enter a valid email address.");
            emailFormat = false;
            return;
        }else{
            emailFormat = true;
        }

        if(emailFormat && emailNotEmpty && passNotEmpty && passMinLength){
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast toast = Toast.makeText(getApplicationContext(), "Sign in sucessful", Toast.LENGTH_SHORT);
                                toast.show();

                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast toast = Toast.makeText(getApplicationContext(), "Not signed in", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            // ...
                        }
                    });

        }



    }
}
