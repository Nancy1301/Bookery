package com.example.bookery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.io.Reader;

public class ReaderMenu extends Activity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_menu);

        setTitle("Reader Menu");

        initUI();
        setListener();

    }

    private void setListener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);






    }

    private void initUI() {
        btn1 = (Button) findViewById(R.id.rbutton1);
        btn2 = (Button) findViewById(R.id.rbutton2);
        btn3 = (Button) findViewById(R.id.rbutton3);


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
            case R.id.rbutton1:
                Intent i1 = new Intent(ReaderMenu.this, search_menu.class);
                startActivity(i1);
                break;
            case R.id.rbutton2:
                Intent i2 = new Intent(ReaderMenu.this, Book_List.class);
                startActivity(i2);
                break;
            case R.id.rbutton3:
                Intent i3 = new Intent(ReaderMenu.this, Issue_List.class);
                startActivity(i3);
                break;

            default:
                break;
        }
    }


}
