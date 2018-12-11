package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class GuestClass extends AppCompatActivity {

    private RecyclerView g_recyclerArea;
    private RecyclerView.LayoutManager layoutManager;
    private CodeSampleAdapter adapter;
    private EditText g_searchText;
    private ImageButton g_btnSearch;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_list_code);

        g_searchText = findViewById(R.id.g_searchText);
        g_btnSearch = findViewById(R.id.g_btnSearch);
        g_recyclerArea = findViewById(R.id.g_recyclerArea);

        layoutManager = new LinearLayoutManager(this);
        adapter = new CodeSampleAdapter();

        g_recyclerArea.setLayoutManager(layoutManager);
        g_recyclerArea.setAdapter(adapter);
    }

    public void search_guest(View v){

    }
}
