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

public class AdapterCinema extends RecyclerView.Adapter<AdapterCinema.CinemaHolder> {

    Context context;
    ArrayList<ModelCinema> cinemaItems;

    public AdapterCinema(Context context, ArrayList<ModelCinema> cinemaItems) {
        this.context = context;
        this.cinemaItems = cinemaItems;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cinema_layout,parent,false);
        return new CinemaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, int position) {
        ModelCinema modelCinema = cinemaItems.get(position);
        holder.name.setText(modelCinema.getName());
        holder.info.setText(modelCinema.getInfo());

        Picasso.get().load(modelCinema.getPhoto()).placeholder(R.drawable.loading).into(holder.cinemaImg);

        holder.fwd_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cinema_movie.class);
                intent.putExtra("cinema", modelCinema.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cinemaItems.size();
    }

    public static class CinemaHolder extends RecyclerView.ViewHolder{

        ImageView cinemaImg;
        TextView name, info;
        Button fwd_Btn;

        public CinemaHolder(@NonNull View itemView) {
            super(itemView);
            cinemaImg = itemView.findViewById(R.id.cinema_img);
            name = itemView.findViewById(R.id.cinemaName);
            info = itemView.findViewById(R.id.hallInfo);
            fwd_Btn = itemView.findViewById(R.id.forward_btn);
        }
    }
}

