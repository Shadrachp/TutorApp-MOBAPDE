package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterClass extends AppCompatActivity{

    private EditText nameText;
    private EditText usernameText;
    private EditText passwordText;
    private EditText emailText;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private boolean passNotEmpty, passMinLength, emailNotEmpty, emailFormat;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        nameText = findViewById(R.id.nameText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        progressBar = findViewById(R.id.progressBar);

        passNotEmpty = false;
        passMinLength = false;
        emailNotEmpty = false;
        emailFormat = false;

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
        if (nameText.getText().toString().trim().isEmpty()) {
            nameText.setError("Full name is required!");
            return;
        }

        if (usernameText.getText().toString().trim().isEmpty()) {
            usernameText.setError("Username is required!");
            return;
        }

        if (passwordText.getText().toString().trim().isEmpty()) {
            passwordText.setError("Password is required!");
            passNotEmpty = false;
            return;
        }else{
            passNotEmpty = true;
        }

        if (passwordText.getText().toString().trim().length() < 6) {
            passwordText.setError("Minimum length should 6 characters");
            return;
        }else{
            passMinLength = true;
        }

        if (emailText.getText().toString().trim().isEmpty()) {
            emailText.setError("Email address is required!");
            emailNotEmpty = false;
            return;
        }else{
            emailNotEmpty = true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText.getText().toString().trim()).matches()) {
            emailText.setError("Please enter a valid email address.");
            emailFormat = false;
            return;
        } else{
            emailFormat = true;
        }

        progressBar.setVisibility(View.VISIBLE);

        if(emailFormat && emailNotEmpty && passNotEmpty && passMinLength){
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // remove progress bar
                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MESSAGE", "createUserWithEmail:success");

                                // get current user to update username
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null){
                                    // update user profile
                                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(usernameText.getText().toString().trim())
                                            .build();

                                    // add onComplete listener
                                    user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Username applied!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                // finish activity
                                finish();
                                // prepare new intent and start intent
                                Intent intent = new Intent(getApplicationContext(), UserClass.class);
                                // clear open activities on top of stack
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Email is already registered!", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("ERROR", "createUserWithEmail:failure", task.getException());
                                    Toast toast = Toast.makeText(getApplicationContext(), "Register failed!", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        }
                    });
        }
    }
}
