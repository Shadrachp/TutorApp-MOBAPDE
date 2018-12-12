package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogo;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference userRef, codeRef;
    private FirebaseInterface fbInterface;
    private FirebaseAuth mAuth;
    private ImageView guestArrow;
    private TextView tvGuest;

    private ArrayList<User> users, userdb;
    private ArrayList<CodeSample> codeSamples, samplesDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        codeRef = database.getReference("codesample");

        fbInterface = new FirebaseInterface();
        codeSamples = new ArrayList<>();
        users = new ArrayList<>();
        userdb = new ArrayList<>();
        samplesDb = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        tvLogo = findViewById(R.id.tvLogo);
        tvLogo.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Billabong.ttf"));
        tvGuest = findViewById(R.id.tvGuest);
        guestArrow = findViewById(R.id.arrowGuest);

        guestArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GuestClass.class);
                startActivity(intent);
            }
        });

        tvGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GuestClass.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, UserClass.class));
        }

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbInterface.getUserdata(dataSnapshot);
                users = fbInterface.getUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void triggerRegister(View v){
        Intent intent = new Intent(getApplicationContext(), RegisterClass.class);
        startActivity(intent);
    }

    public void signInAsUser(View v) {
        EditText etUser = findViewById(R.id.etUser);
        EditText etPass = findViewById(R.id.tvPassword);

        final String email = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (email.isEmpty()){
            etUser.setError("Username is required!");
            return;
        }

        if (pass.isEmpty()){
            etPass.setError("Password is required!");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(getApplicationContext(), UserClass.class);
                    for (User user : users)
                        if (user.getEmail().equals(email))
                            intent.putExtra("USER_ID", user.getId());
                    // clear open activities on top of stack
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
