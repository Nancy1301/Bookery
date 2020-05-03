package com.example.bookery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Book_List extends Activity implements View.OnClickListener {

    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        setTitle("Book List");

        initUI();
        setListener();

    }

    private void setListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);







    }

    private void initUI() {
        btn1 = (Button) findViewById(R.id.bbutton1);
        btn2 = (Button) findViewById(R.id.bbutton2);
        btn3 = (Button) findViewById(R.id.bbutton3);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.bbutton1:
                Intent i1 = new Intent(Book_List.this, Selected_Book_List.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key", 1);

                i1.putExtras(bundle);
                startActivity(i1);
                break;
            case R.id.bbutton2:
                Intent i2 = new Intent(Book_List.this, Selected_Book_List.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("key", 2);

                i2.putExtras(bundle1);
                startActivity(i2);
                break;
            case R.id.bbutton3:
                Intent i3 = new Intent(Book_List.this, Selected_Book_List.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("key", 3);

                i3.putExtras(bundle3);
                startActivity(i3);
                break;

            default:
                break;
        }
    }

}
