package com.example.supun.databasetutorial;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public DatabaseHelper myDb;
    public EditText editName;
    public EditText editAddress;
    public EditText editAge;
    public EditText editPosition;
    public EditText editID;
    Button btn;
    Button viewButton;
    Button UpdateButton;
    Button DeleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initilization of textfields
        editName = (EditText) findViewById(R.id.editTextName);
        editAddress = (EditText) findViewById(R.id.editTextAddress);
        editAge = (EditText) findViewById(R.id.editTextAge);
        editPosition = (EditText) findViewById(R.id.editTextPosition);
        editID = (EditText) findViewById(R.id.editTextID);

        //buttons
        btn = (Button) findViewById(R.id.button2);
        viewButton = (Button) findViewById(R.id.buttonView);
        UpdateButton = (Button)findViewById(R.id.buttonUpdate);
        DeleteButton = (Button) findViewById(R.id.buttonDelete);

        //insatnce of the database helper class to access it's methods
        myDb = new DatabaseHelper(this);

//        //creation of the database when calling the constructor
//        Log.d("Test", "Database Created");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addData();

            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                viewAll();
            }
        });


        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateData();
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteData();
            }
        });
    }

    public void addData() {

        String age = editAge.getText().toString();
        int finalage = Integer.parseInt(age);

        String id = editID.getText().toString();
        int finalID = Integer.parseInt(id);

        boolean isInserted = myDb.insertData(finalID, editName.getText().toString(), editAddress.getText().toString(), finalage, editPosition.getText().toString());

        if (isInserted == true) {

            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(MainActivity.this, "Data Inserted not sucessfull", Toast.LENGTH_SHORT).show();

        }
    }

    public void viewAll() {

        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {

            showmessage("Error", "No Data Found");

            return;

        } else {

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                buffer.append("ID :" + res.getString(0) + "\n");
                buffer.append("Name :" + res.getString(1) + "\n");
                buffer.append("Address :" + res.getString(2) + "\n");
                buffer.append("Age :" + res.getInt(3) + "\n");
                buffer.append("Position :" + res.getString(4) + "\n\n");

            }

            showmessage("Data", buffer.toString());

        }
    }

    public void showmessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }

    public void UpdateData(){

        boolean isUpdate = myDb.updateData(editID.getText().toString(),editName.getText().toString(),editAddress.getText().toString(),editAge.getText().toString(),editPosition.getText().toString());

        if (isUpdate == true){

            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();

        }else {

            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_SHORT).show();

        }

    }


    public void DeleteData(){


        Integer deletedrow = myDb.deleteData(editID.getText().toString());

        if (deletedrow >  0) {

            Toast.makeText(MainActivity.this,"Rows have been deleted",Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(MainActivity.this,"Rows have not been deleted",Toast.LENGTH_SHORT).show();
        }

    }

}
