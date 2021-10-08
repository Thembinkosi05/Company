package com.example.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper { private final Context context;
    private static final String DATABASE_NAME = "ProjectOne.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMPLOYEE = "Employee";
    private static final String employee_ID = "emp_id";
    private static final String employee_NAME = "emp_name";
    private static final String employee_SURNAME = "emp_surname";
    private static final String employee_EMAIL = "emp_email";
    private static final String employee_Password = "emp_password";

    private static final String TAble_COMPANY = "Company";
    private static final String company_ID = "comp_id";
    private static final String company_Name = "comp_name";
    private static final String company_Address = "comp_address";
    private static final String CompID = "CompID";



    Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("PRAGMA foreign_keys=ON");
            String queryCOMPANY = "CREATE TABLE " + TAble_COMPANY +
                    " (" + company_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    company_Name + " TEXT, " +
                    company_Address + " TEXT);";
            db.execSQL(queryCOMPANY);

            String queryEmployee = "CREATE TABLE " + TABLE_EMPLOYEE +
                    " (" + employee_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    employee_NAME + " TEXT, " +
                    employee_SURNAME + " TEXT, " +
                    employee_EMAIL + " INTEGER," +
                    employee_Password + " INTEGER," +
                    company_ID + " INTEGER, "+
                    "FOREIGN KEY" + "(" + company_ID + ")" +
                    "REFERENCES Company" + " (" + company_ID + "));";
            db.execSQL(queryEmployee);
        }
        catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TAble_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("PRAGMA foreign_keys=ON");
        onCreate(db);
    }

    void addCompany(String CompName,String CompAddress){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  =new ContentValues();

        cv.put(company_Name,CompName);
        cv.put(company_Address,CompAddress);
        long result = db.insert(TAble_COMPANY,null,cv);
        if(result == -1)
            Toast.makeText(context,"Failed to add company",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"Company Added Successfully",Toast.LENGTH_LONG).show();
    }

    void addEmployee(String Name,String Surname,String Email,int Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  =new ContentValues();

        cv.put(employee_NAME,Name);
        cv.put(employee_SURNAME,Surname);
        cv.put(employee_EMAIL,Email);
        cv.put(employee_Password,Password);
        cv.put(company_ID,1);

        long result = db.insert(TABLE_EMPLOYEE,null,cv);
        if(result == -1)
            Toast.makeText(context,"Failed to add Employee",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"Employee Added Successfully",Toast.LENGTH_LONG).show();
    }
}
