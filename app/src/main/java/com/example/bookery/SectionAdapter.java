package com.example.bookery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SectionAdapter extends ArrayAdapter {
    public SectionAdapter(Context context, ArrayList<Section> users) {
        super(context, 0, users);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Section section = (Section) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_section, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
        // Populate the data into the template view using the data object
        tvName.setText(section.getName());
        tvLocation.setText(section.getid());
        // Return the completed view to render on screen
        return convertView;
    }
}
