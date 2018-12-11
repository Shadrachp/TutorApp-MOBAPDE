package com.example.rachs.tutorapp_mobapde;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileCodeHolder extends RecyclerView.ViewHolder {

    private TextView profile_title, profile_type, postID;
    private Button btnEdit, btnDelete;

    public ProfileCodeHolder(View itemView) {
        super(itemView);

        profile_title = itemView.findViewById(R.id.profile_title);
        profile_type = itemView.findViewById(R.id.profile_type);
        postID = itemView.findViewById(R.id.postID);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EditCodeClass.class);
                intent.putExtra("ID", postID.getText().toString());
                context.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo: add implementation for deleting
            }
        });
    }

    public void setTitle(String title) {
        profile_title.setText(title);
    }

    public void setType(String type) {
        profile_type.setText("Code Type: " + type);
    }

    public void setID(String id){
        postID.setText(id);
    }
}
