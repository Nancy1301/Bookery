package com.example.bookery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter {
    public BookAdapter(@NonNull Context context, ArrayList<Book> books) {
        super(context,0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = (Book) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }
        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvNamebook);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvIdbook);
        TextView tvP_Name = (TextView) convertView.findViewById(R.id.tvP_Name);
        TextView tvA_Name = (TextView) convertView.findViewById(R.id.tvA_Name);


        // Populate the data into the template view using the data object
        tvTitle.setText("Book name:- " + book.getTitle());
        tvId.setText("Book ID:- "+Integer.toString(book.getBookId()));
        tvA_Name.setText("Author Name:- "+book.getAuthor_Name());
        tvP_Name.setText("Publisher name:- "+book.getP_Name());
        // Return the completed view to render on screen
        return convertView;
    }
}
