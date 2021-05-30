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

public class nsMovieAdapter extends RecyclerView.Adapter<nsMovieAdapter.nsMovieHolder> {

    Context context;
    ArrayList<nsMovieModel> movieItems;

    public nsMovieAdapter(NowShowing nowShowing, ArrayList<nsMovieModel> movieItems) {
        this.context = context;
        this.movieItems = movieItems;
    }

    @NonNull
    @Override
    public nsMovieAdapter.nsMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ns_movie_list,parent,false);
        return new nsMovieAdapter.nsMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull nsMovieAdapter.nsMovieHolder holder, int position) {
        nsMovieModel NSmovieModel = movieItems.get(position);
        holder.movieName.setText(NSmovieModel.getName());

        Picasso.get().load(NSmovieModel.getPhoto()).into(holder.movieImg);

        holder.fwd_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cinema_movie.class);
                intent.putExtra("cinema", NSmovieModel.getName());
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
        Button fwd_Btn;

        public nsMovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImg = itemView.findViewById(R.id.ns_movie_img);
            movieName = itemView.findViewById(R.id.nsMovieName);
            fwd_Btn = itemView.findViewById(R.id.forward_btn);

        }
    }
}
