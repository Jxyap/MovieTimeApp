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

public class CustomAdapter extends ArrayAdapter {

    final Context context;
    private final String movie[];
    final Integer logo[];

    public CustomAdapter(@NonNull Context context, String uni[], Integer logo[]) {
        super(context, R.layout.activity_my_ticket, uni);

        this.context = context;
        this.movie = uni;
        this.logo = logo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_my_ticket,null,true);

        TextView title = rowView.findViewById(R.id.txt_movie);

        ImageView image = rowView.findViewById(R.id.img_movie);

        title.setText(movie[position]);
        image.setImageResource(logo[position]);

        return rowView;
    }
}