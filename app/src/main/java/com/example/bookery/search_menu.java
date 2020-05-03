package com.example.bookery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class search_menu extends Activity {
    DatabaseHelper db;
    Button Search1,Search2,Search3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_book);
        db = new DatabaseHelper(this);
        final AutoCompleteTextView editTextis = findViewById(R.id.S_ISBN);
        final AutoCompleteTextView editTextbn = findViewById(R.id.S_Book_name);
        final AutoCompleteTextView editTextbi = findViewById(R.id.S_b_ID);
        ArrayList<Long> ISBNList = new ArrayList<Long>();
        ISBNList = db.getAllISBN();
        ArrayAdapter<Long> ISBNAdapter = new ArrayAdapter<Long>(getApplicationContext(), android.R.layout.simple_list_item_1, ISBNList);
        editTextis.setAdapter(ISBNAdapter);
        ArrayList<String> Booklist = new ArrayList<String>();
        Booklist = db.getAllPublisher();
        ArrayAdapter<String> booklistAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Booklist);
        editTextbn.setAdapter(booklistAdapter);
        ArrayList<Integer> bid = new ArrayList<Integer>();
        bid = db.getAllbid();
        ArrayAdapter<Integer> bidAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, bid);
        editTextbi.setAdapter(bidAdapter);
        Search1 = (Button) findViewById(R.id.search1);
        Search2 = (Button) findViewById(R.id.search2);
        Search3 = (Button) findViewById(R.id.search3);

        Search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String value = (editTextis.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter ISBN number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Long val = Long.parseLong(value);
                if(db.checkbook(val)>0) {

                    Intent i = new Intent(search_menu.this, Selected_booklist.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("key",1);
                    bundle.putLong("value",val);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No Book exist with this ISBN number", Toast.LENGTH_SHORT).show();

                }


            }
        });
        Search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = (editTextbn.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter Book Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.checkbook(value)>0) {

                    Intent i = new Intent(search_menu.this, Selected_booklist.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("key",2);
                    bundle.putString("value",value);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No Book exist with this ISBN number" + value, Toast.LENGTH_SHORT).show();

                }


            }
        });
        Search3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = (editTextbi.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter Book Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                int val = Integer.parseInt(value);
                if(db.checkbook(val)>0) {

                    Intent i = new Intent(search_menu.this, Selected_booklist.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("key",3);
                    bundle.putInt("value",val);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No Book exist with this ISBN number", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
