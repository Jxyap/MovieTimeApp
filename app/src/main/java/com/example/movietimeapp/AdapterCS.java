package com.example.movietimeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCS extends RecyclerView.Adapter<AdapterCS.csMovieHolder> {

    Context context;
    ArrayList<ModelCS> csItems;

    public AdapterCS(Context context, ArrayList<ModelCS> csItems) {
        this.context = context;
        this.csItems = csItems;
    }

    @NonNull
    @Override
    public AdapterCS.csMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cs_movie_list,parent,false);
        return new AdapterCS.csMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCS.csMovieHolder holder, int position) {
        ModelCS CSmodel = csItems.get(position);
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
