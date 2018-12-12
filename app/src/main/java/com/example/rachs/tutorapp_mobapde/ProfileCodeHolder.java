package com.example.rachs.tutorapp_mobapde;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileCodeHolder extends RecyclerView.ViewHolder {

    private TextView profile_title, profile_type, postID;
    private Button btnEdit, btnDelete;
    private FirebaseInterface fbInterface;
    private FirebaseDatabase database;
    private DatabaseReference codeRef;

    public ProfileCodeHolder(View itemView) {
        super(itemView);

        profile_title = itemView.findViewById(R.id.profile_title);
        profile_type = itemView.findViewById(R.id.profile_type);
        postID = itemView.findViewById(R.id.postID);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);

        fbInterface = new FirebaseInterface();
        database = FirebaseDatabase.getInstance();
        codeRef = database.getReference("codesamples");

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EditCodeClass.class);
                String id = postID.getText().toString();
                String[] splitted = id.split(" ");
                String userID = splitted[0];
                String codeID = splitted[1];
                intent.putExtra("USER_ID", userID);
                intent.putExtra("CODE_SAMPLE_ID", codeID);
                context.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog(v);
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

    private void openDeleteDialog(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.delete_title);
        builder.setMessage(R.string.delete_body);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String id = postID.getText().toString();
                String[] splitted = id.split(" ");
                String userID = splitted[0];
                String codeID = splitted[1];

                fbInterface.deleteCode(userID, codeID, codeRef);
                ((Activity) v.getContext()).finish();
                v.getContext().startActivity(new Intent(v.getContext(), ProfileClass.class).putExtra("USER_ID", userID));
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
