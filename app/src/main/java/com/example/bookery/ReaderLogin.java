package com.example.bookery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class ReaderLogin extends Activity {
    public static int READER_ID;
    private EditText edtID;
    private Button btnLogin;
    private DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_login);
        edtID = (EditText) findViewById(R.id.RId);
        btnLogin = (Button) findViewById(R.id.ReaderLogin);
        db = new DatabaseHelper(this);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtID.getText().toString());
                int readerID = db.checkReaderLogin(id);

                if (readerID>0)
                {
                    READER_ID =  readerID;
                    Intent i = new Intent(ReaderLogin.this,ReaderMenu.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"READER DOES NOT EXIST",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public int getReaderid()
    {
        return READER_ID;
    }
}