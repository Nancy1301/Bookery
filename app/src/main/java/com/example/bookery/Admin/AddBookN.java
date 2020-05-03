package com.example.bookery.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

import java.util.ArrayList;

public class AddBookN extends Activity {
    private DatabaseHelper db;
    Button P_next;
    AutoCompleteTextView edt;
    public static String value;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_publisher);

        db = new DatabaseHelper(this);
        final AutoCompleteTextView editText = findViewById(R.id.P_N);

        ArrayList<String> publisherList = new ArrayList<String>();
        publisherList = db.getAllPublisher();
        ArrayAdapter<String> publisherAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, publisherList);
        editText.setAdapter(publisherAdapter);
        P_next = (Button) findViewById(R.id.P_next);
        P_next.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View v) {

                value = editText.getText().toString();
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter publisher name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db.getPublisherID(value)>0) {

                    Intent i = new Intent(AddBookN.this, AddBookNS.class);
                    startActivity(i);
                }
                else
                {
                    if(db.addpublisher(value)>0)
                    {
                        Intent i = new Intent(AddBookN.this, AddBookNS.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Publisher added successfully", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "enter publisher value", Toast.LENGTH_SHORT).show();

                    }

                }


            }
    });
    }
}

