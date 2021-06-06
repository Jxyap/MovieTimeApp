package com.example.movietimeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    Context context;
    ArrayList<ModelTicket> ticket;

    public AdapterHistory(Context context, ArrayList<ModelTicket> ticket) {
        this.context = context;
        this.ticket = ticket;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_layout,parent,false);
        return new AdapterHistory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelTicket modelTicket = ticket.get(position);
        holder.movie.setText(modelTicket.getMovie());
        holder.date.setText(modelTicket.getDate());
        holder.time.setText(modelTicket.getTime());
        holder.cinemaName.setText(modelTicket.getCinemaName());
        holder.seat.setText("Seat(s): "+modelTicket.getSeat());
        Picasso.get().load(modelTicket.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return ticket.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView movie, date, time, cinemaName, seat;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie = itemView.findViewById(R.id.txt_movie);
            date = itemView.findViewById(R.id.txt_date);
            time = itemView.findViewById(R.id.txt_time);
            cinemaName = itemView.findViewById(R.id.txt_cinemaName);
            poster = itemView.findViewById(R.id.img_movie);
            seat = itemView.findViewById(R.id.txt_seat);
        }
    }
}

