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

public class AddBookNS extends Activity {

    private DatabaseHelper db;
    Button S_next;
    AutoCompleteTextView edt;
    public static String value;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_section);

        db = new DatabaseHelper(this);
        final AutoCompleteTextView editText = findViewById(R.id.S_N);

        ArrayList<String> sectionlist = new ArrayList<String>();
        sectionlist = db.getAllSection();
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, sectionlist);
        editText.setAdapter(sectionAdapter);
        S_next = (Button) findViewById(R.id.S_next);
        S_next.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View v) {

                value = editText.getText().toString();
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "enter Section name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db.getSectionID(value)>0) {

                    Intent i = new Intent(AddBookNS.this, AddBookI.class);
                    startActivity(i);
                }
                else
                {
                    if(db.addsection(value)>0)
                    {
                        Toast.makeText(getApplicationContext(), "Section added successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddBookNS.this, AddBookI.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Section cannot be added", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        });
    }
}
