package com.example.rachs.tutorapp_mobapde;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewCodeClass extends AppCompatActivity {

    private TextView codeTitle;
    private TextView ownView;
    private TextView typeView;
    private TextView contentView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_sample_view);

        codeTitle = findViewById(R.id.codeTitle);
        ownView = findViewById(R.id.ownView);
        typeView = findViewById(R.id.typeView);
        contentView = findViewById(R.id.contentView);
    }
}
