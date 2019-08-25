package com.example.supun.databasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "employee.db";

    //table name
    public static final String TABLE_NAME = "employee_table";

    //columns of the table
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "AGE";
    public static final String COL_5 = "POSITION";

    //constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }


    //database creation method
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, ADDRESS TEXT, AGE INTEGER,POSITION TEXT )");

    }

    //database upgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(int id, String name,String address, int age, String position ){

        //create the database and the table
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //putting values to the table
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,age);
        contentValues.put(COL_5,position);

       long result = db.insert(TABLE_NAME,null,contentValues);

       if (result == -1){

           return  false;
       }else {

           return true;
       }

    }


    //get all data function
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        //query
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);

        return res;

    }

    //update data

    public boolean updateData(String id, String name, String address, String age, String position){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,age);
        contentValues.put(COL_5,position);

        db.update(TABLE_NAME, contentValues,"ID = ?",new String[]{ id });

        return  true;
    }


    public Integer deleteData(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID = ?", new String[]{ id });

    }





}
