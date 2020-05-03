package com.example.bookery.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

import java.util.ArrayList;

public class Isuue_Book extends Activity {
    DatabaseHelper db;
    Button Search1,Search2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_book);
        db = new DatabaseHelper(this);
        final AutoCompleteTextView editTextis = findViewById(R.id.I_ISBN);
        final AutoCompleteTextView editTextbi = findViewById(R.id.I_Book_name);
        ArrayList<Long> ISBNList = new ArrayList<Long>();
        ISBNList = db.getAllISBN();
        ArrayAdapter<Long> ISBNAdapter = new ArrayAdapter<Long>(getApplicationContext(), android.R.layout.simple_list_item_1, ISBNList);
        editTextis.setAdapter(ISBNAdapter);

        ArrayList<Integer> bid = new ArrayList<Integer>();
        bid = db.getAllbid();
        ArrayAdapter<Integer> bidAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, bid);
        editTextbi.setAdapter(bidAdapter);
        Search1 = (Button) findViewById(R.id.isearch1);
        Search2 = (Button) findViewById(R.id.isearch2);
        final ArrayList<Integer> Bid = bid;
        Search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = (editTextis.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter ISBN number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Long val = Long.parseLong(value);
                int b_id = db.getBookID(val);
                if(b_id>0) {

                    if (db.Icheckbook(b_id) > 0)
                    {
                        Toast.makeText(getApplicationContext(), "Book is already issued", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                        Intent i = new Intent(Isuue_Book.this, Isuue_Date_Book.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", b_id);

                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "The Book Does Not Exist", Toast.LENGTH_SHORT).show();

                }


            }
        });
        Search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = (editTextis.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter Book ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                int b_id = Integer.parseInt(value);
                if(b_id>0) {

                    if (db.Icheckbook(b_id) > 0)
                    {
                        Toast.makeText(getApplicationContext(), "Book is already issued", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent i = new Intent(Isuue_Book.this, Isuue_Date_Book.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", b_id);

                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "The Book Does Not Exist", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
