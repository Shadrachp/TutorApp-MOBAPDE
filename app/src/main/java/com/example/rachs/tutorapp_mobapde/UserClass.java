package com.example.rachs.tutorapp_mobapde;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserClass extends AppCompatActivity {

    private RecyclerView u_recyclerArea;
    private RecyclerView.LayoutManager layoutManager;
    private EditText u_searchText;
    private ImageButton u_btnSearch;
    private FloatingActionButton fab_Add;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_code);

        u_searchText = findViewById(R.id.g_searchText);
        u_btnSearch = findViewById(R.id.g_btnSearch);
        u_recyclerArea = findViewById(R.id.g_recyclerArea);
        fab_Add = findViewById(R.id.fabAdd);

        fab_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layoutManager = new LinearLayoutManager(this);

        u_recyclerArea.setLayoutManager(layoutManager);
    }

}
