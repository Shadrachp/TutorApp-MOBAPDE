package com.example.rachs.tutorapp_mobapde;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLogo = findViewById(R.id.tvLogo);

        tvLogo.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/Billabong.ttf"));

    }
}
