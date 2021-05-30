package com.example.movietimeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class csAdapter extends RecyclerView.Adapter<csAdapter.csMovieHolder> {

    Context context;
    ArrayList<csModel> csItems;

    public csAdapter(Context context, ArrayList<csModel> csItems) {
        this.context = context;
        this.csItems = csItems;
    }

    @NonNull
    @Override
    public csAdapter.csMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cs_movie_list,parent,false);
        return new csAdapter.csMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull csAdapter.csMovieHolder holder, int position) {
        csModel CSmodel = csItems.get(position);
        holder.movieName.setText(CSmodel.getName());
        Picasso.get().load(CSmodel.getPhoto()).into(holder.movieImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CSmovie.class);
                intent.putExtra("movie",CSmodel.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return csItems.size();
    }

    public static class csMovieHolder extends RecyclerView.ViewHolder{

        ImageView movieImg;
        TextView movieName;

        public csMovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.cs_movie_img);
            movieName = itemView.findViewById(R.id.csMovieName);

        }
    }
}
