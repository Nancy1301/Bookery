package com.example.bookery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Return_book extends Activity {
    DatabaseHelper db;
    Button Search1,Search2,Search3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_book);
        db = new DatabaseHelper(this);
        final AutoCompleteTextView editTextreis = findViewById(R.id.R_ISBN);
        final AutoCompleteTextView editTextrebi = findViewById(R.id.R_Book_name);

        ArrayList<Long> ISBNList = new ArrayList<Long>();
        ISBNList = db.getAllISBN();
        ArrayAdapter<Long> ISBNAdapter = new ArrayAdapter<Long>(getApplicationContext(), android.R.layout.simple_list_item_1, ISBNList);
        editTextreis.setAdapter(ISBNAdapter);

        ArrayList<Integer> bid = new ArrayList<Integer>();
        bid = db.getAllbid();
        ArrayAdapter<Integer> bidAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, bid);
        editTextrebi.setAdapter(bidAdapter);
        Search1 = (Button) findViewById(R.id.ireturn1);
        Search2 = (Button) findViewById(R.id.ireturn2);
        Search3 = (Button) findViewById(R.id.R_done);
         final TextView txt = findViewById(R.id.tvret);
        Search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = (editTextreis.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter ISBN number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Long val = Long.parseLong(value);
                int b_id = db.getBookID(val);
                if(b_id>0) {

                    if (db.Icheckbook(b_id) > 0)
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date2 = new Date();
                        Calendar cal = Calendar.getInstance();
                        String cur = dateFormat.format(date2);
                       String Return_Date = db.getReturnDate(b_id);

                        try {
                            Date date = dateFormat.parse(cur);

                            Date date1 = dateFormat.parse(Return_Date);
                            long difference = date.getTime() - date1.getTime();
                            long differenceDates = difference / (24 * 60 * 60 * 1000);
                            String dayDifference = Long.toString(differenceDates);
                            if(differenceDates > 0 )
                            {
                                long fine = differenceDates*5;
                                txt.setText("Fine is :- " + fine);
                                if(db.deleteTitle(b_id))
                                {
                                    Toast.makeText(getApplicationContext(), "Book is returned successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                txt.setText("No Fine" );
                                if(db.deleteTitle(b_id))
                                {
                                    Toast.makeText(getApplicationContext(), "Book is returned successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }



                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "This Book is not issued", Toast.LENGTH_SHORT).show();


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

                String value = (editTextrebi.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter Book Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                int b_id = Integer.parseInt(value);
                int bid = db.getBookID(b_id);
                if(bid>0) {

                    if (db.Icheckbook(b_id) > 0)
                    {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date2 = new Date();
                        Calendar cal = Calendar.getInstance();
                        String cur = dateFormat.format(date2);
                        String Return_Date = db.getReturnDate(b_id);

                        try {
                            Date date = dateFormat.parse(cur);

                            Date date1 = dateFormat.parse(Return_Date);
                            long difference = Math.abs(date.getTime() - date1.getTime());
                            long differenceDates = difference / (24 * 60 * 60 * 1000);
                            String dayDifference = Long.toString(differenceDates);
                            if(differenceDates > 0 )
                            {
                                long fine = differenceDates*5;
                                txt.setText("Fine is :- " + fine);

                            }
                            else
                            {
                                txt.setText("No Fine" );

                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }



                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "This Book is not issued", Toast.LENGTH_SHORT).show();


                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "The Book Does Not Exist", Toast.LENGTH_SHORT).show();

                }


            }
        });
        Search3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(Return_book.this, AdminMenu.class);
                startActivity(i1);
            }
        });

    }
}
