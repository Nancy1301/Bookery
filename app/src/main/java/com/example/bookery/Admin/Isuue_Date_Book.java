package com.example.bookery.Admin;

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

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Isuue_Date_Book extends Activity {
    DatabaseHelper db;
    Button Issue1,Done;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_book_date);

        final AutoCompleteTextView editTextis = findViewById(R.id.I_R_ID);
        db = new DatabaseHelper(this);
        ArrayList<Integer> ridlist = new ArrayList<Integer>();
        ridlist = db.getAllrid();
        ArrayAdapter<Integer> ridAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, ridlist);
        editTextis.setAdapter(ridAdapter);
        Issue1 = (Button) findViewById(R.id.issue1);
        Done = (Button) findViewById(R.id.idone);

        final TextView txt = findViewById(R.id.tvissue);
        final TextView txtr = findViewById(R.id.tvreturn);



        Issue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = (editTextis.getText().toString());
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter Reader id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.checkReaderLogin(Integer.parseInt(value))>0) {

                    Bundle bundle = getIntent().getExtras();
                    int val = bundle.getInt("key");
                    if(db.Icheckbook(val)>0) {

                        Toast.makeText(getApplicationContext(), "Book is already issued by user", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {

                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date date = new Date();
                        Calendar cal = Calendar.getInstance();
                        String issue = dateFormat.format(date);
                        String value1 = "Issue Date is :- " + issue;
                        txt.setText(value1);
                        cal.add(Calendar.DATE, 15);
                        String return1 = dateFormat.format(cal.getTime());
                        String result = "Return Date is :- " + return1;
                        txtr.setText(result);
                        long insid = db.addI_BOOK(Integer.parseInt(value), val, issue, return1);
                        if (insid > 0) {
                            Toast.makeText(getApplicationContext(), "Book issued successfully", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(getApplicationContext(), "could not issue book", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Reader Does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Isuue_Date_Book.this, AdminMenu.class);
                startActivity(i);


            }
        });
    }
}
