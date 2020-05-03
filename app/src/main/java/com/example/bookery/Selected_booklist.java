package com.example.bookery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Selected_booklist extends Activity {
    ListView listView;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_booklist);
        Bundle bundle = getIntent().getExtras();
        int val = bundle.getInt("key");
        listView = (ListView) findViewById(R.id.listBook);

        db = new DatabaseHelper(this);
        ArrayList<Book> bookList = new ArrayList<Book>();
        if(val==1)
        {
            long val1 = bundle.getLong("value");
            bookList = db.getbookbyISBN(val1);
            BookAdapter adapter = new BookAdapter(this, bookList);
            listView.setAdapter(adapter);
        }
        else if(val==2)
        {
            String val2 = bundle.getString("value");
            bookList = db.getbookbyb_name(val2);
            BookAdapter adapter = new BookAdapter(this, bookList);
            listView.setAdapter(adapter);
        }
        else
            {

                int val3 = bundle.getInt("value");
                bookList = db.getbookbyid(val3);
                BookAdapter adapter = new BookAdapter(this, bookList);
                listView.setAdapter(adapter);

        }

    }
}
