package com.example.rachs.tutorapp_mobapde;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CodeSampleHolder extends RecyclerView.ViewHolder {

    private TextView titleView, ownerView, codeTypeView, idView;

    public CodeSampleHolder(View itemView) {
        super(itemView);

        idView = itemView.findViewById(R.id.idView);
        titleView = itemView.findViewById(R.id.titleView);
        ownerView = itemView.findViewById(R.id.ownerView);
        codeTypeView = itemView.findViewById(R.id.codeTypeView);

        // add on click listener here
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MESSAGE", "ITEM CLICKED");
            }
        });
    }

    public void setTitle(String title){
        titleView.setText(title);
    }

    public void setOwner(String owner){
        ownerView.setText(R.string.postedby + owner);
    }

    public void setType(String codeType){
        codeTypeView.setText(R.string.code_type + codeType);
    }

    public void setId(String id){
        idView.setText(id);
    }
}
