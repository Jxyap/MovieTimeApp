package com.example.movietimeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class imageAdapter extends SliderViewAdapter<imageAdapter.imageAdapterVH> {

    private Context context;
    private List<ImageSliderModel> SliderItems;

    public imageAdapter(Context context, List<ImageSliderModel> sliderItems) {
        this.context = context;
        this.SliderItems = sliderItems;
    }

    @Override
    public imageAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);
        return new imageAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(imageAdapterVH viewHolder, int position) {
        ImageSliderModel imageSliderModel = SliderItems.get(position);

        String url= imageSliderModel.getUrl();
        String mName = imageSliderModel.getmName();
        Picasso.get().load(url).placeholder(R.drawable.loading).into(viewHolder.imageView);
        viewHolder.textView.setText(mName);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mName +" is Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, NSmovie.class);
                intent.putExtra("movie", mName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return SliderItems.size();
    }

    class imageAdapterVH extends SliderViewAdapter.ViewHolder{

        View itemView;
        ImageView imageView;
        TextView textView;

        public imageAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSliderIV);
            textView = itemView.findViewById(R.id.imageSliderTV);
            this.itemView = itemView;
        }
    }
}