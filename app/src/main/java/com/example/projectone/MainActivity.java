package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_Register, btnCancel;
    EditText stdName, stdSurname, stdEmail, password,confirmPass  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCancel = findViewById(R.id.btn_cancel);
        stdName = findViewById(R.id.stdName);
        stdSurname = findViewById(R.id.stdSurname);
        stdEmail = findViewById(R.id.stdEmail);
        password = findViewById(R.id.Password);
        confirmPass = findViewById(R.id.ConfirmPassword);
        btn_Register = findViewById(R.id.btn_register);
       btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(MainActivity.this);
                db.addEmployee(stdName.getText().toString().trim(),stdSurname.getText().toString().trim(),stdEmail.getText().toString().trim(),Integer.parseInt(password.getText().toString().trim()));
            }
        });
    }
}