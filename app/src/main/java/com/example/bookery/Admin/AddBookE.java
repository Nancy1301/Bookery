package com.example.bookery.Admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

import java.util.ArrayList;

public class AddBookE extends Activity {
    DatabaseHelper db;
    private Button btnID,btnDone;
    int value;
    long isbn;
    String value1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_booke);

        db = new DatabaseHelper(this);
        final AutoCompleteTextView editText = findViewById(R.id.tID);
        final EditText edittext = findViewById(R.id.tISBN);
        final TextView tv = findViewById(R.id.tvbid);

        ArrayList<Integer> Bookidkist = new ArrayList<Integer>();
        Bookidkist = db.getAllID();
        ArrayAdapter<Integer> bookidAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, Bookidkist);
        editText.setAdapter(bookidAdapter);

        btnID = (Button) findViewById(R.id.UID);

        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val1 = (editText.getText().toString());
                String val = (edittext.getText().toString());

                if (val1.matches("")||val.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter information", Toast.LENGTH_SHORT).show();
                    return;
                }
                value = Integer.parseInt(val1);
                isbn = Long.parseLong((val));
                if(db.checkBook(value)>0) {
                    {
                        if(db.checkBook(isbn)>0)
                        {
                            Toast.makeText(getApplicationContext(), "Book already exist!!", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            String B_name = db.getBookName(value);
                            int p_id = db.getpublisherid(value);
                            int s_id = db.getSectionID(value);
                            String b_author = db.getbookauthor(value);
                            long isd =db.addBookInfo(Integer.parseInt(AdminLogin.id),p_id,s_id,B_name,b_author,isbn);
                            if(isd>0)
                            {
                                Toast.makeText(getApplicationContext(), "Book is added", Toast.LENGTH_LONG).show();
                                tv.setText("BOOK ID IS :- " + db.getBookID(isbn));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Book cannot be added", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

            }
            else
                    {


                        Toast.makeText(getApplicationContext(), "Book does not exist kindly add it first", Toast.LENGTH_LONG).show();

                    }


            }
        });


    }

}

