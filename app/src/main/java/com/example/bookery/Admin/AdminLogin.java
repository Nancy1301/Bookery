package com.example.bookery.Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;


public class AdminLogin extends Activity {
    EditText edtID,edtPassword;
    Button btnALogin;
    DatabaseHelper db;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);


        db = new DatabaseHelper(this);

        edtID = (EditText) findViewById(R.id.Id);
        edtPassword = (EditText) findViewById(R.id.Password);
        btnALogin = (Button) findViewById(R.id.AdminLogin);

        btnALogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                id = edtID.getText().toString();
                String password = edtPassword.getText().toString();
                String pass = db.getPassword(id);

                if (password.equals(pass))
                {
                    Intent i = new Intent(AdminLogin.this, AdminMenu.class);
                    startActivity(i);

                    Toast.makeText(getApplicationContext(), "Correct Password", Toast.LENGTH_SHORT).show();
                    String A_ID = id;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong User ID and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    }

