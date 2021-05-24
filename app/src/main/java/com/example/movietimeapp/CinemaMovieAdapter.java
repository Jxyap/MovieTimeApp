package com.example.movietimeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.PipedInputStream;
import java.util.ArrayList;

public class CinemaMovieAdapter extends RecyclerView.Adapter<CinemaMovieAdapter.ViewHolder> {

    Context context;
    ArrayList<cMovieModel> movieList;

    public CinemaMovieAdapter(Context context, ArrayList<cMovieModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cMovieModel cMovieModel = movieList.get(position);

        holder.movie_name.setText(cMovieModel.getMovie());
        Picasso.get().load(cMovieModel.getPoster()).into(holder.movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView movie_name;
        ImageView movie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie = itemView.findViewById(R.id.movie);
            movie_name = itemView.findViewById(R.id.movie_name);
        }
    }

}
