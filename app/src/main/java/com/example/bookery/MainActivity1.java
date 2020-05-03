package com.example.bookery;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookery.Admin.Admin;
import com.example.bookery.Admin.AdminLogin;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {

    Button btnAdmin, btnReader;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);

        ArrayList<Admin> users = new ArrayList<>(mydb.getAllUsers());
        if (users.size() < 1) {
            Admin Chandan = new Admin("admin@101", "101", "Chandan");

            mydb.addUser(Chandan);
        }

        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnReader = (Button) findViewById(R.id.btnReader);


        btnAdmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity1.this, AdminLogin.class);
                startActivity(i);
            }
        });

        btnReader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity1.this, ReaderLogin.class);
                startActivity(i);
            }
        });

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
}


