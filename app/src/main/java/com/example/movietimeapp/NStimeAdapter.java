package com.example.movietimeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.PipedInputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class NStimeAdapter extends RecyclerView.Adapter<NStimeAdapter.ViewHolder> {

    Context context;
    ArrayList<nsTimeModel> nsMovie;
    String nsName;
    String selectDate;

    public NStimeAdapter(Context context, ArrayList<nsTimeModel> movieList, String nsName) {
        this.context = context;
        this.nsMovie = movieList;
        this.nsName = nsName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ns_movie_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        nsTimeModel nsModel = nsMovie.get(position);

        holder.cinemaName.setText(nsModel.getCinema());
        Picasso.get().load(nsModel.getPhoto()).into(holder.cinema);
        holder.date.setText(nsModel.getDate1());
        holder.date2.setText(nsModel.getDate2());
        holder.date3.setText(nsModel.getDate3());
        holder.time.setText(nsModel.getTime1());
        holder.time2.setText(nsModel.getTime2());
        holder.time3.setText(nsModel.getTime3());

        if (nsModel.getDate2().equals("no date"))
            holder.date2.setVisibility(View.GONE);
        else
            holder.date2.setVisibility(View.VISIBLE);

        if (nsModel.getDate3().equals("no date"))
            holder.date3.setVisibility(View.GONE);
        else
            holder.date3.setVisibility(View.VISIBLE);

        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.date.setTextColor(Color.RED);
                holder.date2.setTextColor(Color.WHITE);
                holder.date3.setTextColor(Color.WHITE);
                holder.time.setText(nsModel.getTime1());
                holder.time.setVisibility(View.VISIBLE);
                holder.time2.setText(nsModel.getTime2());
                holder.time3.setText(nsModel.getTime3());

                if (nsModel.getTime1().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (nsModel.getTime2().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (nsModel.getTime3().equals("no time"))
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
                holder.time.setText(nsModel.getTime4());
                holder.time.setVisibility(View.VISIBLE);
                holder.time2.setText(nsModel.getTime5());
                holder.time3.setText(nsModel.getTime6());

                if (nsModel.getTime4().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (nsModel.getTime5().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (nsModel.getTime6().equals("no time"))
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
                holder.time.setText(nsModel.getTime7());
                holder.time2.setText(nsModel.getTime8());
                holder.time3.setText(nsModel.getTime9());

                if (nsModel.getTime7().equals("no time"))
                    holder.time.setVisibility(View.GONE);
                else
                    holder.time.setVisibility(View.VISIBLE);

                if (nsModel.getTime8().equals("no time"))
                    holder.time2.setVisibility(View.GONE);
                else
                    holder.time2.setVisibility(View.VISIBLE);

                if (nsModel.getTime9().equals("no time"))
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

                Toast.makeText(context, "You select " + holder.cinemaName.getText().toString() +
                        " in " + nsName + " on " + selectDate + " " + holder.time.getText().toString(), Toast.LENGTH_LONG).show();
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

                Toast.makeText(context, "You select " + holder.cinemaName.getText().toString() +
                        " in " + nsName + " on " + selectDate + " " + holder.time2.getText().toString(), Toast.LENGTH_LONG).show();
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
            }
        });

        boolean isExpand = nsMovie.get(position).isExpand();
        holder.expandableLayout.setVisibility(isExpand ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return nsMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cinemaName;
        ImageView cinema;
        Button date, time, date2, time2, date3, time3;
        ConstraintLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaName = itemView.findViewById(R.id.nsCinemaName);
            cinema = itemView.findViewById(R.id.ns_cinema_img);
            date = itemView.findViewById(R.id.nsMovieDate);
            date2 = itemView.findViewById(R.id.nsMovieDate2);
            date3 = itemView.findViewById(R.id.nsMovieDate3);
            time = itemView.findViewById(R.id.nsMovieTime);
            time2 = itemView.findViewById(R.id.nsMovieTime2);
            time3 = itemView.findViewById(R.id.nsMovieTime3);
            expandableLayout = itemView.findViewById(R.id.nsExpandableList);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nsTimeModel nsTimeModel = nsMovie.get(getAdapterPosition());
                    nsTimeModel.setExpand(!nsTimeModel.isExpand());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}