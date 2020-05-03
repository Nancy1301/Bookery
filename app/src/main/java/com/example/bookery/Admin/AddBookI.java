package com.example.bookery.Admin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

public class AddBookI extends Activity {
    EditText BName, Bauthor,ISBN;
    private DatabaseHelper db;
    int P_ID, S_ID, A_ID,qty;
    String B_author , B_Name ;
    long isbn ;
    Button B_next,b_done;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bookn);


        db = new DatabaseHelper(this);
        P_ID = db.getPublisherID(AddBookN.value);
        int a = db.getlastbookid();
        S_ID = db.getSectionID(AddBookNS.value);
        BName = (EditText) findViewById(R.id.B_name);
        Bauthor = (EditText) findViewById(R.id.B_author);
        ISBN = (EditText)findViewById(R.id.B_ISBN);
        B_next = (Button) findViewById(R.id.add);
        b_done = (Button)findViewById(R.id.done);

        A_ID = Integer.parseInt(AdminLogin.id);
        final TextView txt = findViewById(R.id.tvadd);


        B_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                B_Name = BName.getText().toString();
                B_author = Bauthor.getText().toString();
                String val = ISBN.getText().toString();




                if (B_Name.matches("")||B_author.matches("")||val.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter details", Toast.LENGTH_SHORT).show();
                    return;
                }
                isbn = Long.parseLong(val);
                int b_id = db.getBookID(isbn);
                if (b_id > 0) {


                        Toast.makeText(getApplicationContext(), "Book already exist " , Toast.LENGTH_LONG).show();
                        txt.setText("BOOK ID IS:- " + b_id);
                       // Intent i = new Intent(AddBookI.this, AdminMenu.class);
                        //startActivity(i);
                        //finish();
                    }

                 else
                     {
                    Long insID = db.addBookInfo(A_ID, P_ID, S_ID, B_Name, B_author, isbn);
                    Toast.makeText(getApplicationContext(), "Book added successfully", Toast.LENGTH_LONG).show();
                    String value = B_Name;
                    TextView tvName = (TextView) findViewById(R.id.tvadd);
                    int value1 = 0;
                    value1 = db.getBookID(isbn);
                    String val1 = "BOOK ID IS :-" + String.valueOf(value1);
                    tvName.setText(val1);
                }


            }
        });
        b_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(AddBookI.this, AdminMenu.class);
                startActivity(i);
                finish();

            }
        });
    }

}

