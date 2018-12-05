package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GuestClass extends AppCompatActivity {

    private RecyclerView recyclerArea;
    private RecyclerView.LayoutManager layoutManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list_code);

        recyclerArea = findViewById(R.id.recyclerArea);

        layoutManager = new LinearLayoutManager(this);

        recyclerArea.setLayoutManager(layoutManager);

    }
}
