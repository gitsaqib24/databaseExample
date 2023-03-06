package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_id,et_name,et_phone;
    Button   insertbtn , updatebtn , viewbtn , deletebtn ;
    TextView myTextView ;
    mydb_Halper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id       = findViewById(R.id.myid);
        et_name     = findViewById(R.id.myname);
        et_phone    = findViewById(R.id.myphone);
        insertbtn   = findViewById(R.id.insertbtn);
        updatebtn   = findViewById(R.id.updatebtn);
        viewbtn     = findViewById(R.id.viewbtn);
        deletebtn   = findViewById(R.id.deletebtn);
        myTextView  = findViewById(R.id.myTextView);
        db          = new mydb_Halper(this);

        insertbtn.setOnClickListener(view -> {
            insert();
            viewdata();
            et_id.setText("");
            et_name.setText("");
            et_phone.setText("");
        });
        updatebtn.setOnClickListener(view -> {
            update();
            viewdata();
            et_id.setText("");
            et_name.setText("");
            et_phone.setText("");
        });
        deletebtn.setOnClickListener(view -> {
            delete();
            viewdata();
            et_id.setText("");
            et_name.setText("");
            et_phone.setText("");
        });
        viewbtn.setOnClickListener(view -> {
            viewdata();
            et_id.setText("");
            et_name.setText("");
            et_phone.setText("");
        });

        viewdata();



    }

    private void insert()
    {
        String name = et_name.getText().toString();
        String phone= et_phone.getText().toString();
        Boolean result = db.insert_data(name,phone);
        if (result)
        {
            Toast.makeText(this, "insert Successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Insert Faild", Toast.LENGTH_SHORT).show();
        }
    }
    private void delete()
    {
        String id = et_id.getText().toString();
        int result = db.delete_data(id);
        Toast.makeText(this, "Row Effected", Toast.LENGTH_SHORT).show();

    }
    private void update()
    {
        String id   = et_id.getText().toString();
        String name = et_name.getText().toString();
        String phone= et_phone.getText().toString();
        Boolean result = db.update_data(id,name,phone);
        if(result)
        {
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
            et_id.setText("");
            et_name.setText("");
            et_phone.setText("");
        }else {
            Toast.makeText(this, "Update Faild", Toast.LENGTH_SHORT).show();
        }


    }
    private void viewdata()
    {
        Cursor cursor = db.getAllInfo();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor != null && cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                stringBuilder.append("ID        :   ").append(cursor.getString(0)).append("\n");
                stringBuilder.append("Name :   ").append(cursor.getString(1)).append("\n");
                stringBuilder.append("Phone:   ").append(cursor.getString(2)).append("\n");
                stringBuilder.append("--------------------------------------------").append("\n");
            }
            myTextView.setText(stringBuilder.toString());
        }else {
            Toast.makeText(this, "Data Retrieval Failed", Toast.LENGTH_SHORT).show();
        }
    }
}