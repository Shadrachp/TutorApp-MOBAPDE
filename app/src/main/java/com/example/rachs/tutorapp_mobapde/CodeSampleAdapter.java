package com.example.rachs.tutorapp_mobapde;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CodeSampleAdapter extends RecyclerView.Adapter<CodeSampleHolder> {

    private ArrayList<CodeSample> codeSamples;

    public CodeSampleAdapter(){
        codeSamples = new ArrayList<>();
    }

    @Override
    public CodeSampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.code_sample_row, parent, false);

        CodeSampleHolder holder = new CodeSampleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CodeSampleHolder holder, int position) {
        holder.setId(codeSamples.get(position).getId());
        holder.setTitle(codeSamples.get(position).getTitle());
        holder.setOwner(codeSamples.get(position).getUsername());
        holder.setType(codeSamples.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return codeSamples.size();
    }

    // TODO: implement add
    public void addItem(CodeSample codeSample){
        for (CodeSample sample : codeSamples) {
            if (sample.getId().equals(codeSample.getId()))
                return;
        }
        codeSamples.add(codeSample);
        notifyItemInserted(codeSamples.size() - 1);
    }
}
