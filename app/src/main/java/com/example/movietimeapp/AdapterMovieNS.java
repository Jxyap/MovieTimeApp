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

public class AdapterMovieNS extends RecyclerView.Adapter<AdapterMovieNS.nsMovieHolder> {

    Context context;
    ArrayList<ModelMovieNS> movieItems;

    public AdapterMovieNS(Context context, ArrayList<ModelMovieNS> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public AdapterMovieNS.nsMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ns_movie_list,parent,false);
        return new AdapterMovieNS.nsMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovieNS.nsMovieHolder holder, int position) {
        ModelMovieNS NSmovieModelMovieNS = movieItems.get(position);
        holder.movieName.setText(NSmovieModelMovieNS.getName());
        Picasso.get().load(NSmovieModelMovieNS.getPhoto()).placeholder(R.drawable.loading).into(holder.movieImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NSmovie.class);
                intent.putExtra("movie", NSmovieModelMovieNS.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public static class nsMovieHolder extends RecyclerView.ViewHolder{

        ImageView movieImg;
        TextView movieName;

        public nsMovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.ns_movie_img);
            movieName = itemView.findViewById(R.id.nsMovieName);

        }
    }
}
