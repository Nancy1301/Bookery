package com.example.bookery.Admin;
import com.example.bookery.DatabaseHelper;
import com.example.bookery.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddReader extends Activity {

    private EditText edtF_Name,edtM_Name,edtL_Name,edtR_ID;
    private Button btnAddUser;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reader);


            db = new DatabaseHelper(this);

        initUI();

        btnAddUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String L_name = edtL_Name.getText().toString();
                String M_name = edtM_Name.getText().toString();
                String F_name = edtF_Name.getText().toString();
                String r_id = edtR_ID.getText().toString();




                if (F_name.matches("")||L_name.matches("")||L_name.matches("")||r_id.matches("")) {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();
                    return;
                }
                int R_ID = Integer.parseInt(r_id);
                Long insID = db.AddReader(R_ID,AdminLogin.id,F_name,M_name,L_name);
                if (insID>0) {
                    Toast.makeText(getApplicationContext(), "User "+insID+" Added", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Could not be added kindly add information correctly.", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
    private void initUI() {
        edtF_Name = (EditText) findViewById(R.id.F_Name);
        edtM_Name = (EditText) findViewById(R.id.M_Name);
        edtL_Name = (EditText) findViewById(R.id.L_Name);
        edtR_ID = (EditText) findViewById(R.id.R_ID);
        btnAddUser = (Button) findViewById(R.id.AddReader);
    }
}
