package com.example.bookery.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookery.R;


public class AddBook extends Activity {

    Button btnnew,btnexis;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        btnnew = (Button) findViewById(R.id.Newbook);
        btnexis = (Button) findViewById(R.id.existingbook);


        btnnew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddBook.this, AddBookN.class);
                startActivity(i);
            }
        });

        btnexis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddBook.this, AddBookE.class);
                startActivity(i);
            }
        });

    }

}
