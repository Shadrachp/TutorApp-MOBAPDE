package com.example.rachs.tutorapp_mobapde;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ProfileCodeAdapter extends RecyclerView.Adapter<ProfileCodeHolder> {

    private ArrayList<CodeSample> samples;

    public ProfileCodeAdapter(){
        samples = new ArrayList<>();
    }

    @Override
    public ProfileCodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.profile_row, parent, false);

        ProfileCodeHolder holder = new ProfileCodeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProfileCodeHolder holder, int position) {
        holder.setTitle(samples.get(position).getTitle());
        holder.setType(samples.get(position).getType());
        holder.setID(samples.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    // TODO: implement add
    public void addItem(CodeSample codeSample){
        for (CodeSample sample : samples) {
            if (sample.getId().equals(codeSample.getId())) {
                Log.d("RESULT: ", "Result: " + sample.getId().equals(codeSample.getId()));
                return;
            }
        }
        samples.add(codeSample);
        notifyItemInserted(samples.size() - 1);
    }
}
