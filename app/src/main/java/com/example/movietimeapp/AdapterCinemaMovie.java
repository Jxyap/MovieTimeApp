package com.example.movietimeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCinemaMovie extends RecyclerView.Adapter<AdapterCinemaMovie.ViewHolder> {

    Context context;
    ArrayList<ModelCinemaMovie> movieList;
    String cName;
    String selectDate;

    public AdapterCinemaMovie(Context context, ArrayList<ModelCinemaMovie> movieList, String cName) {
        this.context = context;
        this.movieList = movieList;
        this.cName = cName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_movie_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelCinemaMovie movieModel = movieList.get(position);

        holder.movie_name.setText(movieModel.getMovie());
        Picasso.get().load(movieModel.getPoster()).placeholder(R.drawable.loading).into(holder.movie);
        holder.date.setText(movieModel.getDate1());
        holder.date2.setText(movieModel.getDate2());
        holder.date3.setText(movieModel.getDate3());
        holder.time.setText(movieModel.getTime1());
        holder.time2.setText(movieModel.getTime2());
        holder.time3.setText(movieModel.getTime3());

        if (movieModel.getDate2().equals("no date"))
            holder.date2.setVisibility(View.GONE);
        else
            holder.date2.setVisibility(View.VISIBLE);

        if (movieModel.getDate3().equals("no date"))
            holder.date3.setVisibility(View.GONE);
        else
            holder.date3.setVisibility(View.VISIBLE);

        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.date.setTextColor(Color.RED);
                holder.date2.setTextColor(Color.WHITE);
                holder.date3.setTextColor(Color.WHITE);
                holder.time.setText(movieModel.getTime1());
                holder.time.setVisibility(View.VISIBLE);
                holder.time2.setText(movieModel.getTime2());
                holder.time3.setText(movieModel.getTime3());

                if (movieModel.getTime1().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (movieModel.getTime2().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (movieModel.getTime3().equals("no time"))
                    holder.time3.setVisibility(View.GONE);
                else
                    holder.time3.setVisibility(View.VISIBLE);
            }
        });

        holder.date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.date2.setTextColor(Color.RED);
                holder.date.setTextColor(Color.WHITE);
                holder.date3.setTextColor(Color.WHITE);
                holder.time.setText(movieModel.getTime4());
                holder.time.setVisibility(View.VISIBLE);
                holder.time2.setText(movieModel.getTime5());
                holder.time3.setText(movieModel.getTime6());

                if (movieModel.getTime4().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (movieModel.getTime5().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (movieModel.getTime6().equals("no time"))
                    holder.time3.setVisibility(View.GONE);
                else
                    holder.time3.setVisibility(View.VISIBLE);
            }
        });

        holder.date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.date3.setTextColor(Color.RED);
                holder.date2.setTextColor(Color.WHITE);
                holder.date.setTextColor(Color.WHITE);
                holder.time.setText(movieModel.getTime7());
                holder.time2.setText(movieModel.getTime8());
                holder.time3.setText(movieModel.getTime9());

                if (movieModel.getTime7().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (movieModel.getTime8().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (movieModel.getTime9().equals("no time"))
                    holder.time3.setVisibility(View.GONE);
                else
                    holder.time3.setVisibility(View.VISIBLE);
            }
        });

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.date.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date.getText().toString();
                else if(holder.date2.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date2.getText().toString();
                else
                    selectDate = holder.date3.getText().toString();

                Intent intent2seat = new Intent(context, seatSelection.class);
                intent2seat.putExtra("cName", cName);
                intent2seat.putExtra("movie", movieModel.getMovie());
                intent2seat.putExtra("date", selectDate);
                intent2seat.putExtra("time", holder.time.getText().toString());
                intent2seat.putExtra("poster",movieModel.getPoster());
                intent2seat.putExtra("back","cinema");
                context.startActivity(intent2seat);
            }
        });

        holder.time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.date.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date.getText().toString();
                else if(holder.date2.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date2.getText().toString();
                else
                    selectDate = holder.date3.getText().toString();

                Intent intent2seat = new Intent(context, seatSelection.class);
                intent2seat.putExtra("cName", cName);
                intent2seat.putExtra("movie", movieModel.getMovie());
                intent2seat.putExtra("date", selectDate);
                intent2seat.putExtra("time", holder.time2.getText().toString());
                intent2seat.putExtra("poster",movieModel.getPoster());
                intent2seat.putExtra("back","cinema");
                context.startActivity(intent2seat);
            }
        });

        holder.time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.date.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date.getText().toString();
                else if(holder.date2.getTextColors().getDefaultColor() == Color.RED)
                    selectDate = holder.date2.getText().toString();
                else
                    selectDate = holder.date3.getText().toString();

                Intent intent2seat = new Intent(context, seatSelection.class);
                intent2seat.putExtra("cName", cName);
                intent2seat.putExtra("movie", movieModel.getMovie());
                intent2seat.putExtra("date", selectDate);
                intent2seat.putExtra("time", holder.time3.getText().toString());
                intent2seat.putExtra("poster",movieModel.getPoster());
                intent2seat.putExtra("back","cinema");
                context.startActivity(intent2seat);
            }
        });

        boolean isExpand = movieList.get(position).isExpand();
        holder.expandableLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movie_name;
        ImageView movie;
        Button date, time, date2, time2, date3, time3;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_name = itemView.findViewById(R.id.movieName);
            movie = itemView.findViewById(R.id.movie_poster);
            date = itemView.findViewById(R.id.movieDate);
            date2 = itemView.findViewById(R.id.movieDate2);
            date3 = itemView.findViewById(R.id.movieDate3);
            time = itemView.findViewById(R.id.movieTime);
            time2 = itemView.findViewById(R.id.movieTime2);
            time3 = itemView.findViewById(R.id.movieTime3);
            expandableLayout = itemView.findViewById(R.id.expandableList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelCinemaMovie ModelCinemaMovie = movieList.get(getAdapterPosition());
                    ModelCinemaMovie.setExpand(!ModelCinemaMovie.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
