package com.example.rachs.tutorapp_mobapde;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogo;
    private StorageReference mStorageRef;
    private DatabaseReference dataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogo = findViewById(R.id.tvLogo);
        tvLogo.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Billabong.ttf"));

        mStorageRef = FirebaseStorage.getInstance().getReference();

    }




}
