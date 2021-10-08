package com.example.projectone;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyActivity extends AppCompatActivity {

    Button btn_addCompany;
    EditText Comp_Name, comp_Address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);

        btn_addCompany = findViewById(R.id.btn_addCompany);
        Comp_Name = findViewById(R.id.Comp_Name);
        comp_Address =findViewById(R.id.comp_Address);
        btn_addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(CompanyActivity.this);
                db.addCompany(Comp_Name.getText().toString().trim(),comp_Address.getText().toString().trim());
            }
        });

    }
}
