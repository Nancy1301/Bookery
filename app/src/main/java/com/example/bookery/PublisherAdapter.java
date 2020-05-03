package com.example.bookery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PublisherAdapter extends ArrayAdapter {
    public PublisherAdapter(@NonNull Context context, ArrayList<Publisher> publishers) {
        super(context,0, publishers);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Publisher publisher = (Publisher) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_publisher, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvPName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tPid);
        // Populate the data into the template view using the data object
        tvTitle.setText(publisher.getP_name());
        tvId.setText(publisher.getP_Id());
        // Return the completed view to render on screen
        return convertView;
    }
}
